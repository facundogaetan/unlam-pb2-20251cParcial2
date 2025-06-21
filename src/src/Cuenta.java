package src;

public abstract class Cuenta {
	private String cbu;
	private String dni;
	protected double saldo;

	public Cuenta(String cbu, String dni, double saldo) {
		this.cbu = cbu;
		this.dni = dni;
		this.saldo = saldo;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void extraer(Double montoAExtraer) throws SaldoInsuficienteException {

		if (montoAExtraer < saldo) {
			double nuevoSaldo = saldo - montoAExtraer;
			saldo = nuevoSaldo;

		}
	}

	@Override
	public String toString() {
		return "CBU: " + cbu + ", DNI:" + dni + ", Saldo: " + saldo;
	}
	
	

}
