package dds.grupo5.festival.modelo.festival

import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.exceptions.BusinessException


class Presentacion {

	def horarioInicio
	Banda banda


	public Presentacion(Object horarioInicio, Banda banda) {
		this.horarioInicio = horarioInicio;
		this.banda = banda;
	}

	def precioExtra(){
		banda.precio()
	}

	def Categoria categoria(){
		banda.categoria
	}

	def validate(){
		validarHorarioCargado()
		validarBanda()
	}

	def validarHorarioCargado(){
		if (horarioInicio.equals(null)){
			throw new BusinessException("No se encuentra la Hora de Comienzo de una Presentacion.")
		}
	}

	def validarBanda(){
		if (banda.equals(null)){
			throw new BusinessException("No se encuentra la Hora de Comienzo de una Presentacion.")
		} else {
			banda.validate()
		}
	}
}