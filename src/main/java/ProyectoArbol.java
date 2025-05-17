
import java.util.Scanner;


/**
 *
 * @author Abner
 */
public class ProyectoArbol {

    public static void main(String[] args) {
        AVLTree arbol = new AVLTree(); // Se crea una instancia del árbol AVL
        Scanner sc = new Scanner(System.in); // Scanner para leer 

        System.out.println("Ingrese números al árbol AVL. Escriba 'exit' o -1 para terminar.");
        while (true) {
            System.out.print("Ingrese número: ");
            String entrada = sc.nextLine(); // Se lee la entrada como texto

            if (entrada.equalsIgnoreCase("exit") || entrada.equals("-1")) {
                System.out.println("Programa finalizado.");
                break; // Sale del bucle si el usuario quiere terminar
            }
            try {
                int valor = Integer.parseInt(entrada); // Convierte la entrada a número
                arbol.insertar(valor); // Inserta el número en el árbol AVL
                System.out.println("\nÁrbol actualizado:");
                arbol.printTree(arbol.raiz); // Muestra el árbol 
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo."); // Si no es número válido
            }
        }

        sc.close(); // Cierra el scanner al final
    }
}
