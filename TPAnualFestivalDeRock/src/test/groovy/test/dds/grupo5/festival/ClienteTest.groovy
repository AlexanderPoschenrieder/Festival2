package test.dds.grupo5.festival;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.cliente.TipoJubilado
import dds.grupo5.festival.modelo.cliente.TipoMayor
import dds.grupo5.festival.modelo.cliente.TipoMenor
import dds.grupo5.festival.modelo.exceptions.ValueOutOfRangeException

class ClienteTest {
	
	Cliente menor
	Cliente unoOcho
	Cliente mayor
	Cliente seisCinco
	Cliente jubilado
	Cliente noNacio
	Cliente forFai
		
	@Before
	def void startTest(){
		menor = new Cliente("Juan", "Carlos", 10)
		unoOcho = new Cliente("Juan", "Carlos", 18)
		mayor = new Cliente("Juan", "Carlos", 29)
		seisCinco = new Cliente("Juan", "Carlos", 65)
		jubilado = new Cliente("Juan", "Carlos", 70)
	
	}
	
	@After
	def void endTest(){
		
	}
	
	@Test
	public void testSetearTipoPersona() {
		assertEquals(menor.tipoPersona.class, TipoMenor)
		assertEquals(unoOcho.tipoPersona.class, TipoMayor)
		assertEquals(mayor.tipoPersona.class, TipoMayor)
		assertEquals(jubilado.tipoPersona.class, TipoJubilado)
		assertEquals(jubilado.tipoPersona.class, TipoJubilado)
	}
	
	@Test
	public void testDescuento(){
		assertEquals(menor.tipoPersona.descuento(100),10)
		assertEquals(menor.tipoPersona.descuento(120),24.00)
		assertEquals(mayor.tipoPersona.descuento(100),0)
		assertEquals(jubilado.tipoPersona.descuento(100),15.00)
	}
	
	@Test(expected = ValueOutOfRangeException.class)
	public void testSetearTipoPersonaFail1() {
		noNacio = new Cliente("Juan", "Carlos", -10)	
	}

	@Test(expected = ValueOutOfRangeException.class)
	public void testSetearTipoPersonaFail2() {
		noNacio = new Cliente("Juan", "Carlos", 0)
	}
	
	@Test(expected = ValueOutOfRangeException.class)
	public void testSetearTipoPersonaFail3() {
		forFai = new Cliente("Juan", "Carlos", 101)
	}
	
}
