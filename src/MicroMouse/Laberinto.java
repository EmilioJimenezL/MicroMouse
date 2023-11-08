package MicroMouse;

import java.awt.*;

public class Laberinto {
    private char[][] mapaOriginal;
    private int[][] mapaFlood;
    private Punto inicio = new Punto();
    private Punto[] fin;
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
        int finCount = 0;
        for (int i = 0; i < mapaOriginal.length; i++) {
            for (int j = 0; j < mapaOriginal[i].length; j++) {
                if (mapaOriginal[i][j] == 'F') {
                    finCount++;
                }
            }
        }
        fin = new Punto[finCount];
        System.out.printf("FIN COUNT is %d\n", finCount);

        int k = 0;
        for (int i = 0; i < mapaOriginal.length; i++) {
            for (int j = 0; j < mapaOriginal[i].length; j++) {
                if (mapaOriginal[i][j] == 'F') {
                    System.out.printf("Final encontrado en %d, %d\n", j, i);
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
        return this.finalMasCercano;
    }

    public int[][] convertirAFlood(){
        System.out.println("FINAL ES: " + finalMasCercano);
        for(int i = 0; i < mapaOriginal.length; i++){
            for (int j = 0; j < mapaOriginal[0].length; j++) {
                Punto punto = new Punto(j, i);
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
