package org.palomafp.datosapi.controller;

import java.util.List;
import org.palomafp.datosapi.dao.DatosDao;
import org.palomafp.datosapi.model.Dato;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controlador REST para gestionar los datos de la API. */
@RestController
@RequestMapping("/api/datos") //En vez de añadir @GetMapping("/datos") en cada método
@CrossOrigin(origins = "*")
public class DatosController {

  private DatosDao datosDao;

  public DatosController() {
    this.datosDao = new DatosDao();
  }

  /*
  // Incluido en OPCIÓN 2
  @GetMapping
  public List<Dato> obtenerDatos() {
    return datosDao.obtenerTodos();
  }
  */

  // OPCIÓN 1: Sobrecarga por parámetros específicos -> INCOMPATIBLE CON OPCIÓN 2
  // URL: GET /api/datos?codigo=500
  @GetMapping(params = {"codigo", "!descripcion"})
  public List<Dato> obtenerDatosPorCodigo(@RequestParam int codigo) {
    return datosDao.obtenerPorCodigo(codigo);
  }

  @GetMapping(params = {"descripcion", "!codigo"})
  public List<Dato> obtenerDatosPorDescripcion(@RequestParam String descripcion) {
    return datosDao.obtenerPorDescripcion(descripcion);
  }

  // OPCIÓN 2: Búsqueda flexible (Filtros opcionales) -> INCOMPATIBLE CON OPCIÓN 1
  // URL: GET /api/datos?codigo=10&descripcion=laptop
  @GetMapping
  public List<Dato> obtenerDatosConFiltros(
          @RequestParam(required = false) Integer codigo,
          @RequestParam(required = false) String descripcion) {

    if (codigo == null && descripcion == null) {
      return datosDao.obtenerTodos();
    } else {
      return datosDao.obtenerPorCodigoDescripcion(codigo, descripcion);
    }
  }

  // OPCIÓN 3: Path Variable (Recurso único)
  // URL: GET /api/datos/500 (donde 500 se corresponde con el código)
  @GetMapping("/{codigo}")
  public Dato obtenerPorId(@PathVariable int codigo) {
    List<Dato> datos = datosDao.obtenerPorCodigo(codigo);
    return datos.isEmpty() ? null : datos.get(0);
  }

  @PostMapping(params = {"codigo", "descripcion"})
  public void anyadirDato(
      @RequestParam int codigo,
      @RequestParam String descripcion) {
    Dato dato = new Dato(codigo, descripcion);
    datosDao.anyadir(dato);
  }

}