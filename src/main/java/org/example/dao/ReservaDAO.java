package org.example.dao;

import org.example.modelo.Reserva;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDAO {
    private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }

    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM RESERVAS");
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var reserva = new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("FECHAINGRESO"),
                                resultSet.getDate("FECHASALIDA"),
                                resultSet.getInt("VALOR"),
                                resultSet.getString("FORMADEPAGO")
                        );
                        resultado.add(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar reservas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public List<Reserva> listar(int id) {
        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM RESERVAS WHERE ID = ?");
            try (statement) {
                statement.setInt(1, id);
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var reserva = new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("FECHAINGRESO"),
                                resultSet.getDate("FECHASALIDA"),
                                resultSet.getInt("VALOR"),
                                resultSet.getString("FORMADEPAGO")
                        );
                        resultado.add(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar reservas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al eliminar reserva",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer id, Date fechaentrada, Date fechasalida, Integer valor, String formapago) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET FECHAENTRADA = ?, FECHASALIDA = ?, VALOR = ?, FORMAPAGO = ? WHERE ID = ?");

            try (statement) {
                statement.setDate(1, (java.sql.Date) fechaentrada);
                statement.setDate(2, (java.sql.Date) fechasalida);
                statement.setInt(3, valor);
                statement.setString(4, formapago);
                statement.setInt(5, id);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al modificar reserva",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void guardar(Reserva reserva) {
        String sql = "INSERT INTO RESERVAS (FECHAINGRESO, FECHASALIDA, VALOR, FORMADEPAGO) VALUES (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            try (statement) {
                statement.setDate(1, (java.sql.Date) reserva.getFechaentrada());
                statement.setDate(2, (java.sql.Date) reserva.getFechasalida());
                statement.setInt(3, reserva.getValor());
                statement.setString(4, reserva.getFormapago());
                statement.execute();

                try (var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idGenerado = generatedKeys.getLong(1);
                        // Asignar el ID generado a la reserva
                        reserva.setId((int) idGenerado);
                    } else {
                        throw new SQLException("No se ha generado ningún ID para la reserva.");
                    }
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar reserva",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
