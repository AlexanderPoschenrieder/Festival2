package dds.grupo5.festival.homes

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.ApplicationContext

import dds.grupo5.festival.modelo.festival.Festival
import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.festival.Presentacion
import dds.grupo5.festival.modelo.festival.Ubicacion
import dds.grupo5.festival.modelo.festival.Noche

class HomeFestivales extends CollectionBasedHome {

	HomeFestivales(){
		init()
	}
	
	def init(){
		// Instancio objetos a utilizar para crear los objetos del Home
		fecha1 = new Date(2013,01,02)
		fecha2 = new Date(2013,01,03)
		fecha3 = new Date(2013,01,04)
		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		categoria3 = new Categoria(3, 100)
		categoria4 = new Categoria(4, 200)
		banda1 = new Banda("Babasonicos",categoria4)
		banda2 = new Banda("Catupecu",categoria3)
		banda3 = new Banda("Ella es tan cargosa",categoria2)
		banda4 = new Banda("Zagala Rock",categoria1)
		banda5 = new Banda("Tan bionica",categoria3)
		banda6 = new Banda("La portuaria",categoria2)
		banda7 = new Banda("Fabiana Cantilo",categoria1)
		banda8 = new Banda("Iron Maiden",categoria4)
		banda9 = new Banda("Bon Jovi",categoria3)
		ubicacion1 = new Ubicacion('A',1,1,500,true)
		ubicacion2 = new Ubicacion('A',1,2,300,true)
		ubicacion3 = new Ubicacion('B',2,1,200,true)
		ubicacion4 = new Ubicacion('B',2,2,150,true)
		ubicacion5 = new Ubicacion('C',1,1,75,true)
		ubicacion6 = new Ubicacion('C',2,2,50,true)
		noche1 = new Noche(fecha1,21)
		noche1.agregarPresentacion(new Presentacion(21, banda1))
		noche1.agregarPresentacion(new Presentacion(22, banda2))
		noche2 = new Noche(fecha2,20)
		noche1.agregarPresentacion(new Presentacion(20, banda3))
		noche1.agregarPresentacion(new Presentacion(21, banda4))
		noche3 = new Noche(fecha2,21)
		noche3.agregarPresentacion(new Presentacion(21, banda5))
		noche3.agregarPresentacion(new Presentacion(22, banda6))
		noche4 = new Noche(fecha3,20)
		noche4.agregarPresentacion(new Presentacion(20, banda7))
		noche4.agregarPresentacion(new Presentacion(21, banda8))
		noche4.agregarPresentacion(new Presentacion(22, banda9))
		
		noche1 = [ubicacion1, ubicacion2]
		noche2 = [ubicacion3]
		noche3 = [ubicacion4, ubicacion5]
		noche4 = [ubicacion6]
		
		this.create(new Festival(fecha1).noches = [noche1 , noche2])
		this.create(new Festival(fecha2).noches = [noche3 , noche4])	
	}
	
	def getNoche(nocheDescription){
		ApplicationContext.instance.getSingleton(Noche.class).get(nocheDescription)
	}
	
	public void validateFestival(Festival festival){
		festival.validate()
	}
	
	@Override
	public Class<Festival> getEntityType() {
		Festival.class
	}
	
	@Override
	protected Predicate getCriterio(Festival example) {
		null
	}

	@Override
	public Entity createExample() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Predicate getCriterio(Entity example) {
		// TODO Auto-generated method stub
		return null;
	}
}
