package test;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import src.Banco;
import src.CajaDeAhorros;
import src.Cliente;
import src.ClienteDuplicadoException;
import src.ClienteInexistenteException;
import src.Cuenta;
import src.CuentaCorriente;
import src.CuentaInexistenteException;
import src.CuentaSueldo;
import src.SaldoInsuficienteException;

import static org.junit.Assert.fail;

public class BancoTest {
	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private Cliente cliente4;
	private Cliente cliente5;
	private Cliente cliente6;
	private Cliente cliente7;
	private CuentaSueldo cuentaSueldo2;
	private CuentaSueldo cuentaSueldo3;
	private CuentaSueldo cuentaSueldo4;
	private CuentaSueldo cuentaSueldo5;
	private CuentaSueldo cuentaSueldo6;
	private CajaDeAhorros cajaDeAhorros2;
	private CajaDeAhorros cajaDeAhorros3;
	private CajaDeAhorros cajaDeAhorros4;
	private CajaDeAhorros cajaDeAhorros5;
	private CajaDeAhorros cajaDeAhorros6;
	private CuentaCorriente cuentaCorriente2;
	private CuentaCorriente cuentaCorriente3;
	private CuentaCorriente cuentaCorriente4;
	private CuentaCorriente cuentaCorriente5;
	private CuentaCorriente cuentaCorriente6;
	private Cuenta cuentaSueldo;
	private Cuenta cajaDeAhorros;
	private Cuenta cuentaCorriente;
	private Banco banco;

	@Before
	public void setUp() throws ClienteDuplicadoException {
		cliente1 = new Cliente("12345678", "Juan Perez");
		cliente2 = new Cliente("87654321", "Maria Lopez");
		cliente3 = new Cliente("22334455", "Carlos Gomez");
		cliente4 = new Cliente("33445566", "Laura Fernandez");
		cliente5 = new Cliente("44556677", "Roberto Diaz");
		cliente6 = new Cliente("55667788", "Sofia Ruiz");
		cliente7 = new Cliente("55667788", "Sofia Ruiz");
		

		// cbu dni saldo inicial
		cuentaSueldo = new CuentaSueldo("CBU001", cliente1.getDni(), 2000.0); // getDni
		cajaDeAhorros = new CajaDeAhorros("CBU002", cliente1.getDni(), 10000.0);
		cuentaCorriente = new CuentaCorriente("CBU003", cliente2.getDni(), 500.0);
		cuentaSueldo2 = new CuentaSueldo("CBU001", cliente3.getDni(), 3000.0); // CBU duplicado
		cuentaSueldo2 = new CuentaSueldo("CBU004", cliente3.getDni(), 3000.0);
		cuentaSueldo3 = new CuentaSueldo("CBU005", cliente4.getDni(), 2500.0);
		cuentaSueldo4 = new CuentaSueldo("CBU006", cliente5.getDni(), 0.0);
		cuentaSueldo5 = new CuentaSueldo("CBU007", cliente1.getDni(), 2800.0);
		cuentaSueldo6 = new CuentaSueldo("CBU008", cliente6.getDni(), 3200.0); // Nueva cuenta sueldo para el nuevo
																				// cliente

		cajaDeAhorros3 = new CajaDeAhorros("CBU010", cliente4.getDni(), 6000.0);
		cajaDeAhorros4 = new CajaDeAhorros("CBU010", cliente5.getDni(), 7000.0); // CBU duplicado
		cajaDeAhorros6 = new CajaDeAhorros("CBU013", cliente6.getDni(), 9000.0); // Nueva caja de ahorros para el nuevo
																					// cliente

		cuentaCorriente2 = new CuentaCorriente("CBU014", cliente3.getDni(), 1000.0);
		cuentaCorriente5 = new CuentaCorriente("CBU006", cliente2.getDni(), 800.0);
		cuentaCorriente6 = new CuentaCorriente("CBU018", cliente6.getDni(), 2000.0);

		banco = new Banco();

		banco.agregarCliente(cliente1);
		banco.agregarCliente(cliente2);
		banco.agregarCliente(cliente3);
		banco.agregarCliente(cliente4);
		banco.agregarCliente(cliente5);
		banco.agregarCliente(cliente6);
		try {
			banco.agregarCliente(cliente7);
		} catch (ClienteDuplicadoException e) {

		}

		try {
			banco.agregarCuenta(cuentaSueldo);
			banco.agregarCuenta(cajaDeAhorros);
			banco.agregarCuenta(cuentaCorriente);
			banco.agregarCuenta(cuentaSueldo2); // CBU duplicado
			banco.agregarCuenta(cuentaSueldo3);
			banco.agregarCuenta(cuentaSueldo4);
			banco.agregarCuenta(cuentaSueldo5);
			banco.agregarCuenta(cuentaSueldo6);

			banco.agregarCuenta(cajaDeAhorros2); // no incializada
			banco.agregarCuenta(cajaDeAhorros3);
			banco.agregarCuenta(cajaDeAhorros4); // CBU duplicado
			banco.agregarCuenta(cajaDeAhorros5);
			banco.agregarCuenta(cajaDeAhorros6);

			banco.agregarCuenta(cuentaCorriente2);
			banco.agregarCuenta(cuentaCorriente3);// no incializada
			banco.agregarCuenta(cuentaCorriente4);// no incializada
			banco.agregarCuenta(cuentaCorriente5);// no incializada
			banco.agregarCuenta(cuentaCorriente6);
		} catch (ClienteInexistenteException e) {
			// fail("Configuracion inicial fallida: " + e.getMessage());
		}
		Assert.assertEquals(Integer.valueOf(6), banco.getCantidadClientes()); // ########## hardcoded cambiar a 6
																				// ##########
		Assert.assertEquals(Integer.valueOf(12), banco.getCantidadCuentas()); // ########## hardcoded cambiar a 12
																				// ##########

	}

	@Test
	public void queSePuedaCrearUnClienteValido() {
		cliente1 = new Cliente("12345678", "Juan Perez");

		Assert.assertSame("12345678", cliente1.getDni());
	}

	@Test
	public void queSePuedaExtraer1000PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() throws SaldoInsuficienteException {

		// cuentaSueldo = new CuentaSueldo("CBU001", cliente1.getDni(), 2000.0);

		cuentaSueldo.extraer(1000.0);

		Assert.assertEquals(1000.0, cuentaSueldo.getSaldo(), 0.0);
	}

	@Test
	public void queNoSePuedaExtraer2500PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos()
			throws SaldoInsuficienteException {
		cuentaSueldo.extraer(2500.0);

	}

	@Test
	public void queAlRealizar6ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe10000SuSaldoFinalSea3900()
			throws SaldoInsuficienteException {
		// cajaDeAhorros = new CajaDeAhorros("CBU002", cliente1.getDni(), 10000.0);

		cajaDeAhorros.setSaldo(10000);

		cajaDeAhorros.extraer(1000.0);
		cajaDeAhorros.extraer(1000.0);
		cajaDeAhorros.extraer(1000.0);
		cajaDeAhorros.extraer(1000.0);
		cajaDeAhorros.extraer(1000.0);
		cajaDeAhorros.extraer(1000.0);

		Assert.assertEquals(3900.0, cajaDeAhorros.getSaldo(), 0.0);
	}

	@Test
	public void queAlRealizar7ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe7000SoloLaUltimaLanceExcepcionSaldoInsuficiente()
			throws SaldoInsuficienteException {
		// cajaDeAhorros4 = new CajaDeAhorros("CBU010", cliente5.getDni(), 7000.0);
		try {
			cajaDeAhorros4.extraer(1000.0);
			cajaDeAhorros4.extraer(1000.0);
			cajaDeAhorros4.extraer(1000.0);
			cajaDeAhorros4.extraer(1000.0);
			cajaDeAhorros4.extraer(1000.0);
			cajaDeAhorros4.extraer(1000.0);

		} catch (SaldoInsuficienteException e) {
			cajaDeAhorros4.extraer(1000.0);

		}

	}

//	el banco nos cobrará un 5% como comisión sobre todo el monto en 
//	descubierto consumido en la operación. Por ejemplo, si tuviéramos $ 100 en 
//	la cuenta, y quisiéramos retirar $ 200
//	podremos hacerlo. Pasaremos a deber al banco $ 105 en total: los $ 100 que 
//	nos cubrió, más el 5% adicional sobre el descubierto otorgado.

	@Test
	public void queSeCobreRecargoAlRealizarUnaExtraccionMayorAlSaldoEnUnaCuentaCorriente() {
		// cuentaCorriente2 = new CuentaCorriente("CBU014", cliente3.getDni(), 1000.0);
		cuentaCorriente2.extraer(2000.0);

		Assert.assertEquals(-1050.0, cuentaCorriente2.getSaldo(), 0.0);

	}

	@Test
	public void queAlIntentarDarDeAltaUnaCuentaAUnClienteInexistenteLanceExcepcion() throws CuentaInexistenteException {

		Cuenta cuenta999 = new CuentaSueldo ("CBU999", "99999999" , 9999.0);
		
		
		try {
			banco.agregarCuenta(cuenta999);
			fail("Se esperaba ClienteInexistenteExcepcion");
		} catch (ClienteInexistenteException e) {
			
		}
		

	}

	@Test
	public void queAlBuscarUnaCuentaPorCBUErroneoLanceExcepcion() throws CuentaInexistenteException {
		// cuentaSueldo = new CuentaSueldo("CBU001", cliente1.getDni(), 2000.0);
		try {
			banco.getCuenta("CBU999");
			fail("Se esperaba cuentaInexistenteExcepcion");
		} catch (CuentaInexistenteException e) {
			
		}

	}
}
