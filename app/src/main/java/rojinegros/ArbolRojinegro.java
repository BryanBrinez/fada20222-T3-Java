package rojinegros;

import lombok.Getter;
import lombok.Setter;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolRojinegro {
    @Getter
    @Setter
    private ArbolRojinegro izq;

    @Getter
    @Setter
    private ArbolRojinegro father;


    @Getter
    @Setter
    private ArbolRojinegro der;

    @Getter
    @Setter
    private int valor;

    @Getter
    @Setter
    private boolean black; //Si es negro True, en otro caso rojo

    public ArbolRojinegro(ArbolRojinegro izq,
                          ArbolRojinegro der,
                          int valor,
                          boolean black) {
        this.izq = izq;
        this.der = der;
        this.valor = valor;
        this.black = black;
    }

    public ArbolRojinegro() {
        this.izq = null;
        this.der = null;
        this.black = true;
    }
    /*
     * Metodos a implementar
     */

    public void insertar(int x) throws Exception {
        throw new OperationNotSupportedException();
    }

    public int maximo() throws OperationNotSupportedException {
        if (this.der == null) {
            return this.valor;
        }
        else{
            return this.der.maximo();
        }
    }

    public int minimo() throws OperationNotSupportedException {
        if (this.izq == null) {
            return this.valor;
        }
        else{
            return this.izq.minimo();
        }

    }

    public ArbolRojinegro search(int valorBuscar) throws NullPointerException {
        if (this.valor == valorBuscar) {
            return this;
        }
        else {
            if (valorBuscar >= this.valor) {
                if(this.getDer() != null) {
                    return this.getDer().search(valorBuscar);
                }
                else {
                    return null;
                }
            }
            else {
                if (this.getIzq() != null){
                    return this.getIzq().search(valorBuscar);
                }
                else {
                    return null;
                }
            }
        }
    }

    public void rotacionIzquierda(int nodoRotar) throws OperationNotSupportedException {
        ArbolRojinegro nodo = this.search(nodoRotar);

        ArbolRojinegro x = nodo.der;
        nodo.der = x.izq;
        x.izq = nodo;
    }

    public void rotacionDerecha(int nodoRotar) throws  OperationNotSupportedException {
        //ArbolRojinegro nodo = this.search(nodoRotar);

        //String arbol = this.bfs();

        ArbolRojinegro copia = this;







        //ArbolRojinegro arbolGuardar = this.getIzq().getDer();

        //this.getIzq().setDer(this.getIzq().getFather());

        //this.setIzq(arbolGuardar);


       // this.getIzq().setDer(this.father);
        System.out.println(this.bfs());





        System.out.println(this.bfs()+ " el nodo que es");
    }
    /*
     *  Area de pruebas, no modificar.
     */
    //Verificaciones
    /*
     * Busqueda por amplitud para verificar arbol.
     */
    public String bfs() {
        String salida = "";
        String separador = "";
        Queue<ArbolRojinegro> cola = new LinkedList<>();
        cola.add(this);
        while (cola.size() > 0) {
            ArbolRojinegro nodo = cola.poll();
            salida += separador + String.valueOf(nodo.getValor());
            separador = " ";
            if (nodo.getIzq() != null) {
                cola.add(nodo.getIzq());
            }
            if (nodo.getDer() != null) {
                cola.add(nodo.getDer());
            }
        }
        return salida;
    }

    /*
     * Recorrido inorder.
     * Verifica propiedad de orden.
     */
    public String inorden() {
        String recorrido = "";
        String separador = "";
        if (this.getIzq() != null) {
            recorrido += this.getIzq().inorden();
            separador = " ";
        }
        recorrido += separador + String.valueOf(this.getValor());
        if (this.getDer() != null) {
            recorrido += " " + this.getDer().inorden();
        }
        return recorrido;
    }

}