package dds.grupo5.festival.modelo.UI
import java.util.List;

import org.uqbar.commons.utils.Observable

import dds.grupo5.festival.modelo.cliente.Cliente;
import dds.grupo5.festival.modelo.interfacesExternas.ImpresoraFiscal;
import dds.grupo5.festival.modelo.negocio.Entrada;
import dds.grupo5.festival.modelo.negocio.PuntoDeVenta;
import dds.grupo5.festival.modelo.negocio.Venta;


@Observable
class VentaModel {

	List<Entrada> entradas = []
	String nombreCliente
	String apellidoCliente
	String puntoDeVenta
	ImpresoraFiscal impresoraFiscal
	int nroFactura
	BigDecimal importe
	Date fechaVenta
	
	public VentaModel(){
		this.entradas = []
		this.nombreCliente = " "
		this.apellidoCliente = " "
	}
	
	void calcularValorVenta(){
		nuevaVenta.calcularImporte()
	}

}
