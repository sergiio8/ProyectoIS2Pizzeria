package negocio.ingredientes;

public class TModificacionIngrediente {
	private String nombreAntiguo;
	private TIngrediente ingrediente;
	public TModificacionIngrediente (String nombre,TIngrediente i) {
		nombreAntiguo=nombre;
		ingrediente=i;
	}
	public String getNombreAntiguo() {
		return nombreAntiguo;
	}
	public TIngrediente getIngrediente() {
		return this.ingrediente;
	}
}
