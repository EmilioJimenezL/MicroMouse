package MicroMouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Raton {
    private char[][] mapaOriginal;
    private int[][] mapaFlood;
    Punto inicio, fin;
    List<Punto> listaDeMovimientos = new ArrayList<Punto>();

    public Raton(char[][] mapaO, int[][] mapaF, Punto ini, Punto fin) {
        this.mapaOriginal = mapaO;
        this.mapaFlood = mapaF;
        this.inicio = ini;
        this.fin = fin;
    }

    public List<Punto> getVecinos(Punto punto) {
        List<Punto> lista = new ArrayList<>();
        lista.add(new Punto(punto.getX() + 1, punto.getY()));
        lista.add(new Punto(punto.getX(), punto.getY() - 1));
        lista.add(new Punto(punto.getX() - 1, punto.getY()));
        lista.add(new Punto(punto.getX(), punto.getY() + 1));
        for (int i = lista.size() - 1; i >= 0; i--) {
            if (!lista.get(i).isValidPoint(this.mapaOriginal)) {
                lista.remove(i);
            }
        }
        if(lista.size() < 2 && notStart(punto)){
           lista.clear();
        }
        System.out.println("Los vecinos validos son:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        return lista;
    }

    boolean notStart(Punto punto){
            return !(punto.getX() == inicio.getX() && punto.getY() == inicio.getY());
    }

    public Punto encontrarMejorPaso2(Punto currentPos) {
        List<Punto> vecinos = this.getVecinos(currentPos);
        currentPos = vecinos.get(0);
        for (int i = 0; i < vecinos.size(); i++) {
            if (this.mapaFlood[vecinos.get(i).getY()][vecinos.get(i).getX()] < this.mapaFlood[currentPos.getY()][currentPos.getX()]) {
                currentPos = vecinos.get(i);
                System.out.println("Nuevo vecino elegido");
            }
        }
        System.out.println("El mejor siguiente paso es: ");
        System.out.println(currentPos);
        System.out.println("Ya que su distancia manhattan es:");
        System.out.println(this.mapaFlood[currentPos.getY()][currentPos.getX()]);
        return currentPos;
    }

    /**
     * returns TRUE if not in list
     * @param punto
     * @return
     */
    boolean notInList(List<Punto> listaPuntos, Punto punto){
        for (Punto p :
                listaPuntos) {
            if(p.getX() == punto.getX() && p.getY() == punto.getY())
                return false;
        }
        return true;

    }

    public Punto encontrarMejorPaso(Punto currentPos, List<Punto> exclude) {
        List<Punto> vecinos = this.getVecinos(currentPos);
        if(vecinos.isEmpty()){
            return null;
        }
        currentPos = vecinos.get(0);
        for (int i = 0; i < vecinos.size(); i++) {
            if (this.mapaFlood[vecinos.get(i).getY()][vecinos.get(i).getX()] < this.mapaFlood[currentPos.getY()][currentPos.getX()]
            && notInList(exclude, vecinos.get(i)) ) {
                currentPos = vecinos.get(i);
                System.out.println("Nuevo vecino elegido");
            }
        }
        System.out.println("El mejor siguiente paso es: ");
        System.out.println(currentPos);
        System.out.println("Ya que su distancia manhattan es:");
        System.out.println(this.mapaFlood[currentPos.getY()][currentPos.getX()]);
        return currentPos;
    }

/*    public List<Punto> encontrarMejorCamino() {
        Punto current = inicio;
        System.out.println("El raton inicia en: ");
        System.out.println(inicio);
        Punto siguiente;
        this.listaDeMovimientos.add(current);
        while (this.mapaOriginal[current.getY()][current.getX()] != 'F') {
            System.out.println("Valor flood original:");
            System.out.println(mapaFlood[current.getY()][current.getX()]);
            mapaFlood[current.getY()][current.getX()]++;
            System.out.println("Nuevo valor flood: ");
            System.out.println(mapaFlood[current.getY()][current.getX()]);
            List<Punto> vecinos = getVecinos(current);
            if (vecinos.size() == 1 && mapaOriginal[current.getY()][current.getX()] != 'S') {
                System.out.println("PUNTO MUERTOOOO");
                mapaFlood[current.getY()][current.getX()] = 2 * mapaFlood.length;
                int i = listaDeMovimientos.size() - 1;
                while (this.getVecinos(listaDeMovimientos.get(i)).size() < 3) {
                    System.out.println("Regresando a interseccion");
                    mapaFlood[current.getY()][current.getX()] = 2 * mapaFlood.length;
                    listaDeMovimientos.remove(i);
                    i--;
                }
                current = listaDeMovimientos.get(i - 1);
            }
            siguiente = encontrarMejorPaso(current);
            this.listaDeMovimientos.add(new Punto(siguiente));
            current = siguiente;
            System.out.println("El raton se mueve a: ");
            System.out.println(current);
            System.out.println("El raton ha caminado:");
            System.out.println(listaDeMovimientos.size());
            Scanner scanner = new Scanner(System.in);
            //scanner.nextLine();
        }
        //TODO encontrarMejorCamino
        return listaDeMovimientos;
    }*/

    public List<Punto> encontrarMejorCamino2() {

        System.out.println("AAAAAA");

        Punto previo = inicio;
        Punto current = inicio;

        listaDeMovimientos.clear();
        listaDeMovimientos.add(inicio);

        Punto mejorPaso = encontrarMejorPaso(current, listaDeMovimientos);
        listaDeMovimientos.add(mejorPaso);
        current = mejorPaso;

        while (!isEnd(listaDeMovimientos.get(listaDeMovimientos.size() - 1))) {

            mejorPaso = encontrarMejorPaso(current, listaDeMovimientos);

            // dead point, PUNTO MUERTO
            // ir hacia atras hasta encontrar un Punto con 3 o mas caminos
            if(mejorPaso == null){
                //current =

            }
            listaDeMovimientos.add(mejorPaso);
            current = mejorPaso;

        }

        //TODO encontrarMejorCamino
        return listaDeMovimientos;
    }

    boolean isEnd(Punto p) {
        return p.getX() == fin.getX() && p.getY() == fin.getY();
    }
}
