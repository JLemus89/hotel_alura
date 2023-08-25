package org.example.controller;

import org.example.dao.ReservaDAO;
import org.example.factory.ConnectionFactory;
import org.example.modelo.Reserva;

import java.util.Date;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

   public ReservaController() {
            var factory = new ConnectionFactory();
            this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
        }

        public List<Reserva> listar() {
            return reservaDAO.listar();
        }

        public List<Reserva> listar(Integer id) {
            return reservaDAO.listar(id);
        }

        public int eliminar(Integer id) {
            return reservaDAO.eliminar(id);
        }

        public int modificar(Integer id, Date fechaentrada, Date fechasalida, Integer valor, String formapago) {
            return reservaDAO.modificar(id, fechaentrada, fechasalida, valor, formapago);
        }

        public void guardar(Reserva reserva) {
            reservaDAO.guardar(reserva);
        }

}
