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
import presentacion.Evento;
import presentacion.factoria.FactoriaAbstractaPresentacion;

import java.util.Iterator;

import org.json.JSONArray;

public class ControladorImp extends Controlador { //implementacion
	private Carrito carrito;

	@Override
	public void accion(Evento e, Object datos) {
		SAFactura saFact = FactoriaAbstractaNegocio.getInstace().crearSAFactura();
		switch(e) {
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
			TDatosVenta dt = (TDatosVenta) datos;
			carrito.cerrarVenta(dt);
            boolean sol = saFact.crearFactura(dt);
            if (sol) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_OK, sol);
			}
		    else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ALTA_FACTURA_VISTA).actualizar(Evento.ALTA_FACTURA_VISTA_WR, sol);
            break;
		case BUSCAR_FACTURA:
			TFactura tf = saFact.buscarFactura((String) datos);
			if (tf != null) FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA_OK, tf);
			else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.BUSCAR_FACTURA_VISTA).actualizar(Evento.BUSCAR_FACTURA_VISTA_WR, tf);
			break;
		case MODIFICAR_FACTURA:
			boolean sol3 = saFact.modificarFactura((TLineaFactura) datos);
			if (sol3) FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_FACTURA_VISTA).actualizar(Evento.MODIFICAR_FACTURA_VISTA_OK, sol3);
			else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.MODIFICAR_FACTURA_VISTA).actualizar(Evento.MODIFICAR_FACTURA_VISTA_WR, sol3);
			break;
		case LISTAR_FACTURAS:
			saFact.mostrarFacturas();
			break;
		case ANADIR_PRODUCTO:
			TLineaFactura tf2 = (TLineaFactura) datos;
			if (tf2 != null) {
				saFact.anadirProducto(tf2, carrito);
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA_OK, null);
			}
			else FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ANADIR_PRODUCTO_VISTA).actualizar(Evento.ANADIR_PRODUCTO_VISTA_WR, null);
			break;
		case ABRIR_VENTA:
			carrito = new Carrito();
			break;
		case BUSCA_CLIENTE:
			SAClientes infoCliente = FactoriaAbstractaNegocio.getInstace().crearSAClientes();
			TCliente c = infoCliente.consulta((String)datos);
			if (c == null) {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.CLIENTE_NO_REGISTRADO).actualizar(Evento.CLIENTE_NO_REGISTRADO, datos);
			}
			else {
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.CLIENTE_REGISTRADO).actualizar(Evento.CLIENTE_REGISTRADO, datos);
				FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ACTUALIZAR_VISTA_CLIENTES).actualizar(Evento.ACTUALIZAR_VISTA_CLIENTES, datos);
				
			}
			break;
			
		case VISTA_REGISTRO_DE_CLIENTE:
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.VISTA_REGISTRO_DE_CLIENTE).actualizar(Evento.ACTUALIZAR_VISTA_CLIENTES, datos);
			FactoriaAbstractaPresentacion.getInstace().createVista(Evento.ACTUALIZAR_VISTA_CLIENTES).actualizar(Evento.ACTUALIZAR_VISTA_CLIENTES, datos);
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
}

