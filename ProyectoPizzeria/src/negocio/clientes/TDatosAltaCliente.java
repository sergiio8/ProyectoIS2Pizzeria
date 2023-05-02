package negocio.clientes;

import negocio.mesas.TReserva;

public class TDatosAltaCliente {

	
	private TCliente cliente;
	private TReserva reserva;
	
	public TDatosAltaCliente(TCliente cliente, TReserva reserva) {
		this.cliente = cliente;
		this.reserva = reserva;
	}
	
	public TReserva getReserva() {
		return this.reserva;
	}
	
	public TCliente getCliente() {
		return this.cliente;
	}
	
}
