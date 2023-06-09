package presentacion.clientes;

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

public class VistaListarClientes extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JTable tabla;
	private JPanel tablaPanel;
	private JButton confirmButton;
	private JPanel buttonPanel;
	private ModeloTablaClientes tableModel;
	
	public VistaListarClientes(Frame parent){
		super(parent, true);
		this.setTitle("Lista de clientes");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setContentPane(mainPanel);
		
		tablaPanel = new JPanel();
		tableModel = new ModeloTablaClientes();
		tabla = new JTable(tableModel);
		
		tablaPanel.setLayout(new BorderLayout());
		tablaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Clientes", TitledBorder.LEFT,TitledBorder.TOP));
		tablaPanel.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		mainPanel.add(tablaPanel, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		confirmButton = new JButton("Confirmar");
		confirmButton.addActionListener((e)->{
			this.dispose();
		});
		buttonPanel.add(confirmButton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(300,300));
		pack();
		setLocationRelativeTo(parent);
		
	}
	
	
	
	
	
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case VISTA_LISTAR_CLIENTES:
			tableModel.update(datos);
			setVisible(true);
		}
	}

}
