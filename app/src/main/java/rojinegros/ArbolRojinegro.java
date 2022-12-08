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
        } else {
            return this.der.maximo();
        }
    }

    public int minimo() throws OperationNotSupportedException {
        if (this.izq == null) {
            return this.valor;
        } else {
            return this.izq.minimo();
        }

    }

    public ArbolRojinegro search(int valorBuscar) throws NullPointerException {
        if (this.valor == valorBuscar) {
            return this;
        } else {
            if (valorBuscar >= this.valor) {
                if (this.getDer() != null) {
                    return this.getDer().search(valorBuscar);
                } else {
                    return null;
                }
            } else {
                if (this.getIzq() != null) {
                    return this.getIzq().search(valorBuscar);
                } else {
                    return null;
                }
            }
        }
    }

    //rotacion izquierda
    public void rotacionIzquierda(int nodoRotar) throws Exception {
        ArbolRojinegro x = this.search(nodoRotar);

        ArbolRojinegro y = x.getDer();
        ArbolRojinegro padre = x.getFather();
        x.setDer(y.getIzq());
        y.setIzq(x);

        if (x.getFather() == null) {
            x.setFather(y);
            ArbolRojinegro raiz = x.getFather();
            ArbolRojinegro tmpFather = x.getFather();
            ArbolRojinegro tmpIzq = x.getIzq();
            ArbolRojinegro tmpDer = x.getDer();
            int tmpValor = x.valor;

            x.setIzq(raiz);
            x.setDer(raiz.getDer());
            x.setValor(raiz.getValor());

            raiz.setFather(tmpFather);
            raiz.setIzq(tmpIzq);
            raiz.setDer(tmpDer);
            raiz.setValor(tmpValor);

        }

    }

    public void rotacionDerecha(int nodoRotar) throws OperationNotSupportedException {

        ArbolRojinegro y = this.search(nodoRotar);
        ArbolRojinegro x = y.getIzq();
        ArbolRojinegro padre = y.getFather();
        y.setIzq(x.getDer());
        x.setDer(y);
        if (y.getFather() == null) {
            y.setFather(x);
            ArbolRojinegro raiz = y.getFather();

            ArbolRojinegro tmpFather = y.getFather();
            ArbolRojinegro tmpIzq = y.getIzq();
            ArbolRojinegro tmpDer = y.getDer();
            int tmpValor = y.valor;

            //this.father = null;
            y.setDer(raiz);
            y.setIzq(raiz.getIzq());
            y.setValor(raiz.getValor());

            raiz.setFather(tmpFather);
            raiz.setIzq(tmpIzq);
            raiz.setDer(tmpDer);
            raiz.setValor(tmpValor);

        }
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