package org.palomafp.datosapi.dao;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.palomafp.datosapi.model.Dato;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para el DAO 'DatosDAO'.
 * Estas pruebas verifican el comportamiento del DAO de forma aislada.
 */
class DatosDAOTest {

	@Test
	void obtenerTodos_devuelveCincoElementosIniciales() {
		DatosDAO datosDAO = new DatosDAO();
		List<Dato> datos = datosDAO.obtenerTodos();

		assertEquals(5, datos.size());
	}

	@Test
	void obtenerPorCodigo_filtraCorrectamente() {
		DatosDAO datosDAO = new DatosDAO();
		List<Dato> filtrados = datosDAO.obtenerPorCodigo(1);

		assertEquals(1, filtrados.size());
		assertEquals(1, filtrados.get(0).getCodigo());
	}

	@Test
	void obtenerPorCodigo_inexistenteDevuelveListaVacia() {
		DatosDAO datosDAO = new DatosDAO();
		List<Dato> filtrados = datosDAO.obtenerPorCodigo(999);

		assertTrue(filtrados.isEmpty());
	}

	@Test
	void obtenerPorDescripcion_esCaseInsensitive() {
		DatosDAO datosDAO = new DatosDAO();
		List<Dato> filtrados = datosDAO.obtenerPorDescripcion("pRiMeR");

		assertEquals(1, filtrados.size());
		assertEquals(1, filtrados.get(0).getCodigo());
	}

	@Test
	void anyadir_agregaNuevoElemento() {
		DatosDAO datosDAO = new DatosDAO();
		datosDAO.anyadir(new Dato(6, "Elemento nuevo"));

		assertEquals(6, datosDAO.obtenerTodos().size());
		assertEquals(1, datosDAO.obtenerPorCodigo(6).size());
	}
}