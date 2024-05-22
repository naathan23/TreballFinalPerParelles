import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazLector extends JFrame {
    public InterfazLector() {
        
        setTitle("interfaz lector");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton boton1 = new JButton("ver libros");
        boton1.setBounds(50, 50, 250, 30);
        add(boton1);

        JButton boton2 = new JButton("ver un libro");
        boton2.setBounds(50, 100, 250, 30);
        add(boton2);

        JButton boton3 = new JButton("ver mis prestamos");
        boton3.setBounds(50, 150, 250, 30);
        add(boton3);


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
                VerMisPrestamos verMisPrestamos = new VerMisPrestamos();
                verMisPrestamos.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazLector interfazLector = new InterfazLector();
                interfazLector.setVisible(true);
            }
        });
    }
}
