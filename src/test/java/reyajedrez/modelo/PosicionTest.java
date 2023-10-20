package reyajedrez.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PosicionTest {
	
	private static final String FILA_NO_ESPERADA = "La fila devuelta no es la esperada.";
	private static final String COLUMNA_NO_ESPERADA = "La columna devuelta no es la esperada.";
	private static final String POSICION_NO_ESPERADA = "La posición copiada debería ser la misma que la pasada como parámetro.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String EXCEPCION_NO_VALIDA = "El tipo de excepción no es válido o no ha saltado.";
	private static final String OBJETOS_DEBERIAN_SER_IGUALES = "Los objetos deberían ser iguales.";
	private static final String OBJETOS_DEBERIAN_SER_DIFERENTES = "Los objetos deberían ser diferentes.";
	private static final String HASHS_DEBERIAN_SER_IGUALES = "Los hashs deberían ser iguales.";
	private static final String HASHS_DEBERIAN_SER_DIFERENTES = "Los hashs deberían ser diferentes.";
	private static final String MENSAJE_ERROR_FILA_NO_VALIDA = "ERROR: Fila no válida.";
	private static final String MENSAJE_ERROR_COLUMNA_NO_VALIDA = "ERROR: Columna no válida.";
	private static final String MENSAJE_ERROR_COPIAR_POSICION_NULA = "ERROR: No es posible copiar una posición nula.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";


	@Test
	void constructorFilaValidaColumnaValidaCreaPosicionCorrectamente() {
		Posicion posicion;
		posicion = new Posicion(1, 'a');
		assertEquals(1, posicion.getFila(), FILA_NO_ESPERADA);
		assertEquals('a', posicion.getColumna(), COLUMNA_NO_ESPERADA);
	}
	
	@Test
	void constructorFilaNoValidaColumnaValidaLanzaExcepcion() {
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> { new Posicion(0, 'a');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_FILA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
		excepcion = assertThrows(IllegalArgumentException.class, () -> {  new Posicion(9, 'a');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_FILA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
	}
	
	@Test
	void constructorFilaValidaColumnaNoValidaLanzaExcepcion() {
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> { new Posicion(1, '`');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_COLUMNA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
		excepcion = assertThrows(IllegalArgumentException.class, () -> {  new Posicion(1, 'i');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_COLUMNA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
	}
	
	@Test
	void constructorFilaNoValidaColumnaNoValidaLanzaExcepcion() {
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> { new Posicion(0, '`');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_FILA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
		excepcion = assertThrows(IllegalArgumentException.class, () -> { new Posicion(0, 'h');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_FILA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
		excepcion = assertThrows(IllegalArgumentException.class, () -> { new Posicion(9, '`');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_FILA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
		excepcion = assertThrows(IllegalArgumentException.class, () -> { new Posicion(9, 'h');}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_FILA_NO_VALIDA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
	}
	
	@Test
	void constructorPosicionValidaDevuelveCopiaDeLaPosicion() {
		Posicion posicion = new Posicion(1, 'a');
		Posicion nuevaPosicion = new Posicion(posicion);
		assertEquals(posicion, nuevaPosicion, POSICION_NO_ESPERADA);
	}
	
	@Test
	void constructorPosicionNulaLanzaExcepcion() {
		NullPointerException excepcion = assertThrows(NullPointerException.class, () -> { new Posicion(null);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_COPIAR_POSICION_NULA, excepcion.getMessage(), MENSAJE_NO_CORRECTO);
	}
	
	@Test
	void equalsHashCodeComparaCorrectamente() {
		Posicion posicion1 = new Posicion(1, 'a');
		assertEquals(posicion1, posicion1, OBJETOS_DEBERIAN_SER_IGUALES);
		assertEquals(posicion1.hashCode(), posicion1.hashCode(), HASHS_DEBERIAN_SER_IGUALES);
		assertEquals(posicion1, new Posicion(1, 'a'), OBJETOS_DEBERIAN_SER_IGUALES);
		assertEquals(posicion1.hashCode(), new Posicion(1, 'a').hashCode(), HASHS_DEBERIAN_SER_IGUALES);
		assertNotEquals(posicion1, null, OBJETOS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1, "Otro", OBJETOS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1.hashCode(), "Otro".hashCode(), HASHS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1, new Posicion(2, 'a'), OBJETOS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1.hashCode(), new Posicion(2, 'a').hashCode(), HASHS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1, new Posicion(1, 'b'), OBJETOS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1.hashCode(), new Posicion(1, 'b').hashCode(), HASHS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1, new Posicion(2, 'b'), OBJETOS_DEBERIAN_SER_DIFERENTES);
		assertNotEquals(posicion1.hashCode(), new Posicion(2, 'b').hashCode(), HASHS_DEBERIAN_SER_DIFERENTES);
	}
	
	@Test
	void toStringDevuelveLaCadenaEsperada() {
		assertEquals("fila=1, columna=a", new Posicion(1, 'a').toString(), CADENA_NO_ESPERADA);
	}

}
