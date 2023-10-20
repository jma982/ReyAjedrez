package reyajedrez.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReyTest {

	private static final String COLOR_NO_ESPERADO = "El color no es el esperado.";
	private static final String POSICION_NO_ESPERADA = "La posición no es la esperada.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String EXCEPCION_NO_VALIDA = "El tipo de excepción no es válida o no ha saltado.";
	private static final String MENSAJE_ERROR_COLOR_NULO = "ERROR: El color no puede ser nulo.";
	private static final String MENSAJE_ERROR_DIRECCION_NULA = "ERROR: La dirección no puede ser nula.";
	private static final String MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO = "ERROR: Movimiento no válido (se sale del tablero).";
	private static final String MENSAJE_ERROR_ENROQUE_POSICION = "ERROR: El rey no está en su posición inicial.";
	private static final String MENSAJE_ERROR_ENROQUE_MOVIMIENTOS = "ERROR: El rey ya se ha movido antes.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";

	private static Rey reyBlanco;
	private static Rey reyNegro;

	@BeforeEach
	void asignarValoresReyes()
	{
		reyBlanco = new Rey(Color.BLANCO);
		reyNegro = new Rey(Color.NEGRO);
	}

	@Test
	void constructores()
	{
		// Test de constructor por defecto

		reyBlanco = new Rey();
		assertEquals(Color.BLANCO, reyBlanco.getColor(), COLOR_NO_ESPERADO);
		assertEquals(new Posicion(1, 'e'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);

		// Test de constructor con parametros de color

		assertEquals(Color.BLANCO, reyBlanco.getColor(), COLOR_NO_ESPERADO);
		assertEquals(new Posicion(1, 'e'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);

		assertEquals(Color.NEGRO, reyNegro.getColor(), COLOR_NO_ESPERADO);
		assertEquals(new Posicion(8, 'e'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);

		NullPointerException excepcion = assertThrows(NullPointerException.class, () -> { new Rey(null);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_COLOR_NULO, excepcion.getMessage(), MENSAJE_NO_CORRECTO);

	}

	@Test
	void moverIncorrectamente() throws OperationNotSupportedException
	{
		NullPointerException excepcionNula;
		OperationNotSupportedException excepcionMovimiento;

		excepcionNula = assertThrows(NullPointerException.class, () -> { reyBlanco.mover(null);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_DIRECCION_NULA, excepcionNula.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionNula = assertThrows(NullPointerException.class, () -> { reyNegro.mover(null);}, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_DIRECCION_NULA, excepcionNula.getMessage(), MENSAJE_NO_CORRECTO);

		reyBlanco = new Rey(Color.BLANCO);

		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.SUR); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.SURESTE); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.SUROESTE); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);

		reyNegro = new Rey(Color.NEGRO);

		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.NORTE); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.NORESTE); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.NOROESTE); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_MOVIMIENTO_NO_VALIDO, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);

		reyBlanco = new Rey(Color.BLANCO);
		reyBlanco.mover(Direccion.NORTE);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.ENROQUE_CORTO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_POSICION, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.ENROQUE_LARGO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_POSICION, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		reyBlanco.mover(Direccion.SUR);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.ENROQUE_CORTO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_MOVIMIENTOS, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyBlanco.mover(Direccion.ENROQUE_LARGO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_MOVIMIENTOS, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);

		reyNegro = new Rey(Color.NEGRO);
		reyNegro.mover(Direccion.SUR);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.ENROQUE_CORTO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_POSICION, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.ENROQUE_LARGO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_POSICION, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		reyNegro.mover(Direccion.NORTE);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.ENROQUE_CORTO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_MOVIMIENTOS, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
		excepcionMovimiento = assertThrows(OperationNotSupportedException.class, () -> { reyNegro.mover(Direccion.ENROQUE_LARGO); }, EXCEPCION_NO_VALIDA);
		assertEquals(MENSAJE_ERROR_ENROQUE_MOVIMIENTOS, excepcionMovimiento.getMessage(), MENSAJE_NO_CORRECTO);
	}
	
	@Test
	void moverCorrectamente() throws OperationNotSupportedException {

		// Test de movimiento correcto del rey blanco

		reyBlanco = new Rey(Color.BLANCO);

		reyBlanco.mover(Direccion.NORTE);
		assertEquals(new Posicion(2, 'e'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);
		reyBlanco.mover(Direccion.NORESTE);
		assertEquals(new Posicion(3, 'f'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);
		reyBlanco.mover(Direccion.ESTE);
		assertEquals(new Posicion(3, 'g'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);
		reyBlanco.mover(Direccion.SURESTE);
		assertEquals(new Posicion(2, 'h'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);
		reyBlanco.mover(Direccion.SUR);
		assertEquals(new Posicion(1, 'h'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);

		// Test de movimiento correcto del rey negro

		reyNegro = new Rey(Color.NEGRO);

		reyNegro.mover(Direccion.SUR);
		assertEquals(new Posicion(7, 'e'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);
		reyNegro.mover(Direccion.SUROESTE);
		assertEquals(new Posicion(6, 'd'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);
		reyNegro.mover(Direccion.OESTE);
		assertEquals(new Posicion(6, 'c'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);
		reyNegro.mover(Direccion.NOROESTE);
		assertEquals(new Posicion(7, 'b'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);
		reyNegro.mover(Direccion.NORTE);
		assertEquals(new Posicion(8, 'b'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);


		// Test del enroque correcto del rey blanco

		reyBlanco = new Rey(Color.BLANCO);
		reyBlanco.mover(Direccion.ENROQUE_CORTO);
		assertEquals(new Posicion(1, 'g'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);

		reyBlanco = new Rey(Color.BLANCO);
		reyBlanco.mover(Direccion.ENROQUE_LARGO);
		assertEquals(new Posicion(1, 'c'), reyBlanco.getPosicion(), POSICION_NO_ESPERADA);

		// Test del enroque correcto del rey negro

		reyNegro = new Rey(Color.NEGRO);
		reyNegro.mover(Direccion.ENROQUE_CORTO);
		assertEquals(new Posicion(8, 'g'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);

		reyNegro = new Rey(Color.NEGRO);
		reyNegro.mover(Direccion.ENROQUE_LARGO);
		assertEquals(new Posicion(8, 'c'), reyNegro.getPosicion(), POSICION_NO_ESPERADA);

	}

	@Test
	void toStringImprimeCorrectamente()
	{
		reyBlanco = new Rey(Color.BLANCO);
		assertEquals("color=Blanco, posicion=(fila=1, columna=e)", reyBlanco.toString(), CADENA_NO_ESPERADA);
	}

}
