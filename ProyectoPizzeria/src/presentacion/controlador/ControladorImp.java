package presentacion.controlador;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.mesas.SAMesas;
import negocio.mesas.TMesas;
import negocio.facturas.SAFactura;
import negocio.facturas.TDatosVenta;
import presentacion.Evento;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class ControladorImp extends Controlador { //implementacion

	@Override
	public void accion(Evento e, Object datos) {
		switch(e) {
		case ALTA_MESA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_MESA_VISTA).actualizar(Evento.ALTA_MESA_VISTA, null);
			break;
		case ALTA_MESA:

			TMesas tm = (TMesas) datos;
			SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
			
			int res = saMesas.alta(tm);
			
			if(res == -1) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_MESA_VISTA).actualizar(Evento.ALTA_MESA_KO, res);
			}
			else {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_MESA_VISTA).actualizar(Evento.ALTA_MESA_OK, res);
			}
			break;
		case ALTA_FACTURA:
	    	TDatosVenta td = (TDatosVenta) datos;
            SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
        
            boolean sol = saFact.crearFactura(td);
            break;
		}//vcambiossff
	    
        

	}

}
