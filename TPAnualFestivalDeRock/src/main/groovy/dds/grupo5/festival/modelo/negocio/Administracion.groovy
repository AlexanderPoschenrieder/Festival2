package dds.grupo5.festival.modelo.negocio

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.interfacesExternas.ImpresoraFiscal
import dds.grupo5.festival.modelo.festival.Festival
import dds.grupo5.festival.modelo.exceptions.BusinessException



class Administracion {

	def static int nrofactura = 0
	List<Entrada> ventas  = []
	Boolean ventaAnticipadaHabilitada =  false
	def descuento //descuento que se aplicarÃ¡ a las entradas anticipadas (expresado en %)
	def periodoVentasAnticipadas // indica cuantos dias antes de la presentacion se puede
								     // comprar una entrada anticipada	(con descuento)
	ImpresoraFiscal impresoraFiscal
	Set<Festival> festivales
 		
	def registrarVenta(List<Entrada> entradas, PuntoDeVenta puntoVenta){
		//En este punto se decoran las que les corresponda el descuento por venta anticipada 
					
		entradas.each { it.fuisteVendida() }
		entradas.each{ this.generarNroFactura(it)}
		ventas.addAll(entradas)
		entradas.each{impresoraFiscal.imprimirEntrada(it)}
		
	}
	
	def tePertenece(Festival festival){
		if(!festivales.contains(festival)) 
		 throw new BusinessException("El festival no esta incluido en la Administracion")
	}
	/*
	 *	Si las entradas estan en periodo de descuento por anticipadas, las decora como una
	 *	EntradaAnticipada 
	 */
	
	def generarNroFactura(Entrada entrada){
		entrada.setNroFactura(nrofactura++)
	}
		 
	def Entrada dameEntrada(ubicacion, noche, Festival festival,Cliente cliente){
		def Entrada entrada
		def entradaComun = new EntradaComun(ubicacion, noche,festival,cliente)
		if (ventaAnticipadaHabilitada &
			(entradaComun.diasRestantesParaElRecital() >= periodoVentasAnticipadas )) {
			entrada = new EntradaAnticipada(entradaComun, descuento)
		} else {
			entrada = entradaComun
		}
		tePertenece(festival)
		entrada.validate(festival)
		entrada
	}
	
	def cancelarEntrada(nroFactura){
		def Entrada entradaAux=ventas.find{it-> it.nroFactura==nroFactura}
		entradaAux.liberarUbicacion()
		ventas.remove(entradaAux)
		
	}
}