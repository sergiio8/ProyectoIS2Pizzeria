package presentacion.clientes;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.clientes.TCliente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class VistaModificarCliente extends JDialog implements IGUI{

	private JLabel id;
	private JTextField tId;
	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lAp;
	private JTextField tAp;
	private JButton ok;
	private JButton cancel;
	
	public VistaModificarCliente(Frame parent){
		super(parent, true);
		
		setTitle("Modificar Cliente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		this.id = new JLabel("id: ");
		idPanel.add(id);
		
		this.tId = new JTextField(15);
		idPanel.add(tId);
		
		contenedor.add(idPanel);
		
		
		
		JPanel nombrePanel = new JPanel();
		
		lNombre = new JLabel("Nombre: ");
		nombrePanel.add(lNombre);
		
		tNombre = new JTextField(15);
		nombrePanel.add(tNombre);
		
		contenedor.add(nombrePanel);
		
		
		
		JPanel apellidoPanel = new JPanel();
		
		lAp = new JLabel("Apellidos: ");
		apellidoPanel.add(lAp);
		
		tAp = new JTextField(15);
		apellidoPanel.add(tAp);

		contenedor.add(apellidoPanel);
		
		
		JPanel buttonp1 = new JPanel();
		buttonp1.setAlignmentX(CENTER_ALIGNMENT);
		
		ok = new JButton();
		ok.setText("OK");
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tId.getText().isEmpty() || tNombre.getText().isEmpty() || tAp.getText().isEmpty()) {
					JOptionPane.showMessageDialog(VistaModificarCliente.this, "ERROR: Es necesario rellenar todos los datos", "ERROR: Es necesario rellenar todos los datos", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().accion(Evento.MODIFICAR_CLIENTE, new TCliente(tId.getText().toString(),tNombre.getText().toString() , tAp.getText().toString() ));
					
				}
			}
			
		});
		
		this.cancel = new JButton("Cancelar");
		this.cancel.addActionListener((e)->{
			this.setVisible(false);
		});
		buttonp1.add(ok);
		buttonp1.add(cancel);
		
		contenedor.add(buttonp1);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	
	
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		switch(e) {
		case VISTA_MODIFICAR_CLIENTE:
			this.setVisible(true);
			break;
		case VISTA_MODIFICAR_CLIENTE_OK:
			TCliente cliente = (TCliente)datos;
			JOptionPane.showMessageDialog(this, "El cliente con id " + cliente.getId() + "ha sido modificado", "Cliente modificado con éxito", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			break;
		case VISTA_MODIFICAR_CLIENTE_KO:
			TCliente cliente2 = (TCliente)datos;
			JOptionPane.showMessageDialog(this, "ERROR: no se ha podido modificar cliente con id " + cliente2.getId(), "ERROR: Cliente no modificado", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			break;
		}
		
	}

}