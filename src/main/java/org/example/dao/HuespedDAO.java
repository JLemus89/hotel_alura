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
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getDate("FECHADENACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("TELEFONO"),
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

    public List<Huesped> listar(Integer id) {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM HUESPEDES WHERE ID = ?");
            try (statement) {
                statement.setInt(1, id);
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var huesped = new Huesped(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getDate("FECHADENACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("TELEFONO"),
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

    public int eliminar(Integer id, Integer idReserva) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
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

    public int modificar(String nombre, String apellido, Date fechanacimiento, String nacionalidad, Integer telefono) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET NOMBRE = ?, APELLIDO = ?, FECHANACIMIENTO = ?, NACIONALIDAD = ?, TELEFONO = ?, IDRESERVA = ? WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, (java.sql.Date) fechanacimiento);
                statement.setString(4, nacionalidad);
                statement.setInt(5, telefono);
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
        String sql = "INSERT INTO HUESPEDES (NOMBRE, APELLIDO, FECHADENACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            final PreparedStatement statement = con.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, new java.sql.Date(huesped.getFechanacimiento().getTime()));
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());
                statement.execute();

                try (var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        huesped.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException(
                                "Fallo al obtener el id de huesped");
                    }
                }
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
