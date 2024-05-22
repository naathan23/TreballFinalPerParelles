import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazBibliotecario extends JFrame {

    public InterfazBibliotecario() {
        setTitle("interfaz bibliotecario");
        setSize(950, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelLibros = new JPanel(new GridLayout(1, 5, 10, 10));
        JPanel panelUsuarios = new JPanel(new GridLayout(1, 5, 10, 10));
        JPanel panelPrestamos = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton boton1 = new JButton("ver libros");
        JButton boton2 = new JButton("ver un libro");
        JButton boton3 = new JButton("añadir un libro");
        JButton boton4 = new JButton("eliminar un libro");
        JButton boton5 = new JButton("modificar un libro");

        panelLibros.add(boton1);
        panelLibros.add(boton2);
        panelLibros.add(boton3);
        panelLibros.add(boton4);
        panelLibros.add(boton5);

        JButton boton6 = new JButton("ver usuarios");
        JButton boton7 = new JButton("ver un usuario");
        JButton boton8 = new JButton("añadir un usuario");
        JButton boton9 = new JButton("eliminar un usuario");
        JButton boton10 = new JButton("modificar un usuario");

        panelUsuarios.add(boton6);
        panelUsuarios.add(boton7);
        panelUsuarios.add(boton8);
        panelUsuarios.add(boton9);
        panelUsuarios.add(boton10);

        JButton boton11 = new JButton("hacer un prestamo");
        JButton boton12 = new JButton("devolver un prestamo");
        JButton boton13 = new JButton("ver prestamos");

        panelPrestamos.add(boton11);
        panelPrestamos.add(boton12);
        panelPrestamos.add(boton13);

        JPanel panelPrincipal = new JPanel(new GridLayout(3, 5, 10, 10));
        panelPrincipal.add(panelLibros);
        panelPrincipal.add(panelUsuarios);
        panelPrincipal.add(panelPrestamos);

        add(panelPrincipal);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerLibros verLibros = new VerLibros();
                verLibros.setVisible(true);
            }
        });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerUnLibro verUnLibro = new VerUnLibro();
                verUnLibro.setVisible(true);
            }
        });

        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarLibro agregarLibro = new AgregarLibro();
                agregarLibro.setVisible(true);
            }
        });

        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarLibro eliminarLibro = new EliminarLibro();
                eliminarLibro.setVisible(true);
            }
        });

        boton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarLibro modificarLibro = new ModificarLibro();
                modificarLibro.setVisible(true);
            }
        });

        boton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerUsuarios verUsuarios = new VerUsuarios();
                verUsuarios.setVisible(true);
            }
        });

        boton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerUnUsuario verUnUsuario = new VerUnUsuario();
                verUnUsuario.setVisible(true);
            }
        });

        boton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarUsuario agregarUsuario = new AgregarUsuario();
                agregarUsuario.setVisible(true);
            }
        });

        boton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarUsuario eliminarUsuario = new EliminarUsuario();
                eliminarUsuario.setVisible(true);
            }
        });

        boton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarUsuario modificarUsuario = new ModificarUsuario();
                modificarUsuario.setVisible(true);
            }
        });

        boton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HacerUnPrestamo hacerUnPrestamo = new HacerUnPrestamo();
                hacerUnPrestamo.setVisible(true);
            }
        });

        boton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DevolverUnPrestamo devolverUnPrestamo = new DevolverUnPrestamo();
                devolverUnPrestamo.setVisible(true);
            }
        });

        boton13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerPrestamos verPrestamos = new VerPrestamos();
                verPrestamos.setVisible(true);
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazBibliotecario interfazBibliotecario = new InterfazBibliotecario();
                interfazBibliotecario.setVisible(true);
            }
        });
    }
}