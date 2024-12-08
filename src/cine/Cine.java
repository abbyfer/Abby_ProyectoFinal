/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cine;

/**
 *
 * @author afern
 */
import javax.swing.*; 
import java.util.*;   
import java.util.Scanner; 
import java.awt.*;
import java.util.Random;
public class Cine {
    private Reserva reserva; // Objeto para las reservas de asientos
    private String peliculaSeleccionada; // Película elegida
    private String horarioSeleccionado;  // Horario elegido
    //datos del usuario
    private String nombre;
    private int edad; 
    private String genero;  

    public static void main(String[] args) {
        mostrarPortada(); 
    }

    // Muestra la bienvenida con el logo
    public static void mostrarPortada() {
        JFrame frame = new JFrame("Cine Nebula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); 
        frame.setLayout(new BorderLayout());

        //Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(44, 62, 80));
        panelPrincipal.setLayout(new BorderLayout());

        //logo
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(Cine.class.getResource("/cine/logo/cine_logo.png"));
        Image img = icon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        //titulo
        JLabel titulo = new JLabel("¡Bienvenido a Cine Nebula!", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 36));
        titulo.setForeground(new Color(236, 240, 241));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        //boton de inicio
        JButton botonInicio = new JButton("Ingresar!");
        botonInicio.setFont(new Font("Monospaced", Font.BOLD, 24));
        botonInicio.setBackground(new Color(39, 174, 96));
        botonInicio.setForeground(Color.WHITE);
        botonInicio.addActionListener(e -> {
            frame.dispose();  // Cierra la ventana actual
            new Cine();       // Inicia el programa principal
        });

        // Configuracion de paneles
        panelPrincipal.add(logo, BorderLayout.NORTH);
        panelPrincipal.add(titulo, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(44, 62, 80));
        panelBoton.add(botonInicio);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        frame.add(panelPrincipal); //De agrega el panel a la ventana
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setVisible(true);  // Muestra la ventana
    }

    // Constructor principal
    public Cine() {
        obtenerDatos();      // Solicita los datos del usuario
        seleccionarPYH();  // Permite elegir la película y horario
        inicializarSistema();       // Inicializa la reserva de asientos

        // Bucle del menu
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    null,
                    "Menu:\n" +
                    "1. Ver asientos\n" +
                    "2. Reservar asiento\n" +
                    "3. Realizar rifa\n" +
                    "4. Calcular porcentaje de ocupación\n" +
                    "5. Seleccionar otra película\n" +
                    "6. Salir\n" +
                    "Ingrese su opción:"
            );

            if (opcion == null || opcion.equals("6")) {
                JOptionPane.showMessageDialog(null, "Byeee!");
                break;  // Sale del programa
            }

            //opciones del menú
            switch (opcion) {
                case "1":
                    verSilla(); // Muestra el estado actual de las sillas
                    break;
                case "2":
                    reservarSilla(); // Permite reservar una silla
                    break;
                case "3":
                    realizarRifa(); // hace una rifa con los asientos reservados
                    break;
                case "4":
                    mostrarPorcentajeOcupacion(); //ocupación actual
                    break;
                case "5": 
                    seleccionarPYH();  // Cambia la película y horario
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
            }
        }
    }

    // Método para solicitar los datos del usuario
    private void obtenerDatos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------Registro de Usuario -------");
        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine();

        System.out.print("Ingrese su edad: ");
        edad = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Ingrese su genero (Masc/Fem): ");
        genero = scanner.nextLine();

        System.out.printf("\nBienvenid@, "+ nombre + ", " + edad +", "+ genero +" "+ ". Disfruta de tu experiencia en Cine Nebula!\n");
    }

    // Método de seleccion de una película y horario
    private void seleccionarPYH() {
        String[] peliculas = {"Avengers: EndGame", "How To Lose A Guy in 10 Days", "White Chicks", "Harry Potter and the Deathly Hallows – Part 2"};
        String[] horarios = {"10:00", "14:00", "21:00"};

        peliculaSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione una pelicula:", "Peliculas",
                JOptionPane.QUESTION_MESSAGE, null, peliculas, peliculas[0]);

        if (peliculaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una película");
            seleccionarPYH();  //VUelve a solicitar
            return;
        }

        horarioSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un horario:", "Horarios",
                JOptionPane.QUESTION_MESSAGE, null, horarios, horarios[0]);

        if (horarioSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un horario para continuar.");
            seleccionarPYH();  //Vuelve a solicitar
            return;
        }

        JOptionPane.showMessageDialog(null, "Película seleccionada: " + peliculaSeleccionada + "\nHorario: " + horarioSeleccionado);
    }

    // Inicializa el sistema de reserva
    private void inicializarSistema() {
        try {
            int filas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de filas:"));
            int columnas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de columnas:"));
            reserva = new Reserva(filas, columnas, peliculaSeleccionada, horarioSeleccionado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    // Muestra el estado actual  de las sillas reservadas
    private void verSilla() {
        JOptionPane.showMessageDialog(null, reserva.mostrarSilla(), "Estado de las Sillas", JOptionPane.INFORMATION_MESSAGE);
    }

    //Reservar un asiento específico
    private void reservarSilla() {
        try {
            int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de fila:")) - 1;
            int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columna:")) - 1;

            if (reserva.reservarSilla(fila, columna)) {
                JOptionPane.showMessageDialog(null, "Silla reservada exitosamente!");
            } else {
                JOptionPane.showMessageDialog(null, "La silla ya está ocupada o no es válida");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    // Hace una rifa con los asientos reservados
    private void realizarRifa() {
        if (reserva.calcularPorcentaje() == 0) {
            JOptionPane.showMessageDialog(null, "No hay sillas reservadas. No se puede hacer la rifa");
            return;
        }
        String ganador = reserva.Rifa();
        JOptionPane.showMessageDialog(null, "El ganador de la rifa es la silla: " + ganador + "!");
    }

    // Calcula el porcentaje de ocupación actual
    private void mostrarPorcentajeOcupacion() {
        double porcentaje = reserva.calcularPorcentaje();
        JOptionPane.showMessageDialog(null, "Porcentaje de ocupación: " + porcentaje + "%");
    }
}
