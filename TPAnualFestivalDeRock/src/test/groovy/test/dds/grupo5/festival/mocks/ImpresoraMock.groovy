package test.dds.grupo5.festival.mocks

import dds.grupo5.festival.modelo.cliente.Cliente
import dds.grupo5.festival.modelo.festival.Ubicacion
import dds.grupo5.festival.modelo.interfacesExternas.ImpresoraFiscal
import dds.grupo5.festival.modelo.negocio.Entrada

class ImpresoraMock implements ImpresoraFiscal {
	 int contador = 0
	 List<EntradaMock> bufferImpresiones = []
	 
	 /* Impresora Mock genera una EntradaMock, que contiene todos los 
	  * Strings que serian los que se imprimirian sobre la factura
	  */
	 
	 void imprimirEntrada(Entrada entrada){
		 
		 EntradaMock entradaImpresa = new EntradaMock()
		 Ubicacion ubicacionAux = entrada.ubicacion()
		 Cliente clienteAux = entrada.cliente()
		 
		 entradaImpresa.fecha = entrada.noche().fecha
		 entradaImpresa.tipoFac = "A"
		 entradaImpresa.butaca = ubicacionAux.butaca
		 entradaImpresa.fila = ubicacionAux.fila
		 entradaImpresa.sector = ubicacionAux.sector
		 entradaImpresa.nombreCliente = clienteAux.nombre
		 entradaImpresa.apellidoCliente = clienteAux.apellido
		 entradaImpresa.tipoCliente = clienteAux.tipoPersona.toString()
		 entradaImpresa.nroFactura = entrada.nroFactura()
		 entradaImpresa.importe = entrada.calcularPrecio()
		 
		 contador++
		 bufferImpresiones << entradaImpresa
	 }
	 
	 int cantidadImpresiones(){
		 contador
	 }
	 
	 EntradaMock getBuffer(int nro){
		 bufferImpresiones[nro]
	 }
	 
}
