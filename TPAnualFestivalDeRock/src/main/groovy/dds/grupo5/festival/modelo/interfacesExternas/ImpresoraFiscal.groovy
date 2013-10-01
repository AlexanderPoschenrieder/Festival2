package dds.grupo5.festival.modelo.interfacesExternas;

import dds.grupo5.festival.modelo.cliente.Cliente;
import dds.grupo5.festival.modelo.negocio.Entrada;

interface ImpresoraFiscal {
	void imprimirEntrada(Entrada entrada);
	int cantidadImpresiones();
}
