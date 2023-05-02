package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.ingredientes.TDatosAltaIngrediente;
import negocio.ingredientes.TIngrediente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaBuscarIngrediente extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;
	
	private Frame parent;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameText;
	private JPanel cantPanel;
	private JLabel cantLabel;
	private JPanel platosPanel;
	private JLabel platosLabel;
	private JPanel buttonsPanel;
	private JButton okButton;
	private JButton cancelButton;
	
	public VistaBuscarIngrediente(Frame parent) {
		super(parent, true);
		this.parent = parent;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		namePanel = new JPanel();
		nameLabel = new JLabel("Nombre del ingrediente: ");
		nameText = new JTextField(10);
		
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		contenedor.add(namePanel);
		
		contenedor.add(cantPanel = new JPanel());
		contenedor.add(platosPanel = new JPanel());
		
		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
				
		okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String nombre;
			try {
				nombre = nameText.getText();
				if(nombre == null || nombre.equals("")) {
					throw new IllegalArgumentException();
				}
				
				Controlador.getInstance().accion(Evento.BUSCAR_INGREDIENTE, nombre);
				
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(VistaBuscarIngrediente.this, "ERROR: Rellene el campo relativo al nombre", "ERROR: Rellene el campo relativo al nombre", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> setVisible(false));
		
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BUSCAR_INGREDIENTE_VISTA:
			nameText.setEnabled(true);
			nameText.setText("");
			if(cantLabel != null) {
				cantPanel.remove(cantLabel);
				platosPanel.remove(platosLabel);
			}
			buttonsPanel.removeAll();
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);
			revalidate();
			repaint();
			pack();
			setVisible(true);
			break;
		case BUSCAR_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente encontrado", "Ingrediente encontrado", JOptionPane.INFORMATION_MESSAGE);
			TDatosAltaIngrediente datosIngrediente = (TDatosAltaIngrediente)datos;
			TIngrediente ti = datosIngrediente.getIngrediente();
			nameText.setEnabled(false);
			
			cantLabel = new JLabel("Cantidad: " + ti.getCantidad());
			cantPanel.add(cantLabel);
			
			String platos ="{ ";
			List<String> l = datosIngrediente.getListaPlatos();
			for(String n : l) {
				platos+= n+", ";
			}
			if(platos.length()>2) platos = platos.substring(0, platos.length()-2);
			platos+=" }";
			platosLabel = new JLabel("Platos: "+ platos);
			platosPanel.add(platosLabel);
			
			buttonsPanel.removeAll();
			JButton confirmButton = new JButton("Confirmar");
			confirmButton.addActionListener((event) -> setVisible(false));
			buttonsPanel.add(confirmButton);
			revalidate();
			repaint();
			pack();
			break;
		case BUSCAR_INGREDIENTE_KO:
			TDatosAltaIngrediente d = (TDatosAltaIngrediente)datos;
			JOptionPane.showMessageDialog(this, "ERROR: Ingrediente: " + nameText.getText() + " no encontrado", "ERROR: Ingrediente no encontrado", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		default:
			break;
		}
	}
}
