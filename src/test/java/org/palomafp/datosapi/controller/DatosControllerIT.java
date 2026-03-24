package org.palomafp.datosapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Pruebas de integración para el controlador 'DatosController'.
 * Estas pruebas verifican el comportamiento del controlador en conjunto con la
 * lógica de negocio y el DAO.
 */
class DatosControllerIT {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new DatosController()).build();
  }

  @Test
  void getDatos_devuelveListaInicial() throws Exception {
    mockMvc.perform(get("/api/datos"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(5))
        .andExpect(jsonPath("$[0].codigo").value(1));
  }

  @Test
  void getDatosPorCodigo_filtraPorCodigo() throws Exception {
    mockMvc.perform(get("/api/datos").param("codigo", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].codigo").value(2));
  }

  @Test
  void getDatosPorDescripcion_filtraSinImportarMayusculas() throws Exception {
    mockMvc.perform(get("/api/datos").param("descripcion", "qUiNtO"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].codigo").value(5));
  }

  @Test
  void postDato_agregaElementoYPermiteConsultarlo() throws Exception {
    mockMvc.perform(post("/api/datos")
        .param("codigo", "6")
        .param("descripcion", "Elemento insertado"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/api/datos").param("codigo", "6"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].descripcion").value("Elemento insertado"));
  }
}