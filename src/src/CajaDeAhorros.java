package src;

public class CajaDeAhorros extends Cuenta {

	Integer contadorExtracciones = 0;

	public CajaDeAhorros(String cbu, String dni, double saldo) {
		super(cbu, dni, saldo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void extraer(Double montoAExtraer) throws SaldoInsuficienteException {
	    double costo = montoAExtraer;

	    if (contadorExtracciones >= 5) {
	        costo += 100.0;
	    }

	    if (costo <= saldo) {
	        saldo -= costo;
	        contadorExtracciones++;
	    } else {
	        throw new SaldoInsuficienteException("saldo insuficiente");
	    }
	}


}
