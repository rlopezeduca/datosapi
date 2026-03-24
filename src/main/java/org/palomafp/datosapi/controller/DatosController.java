package org.palomafp.datosapi.controller;

import java.util.List;
import org.palomafp.datosapi.dao.DatosDao;
import org.palomafp.datosapi.model.Dato;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controlador REST para gestionar los datos de la API. */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DatosController {

  private DatosDao datosDao;

  public DatosController() {
    this.datosDao = new DatosDao();
  }

  @GetMapping("/datos")
  public List<Dato> obtenerDatos() {
    return datosDao.obtenerTodos();
  }

  @GetMapping(value = "/datos", params = "codigo")
  public List<Dato> obtenerDatoPorCodigo(@RequestParam(name = "codigo") int codigo) {
    return datosDao.obtenerPorCodigo(codigo);
  }

  @GetMapping(value = "/datos", params = "descripcion")
  public List<Dato> obtenerDatoPorDescripcion(
      @RequestParam(name = "descripcion") String descripcion) {
    return datosDao.obtenerPorDescripcion(descripcion);
  }

  @PostMapping(value = "/datos", params = {"codigo", "descripcion"})
  public void anyadirDato(
      @RequestParam(name = "codigo") int codigo,
      @RequestParam(name = "descripcion") String descripcion) {
    Dato dato = new Dato(codigo, descripcion);
    datosDao.anyadir(dato);
  }

}