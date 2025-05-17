
/**
 *
 * @author Abner
 */
public class Node {
    int valor;           // Valor que almacena el nodo
    int altura;          // Altura del nodo dentro del árbol
    Node izquierda;      //  hijo izquierdo
    Node derecha;        //  hijo derecho

    // Constructor del nodo que recibe un valor dado y altura 1
    public Node(int valor) {
        this.valor = valor;
        this.altura = 1; 
    }
}
