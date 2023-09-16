package org.example.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.example.controller.HuespedController;
import org.example.controller.ReservaController;
import org.example.controller.UsuarioController;
import org.example.modelo.Huesped;
import org.example.modelo.Reserva;
import org.example.modelo.Usuario;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private JTable tbUsuarios;
    private DefaultTableModel modeloHuesped;
    private DefaultTableModel modeloUsuario;
    private DefaultTableModel modeloReserva;
    private ReservaController reservaController;
    private HuespedController huespedController;
    private UsuarioController usuarioController;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    String reserva;
    String huespedes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Busqueda frame = new Busqueda();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        this.reservaController = new ReservaController();
        this.huespedController = new HuespedController();
        this.usuarioController = new UsuarioController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(tbReservas);



        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblTitulo = new JLabel("SISTEMA DE BÚSQUEDA");
        lblTitulo.setBounds(331, 62, 280, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBounds(20, 169, 865, 328);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        contentPane.add(panel);



        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Id de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        LlenarTablaHuespedes();


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloReserva = (DefaultTableModel) tbReservas.getModel();
        modeloReserva.addColumn("Numero de Reserva");
        modeloReserva.addColumn("Fecha Check In");
        modeloReserva.addColumn("Fecha Check Out");
        modeloReserva.addColumn("Valor");
        modeloReserva.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        LlenarTablaReservas();


        tbUsuarios = new JTable();
        tbUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbUsuarios.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloUsuario = (DefaultTableModel) tbUsuarios.getModel();
        modeloUsuario.addColumn("Id de Huesped");
        modeloUsuario.addColumn("Nombre");
        modeloUsuario.addColumn("Apellido");
        modeloUsuario.addColumn("Usuario");
        modeloUsuario.addColumn("Contraseña");
        JScrollPane scroll_tableUsuarios = new JScrollPane(tbUsuarios);
        panel.addTab("Usuarios", new ImageIcon(Busqueda.class.getResource("/imagenes/usuarios2.png")), scroll_tableUsuarios, null);
        scroll_tableUsuarios.setVisible(true);
        LlenarTablaUsuarios();

        JLabel logo = new JLabel("");
        logo.setBounds(56, 51, 104, 107);
        logo.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        contentPane.add(logo);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(539, 159, 193, 2);
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.getSelectedIndex() == 0) {
                    if (txtBuscar.getText().isEmpty()) {
                        LlenarTablaHuespedes();
                    } else {
                        LlenarTablaHuespedesByApellido();
                    }
                } else if (panel.getSelectedIndex() == 1) {
                    if (txtBuscar.getText().isEmpty()) {
                        LlenarTablaReservas();
                    } else {
                        LlenarTablaReservasId();
                    }
                } else if (panel.getSelectedIndex() == 2) {
                    if (txtBuscar.getText().isEmpty()) {
                        LlenarTablaUsuarios();
                    } else {
                        LlenarTablaUsuariosId();
                    }
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modificar();
            }
        });
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminar();
                limpiarTabla();
                LlenarTablaReservas();
                LlenarTablaHuespedes();
            }
        });
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }
    private List<Reserva> BuscarReservas() {
        return this.reservaController.listar();
    }

    private List<Reserva> BuscarReservasId() {
        return this.reservaController.listar(Integer.valueOf(txtBuscar.getText()));
    }
    private List<Huesped> BuscarHuespedes() {
        return this.huespedController.listar();
    }

    private List<Huesped> BuscarHuespedesByApellido() {
        return this.huespedController.listar((txtBuscar.getText()));
    }

    private List<Usuario> BuscarUsuarios() {
        return this.usuarioController.listar();
    }

    private List<Usuario> BuscarUsuariosByUser() {
        return this.usuarioController.listar((txtBuscar.getText()));
    }



    private void limpiarTabla() {
        ((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
        ((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
        ((DefaultTableModel) tbUsuarios.getModel()).setRowCount(0);
    }
    private void LlenarTablaReservas() {
modeloReserva.setRowCount(0);
        List<Reserva> reserva = BuscarReservas();
        try {
            for (Reserva reservas : reserva) {
                modeloReserva.addRow(new Object[] {
                        reservas.getId(),
                        reservas.getFechaentrada(),
                        reservas.getFechasalida(),
                        reservas.getValor(),
                        reservas.getFormapago()
                });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void LlenarTablaReservasId() {
        modeloReserva.setRowCount(0);
        List<Reserva> reserva = BuscarReservasId();
        try {
            for (Reserva reservas : reserva) {
                modeloReserva.addRow(new Object[] {
                        reservas.getId(),
                        reservas.getFechaentrada(),
                        reservas.getFechasalida(),
                        reservas.getValor(),
                        reservas.getFormapago()
                });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void LlenarTablaHuespedes() {
        modeloHuesped.setRowCount(0);
        List<Huesped> huesped = BuscarHuespedes();
        try {
            for (Huesped huespedes : huesped) {
                modeloHuesped.addRow(new Object[] {
                        huespedes.getId(),
                        huespedes.getNombre(),
                        huespedes.getApellido(),
                        huespedes.getFechanacimiento(),
                        huespedes.getNacionalidad(),
                        huespedes.getTelefono(),
                        huespedes.getIdReserva()
                });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void LlenarTablaHuespedesByApellido() {
        modeloHuesped.setRowCount(0);
        List<Huesped> huesped = BuscarHuespedesByApellido();
        try {
            for (Huesped huespedes : huesped) {
                modeloHuesped.addRow(new Object[] {
                        huespedes.getId(),
                        huespedes.getNombre(),
                        huespedes.getApellido(),
                        huespedes.getFechanacimiento(),
                        huespedes.getNacionalidad(),
                        huespedes.getTelefono(),
                        huespedes.getIdReserva()
                });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void LlenarTablaUsuarios() {
        modeloUsuario.setRowCount(0);
        List<Usuario> usuario = BuscarUsuarios();
        try {
            for (Usuario usuarios : usuario) {
                modeloUsuario.addRow(new Object[] {
                        usuarios.getId(),
                        usuarios.getNombre(),
                        usuarios.getApellido(),
                        usuarios.getUsuario(),
                        usuarios.getContrasena()
                });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void LlenarTablaUsuariosId() {
        modeloUsuario.setRowCount(0);
        List<Usuario> usuario = BuscarUsuariosByUser();
        try {
            for (Usuario usuarios : usuario) {
                modeloUsuario.addRow(new Object[] {
                        usuarios.getId(),
                        usuarios.getNombre(),
                        usuarios.getApellido(),
                        usuarios.getUsuario(),
                        usuarios.getContrasena()
                });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void eliminar() {
        if(validaFilaReserva() && validaFilaHuesped() && validaFilaUsuario()){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un registro de la tabla reservas o huespedes");
            return;
        }
        if (validaFilaReserva()) {
            eliminarHuesped();
        } else if (validaFilaHuesped()) {
            eliminarReserva();
        } else {
            eliminarUsuario();
        }
    }

    private void modificar() {
        if(validaFilaReserva() && validaFilaHuesped() && validaFilaUsuario()){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un registro de la tabla reservas o huespedes");
            return;
        }
        if (validaFilaReserva()) {
            modificarHuesped();
        } else if (validaFilaHuesped()) {
            modificarReserva();
        } else {
            modificarUsuario();
        }
    }

    private boolean validaFilaReserva() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }

    private boolean validaFilaHuesped() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private boolean validaFilaUsuario() {
        return tbUsuarios.getSelectedRowCount() == 0 || tbUsuarios.getSelectedColumnCount() == 0;
    }

    private void eliminarReserva() {
        Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    int id = Integer.parseInt(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    this.reservaController.eliminar(id);
                    JOptionPane.showMessageDialog(this, String.format("Reserva eliminada con éxito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
    }

    private void modificarReserva() {
        Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    Date fechaEntrada = Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1).toString());
                    Date fechaSalida = Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2).toString());
                    Integer valor = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3).toString());
                    String formaPago = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);

                    this.reservaController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
                    JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
    }


    private void eliminarHuesped() {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    int id = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    int idReserva = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
                    this.huespedController.eliminar(id, idReserva);
                    JOptionPane.showMessageDialog(this, String.format("Huesped eliminado con éxito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
    }

    private void modificarHuesped() {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                    Date fechaN = Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
                    String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
                    Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

                    this.huespedController.modificar(id, nombre,apellido,fechaN, nacionalidad, telefono, idReserva);
                    JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
    }

    private void eliminarUsuario() {
        Optional.ofNullable(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), tbUsuarios.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    int id = Integer.parseInt(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 0).toString());
                    this.usuarioController.eliminar(id);
                    JOptionPane.showMessageDialog(this, String.format("Usuario eliminado con éxito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
    }

    private void modificarUsuario() {
        Optional.ofNullable(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), tbUsuarios.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    Integer id = Integer.valueOf(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 0).toString());
                    String nombre = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 1);
                    String apellido = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 2);
                    String usuario = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 3);
                    String contrasena = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 4);

                    this.usuarioController.modificar(id, nombre,apellido,usuario, contrasena);
                    JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
    }

}