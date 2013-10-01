package test.dds.grupo5.festival;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import test.dds.grupo5.festival.mocks.ImpresoraMock
import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.festival.*
import dds.grupo5.festival.modelo.interfacesExternas.ImpresoraFiscal
import dds.grupo5.festival.modelo.negocio.Administracion
import dds.grupo5.festival.modelo.negocio.Entrada
import dds.grupo5.festival.modelo.negocio.EntradaAnticipada
import dds.grupo5.festival.modelo.negocio.EntradaComun
import dds.grupo5.festival.modelo.negocio.PuntoDeVenta




class AdministracionTest {
	Entrada entrada1,entrada2
	Cliente unCliente

	Noche noche1
	Ubicacion ubLibre1,ubLibre2
	Banda banda1,banda2
	Categoria categoria1, categoria2
	Presentacion p1, p2
	Administracion admin
	PuntoDeVenta unPuntoDeVenta
	ImpresoraMock impresoraMock
	Festival festival1
	
	@Before
	public void init(){
		admin = new Administracion()
		impresoraMock = new ImpresoraMock()
		admin.impresoraFiscal= impresoraMock
		unPuntoDeVenta = new PuntoDeVenta(1,2,null,null)

		ubLibre1 = new Ubicacion('a', 1, 23, 200, true)
		ubLibre2= new Ubicacion('a',1,24,200,true)
		noche1 = new Noche(new Date(2013,8,20),21)


		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		banda1 = new Banda("uno", categoria1)
		banda2 = new Banda("dos", categoria2)

		p1 = new Presentacion(17, banda1)
		p2 = new Presentacion(19, banda2)
		
	

		noche1.presentaciones=[p1,p2]
		noche1.ubicaciones = [ubLibre1,ubLibre2]
		
		festival1 = new Festival(new Date(2013,8,20))
		festival1.noches =[noche1]
		admin.festivales=[festival1]
		
		unCliente = new Cliente("Juan", "Carlos", 29)
	}

	@Test
	public void testRegistrarVenta(){
		assertEquals(admin.ventas.size(),0)
		entrada1 = admin.dameEntrada(ubLibre1, noche1,festival1,unCliente)
		entrada2 = admin.dameEntrada(ubLibre2, noche1,festival1,unCliente)
		admin.registrarVenta([entrada1, entrada2], unPuntoDeVenta)
		assertEquals(admin.ventas.size(),2)
	}
	
	@Test
	public void testCancelarVenta(){
		assertEquals(admin.ventas.size(),0)
		entrada1 = admin.dameEntrada(ubLibre1, noche1,festival1,unCliente)
		entrada2 = admin.dameEntrada(ubLibre2, noche1,festival1,unCliente)
		admin.registrarVenta([entrada1, entrada2], unPuntoDeVenta)
		admin.cancelarEntrada(entrada1.nroFactura)
		assertEquals(admin.ventas.size(),1)
		assertEquals(admin.ventas,[entrada2])
		
	}

	@Test
	public void testAplicarDescuentosParaAnticipadas() {

		admin.ventaAnticipadaHabilitada = true;
		admin.descuento = 10;
		admin.periodoVentasAnticipadas = 200;
		
		entrada1 = admin.dameEntrada(ubLibre1, noche1,festival1,unCliente)
		entrada2 = admin.dameEntrada(ubLibre2, noche1,festival1,unCliente)
		
		admin.registrarVenta([entrada1,entrada2], unPuntoDeVenta)
		
		def anticipada1 = new EntradaAnticipada(new EntradaComun(ubLibre1,noche1, festival1,unCliente),10)
		def anticipada2 = new EntradaAnticipada(new EntradaComun(ubLibre2,noche1,festival1,unCliente),10)
		def precioEsperado = anticipada1.calcularPrecio()+ anticipada2.calcularPrecio()
		
		assertEquals(admin.ventas.inject(0) {primerPar,it->it.calcularPrecio()+primerPar}, precioEsperado,0)
	}
	

	
	@Test
	public void testAplicarNroFactura(){
		entrada1=admin.dameEntrada(ubLibre1, noche1, festival1,unCliente)
		admin.registrarVenta([entrada1] , unPuntoDeVenta)
		entrada2= admin.dameEntrada(ubLibre2, noche1, festival1,unCliente)
		admin.registrarVenta([entrada2], unPuntoDeVenta)
		
		assertEquals(entrada2.nroFactura(),entrada1.nroFactura()+1)
	}

}
