package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;

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

import negocio.producto.TEntrante;
import negocio.producto.TPizza;
import negocio.producto.TPostre;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class ModificarPlatoVista extends JDialog implements IGUI {
	private static final long serialVersionUID = 1L;

	public ModificarPlatoVista(Frame parent) {
		super(parent, true);
		initGUI(parent);
	}
	
	private void initGUI(Frame parent) {
		setTitle("Modificar plato");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		//Nombre
		JPanel namePanel = new JPanel(new FlowLayout());
		JLabel nameLabel = new JLabel("Nombre: ");
		JTextField nameText = new JTextField(10);
		
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		contenedor.add(namePanel);
		
		//Precio
		JPanel pricePanel = new JPanel(new FlowLayout());
		JLabel priceLabel = new JLabel("Precio: ");
		JTextField priceText = new JTextField(10);
		
		pricePanel.add(priceLabel);
		pricePanel.add(priceText);
		
		contenedor.add(pricePanel);
		
		//Ingredientes
		JPanel ingredientsPanel = new JPanel(new FlowLayout());
		JLabel ingredientsLabel = new JLabel("Ingredientes: ");
		JTextField ingredientsText = new JTextField(25);
		
		ingredientsPanel.add(ingredientsLabel);
		ingredientsPanel.add(ingredientsText);
		
		contenedor.add(ingredientsPanel);

		//Descripcion
		JPanel descriptionPanel= new JPanel(new FlowLayout());
		JLabel descriptionLabel = new JLabel("Descripcion: ");
		JTextField descriptionText = new JTextField(30);
		
		descriptionPanel.add(descriptionLabel);
		descriptionPanel.add(descriptionText);
		
		contenedor.add(descriptionPanel);		
		
		//Botones
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String nombre;
			double precio;
			ArrayList<String> ingredientes = new ArrayList<String>();
			String descripcion;
			try {
				nombre = nameText.getText();
				if(!priceText.getText().equals(""))
					precio = Double.parseDouble(priceText.getText());
				else precio = 0;
				String[] aux = ingredientsText.getText().trim().split(",");
				for(String s : aux)
					ingredientes.add(s.trim());
				descripcion = descriptionText.getText();
				
				Controlador.getInstance().accion(Evento.MODIFICAR_PLATO, new TEntrante(nombre,precio,ingredientes,descripcion));			
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(ModificarPlatoVista.this, "ERROR: El precio del plato debe ser un numero positivo", "ERROR: El precio del plato debe ser un numero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(ModificarPlatoVista.this, "ERROR: Seleccione tipo de plato", "ERROR: Seleccione tipo de plato", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> setVisible(false));
		
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case MODIFICAR_PLATO_VISTA:
			setVisible(true);
			break;
		case MODIFICAR_PLATO_OK:
			JOptionPane.showMessageDialog(this, "Plato modificado", "Plato con id: modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case MODIFICAR_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
