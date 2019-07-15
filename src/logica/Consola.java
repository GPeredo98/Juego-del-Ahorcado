package logica;

import java.util.ArrayList;
import java.util.Scanner;

public class Consola {

    static Scanner lectura = new Scanner(System.in);
    static ArrayList<String> lista_de_palabras = new ArrayList<>();
    static ArrayList letras_tomadas = new ArrayList<>();
    static String entrada;
    static String palabra_secreta;
    static String palabra_mostrada;
    static int vidas;

    public static void main(String[] args) {

        cargarPalabrasSecretas();
        palabra_secreta = seleccionarPalabra();
        vidas = 6;
        
        //Aqui se desarrolla el juego
        game:
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
            entrada = entrada.toLowerCase();
            //Verificamos que la entrada sea una letra
            verificarLetraRepetida(verificarEntrada(entrada));
            //quitarVidas(confirmarEntrada(verificarEntrada(entrada)));

            System.out.println(destaparPalabra());
            System.out.println("\n");
            if(!verificarLetrasOcultas()) {
                System.out.println("\u001B[32m"+"¡LO CONSEGUISTE!"+"\u001B[0m");
                break;
            }
        }
        System.out.println("Vidas: " + vidas);
        System.out.println("FIN DEL JUEGO");
    }

    public static void cargarPalabrasSecretas() {
        lista_de_palabras.add("medioambiente");
        lista_de_palabras.add("jacuzzi");
        lista_de_palabras.add("helicoptero");
        lista_de_palabras.add("cerveza");
        lista_de_palabras.add("destornillador");
        lista_de_palabras.add("alcantarillado");
        lista_de_palabras.add("farmaceutico");
    }

    private static void quitarVidas(char letra) {

        boolean coincide = false;
        char[] letras_de_palabra = palabra_secreta.toCharArray();

        //Buscamos si la letra coincide con alguna de la palabra oculta
        for (int i = 0; i < letras_de_palabra.length; i++) {
            if (letra == letras_de_palabra[i]) {
                coincide = true;
            }
        }
        //Preguntamos si coincidio con alguna letra de la palabra oculta
        //Si es asi SONRISA, caso contrario quitamos una vida
        if (coincide) {
            System.out.println("¡SONRISA!");
        } else {
            vidas += -1;
        }
    }

    private static char verificarEntrada(String entrada) {
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

        if (valido) {
            char valor = entrada.charAt(0);
            return valor;
        } else {
            System.out.println("Ingrese una letra:");
            entrada = lectura.next();
            entrada = entrada.toLowerCase();
            return verificarEntrada(entrada);
        }
    }

    public static void verificarLetraRepetida(char letra) {

        boolean hay_igual = false;

        //Verificamos solo si el array tiene por lo menos un elemento
        if (letras_tomadas.size() > 0) {

            //Recorremos el array de letras tomadas buscando si ya hay alguna letra igual
            for (int i = 0; i < letras_tomadas.size(); i++) {

                if (String.valueOf(letra).equals(letras_tomadas.get(i).toString())) {
                    hay_igual = true;
                }
            }
            //Preguntamos si es que encontro una letra igual
            //Si esa asi, se lo indicamos al jugador, caso contrario agregamos la letra
            if (hay_igual) {
                System.out.println("\033[31m" + "Ya ingresó esa letra" + "\u001B[0m");
                
            } else {
                letras_tomadas.add(letra);
                quitarVidas(letra);
            }
        } else {
            letras_tomadas.add(letra);
            quitarVidas(letra);
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
        
        boolean ocultas = true;

        palabra_mostrada = palabra_secreta;
        String letras = "";

        for (int i = 0; i < letras_tomadas.size(); i++) {
            letras = letras + letras_tomadas.get(i);
        }
        String expresion_regular = "[a-z&&[^." + letras + "]]";

        palabra_mostrada = palabra_secreta.replaceAll("", " ");
        palabra_mostrada = palabra_mostrada.replaceAll(expresion_regular, "_");
        
        //Verificamos si aun quedan letras ocultas
        for (int i = 0; i < palabra_mostrada.length(); i++) {
            if(palabra_mostrada.charAt(i) == '_'){
                ocultas = false;
            }
        }
        return palabra_mostrada;
    }
    
    public static boolean verificarLetrasOcultas() {
        for (int i = 0; i < palabra_mostrada.length(); i++) {
            //Si nuestra palabra oculta aun tiene letras ocultas, devuelve true
            if(palabra_mostrada.charAt(i)=='_'){
                return true;
            }
        }
        return false;
    }
}
