package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.ingredientes.TIngrediente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaModificarIngrediente extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	
	private JTextField nombreText;
	private JRadioButton botonCantidad;
	private JRadioButton botonPlatos;
	private JRadioButton botonAmbos;
	private JLabel lCantidad;
	private JLabel lPlatos;
	private JTextField tCantidad;
	private JTextField tPlatos;
	
	public VistaModificarIngrediente(Frame parent){
		initGUI(parent);
	}
	private void initGUI(Frame parent) {
		setTitle("Modificar ingrediente");
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		setContentPane(panelPrincipal);
		
		JPanel primerPanel = new JPanel(new GridLayout(2,2, 20, 20));
		primerPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel labelNombre = new JLabel("Nombre del ingrediente a modificar:");
		primerPanel.add(labelNombre);
		
		nombreText = new JTextField(10);
		//nombreText.setMaximumSize(new Dimension(10,10));
		primerPanel.add(nombreText);
		
		JLabel eleccion = new JLabel("Elija los aspectos a modificar");
		primerPanel.add(eleccion);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		botonCantidad = new JRadioButton("Cantidad");
		botonCantidad.addActionListener((e) -> {
			lCantidad.setVisible(true);
			tCantidad.setVisible(true);
			lPlatos.setVisible(false);
			tPlatos.setVisible(false);
		});
		botonPlatos = new JRadioButton("Platos");
		botonPlatos.addActionListener((e) -> {
			lCantidad.setVisible(false);
			tCantidad.setVisible(false);
			lPlatos.setVisible(true);
			tPlatos.setVisible(true);
		});
		botonAmbos = new JRadioButton("Ambos");
		botonAmbos.addActionListener((e) -> {
			lCantidad.setVisible(true);
			tCantidad.setVisible(true);
			lPlatos.setVisible(true);
			tPlatos.setVisible(true);
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(botonCantidad);
		bg.add(botonPlatos);
		bg.add(botonAmbos);
		panelBotones.add(botonCantidad);
		panelBotones.add(botonPlatos);
		panelBotones.add(botonAmbos);
		primerPanel.add(panelBotones);
		
		JPanel segundoPanel = new JPanel(new GridLayout(2,2, 20, 20));
		primerPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		lCantidad = new JLabel("Nueva cantidad:");
		segundoPanel.add(lCantidad);
		tCantidad = new JTextField();
		tCantidad.setPreferredSize(new Dimension(80,20));
		segundoPanel.add(tCantidad);
		
		lPlatos = new JLabel("Nuevos platos (formato {pizza, pasta}):");
		segundoPanel.add(lPlatos);
		tPlatos = new JTextField();
		tPlatos.setPreferredSize(new Dimension(80,20));
		segundoPanel.add(tPlatos);
		
		JPanel tercerPanel = new JPanel(new FlowLayout());
		JButton ok = new JButton("Ok");
		ok.addActionListener((e) -> ok());
		tercerPanel.add(ok);
		JButton volver = new JButton("Volver");
		volver.addActionListener((e) -> volver());
		tercerPanel.add(volver);
		
		panelPrincipal.add(primerPanel);
		panelPrincipal.add(segundoPanel);
		panelPrincipal.add(tercerPanel);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	private void ok() {
		String nombre;
		int cantidad = -1;
		String[]platos = null;
		try {
			nombre=nombreText.getText();
			if(nombre.equals("")) {
				throw new IllegalArgumentException("Alejandro matricula YA");
			}
			if(botonCantidad.isSelected() || botonAmbos.isSelected()) {
				cantidad= Integer.parseInt(tCantidad.getText());
				if(cantidad<0) {
					throw new NumberFormatException();
				}
			}
			if(botonPlatos.isSelected() || botonAmbos.isSelected()) {
				String aux="";
				
		        for (int j=1; j<tPlatos.getText().length()-1;j++) {
		            aux+=tPlatos.getText().charAt(j);
		        }
		        
		        platos= aux.split(",");
			}
			Controlador.getInstance().accion(Evento.MODIFICAR_INGREDIENTE, new TIngrediente(nombre,cantidad));
			setVisible(false);
		}
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(VistaModificarIngrediente.this, "ERROR: Error en la cantidad introducida","ERROR: Error en la cantidad introducida", JOptionPane.ERROR_MESSAGE);
		}
		catch (IllegalArgumentException a) {
			JOptionPane.showMessageDialog(VistaModificarIngrediente.this, "ERROR: Error en el nombre del ingrediente","ERROR: Error en el nombre del ingrediente", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(VistaModificarIngrediente.this, JOptionPane.ERROR_MESSAGE);
		}
	}
	private void volver() {
		setVisible(false);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case MODIFICAR_INGREDIENTE_VISTA:
			lCantidad.setVisible(false);
			tCantidad.setVisible(false);
			lPlatos.setVisible(false);
			tPlatos.setVisible(false);
			setVisible(true);
			break;
		case MODIFICAR_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente modificado correctamente", "Ingrediente modificado correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case MODIFICAR_INGREDIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO MODIFICAR EL INGREDIENTE", "ERROR: NO SE HA PODIDO MODIFICAR EL INGREDIENTE", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
