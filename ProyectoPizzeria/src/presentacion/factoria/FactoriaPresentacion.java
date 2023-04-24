package presentacion.factoria;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.mesas.VistaAnadirMesa;
import presentacion.mesas.VistaBorrarMesa;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	
	FactoriaPresentacion(){
		
	}
	
	private IGUI vistaAnadirMesa = null;
	private IGUI vistaBorrarMesa = null;
	
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
		default:
			return null;
		}
	}

}
