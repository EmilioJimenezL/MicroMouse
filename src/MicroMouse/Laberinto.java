package MicroMouse;

import java.awt.*;

public class Laberinto {
    private char[][] mapaOriginal;
    private int[][] mapaFlood;
    private Punto inicio = new Punto();
    private Punto[] fin = new Punto[4];
    private Punto finalMasCercano = new Punto();

    public Laberinto (char[][] mapa){
        this.mapaOriginal = mapa;
        mapaFlood = new int[mapaOriginal.length][mapaOriginal[0].length];
    }
    public Punto encontrarInicio(){
        for (int i = 0; i < mapaOriginal.length; i++) {
            for (int j = 0; j < mapaOriginal[0].length; j++) {
                if (mapaOriginal[i][j] == 'S') {
                    this.inicio.setX(j);
                    this.inicio.setY(i);
                }
            }
        }
        return this.inicio;
    }
    public Punto[] encontrarFin(){
        int k = 0;
        for (int i = 0; i < mapaOriginal.length; i++) {
            for (int j = 0; j < mapaOriginal[i].length; j++) {
                if (mapaOriginal[i][j] == 'F') {
                    this.fin[k] = new Punto(j, i);
                    k++;
                }
            }
        }
        return this.fin;
    }
    public Punto encontrarFinalMasCercano(){
        int minD = Math.abs(inicio.getX()-fin[0].getX()) + Math.abs(inicio.getY() - fin[0].getY());
        finalMasCercano.setX(fin[0].getX());
        finalMasCercano.setY(fin[0].getY());
        for (int i = 1; i < fin.length; i++) {
            int currentD = inicio.getManhattanDistance(fin[i]);
            if (currentD < minD) {
                finalMasCercano.setX(fin[i].getX());
                finalMasCercano.setY(fin[i].getY());
            }
        }
        System.out.println(finalMasCercano);
        return this.finalMasCercano;
    }

    public int[][] convertirAFlood(){
        for(int i = 0; i < mapaOriginal.length; i++){
            for (int j = 0; j < mapaOriginal[0].length; j++) {
                Punto punto = new Punto(i, j);
                this.mapaFlood[i][j] = punto.getManhattanDistance(finalMasCercano);
                if(mapaOriginal[i][j] == '1'){
                    mapaFlood[i][j] = 2*mapaOriginal.length;
                }
            }
        }

        return mapaFlood;
    }

    public void printFloodMap(){
        for(int i = 0; i < mapaFlood.length; i++){
            for(int j = 0; j < mapaFlood[0].length; j++){
                System.out.printf("%d\t", mapaFlood[i][j]);
            }
            System.out.println();
        }
    }

    public void printIsValidPointMap(){
        for(int i = 0; i < mapaFlood.length; i++){
            for(int j = 0; j < mapaFlood[0].length; j++){
                System.out.printf("%b\t", new Punto(j, i).isValidPoint(mapaOriginal));
            }
            System.out.println();
        }
    }
}
