package test.dds.grupo5.festival;


import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.exceptions.BusinessException
import dds.grupo5.festival.modelo.festival.Noche
import dds.grupo5.festival.modelo.festival.Presentacion
import dds.grupo5.festival.modelo.festival.Ubicacion

class NocheTest {

	Noche nocheVacia, nocheCargada
	Presentacion p1, p2, p3, p4
	Date fecha
	Ubicacion ubLibre1, ubLibre2, ubLibre3, ubLibre4
	Ubicacion ubOcupada1, ubOcupada2,ubOcupada3,ubOcupada4
	Banda banda1, banda2, banda3, banda4
	Categoria categoria1, categoria2, categoria3, categoria4

	@Before
	def void init(){
		ubLibre1 = new Ubicacion(1, 1, 23, 200, true)
		ubLibre2 = new Ubicacion(1, 2, 25, 200, true)
		ubLibre3 = new Ubicacion(2, 1, 23, 100, true)
		ubLibre4 = new Ubicacion(3, 10, 1, 20, true)

		ubOcupada1 = new Ubicacion(1, 1, 22, 200, false)
		ubOcupada2 = new Ubicacion(1, 3, 30, 150, false)
		ubOcupada3 = new Ubicacion(2, 1, 1, 100, false)
		ubOcupada4 = new Ubicacion(3, 10, 11, 20, false)

		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		categoria3 = new Categoria(3, 100)
		categoria4 = new Categoria(4, 200)

		banda1 = new Banda("uno", categoria1)
		banda2 = new Banda("dos", categoria2)
		banda3 = new Banda("tres", categoria3)
		banda4 = new Banda("cuatro", categoria4)

		p1 = new Presentacion(17, banda1)
		p2 = new Presentacion(19, banda2)
		p3 = new Presentacion(21, banda3)
		p4 = new Presentacion(00, banda4)

		fecha = new Date(2012, 01, 01)

		nocheVacia = new Noche(fecha,21)
		nocheCargada = new Noche(fecha,17)

		nocheCargada.ubicaciones = [ubLibre1,ubOcupada2,ubLibre3,ubOcupada4]
		nocheCargada.presentaciones = [p1,p2,p3,p4]
	}

	@Test
	public void testPresentacionDeMayorCategoria() {
		assertEquals(nocheCargada.presentacionDeMayorCategoria(), p4)
		assertNotSame(nocheCargada.presentacionDeMayorCategoria(), p1)
	}


	@Test(expected = BusinessException)
	public void testPresentacionDeMayorCategoriaNull() {
		nocheVacia.presentacionDeMayorCategoria()
	}

	@Test
	public void testPrecioExtra() {
		assertEquals(nocheCargada.precioExtra(), 200)
	}


	@Test
	public void testUbicacionDisponible() {
		assertTrue(nocheCargada.ubicacionDisponible(ubLibre1))
		assertFalse(nocheCargada.ubicacionDisponible(ubOcupada2))
		assertTrue(nocheCargada.ubicacionDisponible(ubLibre3))
		assertFalse(nocheCargada.ubicacionDisponible(ubOcupada4))
	}


	@Test(expected=BusinessException)
	public void testUbicacionDisponibleUbicacionDeOtraNoche() {
		nocheCargada.ubicacionDisponible(ubOcupada1)
	}
	
	@Test(expected = BusinessException)
	public void validarUbicaciones(){
		nocheVacia.validarUbicaciones()
	}
	
	@Test(expected = BusinessException)
	public void validarPresentacionesFail(){
		nocheCargada.validarPresentaciones()//una de las presentaciones tiene menor horario
	}
	
	@Test
	public void validarPresentaciones(){
		p4.horarioInicio=22
		nocheCargada.validarPresentaciones()
	}
}
