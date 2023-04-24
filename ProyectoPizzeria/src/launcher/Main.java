package launcher;

import presentacion.Evento;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaAnadirMesa;

public class Main {
	
	public static void main(String[] args) {
		//Controlador.getInstance().accion(Evento.ALTA_MESA_VISTA, null);
		
		Controlador.getInstance().accion(Evento.BAJA_MESA_VISTA, null);
		//hola
	}
}
