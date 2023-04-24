package negocio.mesas;

public class TMesas { //transfer
	
	private int id;
	private Localizacion localizacion;
	
	public TMesas(int id, String localizacion) {
		this.id = id;
		if("interior".equals(localizacion)) {
			this.localizacion = Localizacion.INTERIOR;
		}
		else {
			this.localizacion = Localizacion.EXTERIOR;
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Localizacion getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	
	
}
