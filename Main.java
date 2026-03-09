import java.util.Scanner;

class Nodo {
    String texto;
    Nodo siguiente;

    public Nodo(String texto) {
        this.texto = texto;
        this.siguiente = null;
    }
}

// Implementación de la pila desde cero
class PilaManual {
    private Nodo cima;

    public PilaManual() {
        this.cima = null;
    }

    // Agrega un elemento a la cima de la pila
    public void push(String texto) {
        Nodo nuevoNodo = new Nodo(texto);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
    }

    // Retira y devuelve el elemento de la cima
    public String pop() {
        if (isEmpty()) {
            return null; // Si está vacía, no hay nada que sacar
        }
        String textoExtraido = cima.texto;
        cima = cima.siguiente; // La nueva cima ahora es el elemento de abajo
        return textoExtraido;
    }

    // Observa el elemento de la cima sin sacarlo
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.texto;
    }

    // Verifica si la pila está vacía
    public boolean isEmpty() {
        return cima == null;
    }
}

// Clase principal
public class Main { {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Se instancian las dos pilas requeridas en la actividad
        PilaManual pilaPrincipal = new PilaManual(); // Para guardar el texto escrito
        PilaManual pilaRehacer = new PilaManual();   // Para guardar lo deshecho

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

            // Validación básica para evitar errores si el usuario no mete un número
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del teclado
            } else {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Descartar entrada inválida
                continue;
            }

            switch (opcion) {
                case 1:
                    // Escribir texto
                    System.out.print("Escribe una nueva línea de texto: ");
                    String nuevaLinea = scanner.nextLine();
                    pilaPrincipal.push(nuevaLinea);

                    // Al escribir algo nuevo, el historial de "Rehacer" se debe borrar
                    pilaRehacer = new PilaManual();
                    System.out.println("Texto agregado.");
                    break;

                case 2:
                    // Deshacer (Undo)
                    if (!pilaPrincipal.isEmpty()) {
                        String textoDeshecho = pilaPrincipal.pop(); // Sacamos de la principal
                        pilaRehacer.push(textoDeshecho);            // Lo guardamos en la secundaria
                        System.out.println("Acción deshecha: se eliminó '" + textoDeshecho + "'");
                    } else {
                        System.out.println("No hay acciones para deshacer.");
                    }
                    break;

                case 3:
                    // Rehacer (Redo)
                    if (!pilaRehacer.isEmpty()) {
                        String textoRehecho = pilaRehacer.pop(); // Sacamos de la secundaria
                        pilaPrincipal.push(textoRehecho);        // Lo devolvemos a la principal
                        System.out.println("Acción rehecha: se restauró '" + textoRehecho + "'");
                    } else {
                        System.out.println("No hay acciones para rehacer.");
                    }
                    break;

                case 4:
                    // Mostrar texto actual
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