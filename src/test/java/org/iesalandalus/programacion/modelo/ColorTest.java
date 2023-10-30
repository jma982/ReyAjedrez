package org.iesalandalus.programacion.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.iesaladanlus.programacion.reyajedrez.modelo.Color;
import org.junit.jupiter.api.Test;


class ColorTest {
	
	private static final String NOMBRE_NO_VALIDO = "El nombre del color no es válido.";

	@Test
	void nombresValidosColores() {
		assertEquals("Blanco", Color.BLANCO.toString(), NOMBRE_NO_VALIDO);
		assertEquals("Negro", Color.NEGRO.toString(), NOMBRE_NO_VALIDO);
	}

}
