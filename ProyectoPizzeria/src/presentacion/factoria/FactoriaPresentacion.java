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
import presentacion.producto.AnadirPlatoVista;
import presentacion.producto.BuscarPlatoVista;
import presentacion.producto.EliminarPlatoVista;
import presentacion.producto.ModificarPlatoVista;
import presentacion.producto.VistaListarPlatos;
import presentacion.producto.VistaPrincipalPlatos;
import presentacion.clientes.*;
import presentacion.facturas.AnadirProducto;
import presentacion.facturas.BuscarFactura;
import presentacion.facturas.CerrarVenta;
import presentacion.facturas.ListarFacturas;
import presentacion.facturas.VistaPrincipalFacturas;
import presentacion.ingredientes.VistaAnadirIngrediente;
import presentacion.ingredientes.VistaEliminar;
import presentacion.ingredientes.VistaListarIngredientes;
import presentacion.ingredientes.VistaMainIngredientes;
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
	private IGUI vistaPrincipalClientes = null;
	private IGUI vistaRegistrarCliente = null;
	private IGUI vistaModificarCliente = null;
	private IGUI vistaPrincipalFactura = null;
	private IGUI vistaAltaFactura = null;
	private IGUI vistaBuscarFactura = null;
	private IGUI vistaAnadirProducto = null;
	private IGUI vistaListarFacturas = null;
	private IGUI vistaEliminarIngrediente = null;
	private IGUI vistaAnadirIngrediente = null;
	private IGUI vistaModificarIngrediente = null;
	private IGUI vistaPrincipalIngrediente = null;
	private IGUI vistaPrincipalPlato = null;
	private IGUI vistaAnadirPlato = null;
	private IGUI vistaBorrarPlato = null;
	private IGUI vistaModificarPlato = null;
	private IGUI vistaBuscarPlato = null;
	private IGUI vistaListarIngredientes= null;

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
		case VISTA_PRINCIPAL_CLIENTES:
			vistaPrincipalClientes = new VistaPrincipalCliente();
			return vistaPrincipalClientes;
		case VISTA_CLIENTE_LOGUEADO:
			if(vistaClienteLogueado == null) {
				vistaClienteLogueado= new VistaClienteLogueado((Frame)vistaPrincipalClientes);
			}
			return vistaClienteLogueado;
		case VISTA_REGISTRO_DE_CLIENTE:
			if(vistaRegistrarCliente == null) {
				vistaRegistrarCliente= new VistaRegistrarCliente((Frame)vistaPrincipalClientes);
			}
			return vistaRegistrarCliente;
		case VISTA_MODIFICAR_CLIENTE:
			if(vistaModificarCliente == null) {
				vistaModificarCliente= new VistaModificarCliente((Frame)vistaPrincipalClientes);
			}
			return vistaModificarCliente;
		case VISTA_PRINCIPAL_FACTURA:
			this.vistaPrincipalFactura = new VistaPrincipalFacturas();
			return vistaPrincipalFactura;
		case ALTA_FACTURA_VISTA:
			if (vistaAltaFactura == null) {
				vistaAltaFactura = new CerrarVenta((Frame) vistaPrincipalFactura);
			}
			return vistaAltaFactura;
		case BUSCAR_FACTURA_VISTA:
			if (vistaBuscarFactura == null) {
				vistaBuscarFactura = new BuscarFactura((Frame) vistaPrincipalFactura);
			}
			return vistaBuscarFactura;
		case LISTAR_FACTURAS_VISTA:
			if (vistaListarFacturas == null) {
				vistaListarFacturas = new ListarFacturas((Frame) vistaPrincipalFactura);
			}
			return vistaListarFacturas;
		case ANADIR_PRODUCTO_VISTA:
			if (vistaAnadirProducto == null) {
				vistaAnadirProducto = new AnadirProducto((Frame) vistaPrincipalFactura);
			}
			return vistaAnadirProducto;
		case ANADIR_PRODUCTO_VISTA_BIS:
			if (vistaAnadirProducto == null) {
				vistaAnadirProducto = new AnadirProducto((Frame) vistaPrincipalFactura);
			}
			return vistaAnadirProducto;
		case BAJA_INGREDIENTE_VISTA:
			if(vistaEliminarIngrediente == null) {
				vistaEliminarIngrediente = new VistaEliminar((Frame) vistaPrincipalIngrediente);
			}
			return vistaEliminarIngrediente;
		case ALTA_INGREDIENTE_VISTA:
			if(vistaAnadirIngrediente == null) {
				vistaAnadirIngrediente = new VistaAnadirIngrediente((Frame) vistaPrincipalIngrediente);
			}
			return vistaAnadirIngrediente;
		case MODIFICAR_INGREDIENTE_VISTA:
			if(vistaModificarIngrediente == null) {
				vistaModificarIngrediente = new VistaModificarIngrediente((Frame) vistaPrincipalIngrediente);
			}
			return vistaModificarIngrediente;
		case VISTA_PRINCIPAL_INGREDIENTE:
			vistaPrincipalIngrediente = new VistaMainIngredientes();
			return vistaPrincipalIngrediente;
		case VISTA_PRINCIPAL_PLATO:
			vistaPrincipalPlato = new VistaPrincipalPlatos();
			return vistaPrincipalPlato;
			
		case ALTA_PLATO_VISTA:
			if(vistaAnadirPlato == null)
				vistaAnadirPlato = new AnadirPlatoVista((Frame) vistaPrincipalPlato);
			return vistaAnadirPlato;
		case BAJA_PLATO_VISTA:
			if(vistaBorrarPlato == null)
				vistaBorrarPlato = new EliminarPlatoVista((Frame) vistaPrincipalPlato);
			return vistaBorrarPlato;
		case MODIFICAR_PLATO_VISTA:
			if(vistaModificarPlato == null)
				vistaModificarPlato = new ModificarPlatoVista((Frame) vistaPrincipalPlato);
			return vistaModificarPlato;
		case BUSCAR_PLATO_VISTA:
			if(vistaBuscarPlato == null)
				vistaBuscarPlato = new BuscarPlatoVista((Frame) vistaPrincipalPlato);
			return vistaBuscarPlato;
		case LISTAR_PLATOS:
			return new VistaListarPlatos((Frame) vistaPrincipalPlato);
		case LISTAR_INGREDIENTE_VISTA:
			this.vistaListarIngredientes= new VistaListarIngredientes((Frame) vistaPrincipalIngrediente);
			return vistaListarIngredientes;
		default:
			return null;
		}
	}

}
