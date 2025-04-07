import java.util.ArrayList;
import java.util.List;

public class ModaDiv {
    private static class Limits {
        int[] a;
        int prim;
        int ult;

        Limits(int[] a, int prim, int ult) {
            this.a = a;
            this.prim = prim;
            this.ult = ult;
        }
    }

    private static class SetVectors {
        List<Limits> homogeneo;
        List<Limits> heterogeneo;

        SetVectors() {
            homogeneo = new ArrayList<>();
            heterogeneo = new ArrayList<>();
        }

        void insertar(Limits p) {
            // Si el subarreglo tiene un solo elemento, lo insertamos en el conjunto
            // homogéneo
            if (p.prim == p.ult) {
                homogeneo.add(p);
            } else {
                // Si el subarreglo tiene más de un elemento, lo insertamos en el conjunto
                // heterogéneo
                heterogeneo.add(p);
            }
        }

        Limits mayor() {
            // Extraemos el subarreglo más largo del conjunto con mayor longitud
            if (!heterogeneo.isEmpty()) {
                return heterogeneo.remove(0);
            } else if (!homogeneo.isEmpty()) {
                return homogeneo.remove(0);
            } else {
                return null;
            }
        }

        boolean esVacio(List<Limits> conjunto) {
            return conjunto.isEmpty();
        }

        int longMayor(List<Limits> conjunto) {
            int max = 0;
            // Calculamos la longitud del subarreglo más largo en el conjunto
            for (Limits p : conjunto) {
                max = Math.max(max, p.ult - p.prim + 1);
            }
            return max;
        }
    }

    public static int moda3(int[] a, int prim, int ult) {
        SetVectors sv = new SetVectors();
        Limits p = new Limits(a, prim, ult);
        sv.insertar(p);

        while (sv.longMayor(sv.heterogeneo) > sv.longMayor(sv.homogeneo)) {
            // Extraemos el subarreglo más largo del conjunto heterogéneo
            p = sv.mayor();
            int mediana = a[(p.prim + p.ult) / 2];
            int izq, der;
            Pivote2(a, mediana, p.prim, p.ult, izq, der);

            // Dividimos el subarreglo p en tres partes: p1, p2 y p3
            Limits p1 = new Limits(a, p.prim, izq - 1);
            Limits p2 = new Limits(a, izq, der - 1);
            Limits p3 = new Limits(a, der, p.ult);

            // Insertamos p1 y p3 en el conjunto heterogéneo si tienen más de un elemento
            if (p1.prim < p1.ult) {
                sv.insertar(p1);
            }
            if (p3.prim < p3.ult) {
                sv.insertar(p3);
            }
            // Insertamos p2 en el conjunto homogéneo si tiene más de un elemento
            if (p2.prim < p2.ult) {
                sv.insertar(p2);
            }
        }

        // Si el conjunto homogéneo está vacío, devolvemos el primer elemento del
        // arreglo original
        if (sv.esVacio(sv.homogeneo)) {
            return a[prim];
        }

        // Extraemos el subarreglo más largo del conjunto homogéneo y devolvemos su
        // primer elemento
        p = sv.mayor();
        return a[p.prim];
    }

    private static void Pivote2(int[] a, int mediana, int prim, int ult, int izq, int der) {
        izq = prim;
        der = ult;
        while (izq <= der) {
            // Movemos el índice izq hacia la derecha hasta encontrar un elemento mayor o
            // igual a la mediana
            while (a[izq] < mediana) {
                izq++;
            }
            // Movemos el índice der hacia la izquierda hasta encontrar un elemento menor o
            // igual a la mediana
            while (a[der] > mediana) {
                der--;
            }
            // Si izq es menor o igual a der, intercambiamos los elementos a[izq] y a[der]
            if (izq <= der) {
                int temp = a[izq];
                a[izq] = a[der];
                a[der] = temp;
                izq++;
                der--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 2, 2, 4, 2, 2, 2 };
        System.out.println(moda3(a, 0, a.length - 1)); // Imprime 2
    }
}
