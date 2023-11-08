package MicroMouse;

import java.util.ArrayList;
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

    public List<Punto> encontrarMejorCamino() {
        //TODO encontrarMejorCamino
        return listaDeMovimientos;
    }

    /**
     * @return
     */
    /**
     public Punto encontrarMejorPaso(Punto currentPos){
     //List<Punto> vecinos = currentPos.getVecinos(this.mapaOriginal);
     int currentD = currentPos.getManhattanDistance(fin);
     for (int i = 0; i < vecinos.size(); i++) {
     ;
     }
     return siguientePaso;
     }
     */
}
