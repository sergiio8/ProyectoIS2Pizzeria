package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaBorrarMesa;

public class EliminarPlatoVista extends JDialog implements IGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField idPlato;
	private JButton okButton;
	private JButton cancelButton;
	
	public EliminarPlatoVista(Frame parent) {
		super(parent,true);
		setTitle("Eliminar Plato");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		idPanel.add(new JLabel("ID Plato: "));
		idPlato = new JTextField(10);
		idPanel.add(idPlato);
		
		contenedor.add(idPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String id = idPlato.getText();
			Controlador.getInstance().accion(Evento.BAJA_PLATO, id);
		});
		
		buttonPanel.add(okButton);
		
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> setVisible(false));
		
		buttonPanel.add(cancelButton);
		
		contenedor.add(buttonPanel);
		mainPanel.add(contenedor,BorderLayout.CENTER);
		
		pack();
		setResizable(false);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		
		
	}

}
