/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine;
import java.util.Random;

public class Reserva {
    private boolean[][] silla; // Matriz de los asientos
    private String pelicula;      // Nombre de la película
    private String horario;       // Horario 

    // Constructor de la sala de cine con filas y columnas
    public Reserva(int filas, int columnas, String pelicula, String horario) {
        this.silla = new boolean[filas][columnas];  // Inicializa la matriz de asientos
        this.pelicula = pelicula;                     // Asigna la pelicula seleccionada
        this.horario = horario;                       // Asigna el horario seleccionado
        ReservasAleatorias();                 //reservas aleatorias al inicio
    }

    // Metodo de reserva para un asiento específico
    public boolean reservarSilla(int fila, int columna) {
        // Verifica si el asiento esta en los límites o ocupado
        if (fila < 0 || fila >= silla.length || columna < 0 || columna >= silla[0].length || silla[fila][columna]) {
            return false; // No se puede reservar
        }
        silla[fila][columna] = true;  // Marca el asiento como reservado
        return true;  // se pudo reservar
    }

    // Metodo que muestra el estado actual de las sillas
    public String mostrarSilla() {
    String resultado = "Pelicula: " + pelicula + "\nHorario: " + horario + "\n\n";
    // Recorre la matriz de asientos
    for (boolean[] fila : silla) {
        for (boolean asiento : fila) {
            // Muestra X para reservado y [ ] para disponible
            resultado += (asiento ? "[X] " : "[ ] ");
        }
        resultado += "\n";  // Salto de línea para cada fila
    }
    return resultado;  // Devuelve el resultado
}

    // Calcula el porcentaje de ocupación de los asientos
    public double calcularPorcentaje() {
        int ocupados = 0;  // Contador de sillas ocupados

        // cuenta las sillas ocupadas
        for (boolean[] fila : silla) {
            for (boolean asiento : fila) {
                if (asiento) ocupados++;  
            }
        }
        
        // Calcula el porcentaje de ocupación
        return (ocupados * 100.0) / (silla.length * silla[0].length);
    }

    // Selecciona con random un ganador
    public String Rifa() {
        Random random = new Random();  
        while (true) {
            int fila = random.nextInt(silla.length); 
            int columna = random.nextInt(silla[0].length); 
            // Si la silla esta reservada, es el ganador
            if (silla[fila][columna]) {
                return "Fila " + (fila + 1) + ", Columna " + (columna + 1);  // Formato base 1
            }
        }
    }

    //reservas aleatorias para el 20% de los asientos para que no se muestren salas vacias
    private void ReservasAleatorias() {
        Random random = new Random();
        int reservas = (int) (silla.length * silla[0].length * 0.2);
        for (int i = 0; i < reservas; i++) {
            int fila = random.nextInt(silla.length); 
            int columna = random.nextInt(silla[0].length); 
            
            // Marca el asiento como reservado
            silla[fila][columna] = true;
        }
    }
}
