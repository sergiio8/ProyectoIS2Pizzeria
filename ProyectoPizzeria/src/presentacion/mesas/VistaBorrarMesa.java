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

public class VistaBorrarMesa extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private JButton okButton;
	private JButton cancelButton;
	
	
	public VistaBorrarMesa(Frame parent) {
		super(parent, true);
		
		setTitle("Borrar Mesa");
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
		
		

		JPanel buttonsPanel = new JPanel();
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
				Controlador.getInstance().accion(Evento.BAJA_MESA, id);
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaBorrarMesa.this, "ERROR: El numero de mesa debe ser un entero positivo", "ERROR: El numero de mesa debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
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
		case BAJA_MESA_VISTA:
			this.idMesaField.setText("");
			setVisible(true);
			break;
		case BAJA_MESA_RES:
			boolean res = Boolean.parseBoolean(datos.toString());
			if(res) {
				JOptionPane.showMessageDialog(this, "Mesa borrada correctamente", "Mesa borrada correctamente", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO BORRAR LA MESA", "ERROR: NO SE HA PODIDO BORRAR LA MESA", JOptionPane.ERROR_MESSAGE);
			}
			setVisible(false);
			break;
		case BAJA_MESA_RES_KO:
			
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			
			setVisible(false);
			break;
		}
	
	}
		
	

}
