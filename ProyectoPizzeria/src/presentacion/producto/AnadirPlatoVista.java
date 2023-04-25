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

import negocio.ingredientes.SAIngrediente;
import negocio.ingredientes.TIngrediente;
import negocio.mesas.TMesas;
import negocio.producto.TEntrante;
import negocio.producto.TPizza;
import negocio.producto.TPostre;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaAnadirMesa;

public class AnadirPlatoVista extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;

	public AnadirPlatoVista(Frame parent) {
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Anadir plato");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		//ID
		JPanel idPanel = new JPanel();
		JLabel idLabel = new JLabel("ID_plato: ");
		JTextField idText = new JTextField();
		
		idPanel.add(idLabel);
		idPanel.add(idText);
		
		contenedor.add(idPanel);
		
		//Nombre
		JPanel namePanel = new JPanel(new FlowLayout());
		JLabel nameLabel = new JLabel("Nombre: ");
		JTextField nameText = new JTextField();
		
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		contenedor.add(namePanel);
		
		//Precio
		JPanel pricePanel = new JPanel(new FlowLayout());
		JLabel priceLabel = new JLabel("Precio: ");
		JTextField priceText = new JTextField();
		
		pricePanel.add(priceLabel);
		pricePanel.add(priceText);
		
		contenedor.add(pricePanel);
		
		//Ingredientes
		JPanel ingredientsPanel = new JPanel(new FlowLayout());
		JLabel ingredientsLabel = new JLabel("Ingredientes: ");
		JTextField ingredientsText = new JTextField();
		
		pricePanel.add(ingredientsLabel);
		ingredientsPanel.add(ingredientsText);
		
		contenedor.add(ingredientsPanel);

		//Descripcion
		JPanel descriptionPanel= new JPanel(new FlowLayout());
		JLabel descriptionLabel = new JLabel("Descripcion: ");
		JTextField descriptionText = new JTextField();
		
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
		
		typeButtonPanel.add(entranteButton);
		typeButtonPanel.add(pizzaButton);
		typeButtonPanel.add(postreButton);
		
		typePanel.add(typeLabel);
		typePanel.add(typeButtonPanel);
		
		contenedor.add(typePanel);
		
		
		//Botones
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String id;
			String nombre;
			double precio;
			ArrayList<TIngrediente> ingredientes = new ArrayList<TIngrediente>();
			String descripcion;
			try {
				id = idText.getText();
				nombre = nameText.getText();
				precio = Double.parseDouble(priceText.getText());
				String[] aux = ingredientsText.getText().trim().split(",");
				for(String s : aux)
					ingredientes.add(new TIngrediente(s));
				descripcion = descriptionText.getText();
				if(precio <= 0) {
					throw new NumberFormatException();
				}
				if(entranteButton.isSelected()) {
					Controlador.getInstance().accion(Evento.ALTA_PLATO, new TEntrante(id, nombre,precio,ingredientes,descripcion));
				}
				else if(pizzaButton.isSelected()) {
					Controlador.getInstance().accion(Evento.ALTA_PLATO, new TPizza(id, nombre,precio,ingredientes,descripcion));
				}
				else if(postreButton.isSelected()) {
					Controlador.getInstance().accion(Evento.ALTA_PLATO, new TPostre(id, nombre,precio,ingredientes,descripcion));
				}
				else {
					throw new IllegalArgumentException();
				}				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(AnadirPlatoVista.this, "ERROR: El precio del plato debe ser un numero positivo", "ERROR: El precio del plato debe ser un numero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(AnadirPlatoVista.this, "ERROR: Seleccione tipo de plato", "ERROR: Seleccione tipo de plato", JOptionPane.ERROR_MESSAGE);
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
		
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_PLATO_VISTA:
			setVisible(true);
			break;
		case ALTA_PLATO_OK:
			JOptionPane.showMessageDialog(this, "Plato anadid con id: " + datos.toString(), "Plato anadido con id: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}
