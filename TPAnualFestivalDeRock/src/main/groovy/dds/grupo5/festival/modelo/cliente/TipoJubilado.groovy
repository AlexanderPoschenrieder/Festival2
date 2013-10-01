package dds.grupo5.festival.modelo.cliente

class TipoJubilado extends TipoPersona{
	@Override
	def descuento(precioBase) {
		return 0.15 * precioBase
	}
}
