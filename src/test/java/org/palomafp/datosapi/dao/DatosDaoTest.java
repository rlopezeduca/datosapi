package org.palomafp.datosapi.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.palomafp.datosapi.model.Dato;

/**
 * Pruebas unitarias para el DAO 'DatosDAO'.
 * Estas pruebas verifican el comportamiento del DAO de forma aislada.
 */
class DatosDaoTest {

  @Test
  void obtenerTodos_devuelveCincoElementosIniciales() {
    DatosDao datosDao = new DatosDao();
    List<Dato> datos = datosDao.obtenerTodos();

    assertEquals(5, datos.size());
  }

  @Test
  void obtenerPorCodigo_filtraCorrectamente() {
    DatosDao datosDao = new DatosDao();
    List<Dato> filtrados = datosDao.obtenerPorCodigo(1);

    assertEquals(1, filtrados.size());
    assertEquals(1, filtrados.get(0).getCodigo());
  }

  @Test
  void obtenerPorCodigo_inexistenteDevuelveListaVacia() {
    DatosDao datosDao = new DatosDao();
    List<Dato> filtrados = datosDao.obtenerPorCodigo(999);

    assertTrue(filtrados.isEmpty());
  }

  @Test
  void obtenerPorDescripcion_esCaseInsensitive() {
    DatosDao datosDao = new DatosDao();
    List<Dato> filtrados = datosDao.obtenerPorDescripcion("pRiMeR");

    assertEquals(1, filtrados.size());
    assertEquals(1, filtrados.get(0).getCodigo());
  }

  @Test
  void anyadir_agregaNuevoElemento() {
    DatosDao datosDao = new DatosDao();
    datosDao.anyadir(new Dato(6, "Elemento nuevo"));

    assertEquals(6, datosDao.obtenerTodos().size());
    assertEquals(1, datosDao.obtenerPorCodigo(6).size());
  }
}