package Solver8puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] matriz = matriz_aleatoria();
        // int[][] matriz = {{7,2,4},{5,0,6},{8,3,1}};
        if (solucion(matriz)) {

            Nodo raiz = new Nodo(new Estado(matriz), null, true);
            Algoritmo estrella = new Algoritmo();

            Nodo hojaSolucion = estrella.crear_arbol(raiz);

            List<Nodo> caminoSolucion = Algoritmo.caminoOptimo(hojaSolucion);
            Algoritmo.caminoOptimo(caminoSolucion);
            System.out.println("Num Hojas :" + Variables.nodosHoja.size());
            System.out.println(caminoSolucion);
        } else {
            System.out.println("No tiene solucion");
            imprimir_matriz(matriz);
        }
    }
    
    
    public static boolean solucion(int[][] matrix) {
        int count = 0;
        List<Integer> array = new ArrayList<Integer>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                array.add(matrix[i][j]);
            }
        }

        Integer[] anotherArray = new Integer[array.size()];
        array.toArray(anotherArray);

        for (int i = 0; i < anotherArray.length - 1; i++) {
            for (int j = i + 1; j < anotherArray.length; j++) {
                if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
                    count++;
                }
            }
        }

        return count % 2 == 0;
    }

    
    public static void imprimir_matriz(int matriz[][]) {
        for (int i = 0; i < 3; i++) {
            System.out.print("|  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j]);
                if (j != matriz[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("  |");
        }
    }

    public static int[][] matriz_aleatoria() {
        int[][] matriz_inicial = {{0,1,2},{3,4,5},{6,7,8}};
        Estado matriz_final = new Estado(matriz_inicial);
        int numero_pasos =0;
        do{
           numero_pasos = (int) (Math.random() * 50);
        }while(!(numero_pasos>=20));
        for (int i = 0; i < numero_pasos; i++) {
            int numero_cambios = (int) (Math.random() * matriz_final.generar_estados().size());
            matriz_final.nodoActual = matriz_final.generar_estados().get(numero_cambios).nodoActual;
        }
        matriz_inicial = matriz_final.nodoActual;
        return matriz_inicial;
    }
    
}
