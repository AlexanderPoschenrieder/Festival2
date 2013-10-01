package test.dds.grupo5.festival;

import static org.junit.Assert.*

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dds.grupo5.festival.modelo.banda.Banda;
import dds.grupo5.festival.modelo.banda.Categoria;

class BandaTest {

	Banda banda1, banda2, banda3, banda4
	Categoria categoria1, categoria2, categoria3, categoria4

	@Before
	def void startTest(){
		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		categoria3 = new Categoria(3, 100)
		categoria4 = new Categoria(4, 200)
		banda1 = new Banda("1", categoria1)
		banda2 = new Banda("2", categoria2)
		banda3 = new Banda("3", categoria3)
		banda4 = new Banda("4", categoria4)
	}

	/*
	 * Prueba que se calcule correctamente el precio extra de las bandas
	 */
	@Test
	public void testPrecio() {
		assertEquals(banda1.precio(), 0)
		assertEquals(banda2.precio(), 50)
		assertEquals(banda3.precio(), 100)
		assertEquals(banda4.precio(), 200)
	}
}
