/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine;

/**
 *
 * @author afern
 */
import java.util.ArrayList;
public class Reserva {

    private boolean[][] silla;
    public Reserva(int filas, int columnas) {
        silla = new boolean[filas][columnas];
    }

    public String intentarReservar(int filaUsuario, int columnaUsuario) {
        //para que sea mas amigable, las filas y columnas inician en 1 en vez de 0
        int fila = filaUsuario - 1;
        int columna = columnaUsuario - 1;

        if (fila < 0 || fila >= silla.length || columna < 0 || columna >= silla[0].length) {
            return "La silla no es valida";
        }
        if (silla[fila][columna]) {
            return "La silla ya esta ocupada";
        }
        silla[fila][columna] = true;
        return "Silla reservada exitosamente!";
    }
//imprime la salas con una x sobre las sillas ocupadas
    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < silla.length; i++) {
            for (int j = 0; j < silla[i].length; j++) {
                if (silla[i][j]) {
                    resultado += "[X] ";
                } else {
                    resultado += "[ ] ";
                }
            }
            resultado += "\n";
        }
        return resultado;
    }
}
