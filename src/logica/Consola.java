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

    static Scanner lectura = new Scanner(System.in);
    static ArrayList<String> lista_de_palabras = new ArrayList<>();
    static ArrayList letras_tomadas = new ArrayList<>();
    static String entrada;
    static String palabra_secreta;
    static int vidas;

    public static void main(String[] args) {

        cargarPalabras();
        palabra_secreta = seleccionarPalabra();
        vidas = 6;

        while (vidas > 0) {
            System.out.println("Vidas: " + vidas);
            System.out.print("Letras: ");
            for (int i = 0; i < letras_tomadas.size(); i++) {
                System.out.print(letras_tomadas.get(i).toString().toUpperCase() + " ");
            }
            System.out.println("");

            //Obtenemos la letra que ingresa el jugador
            System.out.println("Ingrese una letra:");
            entrada = lectura.next();
            //Verificamos que la entrada sea una letra
            verificarLetraRepetida(confirmarEntrada(verificarEntrada(entrada)));

            System.out.println(destaparPalabra());
            System.out.println("\n");
        }
    }

    public static void cargarPalabras() {
        lista_de_palabras.add("medioambiente");
        lista_de_palabras.add("jacuzzi");
        lista_de_palabras.add("helicoptero");
        lista_de_palabras.add("cerveza");
        lista_de_palabras.add("destornillador");
        lista_de_palabras.add("alcantarillado");
        lista_de_palabras.add("farmaceutico");
    }

    private static boolean verificarEntrada(String entrada) {
        boolean valido = true;

        if (entrada.length() > 1) {
            valido = false;
            System.out.println("\033[31m" + "Debe mandar solo un caractér" + "\u001B[0m");
        }
        if (entrada.length() < 1) {
            valido = false;
            System.out.println("\033[31m" + "Es necesario mandar una letra" + "\u001B[0m");
        }
        if (verificarSiEsNumero(entrada)) {
            valido = false;
            System.out.println("\033[31m" + "No se aceptan números" + "\u001B[0m");
        }
        return valido;
    }

    public static char confirmarEntrada(boolean valida) {
        if (valida) {
            char valor = entrada.charAt(0);
            return valor;
        } else {
            System.out.println("Ingrese una letra:");
            entrada = lectura.next();
            return confirmarEntrada(verificarEntrada(entrada));
        }
    }

    public static void verificarLetraRepetida(char letra) {
        boolean repetido = false;
        if (letras_tomadas.size() > 0) {
            //Recorremos el array de letras tomadas buscando si ya hay alguna letra igual
            for (int i = 0; i < letras_tomadas.size(); i++) {
                
                if (String.valueOf(letra).equals(letras_tomadas.get(i).toString())) {
                    repetido = true;
                }
            }
            //Preguntamos si es que encontro una letra igual
                //Si esa asi, se lo indicamos al jugador, caso contrario agregamos la letra
                if(repetido){
                    System.out.println("\033[31m" + "Ya ingresó esa letra" + "\u001B[0m");
                } else {
                    letras_tomadas.add(letra);
                }
        } else {
            letras_tomadas.add(letra);
        }
    }

    private static String seleccionarPalabra() {
        int random = (int) (Math.random() * lista_de_palabras.size());
        return lista_de_palabras.get(random);
    }

    public static boolean verificarSiEsNumero(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

    public static String destaparPalabra() {

        String palabra_mostrada = palabra_secreta;
        String letras = "";

        for (int i = 0; i < letras_tomadas.size(); i++) {
            letras = letras + letras_tomadas.get(i);
        }
        String expresion_regular = "[a-z&&[^." + letras + "]]";

        palabra_mostrada = palabra_secreta.replaceAll("", " ");
        palabra_mostrada = palabra_mostrada.replaceAll(expresion_regular, "_");

        return palabra_mostrada;
    }

}
