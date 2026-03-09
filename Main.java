import java.util.Scanner;

// Clase Nodo
class Nodo {
    String texto;
    Nodo siguiente;

    public Nodo(String texto) {
        this.texto = texto;
        this.siguiente = null;
    }
}

// Clase PilaManual
class PilaManual {
    private Nodo cima;

    public PilaManual() {
        this.cima = null;
    }

    public void push(String texto) {
        Nodo nuevoNodo = new Nodo(texto);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String textoExtraido = cima.texto;
        cima = cima.siguiente;
        return textoExtraido;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.texto;
    }

    public boolean isEmpty() {
        return cima == null;
    }
}

// CLASE PRINCIPAL DEL PROGRAMA
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PilaManual pilaPrincipal = new PilaManual();
        PilaManual pilaRehacer = new PilaManual();

        int opcion = 0;

        System.out.println("BIENVENIDO AL SIMULADOR DE EDITOR DE TEXTO");

        while (opcion != 5) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Escribir texto");
            System.out.println("2. Deshacer");
            System.out.println("3. Rehacer");
            System.out.println("4. Mostrar texto actual");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Escribe una nueva línea de texto: ");
                    String nuevaLinea = scanner.nextLine();
                    pilaPrincipal.push(nuevaLinea);
                    pilaRehacer = new PilaManual();
                    System.out.println("Texto agregado.");
                    break;
                case 2:
                    if (!pilaPrincipal.isEmpty()) {
                        String textoDeshecho = pilaPrincipal.pop();
                        pilaRehacer.push(textoDeshecho);
                        System.out.println("Acción deshecha: se eliminó '" + textoDeshecho + "'");
                    } else {
                        System.out.println("No hay acciones para deshacer.");
                    }
                    break;
                case 3:
                    if (!pilaRehacer.isEmpty()) {
                        String textoRehecho = pilaRehacer.pop();
                        pilaPrincipal.push(textoRehecho);
                        System.out.println("Acción rehecha: se restauró '" + textoRehecho + "'");
                    } else {
                        System.out.println("No hay acciones para rehacer.");
                    }
                    break;
                case 4:
                    mostrarTextoActual(pilaPrincipal);
                    break;
                case 5:
                    System.out.println("Saliendo del editor. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarTextoActual(PilaManual pila) {
        if (pila.isEmpty()) {
            System.out.println("\n[El editor está vacío]");
            return;
        }

        PilaManual pilaTemporal = new PilaManual();
        while (!pila.isEmpty()) {
            pilaTemporal.push(pila.pop());
        }

        System.out.println("\nTEXTO ACTUAL");
        while (!pilaTemporal.isEmpty()) {
            String linea = pilaTemporal.pop();
            System.out.println(linea);
            pila.push(linea);
        }
        System.out.println("--------------------");
    }
}