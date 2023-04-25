package presentacion.factoria;

import java.awt.Frame;

import presentacion.Evento;

import presentacion.IGUI;
import presentacion.mesas.VistaAnadirMesa;
import presentacion.mesas.VistaBorrarMesa;
import presentacion.mesas.VistaBuscarMesa;
import presentacion.mesas.VistaModificarMesa;
import presentacion.clientes.*;
import presentacion.facturas.AnadirProducto;
import presentacion.facturas.BuscarFactura;
import presentacion.facturas.CerrarVenta;
import presentacion.facturas.ListarFacturas;
import presentacion.facturas.ModificarFactura;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	
	FactoriaPresentacion(){
		
	}
	
	private IGUI vistaAnadirMesa = null;
	private IGUI vistaBorrarMesa = null;
	private IGUI vistaModificarMesa = null;
	private IGUI vistaBuscarMesa = null;
	private IGUI vistaClienteLogueado = null;
	private IGUI vistaClienteNoRegistrado = null;
	private IGUI vistaPrincipalClientes = null;
	private IGUI vistaAltaFactura = null;
	private IGUI vistaModificarFactura = null;
	private IGUI vistaBuscarFactura = null;
	private IGUI vistaAnadirProducto = null;
	private IGUI vistaListarFacturas = null;

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
		case MODIFICAR_MESA_VISTA:
			if(vistaModificarMesa == null) {
				vistaModificarMesa = new VistaModificarMesa(null);
			}
			return vistaModificarMesa;
		case BUSCAR_MESA_VISTA:
			if(vistaBuscarMesa == null) {
				vistaBuscarMesa = new VistaBuscarMesa(null);
			}
			return vistaBuscarMesa;
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
		case ALTA_FACTURA_VISTA:
			if (vistaAltaFactura == null) {
				vistaAltaFactura = new CerrarVenta(null);
			}
			return vistaAltaFactura;
		case BUSCAR_FACTURA_VISTA:
			if (vistaBuscarFactura == null) {
				vistaBuscarFactura = new BuscarFactura(null);
			}
			return vistaBuscarFactura;
		case MODIFICAR_FACTURA_VISTA:
			if (vistaModificarFactura == null) {
				vistaModificarFactura = new ModificarFactura(null);
			}
			return vistaModificarFactura;
		case LISTAR_FACTURAS_VISTA:
			if (vistaListarFacturas == null) {
				vistaListarFacturas = new ListarFacturas(null);
			}
			return vistaListarFacturas;
		case ANADIR_PRODUCTO_VISTA:
			if (vistaAnadirProducto == null) {
				vistaAnadirProducto = new AnadirProducto(null);
			}
			return vistaAnadirProducto;
		default:
			return null;
		}
	}

}
