package dds.grupo5.festival.modelo.negocio

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.festival.Festival

/*
 * Clase Decoradoradora de Entrada para la venta anticipada
 */
class EntradaAnticipada extends Entrada {
	Entrada entrada
	float descuento

	//metodos que redireccionan solamente----------------------------------
	
	Boolean ubicacionDisponible(){
		entrada.ubicacionDisponible()
		}
	
	def diasRestantesParaElRecital(){
		entrada.diasRestantesParaElRecital()
	}

	def validate(Festival festival){
		entrada.validate(festival)
	}
	
	def ubicacion(){
		entrada.ubicacion()
	}
	
	def noche(){
		entrada.noche()
	}
	def festival(){
		entrada.festival()
	}
	
	def cliente(){
		entrada.cliente()
	}
	
	def fuisteVendida(){
		entrada.fuisteVendida()
	}
	def nroFactura(){
		entrada.nroFactura()
	}
	def setNroFactura(unNumero){
		entrada.setNroFactura(unNumero)
	}
	
	def liberarUbicacion(){
		entrada.liberarUbicacion()
	}
	def descuentoCliente(){
		entrada.descuentoCliente()
	}
	
	//---------------------------------------------------------------------
	
	public EntradaAnticipada(Entrada entrada, descuento) {
		this.entrada = entrada;
		this.descuento = descuento;
	}

	
	@Override
	def calcularPrecio(){
		def precio = entrada.calcularPrecio()
		precio - calcularDescuento(precio)
	}
	
	def calcularDescuento(precio){
		precio * descuento/100
	}
}
