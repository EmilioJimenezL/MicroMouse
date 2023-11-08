package MicroMouse;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Utils a = new Utils();
        char[][] mapa = a.leerArchivoMapa();
        Laberinto laberinto = new Laberinto(mapa);
        Punto inicio = laberinto.encontrarInicio();
        Punto fin[] = laberinto.encontrarFin();
        Raton raton = new Raton(mapa, laberinto.convertirAFlood(), inicio, laberinto.encontrarFinalMasCercano());
        List<Punto> lista = raton.encontrarMejorCamino();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }
}