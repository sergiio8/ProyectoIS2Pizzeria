package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.mesas.TMesas;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaBuscarMesa extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private Box contenedor;
	private JLabel localizacionLabel;
	private JPanel locationPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonsPanel;
	
	
	public VistaBuscarMesa(Frame parent) {
		super(parent, true);
		
		setTitle("Buscar Mesa");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		
		this.idMesaLabel = new JLabel("idMesa: ");
		idPanel.add(idMesaLabel);
		
		this.idMesaField = new JTextField(10);
		//this.idMesaField.setPreferredSize(new Dimension(130, 30));
		idPanel.add(idMesaField);
		
		
		contenedor.add(idPanel);
		
		this.locationPanel = new JPanel();
		contenedor.add(locationPanel);
		

		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			int id;
			try {
				id = Integer.parseInt(idMesaField.getText());
				if(id < 1) {
					throw new NumberFormatException();
				}
				
				Controlador.getInstance().accion(Evento.BUSCAR_MESA, id);
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaBuscarMesa.this, "ERROR: El numero de mesa debe ser un entero positivo", "ERROR: El numero de mesa debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		});
		
		
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener((e)->{
			this.setVisible(false);
		});
		
		
		
		buttonsPanel.add(this.okButton);
		buttonsPanel.add(this.cancelButton);
		
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		//setPreferredSize(new Dimension(320, 500));
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BUSCAR_MESA_VISTA:
			buttonsPanel.removeAll();
			buttonsPanel.add(this.okButton);
			buttonsPanel.add(this.cancelButton);
			this.idMesaField.setEnabled(true);
			if(localizacionLabel != null) {
				this.locationPanel.remove(localizacionLabel);
			}
			revalidate();
			repaint();
			pack();
			setVisible(true);
			break;
		case BUSCAR_MESA_RES:
			if(datos == null) {
				JOptionPane.showMessageDialog(this, "ERROR: Mesa no encontrada", "ERROR: Mesa no encontrada", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "Mesa encontrada", "Mesa encontrada", JOptionPane.INFORMATION_MESSAGE);
				TMesas tm = (TMesas) datos;
				this.localizacionLabel = new JLabel("Localizacion: " + tm.getLocalizacion());
				this.locationPanel.add(localizacionLabel);
				this.idMesaField.setEnabled(false);
				this.buttonsPanel.removeAll();
				JButton confirmButton = new JButton("Confirmar");
				confirmButton.addActionListener((event)->{
					setVisible(false);
				});
				this.buttonsPanel.add(confirmButton);
				revalidate();
				repaint();
				pack();
			}
			
			
			break;
		}
	}
}
