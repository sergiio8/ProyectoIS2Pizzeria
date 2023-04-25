package presentacion.clientes;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;

public class VistaClienteNoRegistrado extends JDialog implements IGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton ok;
	
	
	public VistaClienteNoRegistrado(Frame parent) {
		super(parent, true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		ok = new JButton();
		ok.setText("OK");
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VistaClienteNoRegistrado.this.setVisible(false);
			}
			
		});
		
		pack();
		setResizable(false);
		
		
	}
	
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		switch(e) {
		case CLIENTE_NO_REGISTRADO:
			this.setTitle("El id" + (String)datos + "no está registrado");
			this.setVisible(true);
			break;
		}

	}

}
