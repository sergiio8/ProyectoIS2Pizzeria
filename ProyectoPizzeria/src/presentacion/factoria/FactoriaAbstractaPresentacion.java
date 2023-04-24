package presentacion.factoria;

import presentacion.Evento;
import presentacion.IGUI;

public abstract class FactoriaAbstractaPresentacion { //singleton

	private static FactoriaAbstractaPresentacion instancia = null;
	
	public static FactoriaAbstractaPresentacion getInstace() { //creacion perezosa
		if(instancia == null) {
			instancia = new FactoriaPresentacion();
		}
		return instancia;
	}
	
     public abstract IGUI createVista(Evento e);
	//dsasd
}
