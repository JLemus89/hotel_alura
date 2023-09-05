package org.example.modelo;

import java.util.Date;

public class Huesped {
    private Integer id;
    private String nombre;
    private String apellido;
    private Date fechanacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer idReserva;

    public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }
}