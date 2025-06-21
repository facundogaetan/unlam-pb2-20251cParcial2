package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Banco {

	private TreeMap<String, Cliente> clientes = new TreeMap<>();
	private TreeMap<String, Cuenta> cuentas = new TreeMap<>();

	public void agregarCliente(Cliente cliente) throws ClienteDuplicadoException {
		// verificar que no este duplicado
		if (clientes.containsKey(cliente.getDni())) {
			throw new ClienteDuplicadoException("Cliente ya existente");
		}
		clientes.put(cliente.getDni(), cliente);

	}

	public void agregarCuenta(Cuenta cuenta) throws ClienteInexistenteException {
		if(cuenta == null) {
			return;
		}
		if (!clientes.containsKey(cuenta.getDni())) {
			throw new ClienteInexistenteException("Cliente no encontrado, DNI: " + cuenta.getDni());
		}
		cuentas.put(cuenta.getCbu(), cuenta);
	}

	public Cuenta getCuenta(String cbu) throws CuentaInexistenteException {
		Cuenta cuentaBuscada = cuentas.get(cbu);
		if (cuentaBuscada == null) {
			throw new CuentaInexistenteException("No existe cuenta con el cbu: " + cbu);
		}
		return cuentaBuscada;
	}

	public Collection<Cliente> getClientes() {
		return clientes.values();
	}

	private Collection<Cuenta> getCuentas() {
		return cuentas.values();
	}

	public Integer getCantidadClientes() {
		return clientes.size();
	}

	public Integer getCantidadCuentas() {
		return cuentas.size();
	}

}

//	public void agregarCuenta(Object setDni) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void agregarCuenta(Object setDni) {
//		// TODO Auto-generated method stub
//		
//	}
