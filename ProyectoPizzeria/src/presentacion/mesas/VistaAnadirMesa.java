package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class VistaAnadirMesa extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private JLabel localizacionLabel;
	private JRadioButton interiorButton;
	private JRadioButton exteriorButton;
	private JButton okButton;
	private JButton cancelButton;
	
	
	public VistaAnadirMesa(Frame parent) {
		super(parent, true);
		
		setTitle("Anadir Mesa");
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
				Controlador.getInstance().accion(Evento.ALTA_MESA, new TMesas(id, localizacion));
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaAnadirMesa.this, "ERROR: El numero de mesa debe ser un entero positivo", "ERROR: El numero de mesa debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(VistaAnadirMesa.this, "ERROR: Seleccione localizacion", "ERROR: Seleccione localizacion", JOptionPane.ERROR_MESSAGE);
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
		setLocationRelativeTo(parent);
		
		setResizable(false);
		
	}
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_MESA_VISTA:
			setVisible(true);
			break;
		case ALTA_MESA_OK:
			JOptionPane.showMessageDialog(this, "Mesa anadida con id: " + datos.toString(), "Mesa anadida con id: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_MESA_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}

}
