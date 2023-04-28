package presentacion.clientes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.clientes.SAClientes;
import presentacion.*;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaPrincipalMesas;

public class VistaPrincipalCliente extends JFrame implements IGUI{

	private JLabel lid;
	private JTextField tid;
	private JButton ok;
	private JButton cancel;
	private JLabel lcuenta;
	private JButton register;
	
	public VistaPrincipalCliente() {
		
		this.setTitle("MENÚ CLIENTES");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		JPanel labelp1 = new JPanel();
		labelp1.setAlignmentX(CENTER_ALIGNMENT);
		
		lid = new JLabel();
		lid.setText("Introduzca su id de usuario(NIF):");
		labelp1.add(lid);
		mainPanel.add(labelp1);
		
		
		JPanel textp1 = new JPanel();
		textp1.setAlignmentX(CENTER_ALIGNMENT);
		
		tid = new JTextField(15);
		tid.setAlignmentX(CENTER_ALIGNMENT);
		//tid.setMaximumSize(new Dimension(300, 50));
		textp1.add(tid);
		mainPanel.add(textp1);
		
		JPanel buttonp1 = new JPanel();
		buttonp1.setAlignmentX(CENTER_ALIGNMENT);
		
		ok = new JButton();
		ok.setText("OK");
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = tid.getText();
				Controlador.getInstance().accion(Evento.BUSCA_CLIENTE, id);
			}
			
		});
		buttonp1.add(ok);
		
		
		cancel = new JButton();
		cancel.setText("Cancelar");
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VistaPrincipalCliente.this.setVisible(false);
			}
			
		});
		buttonp1.add(cancel);
		mainPanel.add(buttonp1);
		
		
		JPanel lp2 = new JPanel();
		lp2.setAlignmentX(CENTER_ALIGNMENT);
		
		lcuenta = new JLabel();
		lcuenta.setText("¿No está registrado? Regístrese aquí:");
		lp2.add(lcuenta);
		mainPanel.add(lp2);
		
		JPanel buttonp2 = new JPanel();
		buttonp2.setAlignmentX(CENTER_ALIGNMENT);
		
		register = new JButton();
		register.setText("Regístrate");
		register.setSize(new Dimension(300, 50));
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().accion(Evento.VISTA_REGISTRO_DE_CLIENTE, null);
			}
			
		});
		buttonp2.add(register);
		mainPanel.add(buttonp2);
		
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
				VistaPrincipalCliente.this.dispose();
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		add(mainPanel);
		pack();
		//setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		switch(e) {
		case CLIENTE_NO_REGISTRADO:
			JOptionPane.showMessageDialog(this, "Ningún cliente registrado con id " + datos.toString(), "Cliente no registrado", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
