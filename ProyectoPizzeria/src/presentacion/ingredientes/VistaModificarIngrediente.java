package presentacion.ingredientes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TModificacionIngrediente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaModificarIngrediente extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	
	private JTextField nombreText;
	private JRadioButton botonCantidad;
	private JRadioButton botonNombre;
	private JRadioButton botonAmbos;
	private JLabel lCantidad;
	private JLabel lNombre;
	private JTextField tCantidad;
	private JTextField tNombre;
	
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
		nombreText.setPreferredSize(new Dimension(1,1));
		primerPanel.add(nombreText);
		
		JLabel eleccion = new JLabel("Elija los aspectos a modificar:");
		primerPanel.add(eleccion);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		botonCantidad = new JRadioButton("Cantidad");
		botonCantidad.addActionListener((e) -> {
			lCantidad.setVisible(true);
			tCantidad.setVisible(true);
			lNombre.setVisible(false);
			tNombre.setVisible(false);
		});
		botonNombre = new JRadioButton("Nombre");
		botonNombre.addActionListener((e) -> {
			lCantidad.setVisible(false);
			tCantidad.setVisible(false);
			lNombre.setVisible(true);
			tNombre.setVisible(true);
		});
		botonAmbos = new JRadioButton("Ambos");
		botonAmbos.addActionListener((e) -> {
			lCantidad.setVisible(true);
			tCantidad.setVisible(true);
			lNombre.setVisible(true);
			tNombre.setVisible(true);
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(botonCantidad);
		bg.add(botonNombre);
		bg.add(botonAmbos);
		panelBotones.add(botonCantidad);
		panelBotones.add(botonNombre);
		panelBotones.add(botonAmbos);
		primerPanel.add(panelBotones);
		
		JPanel segundoPanel = new JPanel(new GridLayout(2,2, 20, 20));
		primerPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		lCantidad = new JLabel("Nueva cantidad:");
		segundoPanel.add(lCantidad);
		tCantidad = new JTextField();
		tCantidad.setPreferredSize(new Dimension(80,20));
		segundoPanel.add(tCantidad);
		
		lNombre = new JLabel("Nuevo nombre:");
		segundoPanel.add(lNombre);
		tNombre = new JTextField();
		tNombre.setPreferredSize(new Dimension(80,20));
		segundoPanel.add(tNombre);
		
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
		String nuevoNombre;
		try {
			nombre=nombreText.getText();
			if(nombre.equals("")) {
				throw new IllegalArgumentException("Error en el nombre del ingrediente");
			}
			if(botonCantidad.isSelected() || botonAmbos.isSelected()) {
				cantidad= Integer.parseInt(tCantidad.getText());
				if(cantidad<0) {
					throw new NumberFormatException();
				}
			}
			if(botonNombre.isSelected() || botonAmbos.isSelected()) {
		        nuevoNombre = tNombre.getText();
		        if(nuevoNombre.equals("")) {
					throw new IllegalArgumentException("Error en el nuevo nombre del ingrediente");
				}
			}
			else nuevoNombre = nombre;
			tNombre.setText("");
			tCantidad.setText("");
			Controlador.getInstance().accion(Evento.MODIFICAR_INGREDIENTE, new TModificacionIngrediente(nombre, new TIngrediente(nuevoNombre,cantidad)));
			setVisible(false);
		}
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(VistaModificarIngrediente.this, "ERROR: Error en la cantidad introducida","ERROR: Error en la cantidad introducida", JOptionPane.ERROR_MESSAGE);
		}
		catch (IllegalArgumentException a) {
			JOptionPane.showMessageDialog(VistaModificarIngrediente.this, "ERROR: "+a.getMessage(),"ERROR: "+a.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(VistaModificarIngrediente.this, e.getMessage(), e.getMessage(),JOptionPane.ERROR_MESSAGE);
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
			lNombre.setVisible(false);
			tNombre.setVisible(false);
			nombreText.setText("");
			setVisible(true);
			break;
		case MODIFICAR_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente modificado correctamente", "Ingrediente modificado correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case MODIFICAR_INGREDIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO MODIFICAR EL INGREDIENTE", "ERROR: NO SE HA PODIDO MODIFICAR EL INGREDIENTE", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
