package dds.grupo5.festival.modelo.negocio

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.exceptions.BusinessException
import dds.grupo5.festival.modelo.festival.Festival
import dds.grupo5.festival.modelo.festival.Noche
import dds.grupo5.festival.modelo.festival.Ubicacion

class EntradaComun extends Entrada{
	def precioEntrada
	Noche noche
	Ubicacion ubicacion
	def nroFactura
	def Festival festival
	def Cliente cliente
	
	public EntradaComun(Ubicacion ubicacion, Noche noche,Festival festival,Cliente cliente) {
		this.noche = noche
		this.ubicacion = ubicacion
		this.festival = festival
		this.cliente= cliente
	}
	
	
	Boolean ubicacionDisponible(){
		ubicacion.disponible
	}
	
	def diasRestantesParaElRecital(){
		noche.fecha.minus(new Date())
	}
	
	def fuisteVendida(){
		if(!ubicacion.disponible)
			throw new BusinessException("La ubicacion ${ubicacion.toString()} ya habia sido vendida con anterioridad")
		
		ubicacion.actualizarEstadoDeDisponibilidad(false)
	}
	
	def ubicacion(){
		ubicacion
	}
	
	def noche(){
		noche
	}
	
	def festival(){
		festival
	}
	def cliente(){
		cliente
	}
	
	def descuentoCliente(){
		cliente.tipoPersona.descuento(this.ubicacion.precio)
	}
	
	def calcularPrecio(){
		def precioBase = this.ubicacion.precio
		
		precioEntrada = precioBase + noche.precioExtra() - descuentoCliente()
	}	
	
	def nroFactura(){
		nroFactura
	}
	
	def setNroFactura(unNumero){
		nroFactura = unNumero
	}
	def liberarUbicacion(){
		ubicacion.actualizarEstadoDeDisponibilidad(true)
	}
	

	def validate(Festival festival){
		//ninguno debe ser null
		if(noche.equals(null))
			throw new BusinessException("No se especifico para que noche se solicita la ubicacion")

		if(ubicacion.equals(null))
			throw new BusinessException("No se especifico la ubicacion que se desea comprar")

		//la noche pertenece al festival?
		if(!festival.tePertenece(noche))
			throw new BusinessException("La noche ${noche} no pertenece al festival ${this}")

		//La ubicacion pertence a la noche?
		if(!noche.tePertenece(ubicacion))
			throw new BusinessException("La ubicacion ${ubicacion.toString()} no pertenece a la noche ${noche}")

		//La ubicacion esta disponible?
		if(!ubicacion.disponible)
			throw new BusinessException("La ubicacion ${ubicacion.toString()} no se encuentra disponible")
	}
	
}
