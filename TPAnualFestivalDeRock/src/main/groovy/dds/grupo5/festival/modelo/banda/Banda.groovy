package dds.grupo5.festival.modelo.banda

import dds.grupo5.festival.modelo.exceptions.BusinessException


class Banda {
	def nombre
	Categoria categoria

	public Banda(String nombre, Categoria categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}

	def precio(){
		this.categoria.precio()
	}

	def validate(){
		validarNombreCargado()
		validarCategoria()
	}

	def validarNombreCargado(){
		if (nombre.equals(null)){
			throw new BusinessException("No se encuentra el Nombre de una Banda.")
		}
	}

	def validarCategoria(){
		if (categoria.equals(null)) {
			throw new BusinessException("Una Banda no se encuentra Categorizada.")
		} else {
			categoria.validate()
		}
	}
}
