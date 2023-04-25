package presentacion.factoria;

import java.awt.Frame;

import presentacion.Evento;

import presentacion.IGUI;
import presentacion.mesas.VistaAnadirMesa;
import presentacion.mesas.VistaBorrarMesa;
import presentacion.clientes.*;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	
	FactoriaPresentacion(){
		
	}
	
	private IGUI vistaAnadirMesa = null;
	private IGUI vistaBorrarMesa = null;
	private IGUI vistaClienteLogueado = null;
	private IGUI vistaClienteNoRegistrado = null;
	private IGUI vistaPrincipalClientes = null;
	
	@Override
	public IGUI createVista(Evento e) {
		switch(e) {
		case ALTA_MESA_VISTA:
			
			if(vistaAnadirMesa == null) {
				vistaAnadirMesa = new VistaAnadirMesa(null);
			}
			return vistaAnadirMesa;
		case BAJA_MESA_VISTA:
			if(vistaBorrarMesa == null) {
				vistaBorrarMesa = new VistaBorrarMesa(null);
			}
			return vistaBorrarMesa;
		case CLIENTE_NO_REGISTRADO:
			if(vistaClienteNoRegistrado == null) {
				vistaClienteNoRegistrado = new VistaClienteNoRegistrado(null);
			}
			return vistaClienteNoRegistrado;
		case CLIENTE_REGISTRADO:
			if(vistaClienteLogueado == null) {
				vistaClienteLogueado= new VistaClienteLogueado();
			}
			return vistaClienteLogueado;
		case ACTUALIZAR_VISTA_CLIENTES:
			if(vistaPrincipalClientes == null) {
				vistaPrincipalClientes= new VistaPrincipalCliente();
			}
			return vistaPrincipalClientes;
			
		default:
			return null;
		}
	}

}
