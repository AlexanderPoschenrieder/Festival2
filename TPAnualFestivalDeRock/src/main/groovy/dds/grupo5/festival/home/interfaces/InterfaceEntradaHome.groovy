package dds.grupo5.festival.home.interfaces

import dds.grupo5.festival.modelo.negocio.Entrada;

interface InterfaceEntradaHome {
	public void agregarEntrada(Entrada entrada)
	
	public void eliminarEntrada(Entrada entrada) 
	
	public Entrada buscarEntrada(Entrada entrada) // le agregamos un id al objeto entrada para facilitar la busqueda?
		
	public List<Entrada> getEntradas(busqueda)
	
}
