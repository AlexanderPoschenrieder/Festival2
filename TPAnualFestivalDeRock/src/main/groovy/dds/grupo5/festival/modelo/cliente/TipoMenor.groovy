package dds.grupo5.festival.modelo.cliente

class TipoMenor extends TipoPersona{
	@Override
	def descuento(precioBase){
		if (precioBase > 50 && precioBase <= 100){
			return 10
		} else 	if(precioBase > 100){
			return precioBase * 0.20
		}
	}
	
}
