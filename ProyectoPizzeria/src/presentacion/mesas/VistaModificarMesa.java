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

public class VistaModificarMesa extends JDialog implements IGUI{
	
private static final long serialVersionUID = 1L;
	
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private JLabel localizacionLabel;
	private JRadioButton interiorButton;
	private JRadioButton exteriorButton;
	private JButton okButton;
	private JButton cancelButton;
	
	
	public VistaModificarMesa(Frame parent) {
		super(parent, true);
		
		setTitle("Modificar Mesa");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		
		this.idMesaLabel = new JLabel("idMesa: ");
		idPanel.add(idMesaLabel);
		
		this.idMesaField = new JTextField(10);
		//this.idMesaField.setPreferredSize(new Dimension(130, 30));
		idPanel.add(idMesaField);
		
		
		contenedor.add(idPanel);
		
		JPanel locationTextPanel = new JPanel();
		
		this.localizacionLabel = new JLabel("Localizacion:");
		locationTextPanel.add(this.localizacionLabel);

		
		
		
		JPanel locationButtonsPanel = new JPanel();
		locationButtonsPanel.setLayout(new BoxLayout(locationButtonsPanel, BoxLayout.Y_AXIS));
		this.interiorButton = new JRadioButton("Interior");
		this.exteriorButton = new JRadioButton("Exterior");
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(interiorButton);
		bGroup.add(exteriorButton);
		
		locationButtonsPanel.add(this.interiorButton);
		locationButtonsPanel.add(this.exteriorButton);

		
		locationTextPanel.add(locationButtonsPanel);
		contenedor.add(locationTextPanel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			int id;
			String localizacion;
			try {
				id = Integer.parseInt(idMesaField.getText());
				if(id < 1) {
					throw new NumberFormatException();
				}
				if(interiorButton.isSelected()) {
					localizacion = "interior";
				}
				else if(exteriorButton.isSelected()) {
					localizacion = "exterior";
				}
				else {
					throw new IllegalArgumentException();
				}
				Controlador.getInstance().accion(Evento.MODIFICAR_MESA, new TMesas(id, localizacion));
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaModificarMesa.this, "ERROR: El numero de mesa debe ser un entero positivo", "ERROR: El numero de mesa debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(VistaModificarMesa.this, "ERROR: Seleccione localizacion", "ERROR: Seleccione localizacion", JOptionPane.ERROR_MESSAGE);
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
		
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case MODIFICAR_MESA_VISTA:
			setVisible(true);
			break;
		case MODIFICAR_MESA_RES:
			boolean res = Boolean.parseBoolean(datos.toString());
			if(res) {
				JOptionPane.showMessageDialog(this, "Mesa modificada con id: " + datos.toString(), "Mesa modificada con id: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				JOptionPane.showMessageDialog(this, "ERROR: No se ha podido modificar la mesa", "ERROR: No se ha podido modificar la mesa", JOptionPane.ERROR_MESSAGE);
			}
			
			
			setVisible(false);
			break;
		}
		
	}

}
