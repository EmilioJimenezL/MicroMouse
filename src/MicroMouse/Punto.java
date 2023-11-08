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
    public boolean isValidPoint(int mapLength){
        return !(this.x < 0 || this.x >= mapLength ||
                this.y < 0 || this.y >= mapLength);
    }
    public List<Punto> getVecinos(char[][] mapa) {
        List<Punto> lista = new ArrayList<Punto>();
        lista.add(new Punto(this.x + 1, this.y));
        lista.add(new Punto(this.x, this.y - 1));
        lista.add(new Punto(this.x - 1, this.y));
        lista.add(new Punto(this.x, this.y + 1));
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).isValidPoint(mapa.length)) {
                lista.remove(i);
            }
        }
        return lista;
    }
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
        return String.format("(%d, %d)\n", x, y);
    }
}
