package dds.grupo5.festival.modelo.festival

import java.util.Date;

import dds.grupo5.festival.modelo.exceptions.BusinessException;
import dds.grupo5.festival.modelo.negocio.Entrada;


class Festival {

	Set<Noche> noches = []//las noches no pueden repetirse
	Date fechaInicio

	public Festival(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	//La entrada contiene las referencias a los objetos noche y ubicacion
	//que el cliente desea comprar y que han sido instanciados en el momento
	//en que se realiza la consulta o selecciÃ³n de las mismas por el operador
	def ubicacionesDisponibles(List<Ubicacion> ubicaciones) throws BusinessException{
		if(ubicaciones.empty) {
			throw new BusinessException("La lista de ubicaciones esta vacia")
		}

		if(ubicaciones.any{ !it.disponible }){
			throw new BusinessException("Hubo un error al cargar las entradas, una de las ubicaciones ya se encontraba ocupada")
		}
	}

	def tePertenece(Noche noche){
		noche in noches
	}

	def validarEntradas(List<Entrada> entradas){
		if(entradas.isEmpty())
			throw new BusinessException("Peticion de compra vacia: Ninguna entrada fue seleccionada")

		entradas.each{it.validate(this)}
	}

	

	def validate(){
		validarFechaCargada()
		validarNoches()
	}

	def validarFechaCargada(){
		if (fechaInicio.equals(null)){
			throw new BusinessException("No se encuentra Fecha de Inicio del Festival.")

		}
	}
	
	def validarNoches(){
		if (noches.size()>0) {
			for (Noche noche : noches) {
				if (noche.fecha<fechaInicio) {
					throw new BusinessException("Un a Noche tiene una fecha anterior al del Festival.")
				}
				noche.validate()
			}
		} else {
			throw new BusinessException("No hay noches cargadas.")
		}
	}

}
