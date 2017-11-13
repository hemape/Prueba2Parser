import java.util.ArrayList;

public class Accion {
	private String nombreaccion;
	private ArrayList<String> operacion;
	
	public Accion(String nombreaccion, ArrayList<String> operate) {
		this.nombreaccion=nombreaccion;
		operacion=operate;
	}
	
	public String getNombreaccion() {
		return nombreaccion;
	}

	public void setNombreaccion(String nombreaccion) {
		this.nombreaccion = nombreaccion;
	}

	public ArrayList<String> getOperacion() {
		return operacion;
	}

	public void setOperacion(ArrayList<String> operacion) {
		this.operacion = operacion;
	}

	
	public void print() {
		
		System.out.println("Nombre de la accion: "+nombreaccion);
		System.out.println("Operaciones: ");
		for(int i=0;i<operacion.size();i++)
			System.out.println(operacion.get(i));
		
	}
	
	


}
