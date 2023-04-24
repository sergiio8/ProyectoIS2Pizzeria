package presentacion.factoria;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.mesas.VistaAnadirMesa;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	
	FactoriaPresentacion(){
		
	}
	
	private IGUI vistaAnadirMesa = null;
	
	
	@Override
	public IGUI createVista(Evento e) {
		switch(e) {
		case ALTA_MESA_VISTA:
			if(vistaAnadirMesa == null) {
				vistaAnadirMesa = new VistaAnadirMesa(null);
			}
			return vistaAnadirMesa;
		
		default:
			return null;
		}
	}

}
