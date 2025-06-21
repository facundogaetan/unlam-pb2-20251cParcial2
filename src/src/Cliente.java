package src;

public class Cliente {

	private String nombreCliente;
	private String dni;

	public Cliente(String dni, String nombreCliente) {
		this.dni = dni;
		this.nombreCliente = nombreCliente; 
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	
	
	

}
