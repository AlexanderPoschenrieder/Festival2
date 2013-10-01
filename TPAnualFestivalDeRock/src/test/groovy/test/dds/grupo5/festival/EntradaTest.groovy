package test.dds.grupo5.festival;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.exceptions.BusinessException
import dds.grupo5.festival.modelo.festival.*
import dds.grupo5.festival.modelo.negocio.Entrada
import dds.grupo5.festival.modelo.negocio.EntradaAnticipada
import dds.grupo5.festival.modelo.negocio.EntradaComun

class EntradaTest {
	Noche noche1
	Ubicacion ubLibre1,ubOcupada1
	Banda banda1,banda2
	Presentacion p1, p2
	
	Entrada entrada1
	Categoria categoria1, categoria2
	Cliente unCliente
	
	EntradaAnticipada unaAnticipada
	Festival unFestival

	@Before
	public void init(){
		ubLibre1 = new Ubicacion(1, 1, 23, 200, true)
		ubOcupada1= new Ubicacion(1,1,23,200,false)
		noche1 = new Noche(new Date(2013,8,20),17)
		
		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		banda1 = new Banda("uno", categoria1)
		banda2 = new Banda("dos", categoria2)
		
		p1 = new Presentacion(17, banda1)
		p2 = new Presentacion(19, banda2)
		
		noche1.presentaciones=[p1,p2]
		noche1.ubicaciones = [ubLibre1]
		unFestival =new Festival(new Date(2013,8,20))
		unFestival.noches =[noche1]
		
		unCliente = new Cliente("Juan", "Carlos", 29)
		entrada1 = new EntradaComun(ubLibre1,noche1,unFestival,unCliente)

	
		
		unaAnticipada = new EntradaAnticipada(entrada1,10)
	}
	
	@Test
	public void testCalcularPrecio() {
		assert (entrada1.calcularPrecio()==250)
	}
	
	@Test
	public void testCalcularPrecioAnticipada() {
		def unPrecio
		unPrecio= entrada1.calcularPrecio()
		assert (unaAnticipada.calcularPrecio() == unPrecio*0.90)
	}

	@Test
	public void testDiasRestantesParaElRecital() {
		assert(entrada1.diasRestantesParaElRecital()==(noche1.fecha.minus(new Date())))
	}

	@Test
	public void testFuisteVendida() {		
		assertTrue(entrada1.ubicacion().disponible)
		
		entrada1.fuisteVendida()
		
		assertFalse(entrada1.ubicacion().disponible)
	}
	
	@Test 
	public void testFuisteVendidaAnticipada() {		
		assertTrue(unaAnticipada.ubicacion().disponible)
		
		entrada1.fuisteVendida()
		
		assertFalse(unaAnticipada.ubicacion().disponible)
	}

	@Test(expected = BusinessException)
	public void testFuisteVendidaFail() {
		entrada1 = new EntradaComun(ubOcupada1, noche1,unFestival,unCliente)
		assertFalse(entrada1.ubicacion().disponible)
		entrada1.fuisteVendida()
	}
	
	@Test(expected = BusinessException)
	public void testFuisteVendidaFailAnticipada() {
		unaAnticipada.ubicacion().actualizarEstadoDeDisponibilidad(false)
		assertFalse(unaAnticipada.ubicacion().disponible)
		entrada1.fuisteVendida()
	}

}
