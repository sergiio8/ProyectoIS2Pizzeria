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

public class VistaListarReservasPorMesa extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonsPanel;
	
	private JTable tabla;
	private JPanel tablaPanel;
	private ModeloTablaReservas tableModel;
	
	public VistaListarReservasPorMesa(Frame parent) {
		super(parent, true);
		
		setTitle("Listar Reservas por Mesa");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel idPanel = new JPanel();
		
		
		this.idMesaLabel = new JLabel("idMesa: ");
		idPanel.add(idMesaLabel);
		
		this.idMesaField = new JTextField(10);
		//this.idMesaField.setPreferredSize(new Dimension(130, 30));
		idPanel.add(idMesaField);
		
		
		mainPanel.add(idPanel, BorderLayout.NORTH);
		tablaPanel = new JPanel();
	

		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			int id;
			try {
				id = Integer.parseInt(this.idMesaField.getText());
				if(id < 1) {
					throw new NumberFormatException();
				}
				Controlador.getInstance().accion(Evento.LISTAR_RESERVAS_MESAS, id);
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaListarReservasPorMesa.this, "ERROR: El id de la mesa debe ser un entero positivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
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
		case LISTAR_RESERVAS_MESAS_VISTA:
			buttonsPanel.removeAll();
			buttonsPanel.add(this.okButton);
			buttonsPanel.add(this.cancelButton);
			this.idMesaField.setEnabled(true);
			this.idMesaField.setText("");
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
		case LISTAR_RESERVAS_MESAS_OK:
			
			JOptionPane.showMessageDialog(this, "Reservas encontradas", "Reservas encontradas", JOptionPane.INFORMATION_MESSAGE);
			
			tableModel = new ModeloTablaReservas();
			tabla = new JTable(tableModel);
			tabla.setPreferredSize(new Dimension(600, 300));
			tablaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Reservas", TitledBorder.LEFT,TitledBorder.TOP));
			tablaPanel.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
			tableModel.update(datos);
			this.getContentPane().add(tablaPanel, BorderLayout.CENTER);
			this.idMesaField.setEnabled(false);
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
		case LISTAR_RESERVAS_MESAS_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
