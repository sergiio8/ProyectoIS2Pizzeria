package negocio.mesas;

import java.util.Date;

public class TReserva {
	private int id;
	private int idMesa;
	private String idCliente;
	private Date fecha;
	
	public TReserva(int idMesa, String idCliente,Date fecha, int id) {
		this.idCliente = idCliente;
		this.idMesa = idMesa;
		this.fecha = fecha;
		this.id = id;
	}
	
	public TReserva(int idMesa, String idCliente,Date fecha) {
		this.idCliente = idCliente;
		this.idMesa = idMesa;
		this.fecha = fecha;
	}
	
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
