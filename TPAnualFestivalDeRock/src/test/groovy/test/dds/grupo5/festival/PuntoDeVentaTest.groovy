package test.dds.grupo5.festival

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Ignore
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
import dds.grupo5.festival.modelo.negocio.Venta

class PuntoDeVentaTest {
	
	Festival festival
	Date fecha
	def impresoraFiscalMock
	Entrada entrada1,entrada2,entrada3,entrada4
	Cliente unCliente
	PuntoDeVenta unPuntoDeVenta
	Venta unaVenta, otraVenta

	Noche noche1
	Ubicacion ubLibre1,ubLibre2,ubOcupada1,ubOcupada2
	Banda banda1,banda2
	Presentacion p1, p2
	Categoria categoria1, categoria2
	Administracion admin
	
	@Before
	public void init(){
		
		//Instancio 4 ubicaciones a utilizar
		ubLibre1 = new Ubicacion(1,1,23,200,true)
		ubLibre2 = new Ubicacion(2,2,24,200,true)
		ubOcupada1 = new Ubicacion(1, 1, 22, 200, false)
		ubOcupada2 = new Ubicacion(1, 3, 30, 150, false)
		
		noche1 = new Noche(new Date(2013,8,20),21)

		categoria1 = new Categoria(1, 0)
		categoria2 = new Categoria(2, 50)
		
		banda1 = new Banda("uno", categoria1)
		banda2 = new Banda("dos", categoria2)

		//Instancio 2 presentaciones
		p1 = new Presentacion(17, banda1)
		p2 = new Presentacion(19, banda2)

		//Instancio una noche
		fecha = new Date(2012, 01, 01)
		noche1.presentaciones=[p1,p2]
		noche1.ubicaciones = [ubLibre1, ubLibre2]
		noche1.fecha = fecha
		noche1.horaComienzo = 21
		
		//Instancio un festival
		festival = new Festival()
		festival.noches = [noche1]
		festival.fechaInicio = fecha
		
		//Instancio impresora fiscal
		impresoraFiscalMock = new ImpresoraMock()
		
		//Instancio una administracion
		admin = new Administracion()
		admin.descuento= 10
		admin.periodoVentasAnticipadas = 10
		admin.impresoraFiscal = impresoraFiscalMock
		
		//Instancio el PuntoDeventa
		unPuntoDeVenta = new PuntoDeVenta(1,1,festival,admin)
		
		//Instancio entradas
		entrada1 = new EntradaComun(ubLibre1,noche1)
		entrada2 = new EntradaComun(ubLibre2,noche1)
		entrada3 = new EntradaComun(ubOcupada1,noche1)
		entrada4 = new EntradaComun(ubOcupada2,noche1)
		
		//Instancio el cliente
		unCliente = new Cliente("Juan", "Carlos", 29)		
	}
	
	@Ignore
	@Test
	//Verifico que cambia el nro de la factura proxima luego de registar una
	public void numeroFacturaProximoTest(){
		assertEquals(admin.nrofactura,0)
		unPuntoDeVenta.registrarVenta(unCliente, [entrada1,entrada2])
		assertEquals(admin.nrofactura,1)
	}
	
	@Ignore
	@Test
	//Verifico que al momento de registrar la venta en el punto de venta, se imprimen 2 entradas
	public void registroVentaImprimeEntradas(){
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),0)
		unPuntoDeVenta.registrarVenta(unCliente, [entrada1,entrada2])
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),2)
	}
	
	@Ignore
	@Test(expected=Exception)
	//Verifico que no registra ventas por excepcion de ubicacion ocupada
	public void noRegistroVentaExcepcionUbicacionOcupada(){
		unPuntoDeVenta.registrarVenta(unCliente, [entrada3,entrada4])
		assertEquals(admin.impresoraFiscal.cantidadImpresiones(),0)
	}
	
}
