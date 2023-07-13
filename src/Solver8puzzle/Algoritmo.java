package Solver8puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class Algoritmo {
    public Nodo crear_arbol(Nodo nodo) {
        if (nodo.comparar_funcionObjetivo()) {
            System.out.println("RESUELTO");
        } else {
            while (!nodo.comparar_funcionObjetivo()) {
                if (nodo.raiz) {
                    System.out.println("-------------------------");
                    System.out.println("");
                    System.out.println("\tPADRE");
                    nodo.estado.imprimir_estado();
                    System.out.println(nodo);
                    System.out.println("");
                    System.out.println("-------------------------");
                    nodo.expandir_nodos();
                    //Impresion nodos hijos
                    System.out.println("\tHIJOS");
                    System.out.println("->                   <-");
                    for (int i = 0; i < nodo.hijos.size(); i++) {
                        nodo.hijos.get(i).estado.imprimir_estado();
                        System.out.println(nodo.hijos.get(i));
                        System.out.println("");
                        System.out.println("-------------------------");
                    }
                    nodo = Nodo.obtenerHojaMenor();
                    
                    System.out.println("\n\tHOJAS");
                    hojasAnteriores(Variables.nodosHoja);
                    
                    System.out.println("\nHoja menor seleccionada\n");
                    nodo.estado.imprimir_estado();
                    System.out.println(nodo);
                    
                } else {
                    System.out.println("");
                    System.out.println("-------------------------");
                    System.out.println("\tPADRE");
                    System.out.println("");
                    nodo.estado.imprimir_estado();
                    System.out.println("");
                    System.out.println(nodo + "\n");
                    System.out.println("-------------------------");
                    System.out.println("");
                    nodo.expandir_nodos();
                    System.out.println("\tHIJOS");
                    System.out.println("->                   <-");
                    for (int i = 0; i < nodo.hijos.size(); i++) {
                        nodo.hijos.get(i).estado.imprimir_estado();
                        System.out.println(nodo.hijos.get(i) + "\n");
                        System.out.println("-------------------------");
                    }
                    nodo = Nodo.obtenerHojaMenor();
                    
                    System.out.println("\n\tHOJAS");
                    hojasAnteriores(Variables.nodosHoja);
                    
                    
                    System.out.println("\nHoja menor seleccionada");
                    nodo.estado.imprimir_estado();
                    System.out.println(nodo + "\n");
                    System.out.println("-------------------------");
                }
            }
            
        }
        return nodo;
    }

    public static List<Nodo> caminoOptimo(Nodo hojaSolucion) {
        List<Nodo> camino = new ArrayList();
        do {
            camino.add(hojaSolucion);
            hojaSolucion = hojaSolucion.padre;
        } while (hojaSolucion != null);

        return camino;
    }

    public void hojasAnteriores(List<Nodo> hojas){
        for (int i = hojas.size()-1; i >= 0; i--) {
            System.out.println("\n-------------------------");
            hojas.get(i).estado.imprimir_estado();
            System.out.println(hojas.get(i).toString() + "\n");
            System.out.println("-------------------------\n");
        }
    }
    
    public static void caminoOptimo(List<Nodo> caminoSolucion) {
        System.out.println("\n***************CAMINO OPTIMO*****************\n");
        for (int i = caminoSolucion.size() - 1; i >= 0; i--) {
            caminoSolucion.get(i).estado.imprimir_estado();
            System.out.println("");
            System.out.println(caminoSolucion.get(i));
            System.out.println("----------------------------\n");
        }
    }
}
