package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaListarReservasPorCliente extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel idClienteLabel;
	private JTextField idClienteField;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonsPanel;
	
	private JTable tabla;
	private JPanel tablaPanel;
	private ModeloTablaReservas tableModel;
	
	public VistaListarReservasPorCliente(Frame parent) {
		super(parent, true);
		
		setTitle("Listar Reservas por Cliente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel idPanel = new JPanel();
		
		
		this.idClienteLabel = new JLabel("idCliente: ");
		idPanel.add(idClienteLabel);
		
		this.idClienteField = new JTextField(10);
		//this.idMesaField.setPreferredSize(new Dimension(130, 30));
		idPanel.add(idClienteField);
		
		
		mainPanel.add(idPanel, BorderLayout.NORTH);
		tablaPanel = new JPanel();
	

		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			String id;
			
			id = idClienteField.getText();
			if(id == "" || id == null) {
				JOptionPane.showMessageDialog(VistaListarReservasPorCliente.this, "ERROR: Rellene los campos", "ERROR: Rellene los campos", JOptionPane.ERROR_MESSAGE);
			}
				
			Controlador.getInstance().accion(Evento.LISTAR_RESERVAS_CLIENTE, id);
				
			
			
			
			
			
		});
		
		
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener((e)->{
			this.setVisible(false);
		});
		
		
		
		buttonsPanel.add(this.okButton);
		buttonsPanel.add(this.cancelButton);
		
		
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		//setPreferredSize(new Dimension(600, 300));
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case LISTAR_RESERVAS_CLIENTE_VISTA:
			buttonsPanel.removeAll();
			buttonsPanel.add(this.okButton);
			buttonsPanel.add(this.cancelButton);
			this.idClienteField.setEnabled(true);
			this.idClienteField.setText("");
			if(tablaPanel != null) {
				this.remove(tablaPanel);
				tablaPanel = new JPanel();
				this.add(tablaPanel);
			}
			revalidate();
			repaint();
			pack();
			setLocationRelativeTo(this.getParent());
			setVisible(true);
			break;
		case LISTAR_RESERVAS_CLIENTE_OK:
			
			JOptionPane.showMessageDialog(this, "Reservas encontradas", "Reservas encontradas", JOptionPane.INFORMATION_MESSAGE);
			
			tableModel = new ModeloTablaReservas();
			tabla = new JTable(tableModel);
			tabla.setPreferredSize(new Dimension(600, 300));
			tablaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Reservas", TitledBorder.LEFT,TitledBorder.TOP));
			tablaPanel.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
			tableModel.update(datos);
			this.getContentPane().add(tablaPanel, BorderLayout.CENTER);
			this.idClienteField.setEnabled(false);
			this.buttonsPanel.removeAll();
			JButton confirmButton = new JButton("Confirmar");
			confirmButton.addActionListener((event)->{
				dispose();
			});
			this.buttonsPanel.add(confirmButton);
			revalidate();
			repaint();
			pack();
			setLocationRelativeTo(this.getParent());
			
			
			break;
		case LISTAR_RESERVAS_CLIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
