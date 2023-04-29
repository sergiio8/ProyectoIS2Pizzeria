package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.producto.TPlato;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class BuscarPlatoVista extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;
	
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameText;
	private JPanel typePanel;
	private JLabel typeLabel;
	private JPanel pricePanel;
	private JLabel priceLabel;
	private JPanel ingredientsPanel;
	private JLabel ingredientsLabel;
	private JPanel descriptionPanel;
	private JLabel descriptionLabel;
	private JPanel buttonsPanel;
	private JButton okButton;
	private JButton cancelButton;
	
	public BuscarPlatoVista(Frame parent) {
		super(parent, true);
		initGUI(parent);
	}
	
	private void initGUI(Frame parent) {
		setTitle("Buscar plato");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		//ID
		namePanel = new JPanel();
		nameLabel = new JLabel("ID_plato: ");
		nameText = new JTextField(10);
		
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		contenedor.add(namePanel);
		
		contenedor.add(typePanel = new JPanel());
		contenedor.add(pricePanel = new JPanel());
		contenedor.add(ingredientsPanel = new JPanel());
		contenedor.add(descriptionPanel = new JPanel());
		
		//Botones
		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
				
		okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String nombre;
			try {
				nombre = nameText.getText();
				if(nombre == null || nombre.equals("")) {
					throw new IllegalArgumentException();
				}
				
				Controlador.getInstance().accion(Evento.BUSCAR_PLATO, nombre);
				
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(BuscarPlatoVista.this, "ERROR: Rellene el campo relativo al nombre", "ERROR: Rellene el campo relativo al nombre", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton = new JButton("Cancelar");
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
		case BUSCAR_PLATO_VISTA:
			nameText.setEnabled(true);
			if(typeLabel != null) {
				typePanel.remove(typeLabel);
				pricePanel.remove(priceLabel);
				ingredientsPanel.remove(ingredientsLabel);
				descriptionPanel.remove(descriptionLabel);
			}
			buttonsPanel.removeAll();
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);
			revalidate();
			repaint();
			pack();
			setVisible(true);
			break;
		case BUSCAR_PLATO_OK:
			JOptionPane.showMessageDialog(this, "Plato encontrado", "Plato encontrado", JOptionPane.INFORMATION_MESSAGE);
			TPlato tp = (TPlato) datos;
			nameText.setEnabled(false);
			
			typeLabel = new JLabel("Tipo: " + tp.getTipo());
			typePanel.add(typeLabel);
			priceLabel = new JLabel("Precio: " + tp.getPrecio());
			pricePanel.add(priceLabel);
			String ingredientes = "";
			ArrayList<String> aux = tp.getIngredientes();
			int i = 0;
			while(i<aux.size()-1)
				ingredientes += aux.get(i++) + ", ";
			ingredientes += aux.get(i) + '"';
			ingredientsLabel = new JLabel("Ingredientes: " + ingredientes);
			ingredientsPanel.add(ingredientsLabel);
			descriptionLabel = new JLabel("Descripcion: " + tp.getDescripcion());
			descriptionPanel.add(descriptionLabel);
			
			buttonsPanel.removeAll();
			JButton confirmButton = new JButton("Confirmar");
			confirmButton.addActionListener((event) -> setVisible(false));
			buttonsPanel.add(confirmButton);
			revalidate();
			repaint();
			pack();
			break;
		case BUSCAR_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: Plato no encontrado", "ERROR: Plato no encontrado", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}
}
