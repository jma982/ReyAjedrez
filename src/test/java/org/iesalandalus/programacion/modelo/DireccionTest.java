package org.iesalandalus.programacion.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.iesaladanlus.programacion.reyajedrez.modelo.Direccion;
import org.junit.jupiter.api.Test;


class DireccionTest {
	
	private static final String NOMBRE_NO_VALIDO = "El nombre de la dirección no es válido.";

	@Test
	void nombresValidosDirecciones() {
		assertEquals("Norte", Direccion.NORTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Noreste", Direccion.NORESTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Este", Direccion.ESTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Sureste", Direccion.SURESTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Sur", Direccion.SUR.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Suroeste", Direccion.SUROESTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Oeste", Direccion.OESTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Noroeste", Direccion.NOROESTE.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Enroque corto", Direccion.ENROQUE_CORTO.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Enroque largo", Direccion.ENROQUE_LARGO.toString(), NOMBRE_NO_VALIDO);
	}

}
