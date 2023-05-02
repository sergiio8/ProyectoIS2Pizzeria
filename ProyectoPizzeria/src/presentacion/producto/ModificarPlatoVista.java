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

import negocio.producto.TDatosPlato;
import negocio.producto.TEntrante;
import negocio.producto.TPizza;
import negocio.producto.TPostre;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class ModificarPlatoVista extends JDialog implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private Frame parent;

	public ModificarPlatoVista(Frame parent) {
		super(parent, true);
		this.parent = parent;
		initGUI();
	}
	
	private void initGUI() {
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
		
		//Botones
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String nombre;
			double precio;
			ArrayList<String> ingredientes;
			String descripcion;
			try {
				nombre = nameText.getText();
				if(!priceText.getText().equals(""))
					precio = Double.parseDouble(priceText.getText());
				else precio = 0;
				if(precio < 0)
					throw new NumberFormatException();
				ingredientes = new ArrayList<String>();
				String ing = ingredientsText.getText().trim();
				if(!ing.equals("")) {
					String[] aux = ing.split(",");
					for(String s : aux)
						ingredientes.add(s.trim());
				}
				descripcion = descriptionText.getText().trim();
				TDatosPlato datos = new TDatosPlato(new TEntrante(nombre,precio,descripcion), ingredientes);
				Controlador.getInstance().accion(Evento.MODIFICAR_PLATO, datos);			
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(ModificarPlatoVista.this, "ERROR: El precio del plato debe ser un numero positivo", "ERROR: El precio del plato debe ser un numero positivo", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(this,"Plato: " + datos.toString() + " modificado correctamente", "Plato modificado", JOptionPane.INFORMATION_MESSAGE);
			initGUI();
			setVisible(false);
			break;
		case MODIFICAR_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: Plato " + datos.toString() + " no modificado", "ERROR: MODIFICAR PLATO", JOptionPane.ERROR_MESSAGE);
			initGUI();
			setVisible(false);
			break;
		}
	}
}
