/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author TOSHIBA
 */
public class Consola {
    public static ArrayList<String> lista_de_palabras = new ArrayList<>();
    
    public static void main(String[] args) {
        // Aqui se desarrollara el juego
        
        lista_de_palabras.add("Medioambiente");
        lista_de_palabras.add("Jacuzzi");
        lista_de_palabras.add("Helicoptero");
        lista_de_palabras.add("Cerveza");
        lista_de_palabras.add("Destornillador");
        lista_de_palabras.add("Alcantarillado");
        lista_de_palabras.add("Farmaceutico");

        Scanner lectura = new Scanner(System.in);
        String entrada = lectura.next();
        //String palabra = seleccionarPalabra();

        char letra = verificarEntrada(entrada);
    }

    private static char verificarEntrada(String entrada) {

        if (entrada.length() > 1) {
            System.out.println("Debe mandar solo una letra");
        }
        if (entrada.length() < 1) {
            System.out.println("Es necesario mandar una letra");
        }
        char valor = entrada.charAt(0);
        return valor;
    }

    /*private static String seleccionarPalabra() {
        int random = Math.random()*lista_de_palabras.size();
    }*/
}
