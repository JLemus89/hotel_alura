package org.example.modelo;

import java.sql.Date;

public class Reserva {
    private Integer id;
    private Date fechaentrada;
    private Date fechasalida;
    private Integer valor;
    private String formapago;

    public Reserva(int id, Date fechaentrada, Date fechasalida, int valor, String formapago) {
        this.id = id;
        this.fechaentrada = fechaentrada;
        this.fechasalida = fechasalida;
        this.valor = valor;
        this.formapago = formapago;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaentrada() {
        return this.fechaentrada;
    }

    public void setFechaentrada(Date fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public Date getFechasalida() {
        return this.fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Integer getValor() {
        return this.valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getFormapago() {
        return this.formapago;
    }

    public void setFormapago(String FormaPago) {
        this.formapago = FormaPago;
    }

}
