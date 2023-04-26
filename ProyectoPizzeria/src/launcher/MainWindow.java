package launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JButton mesasButton;
	private JButton clientesButton;
	private JButton platosButton;
	private JButton ingredientesButton;
	private JButton facturasButton;
	private JButton proveedorButton;

	public MainWindow() {
		super("Pizzeria Pai-Pai");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Pizzeria Pai-Pai");
		titulo.setFont(new Font("Serif", Font.BOLD, 75));
		//titulo.setPreferredSize(new Dimension(200, 100));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		mesasButton = new JButton("Subsistema Mesas");
		buttonsPanel.add(mesasButton);
		
		clientesButton = new JButton("Subsistema Clientes");
		buttonsPanel.add(clientesButton);
		
		platosButton = new JButton("Subsistema Platos");
		buttonsPanel.add(platosButton);
		
		ingredientesButton = new JButton("Subsistema Ingredientes");
		buttonsPanel.add(ingredientesButton);
		
		facturasButton = new JButton("Subsistema Facturas");
		buttonsPanel.add(facturasButton);
		
		proveedorButton = new JButton("Subsistema Proveedor");
		buttonsPanel.add(proveedorButton);
		
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		setContentPane(mainPanel);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
}
