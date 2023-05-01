package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import presentacion.Evento;
import presentacion.IGUI;

public class VistaListarPlatos extends JDialog implements IGUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModeloTablaPlato tableModel;
	private Frame parent;
	
	public VistaListarPlatos(Frame parent) {
		super(parent,true);
		this.parent = parent;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Listar Platos");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		JPanel tablaPanel = new JPanel();
		tableModel = new ModeloTablaPlato();
		JTable tabla = new JTable(tableModel);
		
		tablaPanel.setLayout(new BorderLayout());
		tablaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Platos", TitledBorder.LEFT,TitledBorder.TOP));
		tablaPanel.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		mainPanel.add(tablaPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		JButton confirmButton = new JButton("Confirmar");
		confirmButton.addActionListener((e)->{
			setVisible(false);
		});
		buttonPanel.add(confirmButton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(700,300));
		pack();
		setLocationRelativeTo(parent);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case LISTAR_PLATOS:
			tableModel.update(datos);
			setVisible(true);
		}
	}
}
