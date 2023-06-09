package presentacion.controlador;

import negocio.clientes.SAClientes;
import negocio.clientes.TCliente;
import negocio.clientes.TDatosAltaCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.mesas.SAMesas;
import negocio.mesas.TMesas;
import negocio.mesas.TReserva;
import negocio.producto.SAPlato;
import negocio.producto.TDatosPlato;
import negocio.producto.TPlato;
import negocio.facturas.Carrito;
import negocio.facturas.SAFactura;
import negocio.facturas.TDatosVenta;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;
import negocio.ingredientes.SAIngrediente;
import negocio.ingredientes.TDatosAltaIngrediente;
import negocio.ingredientes.TDatosIngrediente;
import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TModificacionIngrediente;
import negocio.ingredientes.TPlatoIngrediente;
import presentacion.Evento;
import presentacion.factoria.FactoriaAbstractaPresentacion;
import presentacion.factoria.FactoriaPresentacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorImp extends Controlador { //implementacion
	private Carrito carrito;
	@Override
	public void accion(Evento e, Object datos) {
		switch(e) {
		case MAIN_WINDOW:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MAIN_WINDOW);
			break;
		case BAJA_RESERVA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_RESERVA_VISTA).actualizar(Evento.BAJA_RESERVA_VISTA, null);
			break;
		case BAJA_RESERVA:
			bajaReserva(datos);
			break;
		case ALTA_RESERVA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_RESERVA_VISTA).actualizar(Evento.ALTA_RESERVA_VISTA, null);
			break;
		case ALTA_RESERVA:
			altaReserva(datos);
			break;
		case MODIFICAR_RESERVA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_RESERVA_VISTA).actualizar(Evento.MODIFICAR_RESERVA_VISTA, null);
			break;
		case MODIFICAR_RESERVA:
			modificarReserva(datos);
			break;
		case LISTAR_RESERVAS:
			
			Collection<TReserva> reservas = listarReservas();
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS).actualizar(Evento.LISTAR_RESERVAS, reservas);
			break;
		case LISTAR_RESERVAS_CLIENTE_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS_CLIENTE_VISTA).actualizar(Evento.LISTAR_RESERVAS_CLIENTE_VISTA, null);
			break;
		case LISTAR_RESERVAS_CLIENTE:
			try {
				Collection<TReserva> reservasC = listarReservasCliente(datos);
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS_CLIENTE_VISTA).actualizar(Evento.LISTAR_RESERVAS_CLIENTE_OK, reservasC);
			}
			catch(Exception er) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS_CLIENTE_VISTA).actualizar(Evento.LISTAR_RESERVAS_CLIENTE_KO, er.getMessage());
			}
			break;
		case LISTAR_RESERVAS_MESAS_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS_MESAS_VISTA).actualizar(Evento.LISTAR_RESERVAS_MESAS_VISTA, null);
			break;
		case LISTAR_RESERVAS_MESAS:
			try {
				Collection<TReserva> reservasM= listarReservasMesa(datos);
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS_MESAS_VISTA).actualizar(Evento.LISTAR_RESERVAS_MESAS_OK, reservasM);
			}
			catch(Exception er) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_RESERVAS_MESAS_VISTA).actualizar(Evento.LISTAR_RESERVAS_MESAS_KO, er.getMessage());
			}
			break;
		case VISTA_PRINCIPAL_RESERVA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_PRINCIPAL_RESERVA);
			break;
		case VISTA_PRINCIPAL_MESA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_PRINCIPAL_MESA);
			break;
		case ALTA_MESA_VISTA:
			
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_MESA_VISTA).actualizar(Evento.ALTA_MESA_VISTA, null);
			break;
			
		case ALTA_MESA:

			altaMesa(datos);
			break;

		case BAJA_MESA_VISTA:
			
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_MESA_VISTA).actualizar(Evento.BAJA_MESA_VISTA, null);
			break;
			
		case BAJA_MESA:
			
			bajaMesa(datos);
			break;
		
		case MODIFICAR_MESA_VISTA:
			
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_MESA_VISTA).actualizar(Evento.MODIFICAR_MESA_VISTA, null);
			break;
		
		case MODIFICAR_MESA:
			modificaMesa(datos);
			break;
			
		case BUSCAR_MESA_VISTA:
			
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_MESA_VISTA).actualizar(Evento.BUSCAR_MESA_VISTA, null);
			break;
			
		case BUSCAR_MESA:
			
			buscaMesa(datos);
			break;
		
		case LISTAR_MESAS:
			
			Collection<TMesas> mesas = listarMesas();
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_MESAS).actualizar(Evento.LISTAR_MESAS, mesas);
			break;
		case VISTA_PRINCIPAL_FACTURA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_PRINCIPAL_FACTURA);
			break;
		case VISTA_FACTURA_EN_PROCESO:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_FACTURA_EN_PROCESO);
			break;
        case ALTA_FACTURA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA, null);
			break;
		case BUSCAR_FACTURA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA, null);
			break;
		case ANADIR_PRODUCTO_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA, null);
			break;
		case ANADIR_PRODUCTO_VISTA_BIS:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA_BIS).actualizar(Evento.ANADIR_PRODUCTO_VISTA_BIS, null);
			break;
		case LISTAR_FACTURAS_VISTA:
			Collection<TFactura> facts = listarFacturas();
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_FACTURAS_VISTA).actualizar(Evento.LISTAR_FACTURAS_VISTA, facts);
			break;
		case ALTA_FACTURA:
			altaFactura(datos);
            break;
		case BUSCAR_FACTURA:
			buscarFactura(datos);
			break;
		case ANADIR_PRODUCTO:
			anadirProducto(datos);
			break;
		case ABRIR_VENTA:
			abrirVenta(datos);
			break;
		case VISTA_PRINCIPAL_CLIENTES:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_PRINCIPAL_CLIENTES);
			break;
			
		case VISTA_ALTA_CLIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_ALTA_CLIENTE).actualizar(Evento.VISTA_ALTA_CLIENTE, datos);
			break;
		case ALTA_CLIENTE:
			altaCliente(datos);
			break;
		case VISTA_MODIFICAR_CLIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_MODIFICAR_CLIENTE).actualizar(Evento.VISTA_MODIFICAR_CLIENTE, datos);
			break;
		case MODIFICAR_CLIENTE:
			modificarCliente(datos);
			break;
		case VISTA_BAJA_CLIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_BAJA_CLIENTE).actualizar(Evento.VISTA_BAJA_CLIENTE, datos);
			break;
		case BAJA_CLIENTE:
			bajaCliente(datos);
			break;
		case VISTA_BUSCAR_CLIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_BUSCAR_CLIENTE).actualizar(Evento.VISTA_BUSCAR_CLIENTE, datos);
			break;
		case BUSCAR_CLIENTE:
			buscarCliente(datos);
			break;
		case VISTA_LISTAR_CLIENTES:
			Collection<TCliente> lClientes = listarClientes();
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_LISTAR_CLIENTES).actualizar(Evento.VISTA_LISTAR_CLIENTES, lClientes);
			break;
			
		case VISTA_PRINCIPAL_PLATO:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_PRINCIPAL_PLATO);
			break;
		case ALTA_PLATO_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_PLATO_VISTA).actualizar(Evento.ALTA_PLATO_VISTA, null);
			break;
			
		case ALTA_PLATO:
			altaPlato(datos);
			break;

		case BAJA_PLATO_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_PLATO_VISTA).actualizar(Evento.BAJA_PLATO_VISTA, null);
			break;
			
		case BAJA_PLATO:
			bajaPlato(datos);
			break;
		
		case MODIFICAR_PLATO_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_PLATO_VISTA).actualizar(Evento.MODIFICAR_PLATO_VISTA, null);
			break;
		
		case MODIFICAR_PLATO:
			modificaPlato(datos);
			break;
			
		case BUSCAR_PLATO_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_PLATO_VISTA).actualizar(Evento.BUSCAR_PLATO_VISTA, null);
			break;
			
		case BUSCAR_PLATO:
			buscaPlato(datos);
			break;
			
		case LISTAR_PLATOS:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_PLATOS).actualizar(Evento.LISTAR_PLATOS, listarPlatos());
			break;
			
		case VISTA_PRINCIPAL_INGREDIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_PRINCIPAL_INGREDIENTE);
			break;

		case ALTA_INGREDIENTE_VISTA:
            FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_INGREDIENTE_VISTA, null);
            break;
        case ALTA_INGREDIENTE:
        	
            altaIngrediente(datos);
            break;
        case ALTA_PLATOINGREDIENTE:
        	altaPlatoIngrediente(datos);
        	break;
        case BAJA_INGREDIENTE_VISTA:
            FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_INGREDIENTE_VISTA).actualizar(Evento.BAJA_INGREDIENTE_VISTA, null);
            break;
        case BAJA_INGREDIENTE:
            bajaIngrediente(datos);
            break;
        case MODIFICAR_INGREDIENTE_VISTA:
            FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_INGREDIENTE_VISTA).actualizar(Evento.MODIFICAR_INGREDIENTE_VISTA, null);
            break;
        case MODIFICAR_INGREDIENTE:
            modificarIngrediente(datos);
            break;
        case LISTAR_INGREDIENTE_VISTA:
        	TDatosIngrediente d = new TDatosIngrediente((List<TIngrediente>) listarIngredientes(), (List<TPlatoIngrediente>) listarPlatoIngrediente());
        	FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_INGREDIENTE_VISTA).actualizar(Evento.LISTAR_INGREDIENTE_VISTA,d);
        	break;
        case BUSCAR_INGREDIENTE_VISTA:
            FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_INGREDIENTE_VISTA).actualizar(Evento.BUSCAR_INGREDIENTE_VISTA, null);
            break;
        case BUSCAR_INGREDIENTE:
        	buscarIngrediente(datos);
        	break;
		default:
			break;
	}
}
	private void buscarIngrediente (Object datos) {
		SAIngrediente sa = FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		TIngrediente ing = sa.consulta((String) datos);
		if(ing == null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_INGREDIENTE_VISTA).actualizar(Evento.BUSCAR_INGREDIENTE_KO, null);
		}else {
			ArrayList<String> platos = sa.cogerPlatos((String) datos);
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_INGREDIENTE_VISTA).actualizar(Evento.BUSCAR_INGREDIENTE_OK, new TDatosAltaIngrediente(ing, platos));
		}
	}
	private Collection<TReserva> listarReservasMesa(Object datos) {
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		Collection<TReserva> reservas = saMesas.consultaTodosRMesa(Integer.parseInt(datos.toString()));
		return reservas;
	}
	private Collection<TReserva> listarReservasCliente(Object datos) {
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		Collection<TReserva> reservas = saMesas.consultaTodosRCliente(datos.toString());
		return reservas;
		
	}
	private Collection<TReserva> listarReservas() {
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		Collection<TReserva> reservas = saMesas.consultaTodosR();
		return reservas;
	}
	private void bajaReserva(Object datos) {
		String id = datos.toString();
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		try {
			boolean res = saMesas.borrarR(id);
			if(res) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_RESERVA_VISTA).actualizar(Evento.BAJA_RESERVA_OK, null);
			}
			else {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_RESERVA_VISTA).actualizar(Evento.BAJA_RESERVA_KO, "No se ha podido modificar la reserva");
			}
		}
		catch(IllegalArgumentException iae) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_RESERVA_VISTA).actualizar(Evento.BAJA_RESERVA_KO, iae.getMessage());
		}
	}
	private void modificarReserva(Object datos) {
		TReserva tr = (TReserva) datos;
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		
		try {
			boolean res = saMesas.modificarR(tr);
			if(res) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_RESERVA_VISTA).actualizar(Evento.MODIFICAR_RESERVA_OK, null);
			}
			else {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_RESERVA_VISTA).actualizar(Evento.MODIFICAR_RESERVA_KO, "No se ha podido modificar la reserva");
			}
		}
		catch(IllegalArgumentException iae) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_RESERVA_VISTA).actualizar(Evento.MODIFICAR_RESERVA_KO, iae.getMessage());
		}
	}
	
	private void altaReserva(Object datos) {
		TReserva tr = (TReserva) datos;
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		try {
			String res = saMesas.altaReserva(tr);
			if(res == null) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_RESERVA_VISTA).actualizar(Evento.ALTA_RESERVA_KO, "No se ha podido registrar la reserva");
			}
			else {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_RESERVA_VISTA).actualizar(Evento.ALTA_RESERVA_OK, res);
			}
		}
		catch(IllegalArgumentException iae) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_RESERVA_VISTA).actualizar(Evento.ALTA_RESERVA_KO, iae.getMessage());
		}
	}
	
	private void altaPlatoIngrediente(Object datos) {
		TPlatoIngrediente pI= (TPlatoIngrediente) datos;
		SAIngrediente saIngrediente = FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		saIngrediente.crear(pI);
	}
	
	private void altaIngrediente(Object datos) {
		TDatosAltaIngrediente d= (TDatosAltaIngrediente) datos;
		List<String> listaPlatos = d.getListaPlatos();
		SAPlato sa = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		String s = null;
		for(String nombre : listaPlatos) {
			TPlato aux = sa.consulta(nombre);
			if(aux==null) s = nombre;
		}
		if(s == null) {
			SAIngrediente saIngrediente= FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
			String nombre= saIngrediente.crear(d.getIngrediente());
			for(String n : listaPlatos) {
				accion(Evento.ALTA_PLATOINGREDIENTE, new TPlatoIngrediente(n, d.getIngrediente().getNombre()));
			}
			if(nombre==null) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_INGREDIENTE_KO, "No se ha podido crear el ingrediente");
			}
			else{
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_INGREDIENTE_OK, nombre);
			}
		}else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_INGREDIENTE_KO, "Plato "+ s+ " no encontrado");
		}
	}
	
	private void bajaIngrediente(Object datos) {
		SAIngrediente saIngrediente= FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		boolean borrar= saIngrediente.borrar((String) datos);
		if(!borrar) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_INGREDIENTE_VISTA).actualizar(Evento.BAJA_INGREDIENTE_KO, (String) datos);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_INGREDIENTE_VISTA).actualizar(Evento.BAJA_INGREDIENTE_OK, (String) datos);
		}
	}
	
	private void modificarIngrediente(Object datos) {
		SAIngrediente saIngrediente= FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		boolean modificado= saIngrediente.modificar((TModificacionIngrediente) datos);
		if(!modificado) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_INGREDIENTE_VISTA).actualizar(Evento.MODIFICAR_INGREDIENTE_KO, datos);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_INGREDIENTE_VISTA).actualizar(Evento.MODIFICAR_INGREDIENTE_OK, datos);
		}
	}
	
	private void altaMesa(Object datos) {
		TMesas tm = (TMesas) datos;
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		
		int res = saMesas.alta(tm);
		
		if(res == -1) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_MESA_VISTA).actualizar(Evento.ALTA_MESA_KO, res);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_MESA_VISTA).actualizar(Evento.ALTA_MESA_OK, res);
		}
	}
	
	private void bajaMesa(Object datos) {
		int id = Integer.parseInt(datos.toString());
		SAMesas saMesas2 = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		try {
			boolean res2 = saMesas2.borrar(id);
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_MESA_VISTA).actualizar(Evento.BAJA_MESA_RES, res2);
		}
		catch(Exception e) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_MESA_VISTA).actualizar(Evento.BAJA_MESA_RES_KO, e.getMessage());
		}
	}
	
	private void modificaMesa(Object datos) {
		TMesas tm = (TMesas) datos;
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		
		boolean res = saMesas.modificar(tm);
		
		FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_MESA_VISTA).actualizar(Evento.MODIFICAR_MESA_RES, res);
	}
	
	private void buscaMesa(Object datos) {
		int id = Integer.parseInt(datos.toString());
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		
		TMesas busqueda = saMesas.consulta(id);
		
		FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_MESA_VISTA).actualizar(Evento.BUSCAR_MESA_RES, busqueda);
	}
	
	private Collection<TMesas> listarMesas() {
		SAMesas saMesas = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		Collection<TMesas> mesas = saMesas.consultaTodos();
		return mesas;
	}
	
	private void altaPlato(Object datos) {
		TDatosPlato datosPlato = (TDatosPlato) datos;
		SAIngrediente saIng = FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		String aux = saIng.consultaIngredientes(datosPlato.getIngredientes());
		if(aux != null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_PLATO_VISTA).actualizar(Evento.ALTA_PLATO_KO, "Ingrediente: "+ aux +" no encontrado");
		}
		else{
			SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
			String nombre = saPlato.alta(datosPlato);
			if(nombre == "") {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_PLATO_VISTA).actualizar(Evento.ALTA_PLATO_KO, "Plato ya existente");
			}
			else {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_PLATO_VISTA).actualizar(Evento.ALTA_PLATO_OK, nombre);
			}
		}
	}
	
	private void bajaPlato(Object datos) {
		String nombre = datos.toString();
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		if (saPlato.borrar(nombre)) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_PLATO_VISTA).actualizar(Evento.BAJA_PLATO_OK, nombre);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_PLATO_VISTA).actualizar(Evento.BAJA_PLATO_KO, nombre);
		}
	}

	private void modificaPlato(Object datos) {
		TDatosPlato datosPlato = (TDatosPlato) datos;
		ArrayList<String> ingredientes = datosPlato.getIngredientes();
		if(!ingredientes.isEmpty()) {
			SAIngrediente saIng = FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
			String aux = saIng.consultaIngredientes(ingredientes);
			if(aux != null) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_PLATO_VISTA).actualizar(Evento.MODIFICAR_PLATO_KO, "Ingrediente: "+ aux +" no encontrado");
				return;
			}
		}
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		String nombre = datosPlato.getPlato().getNombre();
		if(saPlato.modificar(datosPlato)) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_PLATO_VISTA).actualizar(Evento.MODIFICAR_PLATO_OK, nombre);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_PLATO_VISTA).actualizar(Evento.MODIFICAR_PLATO_KO, nombre);
		}
	}
	
	private void buscaPlato(Object datos) {
		String nombre = datos.toString();
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		
		TPlato plato = saPlato.consulta(nombre);
		
		if(plato == null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_PLATO_VISTA).actualizar(Evento.BUSCAR_PLATO_KO, nombre);
		}
		else {
			TDatosPlato datosPlato = new TDatosPlato(plato,saPlato.cogerIngredientes(nombre));
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_PLATO_VISTA).actualizar(Evento.BUSCAR_PLATO_OK, datosPlato);
		}
	}
	
	private Collection<TDatosPlato> listarPlatos() {
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		return saPlato.listarPlatos();
	}
	
	private void altaFactura(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		TDatosVenta dt = (TDatosVenta) datos;
		dt.setProductos(carrito.getProductos());
		String solu = saPlato.comprobarDisponibilidad(dt.getProductos());
		if (solu != null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_WR, solu);
			carrito = new Carrito();
		}
		else {
	        boolean sol = saFact.crearFactura(dt);
	        if (sol) {
	        	saPlato.hacerPedido(dt.getProductos());
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_OK, dt);
		    }
	        else {
	        	FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_WR, "La factura no pudo ser validada");
	        	carrito = new Carrito();
	        }
		}
	}
	
	private void buscarFactura(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		TFactura tf = saFact.buscarFactura(datos.toString());
		if (tf != null) FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA_OK, tf);
		else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA_WR, tf);
	}
	
	private Collection<TFactura> listarFacturas() {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		Collection<TFactura> facturas = saFact.listarFacturas();
		return facturas;
	}
	
	private void anadirProducto(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		TLineaFactura tf2 = (TLineaFactura) datos;
		if (tf2 != null) {
			saFact.anadirProducto(tf2, carrito);
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA_OK, tf2.getIdProducto());
		}
		else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA_WR, "El producto no pudo ser añadido");
	}
	
	private void abrirVenta(Object datos) {
		carrito = new Carrito();
	}
	
	private void buscarCliente(Object datos) {
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		TCliente c = infoCliente.consulta((String)datos);
		FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_BUSCAR_CLIENTE).actualizar(Evento.BUSCAR_CLIENTE_RES, c);
	}
	
	
	private void altaCliente(Object datos) {
		TDatosAltaCliente d = (TDatosAltaCliente)datos;
		TCliente cliente = d.getCliente();
		TReserva reserva = d.getReserva();
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		try {
			String res = infoCliente.altaReservaCliente(reserva);
			if(res == null) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_ALTA_CLIENTE).actualizar(Evento.ALTA_RESERVA_KO, "No se ha podido registrar la reserva");
			}
			else {
				String c = infoCliente.alta(cliente);
				if (c == null) {
					FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_ALTA_CLIENTE).actualizar(Evento.ALTA_CLIENTE_KO, cliente.getId());
				}
				else {
					reserva.setId(res);
					TDatosAltaCliente d2 = new TDatosAltaCliente(cliente, reserva);
					FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_ALTA_CLIENTE).actualizar(Evento.ALTA_CLIENTE_OK, d2);
					//FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_ALTA_CLIENTE).actualizar(Evento.ALTA_CLIENTE_OK, cliente.getId());
				}
			}
		}
		catch(IllegalArgumentException iae) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_ALTA_CLIENTE).actualizar(Evento.ALTA_RESERVA_KO, iae.getMessage());
		}
	
	}
	
	private void modificarCliente (Object datos) {
		TCliente cliente = (TCliente)datos;
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		boolean res = infoCliente.modificar(cliente);
		if(res) {
			FactoriaPresentacion.getInstace().createVista(Evento.VISTA_MODIFICAR_CLIENTE).actualizar(Evento.VISTA_MODIFICAR_CLIENTE_OK, cliente);
		}
		else {
			FactoriaPresentacion.getInstace().createVista(Evento.VISTA_MODIFICAR_CLIENTE).actualizar(Evento.VISTA_MODIFICAR_CLIENTE_KO, cliente);
		}
		
	}
	
	private void bajaCliente(Object datos) {
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		boolean c = infoCliente.borrar((String)datos);
		if(c) {
			FactoriaPresentacion.getInstace().createVista(Evento.VISTA_BAJA_CLIENTE).actualizar(Evento.BAJA_CLIENTE_OK, datos);
		}
		else {
			FactoriaPresentacion.getInstace().createVista(Evento.VISTA_BAJA_CLIENTE).actualizar(Evento.BAJA_CLIENTE_KO, datos);
		}
		SAMesas info = FactoriaAbstractaNegocio.getInstace().crearSAMesas();
		Collection<TReserva> reservas = info.consultaTodosR();
		for(TReserva reserva : reservas) {
			if(reserva.getIdCliente().equals((String)datos)){
				info.borrarR(reserva.getId());
			}
		}
	}
	
	private Collection<TCliente> listarClientes(){
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		Collection<TCliente> c = infoCliente.consultaTodos();
		return c;
	}
	private Collection<TIngrediente> listarIngredientes(){
		SAIngrediente ingrediente= FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		Collection<TIngrediente> c= ingrediente.consultaTodos();
		return c;
	}
	private Collection<TPlatoIngrediente> listarPlatoIngrediente(){
		SAIngrediente ingrediente=FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		Collection<TPlatoIngrediente> p=ingrediente.consultaTodosPlatoIngrediente();
		return p;
	}
}

