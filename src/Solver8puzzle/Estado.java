package Solver8puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class Estado {
    public int[][] nodoActual;
    public int v_heuristica;

    public Estado(int[][] nodoActual) {
        this.nodoActual = nodoActual;
    }

    public int heuristica() {
        this.v_heuristica = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.nodoActual[i][j] != Variables.funcionObjetivo[i][j]) {
                    this.v_heuristica++;
                }
            }
        }
        return this.v_heuristica;
    }

    public int[][] copiar_matriz() {
        int[][] copia_matriz = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copia_matriz[i][j] = this.nodoActual[i][j];
            }
        }
        return copia_matriz;
    }

    List<Estado> generar_estados(){
        List<Estado> listaEstados = new ArrayList<>();
        int [] posicionVacia = obtenerPosicionVacia(0);
        mover_arriba(posicionVacia, listaEstados);
        mover_abajo(posicionVacia, listaEstados);
        mover_izquierda(posicionVacia, listaEstados);
        mover_derecha(posicionVacia, listaEstados);
        return listaEstados;
    }

    public void imprimir_estado() {
        for (int i = 0; i < 3; i++) {
            System.out.print("|  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(this.nodoActual[i][j]);
                if (j != nodoActual[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("  |");
            
        }
    }

    public int[] obtenerPosicionVacia(int posicionVacia) {
        int posicion[] = new int[2];
        for (int i = 0; i < this.nodoActual.length; i++) {
            for (int j = 0; j < this.nodoActual[i].length; j++) {
                if (nodoActual[i][j] == posicionVacia) {
                    posicion[0] = i;
                    posicion[1] = j;
                    return posicion;
                }
            }
        }
        return posicion;
    }

    void mover_arriba(int[] posicionVacia, List<Estado> listaEstados) {
        int i = posicionVacia[0];
        int j = posicionVacia[1];
        if (i > 0) {
            int aux[][] = copiar_matriz();
            aux[i][j] = aux[i - 1][j];
            aux[i - 1][j] = 0;
            listaEstados.add(new Estado(aux));
        }
    }

    void mover_abajo(int[] posicionVacia, List<Estado> listaEstados) {
        int i = posicionVacia[0];
        int j = posicionVacia[1];
        if (i < this.nodoActual.length - 1) {
            int aux[][] = copiar_matriz();
            aux[i][j] = aux[i + 1][j];
            aux[i + 1][j] = 0;
            listaEstados.add(new Estado(aux));
        }
    }

    void mover_izquierda(int[] posicionVacia, List<Estado> listaEstados) {
        int i = posicionVacia[0];
        int j = posicionVacia[1];
        if (j > 0) {
            int aux[][] = copiar_matriz();
            aux[i][j] = aux[i][j - 1];
            aux[i][j - 1] = 0;
            listaEstados.add(new Estado(aux));
        }
    }

    void mover_derecha(int[] posicionVacia, List<Estado> listaEstados) {
        int i = posicionVacia[0];
        int j = posicionVacia[1];
        if (j < this.nodoActual.length - 1) {
            int aux[][] = copiar_matriz();

            aux[i][j] = aux[i][j + 1];
            aux[i][j + 1] = 0;
            listaEstados.add(new Estado(aux));
        }
    }
}
