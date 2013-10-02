package dds.grupo5.festival.modelo.UI

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import org.uqbar.commons.utils.ApplicationContext

import dds.grupo5.festival.homes.EntradaHome
import dds.grupo5.festival.homes.HomeFestivales

class GestionVentasApplication extends Application {

	public static void main(String[] args) {
		new GestionVentasApplication().start()
	}

	@Override
	protected Window<?> createMainWindow() {
		ApplicationContext.instance.configureSingleton(Festival.class, new HomeFestivales())
		ApplicationContext.instance.configureSingleton(Entrada.class, new EntradaHome())
		return new GestionVentasWindow(this)
	}
	
}
