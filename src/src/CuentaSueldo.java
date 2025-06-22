package src;

public class CuentaSueldo extends Cuenta{

	public CuentaSueldo(String cbu, String dni, double saldo) {
		super(cbu, dni, saldo);
		// TODO Auto-generated constructor stub
	}
	//
	//cbu dni saldo inicial
	
	@Override
	public void extraer(Double montoAExtraer) throws SaldoInsuficienteException  {
		if (montoAExtraer > this.getSaldo()) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.setSaldo(this.getSaldo()- montoAExtraer);
		
	}

	

}
