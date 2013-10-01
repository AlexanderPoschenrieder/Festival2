package dds.grupo5.festival.modelo.banda

import dds.grupo5.festival.modelo.exceptions.BusinessException

class Categoria {
	def precioCategoria
	def tipoCategoria

	public Categoria (tipo, precio){
		tipoCategoria = tipo
		precioCategoria = precio
	}

	def precio() {
		precioCategoria
	}

	def numeroCategoria() {
		tipoCategoria
	}
	
	def validate(){
		if (precioCategoria.equals(null)) {
			throw new BusinessException("Una Categoria no posee Precio.")
		}
	}
}
