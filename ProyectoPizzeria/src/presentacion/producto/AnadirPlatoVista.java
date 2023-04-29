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

import org.json.JSONObject;

import negocio.ingredientes.TIngrediente;
import negocio.producto.TEntrante;
import negocio.producto.TPizza;
import negocio.producto.TPlato;
import negocio.producto.TPostre;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class AnadirPlatoVista extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;

	public AnadirPlatoVista(Frame parent) {
		super(parent, true);
		initGUI(parent);
	}
	
	private void initGUI(Frame parent) {
		setTitle("Anadir plato");
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
		JLabel ingredientsLabel = new JLabel("Ingredientes (ing1,..., ingN): ");
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
		
		//Tipo
		JPanel typePanel = new JPanel();
		JLabel typeLabel = new JLabel("Tipo: ");
		
		JPanel typeButtonPanel = new JPanel();
		typeButtonPanel.setLayout(new BoxLayout(typeButtonPanel, BoxLayout.Y_AXIS));
		JRadioButton entranteButton = new JRadioButton("Entrante");
		JRadioButton pizzaButton = new JRadioButton("Pizza");
		JRadioButton postreButton = new JRadioButton("Postre");
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(entranteButton);
		bGroup.add(pizzaButton);
		bGroup.add(postreButton);
		
		typeButtonPanel.add(entranteButton);
		typeButtonPanel.add(pizzaButton);
		typeButtonPanel.add(postreButton);
		
		typePanel.add(typeLabel);
		typePanel.add(typeButtonPanel);
		
		contenedor.add(typePanel);
		
		
		//Botones
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String nombre;
			double precio;
			String ingredientes;
			String descripcion;
			try {
				nombre = nameText.getText();
				if(nombre == null || nombre.equals(""))
					throw new IllegalArgumentException("El plato debe tener nombre");
				precio = Double.parseDouble(priceText.getText());
				if(precio <= 0) {
					throw new NumberFormatException();
				}
				ingredientes = ingredientsText.getText();
				String[] aux = ingredientes.trim().split(",");
				if(aux == null || aux[0].equals(""))
					throw new IllegalArgumentException("El plato debe tener ingredientes");
				descripcion = descriptionText.getText();
				JSONObject jo = new JSONObject();
				jo.put("ingredientes", ingredientes);
				if(descripcion == null || descripcion.equals(""))
					throw new IllegalArgumentException("El plato debe tener descripcion");
				if(entranteButton.isSelected()) {
					jo.put("plato", new TEntrante(nombre,precio,descripcion));
					Controlador.getInstance().accion(Evento.ALTA_PLATO,jo);
				}
				else if(pizzaButton.isSelected()) {
					jo.put("plato", new TPizza(nombre,precio,descripcion));
					Controlador.getInstance().accion(Evento.ALTA_PLATO, jo);
				}
				else if(postreButton.isSelected()) {
					jo.put("plato", new TPostre(nombre,precio,descripcion));
					Controlador.getInstance().accion(Evento.ALTA_PLATO, jo);
				}
				else {
					throw new IllegalArgumentException("Indique el tipo del plato");
				}				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(AnadirPlatoVista.this, "ERROR: El precio del plato debe ser un numero positivo", "ERROR: El precio del plato debe ser un numero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(AnadirPlatoVista.this, "ERROR: " + iae.getMessage(), "ERROR: "+iae.getMessage(), JOptionPane.ERROR_MESSAGE);
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
		case ALTA_PLATO_VISTA:
			setVisible(true);
			break;
		case ALTA_PLATO_OK:
			JOptionPane.showMessageDialog(this, "Plato anadido con nombre: " + datos.toString(), "Plato anadido con nombre: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
