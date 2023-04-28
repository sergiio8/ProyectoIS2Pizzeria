package presentacion.clientes;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lAp;
	private JTextField tAp;
	private JButton ok;
	private JButton cancel;
	private String id;
	
	public VistaModificarCliente(Frame parent){
		super(parent, true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		JPanel p1 = new JPanel();
		p1.setAlignmentX(CENTER_ALIGNMENT);
		
		lNombre = new JLabel();
		lNombre.setText("Nombre: ");
		p1.add(lNombre);
		
		tNombre = new JTextField();
		p1.add(tNombre);
		mainPanel.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setAlignmentX(CENTER_ALIGNMENT);
		
		lAp = new JLabel();
		lAp.setText("Apellidos: ");
		p2.add(lAp);
		
		tAp = new JTextField();
		p2.add(tAp);
		mainPanel.add(p2);
		
		
		JPanel buttonp1 = new JPanel();
		buttonp1.setAlignmentX(CENTER_ALIGNMENT);
		
		ok = new JButton();
		ok.setText("OK");
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tNombre.getText().isEmpty() || tAp.getText().isEmpty()) {
					JOptionPane.showMessageDialog(VistaModificarCliente.this, "Es necesario rellenar todos los datos", "Falta de datos", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().accion(Evento.MODIFICAR_CLIENTE, new TCliente(VistaModificarCliente.this.id,tNombre.getText().toString() , tAp.getText().toString() ));
				}
			}
			
		});
		buttonp1.add(ok);
		
		cancel = new JButton();
		cancel.setText("Cancelar");
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().accion(Evento.VISTA_CLIENTE_LOGUEADO, VistaModificarCliente.this.id);
			}
			
		});
		
		buttonp1.add(cancel);
		mainPanel.add(buttonp1);
		
		add(mainPanel);
		
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
			this.setTitle("Modificar cliente con id: " + (String)datos);
			this.id = (String)datos;
			break;
		case VISTA_CLIENTE_LOGUEADO:
			this.setVisible(false);
		}
		
	}

}