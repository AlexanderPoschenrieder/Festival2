package dds.grupo5.festival.modelo.festival

import dds.grupo5.festival.modelo.exceptions.BusinessException


class Noche {
	//como ambas colecciones son set me aseguro de que no se va a repetir ningun
	//elemento de ellas
	Set<Presentacion> presentaciones = []
	Set<Ubicacion> ubicaciones = []
	Date fecha
	def horaComienzo

	public Noche(Date fecha, hora) {
		this.fecha = fecha
		this.horaComienzo = hora
	}

	/*
	 * Devuelve la presentacion con mayor categoria de la noche
	 */
	def Presentacion presentacionDeMayorCategoria(){
		def Presentacion mayorCategoria

		if(presentaciones.empty)
			throw new BusinessException("La noche no cuenta con ninguna presentacion")

		mayorCategoria = presentaciones.first()

		for (Presentacion presentacion : presentaciones) {
			if(presentacion.categoria().numeroCategoria() > mayorCategoria.categoria().numeroCategoria())
				mayorCategoria = presentacion
		}

		mayorCategoria
	}
	
	def agregarPresentacion(Presentacion presentacion){
		presentaciones << presentacion
	}

	def precioExtra(){
		presentacionDeMayorCategoria().precioExtra()
	}

	/*
	 * Consulta a la ubicaciÃ³n estado de disponibilidad
	 */
	def ubicacionDisponible(Ubicacion ubicacion){
		if(!tePertenece(ubicacion))
			throw new BusinessException("La ubicacion no corresponde a esta noche")
		else
			ubicacion.disponible
	}

	def tePertenece(Ubicacion ubicacion){
		ubicacion in ubicaciones
	}

	//Validadores-------------------------------------------------------
	def validate(){
		validarHoraCargada()
		validarFechaCargada()
		validarUbicaciones()
		validarPresentaciones()
	}
	
	def validarHoraCargada(){
		if (horaComienzo.equals(null)){
			throw new BusinessException("No se encuentra la Hora de Comienzo de una Noche.")
		}
	}
	
	def validarFechaCargada(){
		if (fecha.equals(null)){
			throw new BusinessException("No se encuentra la Fecha de una Noche.")
		}
	}
	
	def validarUbicaciones(){
		if (ubicaciones.size()>0) {
			for (Ubicacion ubicacion : ubicaciones) {
				ubicacion.validate()
			}
		}else{
			throw new BusinessException("No se encontraron Ubicaciones cargadas en una Noche.")
		}
	}
	
	def validarPresentaciones(){
		if (presentaciones.size()>0) {
			for (Presentacion presentacion: presentaciones) {
				if (presentacion.horarioInicio<horaComienzo) {
					throw new BusinessException("La Hora de Comieno de una Presentacion es anterior al de su Noche.")
				}
				presentacion.validate()
			}
		}else{
			throw new BusinessException("No se encontraron Presentaciones cargadas en una Noche.")
		}
	}

}
