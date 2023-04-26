package presentacion.factoria;

import java.awt.Frame;

import javax.swing.SwingUtilities;


import presentacion.Evento;

import presentacion.IGUI;
import presentacion.mesas.VistaAnadirMesa;
import presentacion.mesas.VistaBorrarMesa;
import presentacion.mesas.VistaBuscarMesa;
import presentacion.mesas.VistaListarMesas;
import presentacion.mesas.VistaModificarMesa;
import presentacion.mesas.VistaPrincipalMesas;
import presentacion.clientes.*;
import presentacion.facturas.AnadirProducto;
import presentacion.facturas.BuscarFactura;
import presentacion.facturas.CerrarVenta;
import presentacion.facturas.ListarFacturas;
import presentacion.facturas.ModificarFactura;
import presentacion.ingredientes.VistaAnadirIngrediente;
import presentacion.ingredientes.VistaEliminar;
import presentacion.ingredientes.VistaModificarIngrediente;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	
	FactoriaPresentacion(){
		
	}
	
	private IGUI vistaPrincipalMesa = null;
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
	private IGUI vistaEliminarIngrediente = null;
	private IGUI vistaAnadirIngrediente = null;
	private IGUI vistaModificarIngrediente = null;

	@Override
	public IGUI createVista(Evento e) {
		switch(e) {
		case MAIN_WINDOW:
			return new presentacion.MainWindow();
		case VISTA_PRINCIPAL_MESA:
			this.vistaPrincipalMesa = new VistaPrincipalMesas();
			return vistaPrincipalMesa;
			
		case ALTA_MESA_VISTA:
			
			if(vistaAnadirMesa == null) {
				vistaAnadirMesa = new VistaAnadirMesa((Frame) vistaPrincipalMesa);
			}
			return vistaAnadirMesa;
		case BAJA_MESA_VISTA:
			if(vistaBorrarMesa == null) {
				vistaBorrarMesa = new VistaBorrarMesa((Frame) vistaPrincipalMesa);
			}
			return vistaBorrarMesa;
		case MODIFICAR_MESA_VISTA:
			if(vistaModificarMesa == null) {
				vistaModificarMesa = new VistaModificarMesa((Frame) vistaPrincipalMesa);
			}
			return vistaModificarMesa;
		case BUSCAR_MESA_VISTA:
			if(vistaBuscarMesa == null) {
				vistaBuscarMesa = new VistaBuscarMesa((Frame) vistaPrincipalMesa);
			}
			return vistaBuscarMesa;
		case LISTAR_MESAS:
			return new VistaListarMesas((Frame) vistaPrincipalMesa);
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
		case BAJA_INGREDIENTE_VISTA:
			if(vistaEliminarIngrediente == null) {
				vistaEliminarIngrediente = new VistaEliminar();
			}
		case ALTA_INGREDIENTE_VISTA:
			if(vistaAnadirIngrediente == null) {
				vistaAnadirIngrediente = new VistaAnadirIngrediente();
			}
		case MODIFICAR_INGREDIENTE_VISTA:
			if(vistaModificarIngrediente == null) {
				vistaModificarIngrediente = new VistaModificarIngrediente();
			}
		default:
			return null;
		}
	}

}
