package Solver8puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class Nodo {
    Nodo padre;
    List<Nodo> hijos = new ArrayList();
    Estado estado;
    boolean raiz;
    
    public int v_heuristica;
    public int v_costo;
    public int v_costoTotal;
    public int rama;

    public Nodo(Estado estado, Nodo padre, boolean raiz) {
        this.padre = padre;
        this.estado = estado;
        this.v_costo = padre == null ? 0 : this.padre.v_costo + 1;
        this.v_heuristica = this.estado.heuristica();
        this.v_costoTotal = this.v_costo + this.v_heuristica;
        this.raiz=raiz;
    }

    public boolean comparar_funcionObjetivo() {
        return Arrays.deepEquals(this.estado.nodoActual, Variables.funcionObjetivo);
    }

    public void agregar_hijo(Nodo hijo) {
        this.hijos.add(hijo);
    }

    public void agregar_hoja(Nodo hoja) {
        Variables.nodosHoja.add(hoja);
    }

    public void expandir_nodos() {
        List<Estado> estados = this.estado.generar_estados();
        for (int i = 0; i < estados.size(); i++) {
            Nodo aux = new Nodo(estados.get(i), this,false);
            aux.rama = i + 1;
            this.agregar_hijo(aux);
            this.agregar_hoja(aux);
        }
        if (!this.raiz) {
            Variables.nodosHoja.remove(this);
        }
    }

    @Override
    public String toString() {
        String mensaje = "Datos del Nodo actual\n"
                + "Valor heurística(hn): " + v_heuristica
                + "\nValor costo(gn): " + v_costo
                + "\nValor total(fn): " + v_costoTotal;
        return mensaje;
    }

    public static Nodo obtenerHojaMenor() {
        Nodo hoja = null;
        for (int i = 0; i < Variables.nodosHoja.size(); i++) {
            if(hoja==null){
                hoja=Variables.nodosHoja.get(0);
            }
            if (hoja.v_costoTotal > Variables.nodosHoja.get(i).v_costoTotal ) {
                hoja = Variables.nodosHoja.get(i);
            } 
        }
        return hoja;
    }
    
    
}
