package presentacion.factoria;

import java.awt.Frame;

import javax.swing.SwingUtilities;


import presentacion.Evento;

import presentacion.IGUI;
import presentacion.mesas.VistaAnadirMesa;
import presentacion.mesas.VistaAnadirReserva;
import presentacion.mesas.VistaBorrarMesa;
import presentacion.mesas.VistaBorrarReserva;
import presentacion.mesas.VistaBuscarMesa;
import presentacion.mesas.VistaListarMesas;
import presentacion.mesas.VistaListarReservas;
import presentacion.mesas.VistaListarReservasPorCliente;
import presentacion.mesas.VistaListarReservasPorMesa;
import presentacion.mesas.VistaModificarMesa;
import presentacion.mesas.VistaModificarReserva;
import presentacion.mesas.VistaPrincipalMesas;
import presentacion.mesas.VistaPrincipalReservas;
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
import presentacion.facturas.VistaFacturaEnProceso;
import presentacion.facturas.VistaPrincipalFacturas;
import presentacion.ingredientes.VistaAnadirIngrediente;
import presentacion.ingredientes.VistaBuscarIngrediente;
import presentacion.ingredientes.VistaEliminar;
import presentacion.ingredientes.VistaListarIngredientes;
import presentacion.ingredientes.VistaMainIngredientes;
import presentacion.ingredientes.VistaModificarIngrediente;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	
	FactoriaPresentacion(){
		
	}
	
	private IGUI vistaPrincipalMesa = null;
	private IGUI vistaAnadirReserva = null;
	private IGUI vistaPrincipalReserva = null;
	private IGUI vistaModificarReserva = null;
	private IGUI vistaBorrarReserva = null;
	private IGUI vistaAnadirMesa = null;
	private IGUI vistaListarReservasCliente = null;
	private IGUI vistaListarReservasMesa = null;
	private IGUI vistaBorrarMesa = null;
	private IGUI vistaModificarMesa = null;
	private IGUI vistaBuscarMesa = null;
	private IGUI vistaPrincipalClientes = null;
	private IGUI vistaAltaCliente = null;
	private IGUI vistaListarClientes = null;
	private IGUI vistaBajaCliente = null;
	private IGUI vistaModificarCliente = null;
	private IGUI vistaBuscarCliente = null;
	private IGUI vistaPrincipalFactura = null;
	private IGUI vistaAltaFactura = null;
	private IGUI vistaBuscarFactura = null;
	private IGUI vistaAnadirProducto = null;
	private IGUI vistaListarFacturas = null;
	private IGUI vistaFacturaEnProceso = null;
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
	private IGUI vistaBuscarIngrediente = null;

	@Override
	public IGUI createVista(Evento e) {
		switch(e) {
		case MAIN_WINDOW:
			return new presentacion.MainWindow();
		case VISTA_PRINCIPAL_RESERVA:
			this.vistaPrincipalReserva = new VistaPrincipalReservas();
			return vistaPrincipalReserva;
		case ALTA_RESERVA_VISTA:
			if(this.vistaAnadirReserva == null) {
				vistaAnadirReserva =new VistaAnadirReserva((Frame) vistaPrincipalReserva);
			}
			return vistaAnadirReserva;
		case BAJA_RESERVA_VISTA:
			if(this.vistaBorrarReserva == null) {
				vistaBorrarReserva =new VistaBorrarReserva((Frame) vistaPrincipalReserva);
			}
			return vistaBorrarReserva;
		case MODIFICAR_RESERVA_VISTA:
			if(this.vistaModificarReserva == null) {
				vistaModificarReserva =new VistaModificarReserva((Frame) vistaPrincipalReserva);
			}
			return vistaModificarReserva;
		case LISTAR_RESERVAS:
			return new VistaListarReservas((Frame) vistaPrincipalReserva);
		case LISTAR_RESERVAS_CLIENTE_VISTA:
			if(vistaListarReservasCliente == null) {
				vistaListarReservasCliente = new VistaListarReservasPorCliente((Frame) vistaPrincipalReserva);
			}
			return vistaListarReservasCliente;
		case LISTAR_RESERVAS_MESAS_VISTA:
			if(vistaListarReservasMesa == null) {
				vistaListarReservasMesa = new VistaListarReservasPorMesa((Frame) vistaPrincipalReserva);
			}
			return vistaListarReservasMesa;
			
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
		case VISTA_ALTA_CLIENTE:
			if(vistaAltaCliente == null) {
				vistaAltaCliente= new VistaAltaCliente((Frame)vistaPrincipalClientes);
			}
			return vistaAltaCliente;
		case VISTA_MODIFICAR_CLIENTE:
			if(vistaModificarCliente == null) {
				vistaModificarCliente= new VistaModificarCliente((Frame)vistaPrincipalClientes);
			}
			return vistaModificarCliente;
		case VISTA_BAJA_CLIENTE:
			if(vistaBajaCliente == null) {
				vistaBajaCliente= new VistaBajaCliente((Frame)vistaPrincipalClientes);
			}
			return vistaBajaCliente;
		case VISTA_LISTAR_CLIENTES:
			if(vistaListarClientes == null) {
				vistaListarClientes= new VistaListarClientes((Frame)vistaPrincipalClientes);
			}
			return vistaListarClientes;
		case VISTA_BUSCAR_CLIENTE:
			if(vistaBuscarCliente == null) {
				vistaBuscarCliente= new VistaBuscarCliente((Frame)vistaPrincipalClientes);
			}
			return vistaBuscarCliente;
			
		case VISTA_PRINCIPAL_FACTURA:
			this.vistaPrincipalFactura = new VistaPrincipalFacturas();
			return vistaPrincipalFactura;
		case VISTA_FACTURA_EN_PROCESO:
			this.vistaFacturaEnProceso = new VistaFacturaEnProceso((Frame) vistaPrincipalFactura);
			return vistaFacturaEnProceso;
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
		case BUSCAR_INGREDIENTE_VISTA:
			if(vistaBuscarIngrediente == null)
				vistaBuscarIngrediente = new VistaBuscarIngrediente((Frame) vistaPrincipalIngrediente);
			return vistaBuscarIngrediente;
		default:
			return null;
		}
	}

}
