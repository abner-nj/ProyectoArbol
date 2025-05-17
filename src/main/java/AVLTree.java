import java.util.*;
/**
 *
 * @author Abner
 */
public class AVLTree {
    Node raiz;

    // método para obtener la altura de un nodo
    // tambien sirve para calcular el factor de balance.
    public int getAltura(Node nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    // Calcula la altura entre los subarboles de un nodo
    // tambien sirve para detectar si el nodo está desbalanceado
    public int getFactorBalance(Node nodo) {
        return nodo == null ? 0 : getAltura(nodo.izquierda) - getAltura(nodo.derecha);
    }

    // Realiza una rotación simple a la derecha
    public Node rotarDerecha(Node y) {
        Node x = y.izquierda; // x se convierte en la nueva raíz del subarbol
        Node z2 = x.derecha; // z2 se volvera el hijo izquierdo de y

        x.derecha = y; // y pasa a ser el hijo derecho de x
        y.izquierda = z2; // z2 sera el hijo izquierdo de y

        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1; // actualizar alturas x,y
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1; 

        return x; // nueva raíz del subárbol balanceado
    }

    // Realiza una rotación simple a la izquierda
    public Node rotarIzquierda(Node x) {
        Node y = x.derecha; // y se convierte en la nueva raíz del subarbol
        Node z2 = y.izquierda; // z2 se vuelve el hijo derecho de x

        y.izquierda = x; // x pasa a ser hijo izquierdo de y
        x.derecha = z2; // z2 sera el hijo derecho de x

        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1; // actualizar altura de x,y
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1; 

        return y; // nueva raíz del subárbol balanceado
    }

    // Insercion de un nuevo nodo y se hace el balance si se necesita
    public Node insertar(Node nodo, int valor) {
        if (nodo == null) return new Node(valor); // creacion de un nuevo nodo

        if (valor < nodo.valor) {
            nodo.izquierda = insertar(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertar(nodo.derecha, valor);
        } else {
            return nodo; // hace el return en caso da numeros duplicados
        }

        // Actualizar altura del nodo actual luego de insertar
        nodo.altura = 1 + Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        // Calcular el factor de balance del nodo actual
        int balance = getFactorBalance(nodo);

        // Casos de rotaciones
        if (balance > 1 && valor < nodo.izquierda.valor) {
            return rotarDerecha(nodo); // LL
        }

        if (balance < -1 && valor > nodo.derecha.valor) {
            return rotarIzquierda(nodo); // RR
        }

        if (balance > 1 && valor > nodo.izquierda.valor) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda); // LR
            return rotarDerecha(nodo);
        }

        if (balance < -1 && valor < nodo.derecha.valor) {
            nodo.derecha = rotarDerecha(nodo.derecha); // RL
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Método para insertar desde la raíz
    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }


    // Metodo de impresion 
    
    public void printTree(Node raiz) {
        if (raiz == null) return;
        int altura = getAltura(raiz); // Calcula la altura del arbol
        int ancho = (int) Math.pow(2, altura) * 3; // calcula el ancho del arbol

        List<List<String>> niveles = new ArrayList<>(); // Lista para guardar los valores por niveles
        Queue<Node> cola = new LinkedList<>(); // Cola para recorrido por niveles
        cola.offer(raiz);

        boolean bandera = true; // Indica si todavía hay nodos para seguir recorriendo

        while (bandera) {
            int nivelNodo = cola.size(); // Número de nodos en el nivel actual
            List<String> fila = new ArrayList<>(); // Guarda los valores de los nodos en este nivel
            bandera = false;

            for (int i = 0; i < nivelNodo; i++) {
                Node actual = cola.poll();
                if (actual == null) { // para calcular los espacios 
                    fila.add(" ");
                    cola.offer(null);
                    cola.offer(null);
                } else {
                    fila.add(String.valueOf(actual.valor));
                    cola.offer(actual.izquierda);
                    cola.offer(actual.derecha);
                    if (actual.izquierda != null || actual.derecha != null) {
                        bandera = true;
                    }
                }
            }
            niveles.add(fila); // Añade la fila actual a la lista de niveles
        }

        int espacio = ancho; // Espaciado inicial para centrar los nodos en el primer nivel
        for (List<String> fila : niveles) {
        StringBuilder linea = new StringBuilder(); // Construye la línea completa para imprimir el nivel
        for (String val : fila) {
            linea.append(textoCentrado(val, espacio));
            }
            System.out.println(linea.toString()); // Imprime la línea ya armada en consola
            espacio /= 2; // Reduce el espacio para el siguiente nivel
        }
    }

    // Centra el texto rellenando con espacios a izquierda y derecha
    private String textoCentrado(String text, int ancho) {
        int padding = ancho - text.length(); // Espacios totales que deben agregarse alrededor del texto
        int padStart = padding / 2; // Espacios a la izquierda
        int padEnd = padding - padStart; // Espacios a la derecha
        return " ".repeat(padStart) + text + " ".repeat(padEnd); // Devuelve el texto centrado
    }
}
