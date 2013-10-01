package test.dds.grupo5.festival

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import test.dds.grupo5.festival.mocks.ImpresoraMock
import dds.grupo5.festival.modelo.banda.Banda
import dds.grupo5.festival.modelo.banda.Categoria
import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.festival.Festival
import dds.grupo5.festival.modelo.festival.Noche
import dds.grupo5.festival.modelo.festival.Presentacion
import dds.grupo5.festival.modelo.festival.Ubicacion
import dds.grupo5.festival.modelo.negocio.Administracion
import dds.grupo5.festival.modelo.negocio.Entrada
import dds.grupo5.festival.modelo.negocio.EntradaComun
import dds.grupo5.festival.modelo.negocio.PuntoDeVenta

class impresionEntradaTest {
	
	Festival festival
	Date fecha
	def impresoraFiscalMock
	Entrada entrada1,entrada2,entrada3,entrada4
	Cliente unCliente
	PuntoDeVenta unPuntoDeVenta

	Noche noche1
	Ubicacion ubLibre1,ubLibre2, ubLibre3, ubLibre4
	Banda banda1,banda2
	Presentacion p1, p2
	Categoria categoria1, categoria2
	Administracion admin
	
	@Before
	public void init(){
		
		ubLibre1 = new Ubicacion(1,1,23,200,true)
		ubLibre2 = new Ubicacion(2,2,24,200,true)
		ubLibre3 = new Ubicacion(3,4,23,200,true)
		ubLibre4 = new Ubicacion(4,5,24,200,true)
	
		
		noche1 = new Noche(new Date(2013,8,20),21)

		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		
		banda1 = new Banda("uno", categoria1)
		banda2 = new Banda("dos", categoria2)

		p1 = new Presentacion(17, banda1)
		p2 = new Presentacion(19, banda2)

		noche1.presentaciones=[p1,p2]
		noche1.ubicaciones = [ubLibre1, ubLibre2]
		
		fecha = new Date(2012, 01, 01)
		festival = new Festival(fecha)
		admin = new Administracion()
		admin.festivales = [festival]
		unCliente = new Cliente("Juan", "Carlos", 29)
		
		unPuntoDeVenta = new PuntoDeVenta(1,1,festival,admin)
		entrada1 = new EntradaComun(ubLibre1,noche1,festival,unCliente)
		entrada2 = new EntradaComun(ubLibre2,noche1,festival,unCliente)
		entrada3 = new EntradaComun(ubLibre3,noche1,festival,unCliente)
		entrada4 = new EntradaComun(ubLibre4,noche1,festival,unCliente)
		
		
		admin.impresoraFiscal= new ImpresoraMock()
	}
	
	@Test
	// Testeo que cuando imprimo una entrada el contador de impresiones sube a 1
	public void entradaImpresa(){
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),0)
		admin.registrarVenta([entrada1],unPuntoDeVenta)
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),1)
	}
	
	@Test
	// Pruebo que cuando imprime una venta, que posee 2 entradas, el contador sube a 2
	public void cantidadDeEntradasImpresasTest(){
		admin.registrarVenta([entrada1,entrada2],unPuntoDeVenta)
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),2)
	}
	
	@Test
	// Testeo que cuando imprimo 2 ventas, suma la cantidad total de entradas
	public void entradaImpresaDosVentas(){
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),0)
		admin.registrarVenta([entrada1,entrada2], unPuntoDeVenta)
		admin.registrarVenta([entrada3,entrada4], unPuntoDeVenta)
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),4)
	}
	
	@Test
	/* Chequeo que las entradas impresas se correspondan con las 2 entradas cargadas en la venta
	 * Se verifica que el buffer de impresion contiene las entradas que saldrian impresas.
	 */
	public void entradaImpresaCorrectaTest(){
		admin.registrarVenta([entrada1,entrada2], unPuntoDeVenta)
		//Comparo la primer entrada con los datos de la impresion
		assertEquals(admin.impresoraFiscal.getBuffer(0).apellidoCliente.toString(),unCliente.apellido.toString())
		assertEquals(admin.impresoraFiscal.getBuffer(0).nombreCliente.toString(),unCliente.nombre.toString())
		assertEquals(admin.impresoraFiscal.getBuffer(0).butaca,entrada1.ubicacion().butaca)
		assertEquals(admin.impresoraFiscal.getBuffer(0).fila,entrada1.ubicacion().fila)
		assertEquals(admin.impresoraFiscal.getBuffer(0).sector,entrada1.ubicacion().sector)
		assertEquals(admin.impresoraFiscal.getBuffer(0).tipoFac,"A")
		//los numeros de factura se chequearon en otro test-----assertEquals(unaVenta.impresoraFiscal.getBuffer(0).nroFactura,1)
		assertEquals(admin.impresoraFiscal.getBuffer(0).importe,entrada1.precioEntrada)
		//Comparo la segunda entrada con los datos de la impresion
		assertEquals(admin.impresoraFiscal.getBuffer(1).apellidoCliente.toString(),unCliente.apellido.toString())
		assertEquals(admin.impresoraFiscal.getBuffer(1).nombreCliente.toString(),unCliente.nombre.toString())
		assertEquals(admin.impresoraFiscal.getBuffer(1).butaca,entrada2.ubicacion().butaca)
		assertEquals(admin.impresoraFiscal.getBuffer(1).fila,entrada2.ubicacion().fila)
		assertEquals(admin.impresoraFiscal.getBuffer(1).sector,entrada2.ubicacion().sector)
		assertEquals(admin.impresoraFiscal.getBuffer(1).tipoFac,"A")
		//los numeros de factura se chequearon en otro test-----assertEquals(unaVenta.impresoraFiscal.getBuffer(1).nroFactura,1)
		assertEquals(admin.impresoraFiscal.getBuffer(1).importe,entrada2.precioEntrada)
	}

}
