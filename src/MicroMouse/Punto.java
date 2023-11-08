package MicroMouse;

import java.util.ArrayList;
import java.util.List;

public class Punto {
    private int x;
    private int y;
    public Punto (){
        this.x = 0;
        this.y = 0;
    }

    public Punto(int x, int y){
        this.x = x;
        this.y = y;

    }
    public boolean isValidPoint(char[][] mapaOriginal){
        return !(this.x < 0 || this.x >= mapaOriginal.length ||
                this.y < 0 || this.y >= mapaOriginal.length || mapaOriginal[this.y][this.x] == '1' );
    }

    /**
     *      * 1. Consigue los puntos validos dentro de coordenadas
     *      * 2. Checa si alguno de ellos es una pared
     *      * 3. Consigue el del menor valor en el mapa Flood
     */
    public int getManhattanDistance(Punto a){
        return Math.abs(this.x - a.getX()) + Math.abs(this.y - a.getY());
    }
    // assumes isValidPoint(coordenada) is true,
    // meaning coordinates are not negative or out of bounds.
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }
}
