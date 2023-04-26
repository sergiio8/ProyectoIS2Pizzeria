package presentacion.ingredientes;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaEliminar extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	public VistaEliminar(){
		initGUI();
	}
	private void initGUI() {
		setTitle("Eliminar ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		/*DefaultComboBoxModel comboModel = new DefaultComboBoxModel<>();
		Collection<TIngrediente> c = 
		for() {
		            _lawsModel.addElement();
		        }
		JComboBox _comboLaws= new JComboBox<String> (comboModel);*/
	
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel panel1 = new JPanel();
		JLabel ingLabel = new JLabel("Nombre del ingrediente a eliminar: ");
		panel1.add(ingLabel);
		
		JTextField nombreIngTextField = new JTextField();
		//this.idMesaField.setPreferredSize(new Dimension(130, 30));
		panel1.add(nombreIngTextField);
		contenedor.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panel2);
		
		JButton ok = new JButton("OK");
		ok.addActionListener((e) ->{
			String nombre;
			try {
				nombre = nombreIngTextField.getText();
				if(nombre == null) {
					throw new Exception();
				}
				Controlador.getInstance().accion(Evento.BAJA_INGREDIENTE, nombre);
				
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(VistaEliminar.this, "ERROR: Debe introducir un nombre de ingrediente", "ERROR: Debe introducir un nombre de ingrediente", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});

		JButton cancel = new JButton("Cancelar");
		cancel.addActionListener((e)->{ setVisible(false); });
		
		panel2.add(ok);
		panel2.add(cancel);
		contenedor.add(panel2);
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BAJA_INGREDIENTE_VISTA:
			setVisible(true);
			break;
		case BAJA_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente eliminado correctamente", "Ingrediente eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case BAJA_INGREDIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO ELIMINAR EL INGREDIENTE", "ERROR: NO SE HA PODIDO ELIMINAR EL INGREDIENTE", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
