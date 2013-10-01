package dds.grupo5.festival.modelo.cliente

import dds.grupo5.festival.modelo.exceptions.ValueOutOfRangeException


class Cliente {

	def nombre
	def apellido
	Integer edad
	TipoPersona tipoPersona
	
	def Cliente(nombre, apellido, edad){
		this.nombre = nombre
		this.apellido = apellido
		this.edad = edad
		this.setearTipoPersona() //TODO:se tira exception en el constructor :S 
	}
	
	def setearTipoPersona(){
		if(this.edad > 100 || this.edad < 1){
			throw new ValueOutOfRangeException("Edad erronea")
			
		} else if(this.edad >=1 && this.edad < 18){
			this.tipoPersona = new TipoMenor()
			
		} else if(this.edad >=18 && this.edad < 65){
			this.tipoPersona = new TipoMayor()
		
		} else{
			this.tipoPersona = new TipoJubilado()
		}
	}	
	
}
