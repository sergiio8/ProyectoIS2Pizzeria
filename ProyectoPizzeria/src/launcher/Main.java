package launcher;

import presentacion.Evento;
import presentacion.controlador.Controlador;
import presentacion.ingredientes.VistaMainIngredientes;
import presentacion.mesas.VistaAnadirMesa;

public class Main {
	
	public static void main(String[] args) {
		Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
		
	}
}
