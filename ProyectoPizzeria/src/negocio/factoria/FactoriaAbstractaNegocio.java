package negocio.factoria;

import negocio.mesas.SAMesas;

public abstract class FactoriaAbstractaNegocio {
	
	private static FactoriaAbstractaNegocio instancia = null;
	
	public static FactoriaAbstractaNegocio getInstace() { //creacion perezosa
		if(instancia == null) {
			instancia = new FactoriaNegocio();
		}
		return instancia;
	}
		
		//metodos para crear SAs
	
	
	
	public abstract SAMesas crearSAMesas();
}
