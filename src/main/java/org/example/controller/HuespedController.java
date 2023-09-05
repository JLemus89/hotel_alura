package org.example.controller;

import org.example.dao.HuespedDAO;
import org.example.factory.ConnectionFactory;
import org.example.modelo.Huesped;

import java.util.Date;
import java.util.List;

public class HuespedController {
   private final HuespedDAO huespedDAO;

    public HuespedController() {
        var factory = new ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
    }

    public List<Huesped> listar() {
        return huespedDAO.listar();
    }

    public List<Huesped> listar(Integer id) {
        return huespedDAO.listar(id);
    }

    public int eliminar(Integer id, Integer idReserva) {
        return huespedDAO.eliminar(id, idReserva);
    }

    public int modificar(String nombre, String apellido, Date fechanacimiento, String nacionalidad, Integer telefono) {
        return huespedDAO.modificar( nombre, apellido, fechanacimiento, nacionalidad, telefono);
    }

    public void guardar(Huesped huesped, Integer idReserva) {
        huesped.setIdReserva(idReserva);
        huespedDAO.guardar(huesped);
    }
}
