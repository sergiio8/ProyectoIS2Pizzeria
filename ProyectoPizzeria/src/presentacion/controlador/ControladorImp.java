package presentacion.controlador;

import negocio.clientes.SAClientes;
import negocio.clientes.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.mesas.SAMesas;
import negocio.mesas.TMesas;
import negocio.producto.SAPlato;
import negocio.producto.TPlato;
import negocio.facturas.Carrito;
import negocio.facturas.SAFactura;
import negocio.facturas.TDatosVenta;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;
import negocio.ingredientes.SAIngrediente;
import negocio.ingredientes.TIngrediente;
import presentacion.Evento;
import presentacion.factoria.FactoriaAbstractaPresentacion;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;

public class ControladorImp extends Controlador { //implementacion
	private Carrito carrito;

	@Override
	public void accion(Evento e, Object datos) {
		switch(e) {
		case MAIN_WINDOW:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MAIN_WINDOW);
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
        case ALTA_FACTURA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA, null);
			break;
		case MODIFICAR_FACTURA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_FACTURA_VISTA).actualizar(Evento.MODIFICAR_FACTURA_VISTA, null);
			break;
		case BUSCAR_FACTURA_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA, null);
			break;
		case ANADIR_PRODUCTO_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA, null);
			break;
		case LISTAR_FACTURAS_VISTA:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.LISTAR_FACTURAS_VISTA).actualizar(Evento.LISTAR_FACTURAS_VISTA, null);
			break;
		case ALTA_FACTURA:
			altaFactura(datos);
            break;
		case BUSCAR_FACTURA:
			buscarFactura(datos);
			break;
		case MODIFICAR_FACTURA:
			modificarFactura(datos);
			break;
		case LISTAR_FACTURAS:
			listarFacturas(datos);
			break;
		case ANADIR_PRODUCTO:
			anadirProducto(datos);
			break;
		case ABRIR_VENTA:
			abrirVenta(datos);
			break;
		case BUSCA_CLIENTE:
			buscarCliente(datos);
			break;
			
		case VISTA_REGISTRO_DE_CLIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_REGISTRO_DE_CLIENTE).actualizar(Evento.VISTA_REGISTRO_DE_CLIENTE, datos);
			break;
		case REGISTRO_DE_CLIENTE:
			registroCliente(datos);
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
		case ALTA_INGREDIENTE_VISTA:
            FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_INGREDIENTE_VISTA, null);
            break;
        case ALTA_INGREDIENTE:
            altaIngrediente(datos);
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
	}
}
	private void altaIngrediente(Object datos) {
		TIngrediente ingrediente= (TIngrediente) datos;
		SAIngrediente saIngrediente= FactoriaAbstractaNegocio.getInstace().crearSAIngrediente();
		String nombre= saIngrediente.crear(ingrediente);
		if(nombre.equals(null)) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_MESA_KO, nombre);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_INGREDIENTE_VISTA).actualizar(Evento.ALTA_MESA_OK, nombre);
		}
	}
	private void bajaIngrediente(Object datos) {	
	}
	private void modificarIngrediente(Object datos) {	
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
		boolean res2 = saMesas2.borrar(id);
		FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_MESA_VISTA).actualizar(Evento.BAJA_MESA_RES, res2);
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
		TPlato tp = (TPlato) datos;
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		
		String nombre = saPlato.alta(tp);
		
		if(nombre == "") {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_PLATO_VISTA).actualizar(Evento.ALTA_PLATO_KO, nombre);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_PLATO_VISTA).actualizar(Evento.ALTA_PLATO_OK, nombre);
		}
	}
	
	private void bajaPlato(Object datos) {
		String id = datos.toString();
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		boolean resultado = saPlato.borrar(id);
		if (resultado == true) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_PLATO_VISTA).actualizar(Evento.BAJA_PLATO_OK, resultado);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BAJA_PLATO_VISTA).actualizar(Evento.BAJA_PLATO_KO, resultado);
		}
	}

	private void modificaPlato(Object datos) {
		TPlato tp = (TPlato) datos;
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		
		boolean resultado = saPlato.modificar(tp);
		
		if(resultado == true) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_PLATO_VISTA).actualizar(Evento.MODIFICAR_PLATO_OK, resultado);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_PLATO_VISTA).actualizar(Evento.MODIFICAR_PLATO_KO, resultado);
		}
	}
	
	private void buscaPlato(Object datos) {
		String id = datos.toString();
		SAPlato saPlato = FactoriaAbstractaNegocio.getInstace().crearSAPlato();
		
		TPlato buscar = saPlato.consulta(id);
		
		if(buscar == null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_PLATO_VISTA).actualizar(Evento.BUSCAR_PLATO_KO, buscar);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_PLATO_VISTA).actualizar(Evento.BUSCAR_PLATO_OK, buscar);
		}
	}
	
	private void altaFactura(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		TDatosVenta dt = (TDatosVenta) datos;
		carrito.cerrarVenta(dt);
        boolean sol = saFact.crearFactura(dt);
        if (sol) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_OK, dt.getid_factura());
			
	    }
        else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_WR, dt.getProductos().size());
	}
	
	private void buscarFactura(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		TFactura tf = saFact.buscarFactura((String) datos);
		if (tf != null) FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA_OK, (String) datos);
		else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA_WR, tf);
	}
	
	private void modificarFactura(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		TLineaFactura linea = (TLineaFactura) datos;
		boolean sol3 = saFact.modificarFactura(linea);
		if (sol3) FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_FACTURA_VISTA).actualizar(Evento.MODIFICAR_FACTURA_VISTA_OK, linea.getIdFactura());
		else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_FACTURA_VISTA).actualizar(Evento.MODIFICAR_FACTURA_VISTA_WR, sol3);
	}
	
	private Collection<TFactura> listarFacturas(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		Collection<TFactura> facturas = saFact.mostrarFacturas();
		return facturas;
	}
	
	private void anadirProducto(Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		TLineaFactura tf2 = (TLineaFactura) datos;
		if (tf2 != null) {
			saFact.anadirProducto(tf2, carrito);
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA_OK, tf2.getIdProducto());
		}
		else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA_WR, tf2.getIdProducto());
	}
	
	private void abrirVenta(Object datos) {
		carrito = new Carrito();
	}
	
	private void buscarCliente(Object datos) {
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		TCliente c = infoCliente.consulta((String)datos);
		if (c == null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento. VISTA_PRINCIPAL_CLIENTES).actualizar(Evento.CLIENTE_NO_REGISTRADO, datos);
		}
		else {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_CLIENTE_REGISTRADO).actualizar(Evento.CLIENTE_LOGUEADO, datos);
			
		}
	}
	
	private void registroCliente(Object datos) {
		TCliente cliente = (TCliente)datos;
		SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
		if(infoCliente.consulta(cliente.getId()) != null) {
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_REGISTRO_DE_CLIENTE).actualizar(Evento.CLIENTE_YA_REGISTRADO, cliente.getId());
		}
		else {
			infoCliente.alta(cliente);
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_REGISTRO_DE_CLIENTE).actualizar(Evento.CLIENTE_REGISTRADO, cliente.getId());
		}
	}
	
		
}

