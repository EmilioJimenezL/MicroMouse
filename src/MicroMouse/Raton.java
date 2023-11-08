package MicroMouse;

import java.util.ArrayList;
import java.util.List;

public class Raton {
    private char[][] mapaOriginal;
    private int[][] mapaFlood;
    Punto inicio, fin;
    List<Punto> listaDeMovimientos = new ArrayList<Punto>();
    public Raton (char[][] mapaO, int[][] mapaF, Punto ini, Punto fin){
        this.mapaOriginal = mapaO;
        this.mapaFlood = mapaF;
        this.inicio = ini;
        this.fin = fin;
    }
    public List<Punto> encontrarMejorCamino(){
        //TODO encontrarMejorCamino
        return listaDeMovimientos;
    }
    public Punto encontrarMejorPaso(Punto currentPos){
        List<Punto> vecinos = currentPos.getVecinos(this.mapaOriginal);
        int currentD = currentPos.getManhattanDistance(fin);
        for (int i = 0; i < vecinos.size(); i++) {
            vecinos.get(i);
        }
        return siguientePaso;
    }
}
