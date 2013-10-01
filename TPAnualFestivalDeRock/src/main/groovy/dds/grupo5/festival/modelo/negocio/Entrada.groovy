package dds.grupo5.festival.modelo.negocio

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.festival.Noche
import dds.grupo5.festival.modelo.festival.Ubicacion


abstract class Entrada {
	def fuisteVendida(){}
	def nroFactura(){}
	def setNroFactura(){}
	def calcularPrecio(){}
	def validate(Festival){}
	def ubicacionDisponible(){}
	def diasRestantesParaElRecital(){}
	def impresoraFiscal(){}
	def liberarUbicacion(){}
	def descuentoCliente(){}
}