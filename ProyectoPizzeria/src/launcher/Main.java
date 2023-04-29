package launcher;

import presentacion.Evento;
import presentacion.controlador.Controlador;
import presentacion.ingredientes.VistaMainIngredientes;
import presentacion.mesas.VistaAnadirMesa;
import presentacion.mesas.VistaAnadirReserva;

public class Main {
	
	public static void main(String[] args) {
		//Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
		//Controlador.getInstance().accion(Evento.ALTA_RESERVA_VISTA, null);
		Controlador.getInstance().accion(Evento.LISTAR_RESERVAS, null);
		
		
	}
}
