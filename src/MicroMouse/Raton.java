package MicroMouse;

import javax.swing.text.Style;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
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

    /**
     *
     * @param mapa
     * @param punto
     * @return empty list if it is a dead point,
     * otherwise returns list of available neighbours that are not walls.
     * It does NOT take into account whether neighbours have already been
     * visited.
     */
    public List<Punto> getVecinos(Punto punto) {
        List<Punto> lista = new ArrayList<>();
        lista.add(new Punto(punto.getX() + 1, punto.getY()));
        lista.add(new Punto(punto.getX(), punto.getY() - 1));
        lista.add(new Punto(punto.getX() - 1, punto.getY()));
        lista.add(new Punto(punto.getX(), punto.getY() + 1));
        for (int i = lista.size()-1; i >= 0 ; i--) {
            if (!lista.get(i).isValidPoint(this.mapaOriginal)) {
                lista.remove(i);
            }
        }
        System.out.println("Los vecinos validos son:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        return lista;
    }
    public Punto encontrarMejorPaso(Punto currentPos){
        List<Punto> vecinos = this.getVecinos(currentPos);
        currentPos = vecinos.get(0);
        for (int i = 0; i < vecinos.size(); i++) {
            if (vecinos.get(i).getManhattanDistance(fin) < currentPos.getManhattanDistance(fin)) {
                currentPos = vecinos.get(i);
                System.out.println("Nuevo vecino elegido");
            }
        }
        System.out.println("El mejor siguiente paso es: ");
        System.out.println(currentPos);
        return currentPos;
    }
    public List<Punto> encontrarMejorCamino() {
        Punto current = inicio;
        System.out.println("El raton inicia en: ");
        System.out.println(inicio);
        Punto siguiente;
        this.listaDeMovimientos.add(current);
        while(this.mapaOriginal[current.getY()][current.getX()] != 'F'){
            System.out.println("Valor flood original:");
            System.out.println(mapaFlood[current.getY()][current.getX()]);
            mapaFlood[current.getY()][current.getX()]++;
            System.out.println("Nuevo valor flood: ");
            System.out.println(mapaFlood[current.getY()][current.getX()]);
            List<Punto> vecinos = getVecinos(current);
            if(vecinos.size() == 1){
                System.out.println("PUNTO MUERTOOOO");
                mapaFlood[current.getY()][current.getX()] = 2 * mapaFlood.length;
            }
            siguiente = encontrarMejorPaso(current);
            this.listaDeMovimientos.add(new Punto(siguiente));
            current = siguiente;
            System.out.println("El raton se mueve a: ");
            System.out.println(current);
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        //TODO encontrarMejorCamino
        return listaDeMovimientos;
    }
}
