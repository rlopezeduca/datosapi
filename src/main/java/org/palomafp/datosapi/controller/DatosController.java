package org.palomafp.datosapi.controller;

import java.util.List;
import org.palomafp.datosapi.dao.DatosDAO;
import org.palomafp.datosapi.model.Dato;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")			//No necesario, sirve para organizar la ruta del endpoint del API Rest
@CrossOrigin(origins = "*")		//Sirve para permitir solicitudes desde cualquier origen
public class DatosController {

	private DatosDAO datosDAO;

	public DatosController() {
		this.datosDAO = new DatosDAO();
	}

	@GetMapping("/datos")		//Sirve para mapear las solicitudes GET a la ruta "/datos"
	public List<Dato> obtenerDatos() {
		return datosDAO.obtenerTodos();
	}

	@GetMapping(value = "/datos", params = "codigo")
	public List<Dato> obtenerDatoPorCodigo(@RequestParam(name = "codigo") int codigo) {
		return datosDAO.obtenerPorCodigo(codigo);
	}

	@GetMapping(value = "/datos", params = "descripcion")
	public List<Dato> obtenerDatoPorDescripcion(@RequestParam(name = "descripcion") String descripcion) {
		return datosDAO.obtenerPorDescripcion(descripcion);
	}

	@PostMapping(value = "/datos", params = { "codigo", "descripcion" })
	public void anyadirDato(@RequestParam(name = "codigo") int codigo,
			@RequestParam(name = "descripcion") String descripcion) {
		Dato dato = new Dato(codigo, descripcion);
		datosDAO.anyadir(dato);
	}
	
}