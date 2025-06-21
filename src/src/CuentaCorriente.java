package src;

public class CuentaCorriente extends Cuenta{

	public CuentaCorriente(String cbu, String dni, double saldo) {
		super(cbu, dni, saldo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void extraer(Double montoAExtraer) {
		if (montoAExtraer > saldo) {
			double montoExcedente = montoAExtraer - saldo; //2000 - 1000 = 1000
			double comisionDelBanco = montoExcedente * 0.05; // 1000 * 0.05 = 50
			double montoAExtraerConComisionDelBanco = comisionDelBanco + montoAExtraer; // 50 + 2000 = 2050
			saldo = saldo - montoAExtraerConComisionDelBanco; // 1000 - 2050 = - 1050
			
		}
	}
	

}
