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

/**
 *** @param args the command line arguments
 */
public class Cine {
    private Reserva reserva;

    public static void main(String[] args) {
        new Cine();
    }

    public Cine() {
        iniciarSistema();

        while (true) {
            String op = JOptionPane.showInputDialog(null,
                    "Menú Principal:\n" +
                    "1. Ver asientos\n" +
                    "2. Reservar asiento\n" +
                    "3. Salir\n" +
                    "Ingrese su opción:"
            );

            if (op == null || op.equals("3")) {
                JOptionPane.showMessageDialog(null, "Hasta pronto!");
                break;
            }
            // Luego aquí se va a encontrar el resto de las opciones: películas, tandas, etc.
            switch (op) {
                case "1":
                    verAsientos();
                    break;
                case "2":
                    reservarAsiento();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error, ingrese la opcion otra vez.");
            }
        }
    }

    private void iniciarSistema() {
        boolean config = false;

        while (!config) {
            try {
                int filas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas para la sala:"));
                int columnas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas para la sala:"));
                // Valida que la fila y columna ingresadas sean mayores a 0
                if (filas <= 0 || columnas <= 0) {
                    JOptionPane.showMessageDialog(null, "El número de filas y columnas debe ser mayor a 0. Intente nuevamente.");
                    continue;
                }

                reserva = new Reserva(filas, columnas);
                config = true;
            } catch (NumberFormatException e) {
                int opcion = JOptionPane.showConfirmDialog(null,
                        "Desea intentar nuevamente?",
                        "Error",
                        // Permite respuesta de sí o no
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "El programa no se configuro");
                    break;
                }
            }
        }
    }

    private void verAsientos() {
        JOptionPane.showMessageDialog(null,
                reserva != null ? reserva.toString() : "No configuro la sala.",
                "Asientos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void reservarAsiento() {
        if (reserva == null) {
            JOptionPane.showMessageDialog(null, "No se  configuro la sala");
            return;
        }

        try {
            int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de fila:"));
            int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de columna:"));

            // indices del usuario.
            String resultado = reserva.intentarReservar(fila, columna);
            JOptionPane.showMessageDialog(null, resultado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Intente otra vez");
        }
    }
}

