package org.example.dao;

import org.example.modelo.Usuario;
import org.example.views.Login;
import org.example.views.MenuUsuario;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

public class UsuarioDAO {
    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM USUARIOS");
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var usuario = new Usuario(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("USUARIO"),
                                resultSet.getString("CONTRASENA")
                        );
                        resultado.add(usuario);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar usuarios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public List<Usuario> listar(String usuario) {
        List<Usuario> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM USUARIOS WHERE USUARIO = ?");
            try (statement) {
                statement.setString(1,usuario);
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        var usuario2 = new Usuario(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("USUARIO"),
                                resultSet.getString("CONTRASENA")
                        );
                        resultado.add(usuario2);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar usuarios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return resultado;
    }



    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM USUARIOS WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al eliminar usuario",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer id, String nombre, String apellido, String usr, String contrasena) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE USUARIOS SET "
                    + " NOMBRE = ?, "
                    + " APELLIDO = ?,"
                    + " USUARIO = ?,"
                    + " CONTRASENA = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, usr);
                statement.setString(4, contrasena);
                statement.setInt(5, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al modificar usuario",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void guardar(Usuario usuario) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO USUARIOS (NOMBRE, APELLIDO, USUARIO, CONTRASENA) VALUES (?, ?, ?, ?)");

            try (statement) {
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getApellido());
                statement.setString(3, usuario.getUsuario());
                statement.setString(4, usuario.getContrasena());
                statement.execute();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar usuario",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

}