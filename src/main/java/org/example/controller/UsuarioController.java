package org.example.controller;


import org.example.dao.UsuarioDAO;
import org.example.factory.ConnectionFactory;
import org.example.modelo.Usuario;
import org.example.views.Login;
import org.example.views.MenuPrincipal;
import org.example.views.MenuUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UsuarioController implements ActionListener {
    private final UsuarioDAO usuarioDAO;
    private Login login;

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = login.getTxtUsuario();
        String contrasena = login.getTxtContrasena();

        List<Usuario> usuarios = listar();
    }


    public UsuarioController() {
        var factory = new ConnectionFactory();
        this.usuarioDAO = new UsuarioDAO(factory.recuperaConexion());
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }


    public List<Usuario> listar(String usuario) {
        return usuarioDAO.listar(usuario);
    }

    public int eliminar(Integer id) {
        return usuarioDAO.eliminar(id);
    }

    public int modificar(Integer id, String nombre, String apellido, String usuario, String contrasena) {
        return usuarioDAO.modificar(id, nombre, apellido, usuario, contrasena);
    }

    public void guardar(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }
}