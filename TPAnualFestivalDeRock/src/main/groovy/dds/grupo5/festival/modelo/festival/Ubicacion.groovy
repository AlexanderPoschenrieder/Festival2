package dds.grupo5.festival.modelo.festival

import dds.grupo5.festival.modelo.exceptions.BusinessException


class Ubicacion {
	
	def sector
	int fila
	int butaca
	def precio
	Boolean disponible
	
		
	public Ubicacion(def sector, int fila, int butaca,float precio, Boolean diponibilidad) {
		this.sector = sector;
		this.fila = fila;
		this.butaca = butaca;
		this.precio = precio
		this.disponible = diponibilidad;
	}

	def actualizarEstadoDeDisponibilidad(Boolean estado){
		this.disponible = estado
	}			
	
	@Override
	String toString() {
		"Sector:${sector}, Fila:${fila}, Butaca:${butaca}"	
	}
	
	def validate(){
		if (precio.equals(null)){
			throw new BusinessException("No se encuentra el precio de una Butaca.")

		}
	}
}
