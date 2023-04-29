package presentacion.mesas;

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

public class VistaListarReservas extends JDialog implements IGUI{
private static final long serialVersionUID = 1L;
	
	private JTable tabla;
	private JPanel tablaPanel;
	private JButton confirmButton;
	private JPanel buttonPanel;
	private ModeloTablaReservas tableModel;
	
	public VistaListarReservas(Frame parent){
		super(parent, true);
		this.setTitle("Listar Reservas");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setContentPane(mainPanel);
		
		tablaPanel = new JPanel();
		tableModel = new ModeloTablaReservas();
		tabla = new JTable(tableModel);
		
		tablaPanel.setLayout(new BorderLayout());
		tablaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Reservas", TitledBorder.LEFT,TitledBorder.TOP));
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
		
		this.setPreferredSize(new Dimension(600,300));
		pack();
		setLocationRelativeTo(parent);
		
	}
	
	
	
	
	
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case LISTAR_RESERVAS:
			tableModel.update(datos);
			setVisible(true);
		}
		
	}

}
