package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import negocio.ingredientes.TIngrediente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaAnadirIngrediente extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	
	JTextField t1;
	JTextField t2;
	JTextField t3;
	public VistaAnadirIngrediente(){
		initGUI();
	}
	private void initGUI() {
		setTitle("Crear ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		JLabel l1= new JLabel("Nombre del ingrediente:");
		JLabel l2= new JLabel("Cantidad:");
		JLabel l3= new JLabel("Platos:");
		t1= new JTextField();//Ns si ajustar el tamano o no
		t2= new JTextField();
		t3= new JTextField();
		JButton ok= new JButton("Ok");
		ok.addActionListener((e)-> ok());
		JButton cancel= new JButton("Cancel");
		cancel.addActionListener((e)->cancel());
		JPanel p1= new JPanel(new FlowLayout());
		p1.add(l1);
		p1.add(new JSeparator(JSeparator.VERTICAL));
		p1.add(t1);
		JPanel p2= new JPanel(new FlowLayout());
		p2.add(l2);
		p2.add(new JSeparator(JSeparator.VERTICAL));
		p2.add(t2);
		JPanel p3= new JPanel(new FlowLayout());
		p3.add(l3);
		p3.add(new JSeparator(JSeparator.VERTICAL));
		p3.add(t3);
		JPanel p4 = new JPanel(new FlowLayout());
		p4.add(ok);
		p4.add(new JSeparator(JSeparator.VERTICAL));
		p4.add(cancel);
		JPanel paux= new JPanel(new FlowLayout());
		paux.add(p1,BorderLayout.NORTH);
		paux.add(p2,BorderLayout.SOUTH);
		//Falta anadirlos todo a un panel q sea scrollbar y este al main
		
		
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	private void ok() {
		String nombre;
		int cantidad;
		String[]platos;
		try {
			nombre=t1.getText();
			if(nombre.equals("")||nombre.equals(null)) {
				throw new IllegalArgumentException();
			}
			cantidad= Integer.parseInt(t2.getText());
			if(cantidad<0) {
				throw new NumberFormatException();
			}
			String aux="";
	        for (int j=1; j<t3.getText().length()-1;j++) {
	            aux+=t3.getText().charAt(j);
	        }
	        platos= aux.split(",");
			Controlador.getInstance().accion(Evento.ALTA_INGREDIENTE, new TIngrediente(nombre,cantidad,platos));
		}
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(VistaAnadirIngrediente.this, "ERROR: Error en la cantidad introducida","INTRODUZCA UNA NUEVA", JOptionPane.ERROR_MESSAGE);
		}
		catch (IllegalArgumentException a) {
			JOptionPane.showMessageDialog(VistaAnadirIngrediente.this, "ERROR: Error en el nombre del ingrediente","INTRODUZCA UNO NUEVO", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(VistaAnadirIngrediente.this, JOptionPane.ERROR_MESSAGE);
		}
		this.setVisible(false);
	}
	private void cancel() {
		this.setVisible(false);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_INGREDIENTE_VISTA:
			setVisible(true);
			break;
		case ALTA_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente añadido correctamente", "Ingrediente añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_INGREDIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO AÑADIR EL INGREDIENTE", "ERROR: NO SE HA PODIDO AÑADIR EL INGREDIENTE", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
