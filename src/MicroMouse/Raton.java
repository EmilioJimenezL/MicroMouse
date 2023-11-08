package MicroMouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public List<Punto> getVecinos(char[][] mapa, Punto punto) {
        List<Punto> lista = new ArrayList<Punto>();
        lista.add(new Punto(punto.getX() + 1, punto.getY()));
        lista.add(new Punto(punto.getX(), punto.getY() - 1));
        lista.add(new Punto(punto.getX() - 1, punto.getY()));
        lista.add(new Punto(punto.getX(), punto.getY() + 1));
        for (int i = lista.size()-1; i >= 0 ; i--) {
            if (!lista.get(i).isValidPoint(mapa)) {
                System.out.printf("%s NO es valido.\n", lista.get(i));
                lista.remove(i);
            } else {
                System.out.printf("%s SI es valido.\n", lista.get(i));

            }
        }
        if(lista.size() == 1){
            lista.remove(0);
        }
        return lista;
    }
    public Punto encontrarMejorPaso(Punto currentPos){
        List<Punto> vecinos = this.getVecinos(this.mapaOriginal, currentPos);
        int currentD = currentPos.getManhattanDistance(fin);
        for (int i = 0; i < vecinos.size(); i++) {
            if (vecinos.get(i).getManhattanDistance(fin) < currentD) {
                currentPos = vecinos.get(i);
                currentD = currentPos.getManhattanDistance(fin);
            }
        }
        return currentPos;
    }
    public List<Punto> encontrarMejorCamino() {
        LinkedList<Punto> movimientos = new LinkedList<Punto>();
        List<Punto> movimientos = new ArrayList<Punto>();
        Punto previous = inicio;
        Punto current = inicio;
        int count = 0;
        while(true){
            List<Punto> vecinos = getVecinos(mapaOriginal, current);
            if(vecinos.isEmpty()){
                System.out.println("PUNTO MUERTOOOO");
            }
            count++;
            if(count > 6){
                break;
            }
        }
        //TODO encontrarMejorCamino
        return listaDeMovimientos;
    }
}
