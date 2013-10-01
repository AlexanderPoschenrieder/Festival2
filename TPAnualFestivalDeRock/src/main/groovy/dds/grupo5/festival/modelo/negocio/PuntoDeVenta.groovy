package dds.grupo5.festival.modelo.negocio

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.exceptions.BusinessException
import dds.grupo5.festival.modelo.festival.Festival



class PuntoDeVenta {
	Integer numero
	Integer puesto
	Festival festival
	Administracion administracion

	public PuntoDeVenta(numero, puesto, Festival festival, Administracion administracion) {
		this.numero = numero;
		this.puesto = puesto;
		this.festival = festival;
		this.administracion = administracion;
	}
	
	def registrarVenta(Cliente cliente, List<Entrada> entradas){
		try{
			festival.validarEntradas(entradas)
		}catch(BusinessException ex){
			throw new RuntimeException(ex.message)		
		}
		
		this.administracion.registrarVenta(cliente, entradas, this)		
	}
		
		
}
