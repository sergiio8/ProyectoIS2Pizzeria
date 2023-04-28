package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JToolBar.Separator;

import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TPlatoIngrediente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaAnadirIngrediente extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	
	JTextField t1;
	JTextField t2;
	JTextField t3;
	public VistaAnadirIngrediente(Frame parent){
		initGUI(parent);
	}
	private void initGUI(Frame parent) {
		setTitle("Crear ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		setContentPane(mainPanel);
		
		JPanel panelCrear = new JPanel(new GridLayout(3,2));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(10,40,20,20));
		
		JLabel l1= new JLabel("Nombre del ingrediente:");
		panelCrear.add(l1);
		
		t1= new JTextField();
		t1.setPreferredSize(new Dimension(150,35));
		panelCrear.add(t1);
		
		
		JLabel l2= new JLabel("Cantidad:");
		panelCrear.add(l2);
		
		t2= new JTextField();
		t2.setPreferredSize(new Dimension(150,35));
		panelCrear.add(t2);
		
		
		JLabel l3= new JLabel("Platos: eg:{pizza,pasta}");
		panelCrear.add(l3);
		
		t3= new JTextField();
		t3.setPreferredSize(new Dimension(150,35));
		panelCrear.add(t3);
		
		mainPanel.add(panelCrear, BorderLayout.CENTER);
		
		JPanel panelOk = new JPanel();
		panelOk.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton ok= new JButton("Ok");
		ok.addActionListener((e)-> ok());
		panelOk.add(ok);
		
		JButton volver= new JButton("Volver");
		volver.addActionListener((e)->volver());
		panelOk.add(volver);
		
		mainPanel.add(panelOk, BorderLayout.SOUTH);
		
		
		//Falta anadirlos todo a un panel q sea scrollbar y este al main

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	private void ok() {
		String nombre;
		int cantidad;
		String[]platos;
		try {
			nombre=t1.getText();
			if(nombre.equals("")) {
				throw new IllegalArgumentException("Alejandro matricula YA");
			}
			cantidad= Integer.parseInt(t2.getText());
			if(cantidad<0) {
				throw new NumberFormatException();
			}
			/*String aux="";
			
	        for (int j=1; j<t3.getText().length()-1;j++) {
	            aux+=t3.getText().charAt(j);
	        }
	        
	        platos= aux.split(",");*/
			List<String> l = new ArrayList<String>();
			for (int j=1; j<t3.getText().length()-1;j++) {
				String aux="";
				while(j<t3.getText().length()-1 && t3.getText().charAt(j) != ',') {
					aux+=t3.getText().charAt(j);
					j++;
				}
				l.add(aux);
	        }
	        
			Controlador.getInstance().accion(Evento.ALTA_INGREDIENTE, new TIngrediente(nombre,cantidad));
			for(String s: l) {
				Controlador.getInstance().accion(Evento.ALTA_PLATOINGREDIENTE, new TPlatoIngrediente(s,nombre));
			}
			
			setVisible(false);
		}
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(VistaAnadirIngrediente.this, "ERROR: Error en la cantidad introducida","ERROR: Error en la cantidad introducida", JOptionPane.ERROR_MESSAGE);
		}
		catch (IllegalArgumentException a) {
			JOptionPane.showMessageDialog(VistaAnadirIngrediente.this, "ERROR: Error en el nombre del ingrediente","ERROR: Error en el nombre del ingrediente", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(VistaAnadirIngrediente.this, JOptionPane.ERROR_MESSAGE);
		}
	}
	private void volver() {
		this.setVisible(false);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_INGREDIENTE_VISTA:
			t1.setText("");
			t2.setText("");
			t3.setText("");
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
