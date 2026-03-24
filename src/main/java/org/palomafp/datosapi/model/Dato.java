package org.palomafp.datosapi.model;

/** Modelo que representa un dato con codigo y descripcion. */
public class Dato {

  private int codigo;
  private String descripcion;

  public Dato() {
  }

  public Dato(int codigo, String descripcion) {
    this.codigo = codigo;
    this.descripcion = descripcion;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}