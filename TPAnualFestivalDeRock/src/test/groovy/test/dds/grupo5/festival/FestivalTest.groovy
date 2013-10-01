package test.dds.grupo5.festival;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.cliente.Cliente

import dds.grupo5.festival.modelo.exceptions.BusinessException
import dds.grupo5.festival.modelo.festival.Festival
import dds.grupo5.festival.modelo.festival.Noche
import dds.grupo5.festival.modelo.festival.Presentacion
import dds.grupo5.festival.modelo.festival.Ubicacion
import dds.grupo5.festival.modelo.negocio.Entrada
import dds.grupo5.festival.modelo.negocio.EntradaComun

class FestivalTest {

	Festival festival
	Noche nocheVacia, nocheLlena, nocheDisponiblenocheLlena, nocheDisponible
	Date fecha
	Ubicacion ubLibre1, ubLibre2, ubLibre3, ubLibre4
	Ubicacion ubOcupada1, ubOcupada2,ubOcupada3,ubOcupada4
	Entrada entradaUno, entradaDos, entradaTres,entradaCuatro
	Banda banda1, banda2, banda3
	Categoria categoria1, categoria2, categoria3
	Cliente unCliente
	
	
	@Before
	def void startTest(){
		fecha = new Date(2012, 01, 01)

		festival = new Festival(fecha)
				
		ubLibre1 = new Ubicacion(1, 1, 23, 200, true)
		ubLibre2 = new Ubicacion(1, 2, 25, 200, true)
		ubLibre3 = new Ubicacion(2, 1, 23, 100, true)
		ubLibre4 = new Ubicacion(3, 10, 1, 20, true)
		
		ubOcupada1 = new Ubicacion(1, 1, 22, 200, false)
		ubOcupada2 = new Ubicacion(1, 3, 30, 150, false)
		ubOcupada3 = new Ubicacion(2, 1, 1, 100, false)
		ubOcupada4 = new Ubicacion(3, 10, 11, 20, false)
	
		nocheVacia = new Noche(fecha,21)
		nocheLlena = new Noche(fecha,22)
		nocheDisponible = new Noche(fecha,23)
		
		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		categoria3 = new Categoria(3, 100)
		banda1 = new Banda("1", categoria1)
		banda2 = new Banda("2", categoria2)
		banda3 = new Banda("3", categoria3)
		unCliente = new Cliente("Raul","Firulo",15)
		
		nocheVacia.agregarPresentacion(new Presentacion(21, banda1))
		nocheLlena.agregarPresentacion(new Presentacion(22, banda2))
		nocheDisponible.agregarPresentacion(new Presentacion(23, banda3))
		
		nocheDisponible.ubicaciones = [ubOcupada1,ubOcupada2,ubOcupada3,ubLibre4]
		nocheLlena.ubicaciones = [ubOcupada1,ubOcupada2,ubOcupada3,ubOcupada4]
		nocheVacia.ubicaciones = [ubLibre1,ubLibre2,ubLibre3,ubLibre4]
		
		festival.noches = [nocheVacia, nocheDisponible]
				
		entradaUno = new EntradaComun(ubLibre4, nocheDisponible,festival,unCliente)
		entradaDos = new EntradaComun(ubLibre1, nocheVacia,festival,unCliente)
		entradaTres = new EntradaComun(ubOcupada1, nocheDisponible,festival,unCliente)
		entradaCuatro = new EntradaComun(ubLibre1, nocheDisponible,festival,unCliente)
	}

	@After
	def void endTest(){
		//nada que hacer
	}
	
	@Test
	public void testUbicacionesDisponiblesOk() {
		festival.ubicacionesDisponibles([ubLibre1,ubLibre2])
	}
	
	@Test(expected=BusinessException)
	public void testUbicacionesDisponiblesUbicacionVendida() {
		festival.ubicacionesDisponibles([ubLibre1, ubLibre2, ubOcupada1])
	}

	@Test(expected=BusinessException)
	public void testUbicacionesDisponiblesListaVacia() {
		festival.ubicacionesDisponibles([])
	}
	
	@Test
	public void testNochePerteneAFestival(){
		assertEquals(true,festival.tePertenece(nocheDisponible))
		assertEquals(false,festival.tePertenece(nocheLlena))
	}
	
	@Test
	public void testValidarEntradasOk(){
		festival.validarEntradas([entradaUno,entradaDos])
	}
	
	@Test(expected = BusinessException)
	public void testValidarEntradasListaVacia(){
		festival.validarEntradas([])
	}
	
	@Test
	public void testValidarFestival(){
		festival.validate()
	}
}
