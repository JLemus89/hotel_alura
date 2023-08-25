package org.example.dao;

import org.example.modelo.Huesped;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuespedDAO {
    private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM HUESPEDES");
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var huesped = new Huesped(
                                resultSet.getInt("CC"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getDate("FECHANACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getInt("TELEFONO"),
                                resultSet.getInt("IDRESERVA")

                        );
                        resultado.add(huesped);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar huespedes",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public List<Huesped> listar(Integer cc) {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM HUESPEDES WHERE CC = ?");
            try (statement) {
                statement.setInt(1, cc);
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var huesped = new Huesped(
                                resultSet.getInt("CC"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getDate("FECHANACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getInt("TELEFONO"),
                                resultSet.getInt("IDRESERVA")

                        );
                        resultado.add(huesped);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar huespedes",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int eliminar(Integer cc) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE CC = ?");

            try (statement) {
                statement.setInt(1, cc);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al eliminar huesped",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer cc, String nombre, String apellido, Date fechanacimiento, String nacionalidad, Integer telefono, Integer idReserva) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET NOMBRE = ?, APELLIDO = ?, FECHANACIMIENTO = ?, NACIONALIDAD = ?, TELEFONO = ?, IDRESERVA = ? WHERE CC = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, (java.sql.Date) fechanacimiento);
                statement.setString(4, nacionalidad);
                statement.setInt(5, telefono);
                statement.setInt(6, idReserva);
                statement.setInt(7, cc);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al modificar huesped",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void guardar(Huesped huesped) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO HUESPEDES (CC, NOMBRE, APELLIDO, FECHANACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA) VALUES (?, ?, ?, ?, ?, ?, ?)");

            try (statement) {
                statement.setInt(1, huesped.getCc());
                statement.setString(2, huesped.getNombre());
                statement.setString(3, huesped.getApellido());
                statement.setDate(4, (java.sql.Date) huesped.getFechanacimiento());
                statement.setString(5, huesped.getNacionalidad());
                statement.setInt(6, huesped.getTelefono());
                statement.setInt(7, huesped.getIdReserva());
                statement.execute();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar huesped",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
