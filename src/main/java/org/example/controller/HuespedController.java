package org.example.controller;

import org.example.dao.HuespedDAO;
import org.example.factory.ConnectionFactory;
import org.example.modelo.Huesped;

import java.util.Date;
import java.util.List;

public class HuespedController {
   private HuespedDAO huespedDAO;

    public HuespedController() {
        var factory = new ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
    }

    public List<Huesped> listar() {
        return huespedDAO.listar();
    }

    public List<Huesped> listar(Integer cc) {
        return huespedDAO.listar(cc);
    }

    public int eliminar(Integer cc) {
        return huespedDAO.eliminar(cc);
    }

    public int modificar(Integer cc, String nombre, String apellido, Date fechanacimiento, String nacionalidad, Integer telefono, Integer idreserva) {
        return huespedDAO.modificar(cc, nombre, apellido, fechanacimiento, nacionalidad, telefono, idreserva);
    }

    public void guardar(Huesped huesped) {
        huespedDAO.guardar(huesped);
    }
}
