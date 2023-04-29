package presentacion.clientes;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.clientes.TCliente;
import negocio.mesas.TMesas;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaBuscarMesa;

public class VistaBuscarCliente  extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lId;
	private JTextField tId;
	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lApellido;
	private JTextField tApellido;
	private Box contenedor;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonsPanel;
	private JPanel nombrePanel;
	private JPanel apellidoPanel;
	
	
	public VistaBuscarCliente(Frame parent) {
		// TODO Auto-generated constructor stub {
		super(parent, true);
		
		setTitle("Buscar Cliente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		
		this.lId = new JLabel("Id Cliente: ");
		idPanel.add(lId);
		
		this.tId = new JTextField(10);
		idPanel.add(tId);
		
		
		contenedor.add(idPanel);
		
		nombrePanel = new JPanel();
		contenedor.add(nombrePanel);
		
		apellidoPanel = new JPanel();
		contenedor.add(apellidoPanel);
		

		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			
			if(tId.getText().isEmpty()) {
				JOptionPane.showMessageDialog(VistaBuscarCliente.this, "ERROR: Es necesario rellenar los datos", "ERROR: Es necesario rellenar los datos", JOptionPane.ERROR_MESSAGE);
			}
			else{
				Controlador.getInstance().accion(Evento.BUSCAR_CLIENTE, tId.getText().toString());
			}
				
		});
		
		
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener((e)->{
			this.setVisible(false);
		});
		
		
		
		buttonsPanel.add(this.okButton);
		buttonsPanel.add(this.cancelButton);
		
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		//setPreferredSize(new Dimension(320, 500));
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case VISTA_BUSCAR_CLIENTE:
			buttonsPanel.removeAll();
			buttonsPanel.add(this.okButton);
			buttonsPanel.add(this.cancelButton);
			this.tId.setEnabled(true);
			if(lNombre != null) {
				this.nombrePanel.remove(lNombre);
			}
			if(lApellido != null) {
				this.apellidoPanel.remove(lApellido);
			}
			revalidate();
			repaint();
			pack();
			setVisible(true);
			break;
		case BUSCAR_CLIENTE_RES:
			if(datos == null) {
				JOptionPane.showMessageDialog(this, "ERROR: Cliente no encontrado", "ERROR: Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				JOptionPane.showMessageDialog(this, "Cliente encontrado", "Cliente encontrado", JOptionPane.INFORMATION_MESSAGE);
				TCliente c = (TCliente) datos;
				this.lNombre = new JLabel("Nombre: " + c.getNombre());
				this.nombrePanel.add(lNombre);
				this.lApellido = new JLabel("Apellido: " + c.getNombre());
				this.apellidoPanel.add(lApellido);
				this.tId.setEnabled(false);
				this.buttonsPanel.removeAll();
				JButton confirmButton = new JButton("Confirmar");
				confirmButton.addActionListener((event)->{
					setVisible(false);
				});
				this.buttonsPanel.add(confirmButton);
				revalidate();
				repaint();
				pack();
			}
			
			
			break;
		}
	}
}
