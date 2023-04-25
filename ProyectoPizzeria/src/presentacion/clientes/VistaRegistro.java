package presentacion.clientes;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;

public class VistaRegistro extends JFrame implements IGUI{
	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lAp;
	private JTextField tAp;
	private JLabel lId;
	private JTextField tId;
	
	
	public VistaRegistro() {
		this.setTitle("REGISTRO");
		
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
		p2.add(tNombre);
		mainPanel.add(p2);
		
		
		JPanel p3 = new JPanel();
		p3.setAlignmentX(CENTER_ALIGNMENT);
		
		lId = new JLabel();
		lId.setText("Id (NIF): ");
		p3.add(lId);
		
		tId = new JTextField();
		p3.add(tId);
		mainPanel.add(p3);
		
		pack();
		setResizable(false);
	}
	
	
	
	
	
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		if(this.isVisible()) {
			this.setVisible(false);
		}
		else this.setVisible(true);
	}

}
