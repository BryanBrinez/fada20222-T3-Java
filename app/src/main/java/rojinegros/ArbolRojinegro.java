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

    public void insertar(int dato) throws OperationNotSupportedException {

        ArbolRojinegro padre = this;
        ArbolRojinegro nodo = new ArbolRojinegro();
        nodo.setValor(dato);
        nodo.setBlack(false);

        if (dato > padre.getValor()) {
            if (padre.getDer() == null) {
                padre.setDer(nodo);
                coloresRojoNegro(nodo);

            } else {
                padre.getDer().insertar(dato);
                coloresRojoNegro(nodo);
            }
        } else {
            if (padre.getIzq() == null) {
                padre.setIzq(nodo);
                coloresRojoNegro(nodo);

            } else {
                padre.getIzq().insertar(dato);
                coloresRojoNegro(nodo);
            }
        }


    }


    public void coloresRojoNegro(ArbolRojinegro nodoIngresar) throws OperationNotSupportedException {
        //Caso 1 si el padre del nodo ingresado es rojo y su tio igual, se cambia el color del abuelo a rojo y del padre y su tio a negro

        if (nodoIngresar.getFather() != null && nodoIngresar.getFather().getFather().getIzq() != null) {
            if (!nodoIngresar.getFather().black && !nodoIngresar.getFather().getFather().getIzq().black) {

                nodoIngresar.getFather().getFather().black = false;
                nodoIngresar.getFather().black = true;
                nodoIngresar.getFather().getFather().getIzq().black = true;
            }
        }

        // Si la raiz es rojo se cambia a negro para cumplir el requisito de que la raiz es siempre negra
        if (!this.black) {
            this.black = true;
        }

        //Caso 2 si el padre del nodo ingresado es rojo y el no tiene tio se cambia el padre a negro y el abuelo a rojo
        if (nodoIngresar.getFather() != null) {
            if (!nodoIngresar.getFather().black && nodoIngresar.getFather().getFather().getIzq() == null) {
                nodoIngresar.getFather().black = true;
                nodoIngresar.getFather().getFather().black = false;
                //Debido a que se viola la propiedad de cantidad de nodos negros por lado se realiza la rotacion izquierda
                nodoIngresar.getFather().getFather().rotacionIzquierda(nodoIngresar.getFather().getFather().valor);
            }
        }

        //caso 3
        if (nodoIngresar.getFather() != null) {
            if (nodoIngresar.getFather().getDer() == null && !nodoIngresar.getFather().black
                    && nodoIngresar.getFather().getFather().getDer() == nodoIngresar.getFather()) {

                nodoIngresar.getFather().black = true;
                nodoIngresar.getFather().getFather().black = false;
                nodoIngresar.getFather().getFather().rotacionIzquierda(nodoIngresar.getFather().getFather().valor);
            }
        }


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
    public void rotacionIzquierda(int nodoRotar) throws OperationNotSupportedException {
        ArbolRojinegro x = this.search(nodoRotar);

        ArbolRojinegro y = x.getDer();
        ArbolRojinegro padre = x.getFather();

        x.setDer(y.getIzq());
        y.setIzq(x);
        x.setFather(y);

        ArbolRojinegro raiz = x.getFather();
        ArbolRojinegro padreTemporal = x.getFather();
        ArbolRojinegro izquierdoTemporal = x.getIzq();
        ArbolRojinegro derechoTemporal = x.getDer();

        int valorTemporal = x.valor;

        x.setIzq(raiz);
        x.setDer(raiz.getDer());
        x.setValor(raiz.getValor());

        raiz.setFather(padreTemporal);
        raiz.setIzq(izquierdoTemporal);
        raiz.setDer(derechoTemporal);
        raiz.setValor(valorTemporal);


    }

    public void rotacionDerecha(int nodoRotar) throws OperationNotSupportedException {

        ArbolRojinegro y = this.search(nodoRotar);
        ArbolRojinegro x = y.getIzq();
        ArbolRojinegro padre = y.getFather();

        y.setIzq(x.getDer());
        x.setDer(y);
        y.setFather(x);

        ArbolRojinegro raiz = y.getFather();
        ArbolRojinegro padreTemporal = y.getFather();
        ArbolRojinegro izquierdoTemporal = y.getIzq();
        ArbolRojinegro derechoTemporal = y.getDer();

        int valorTemporal = y.valor;

        y.setDer(raiz);
        y.setIzq(raiz.getIzq());
        y.setValor(raiz.getValor());

        raiz.setFather(padreTemporal);
        raiz.setIzq(izquierdoTemporal);
        raiz.setDer(derechoTemporal);
        raiz.setValor(valorTemporal);


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
