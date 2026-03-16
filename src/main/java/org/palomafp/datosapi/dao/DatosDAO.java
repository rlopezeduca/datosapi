package org.palomafp.datosapi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.palomafp.datosapi.model.Dato;

public class DatosDAO {

	private final List<Dato> datos;

	public DatosDAO() {
		datos = new ArrayList<>();

		Dato dato1 = new Dato(1, "Primer elemento de prueba");
		Dato dato2 = new Dato(2, "Segundo elemento de prueba");
		Dato dato3 = new Dato(3, "Tercer elemento de prueba");
		Dato dato4 = new Dato(4, "Cuarto elemento de prueba");
		Dato dato5 = new Dato(5, "Quinto elemento de prueba");

		datos.add(dato1);
		datos.add(dato2);
		datos.add(dato3);
		datos.add(dato4);
		datos.add(dato5);
	}

	public List<Dato> obtenerTodos() {
		return new ArrayList<>(datos);
	}

	public List<Dato> obtenerPorCodigo(int codigo) {
		List<Dato> datosFiltrados = new ArrayList<>();

		for (Dato dato : datos) {
			if (dato.getCodigo() == codigo) {
				datosFiltrados.add(dato);
			}
		}
		return datosFiltrados;
	}

	public List<Dato> obtenerPorDescripcion(String descripcion) {
		List<Dato> datosFiltrados = new ArrayList<>();
		String descripcionNormalizada = descripcion.toLowerCase(Locale.ROOT);

		for (Dato dato : datos) {
			if (dato.getDescripcion().toLowerCase(Locale.ROOT).contains(descripcionNormalizada)) {
				datosFiltrados.add(dato);
			}
		}
		return datosFiltrados;
	}

	public void anyadir(Dato dato) {
		datos.add(dato);
	}
}
