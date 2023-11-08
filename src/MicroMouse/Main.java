package MicroMouse;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Utils a = new Utils();
        char[][] mapa = a.leerArchivoMapa();
        Laberinto laberinto = new Laberinto(mapa);
        laberinto.encontrarInicio();
        laberinto.encontrarFin();
        Punto finMasCercano = laberinto.encontrarFinalMasCercano();
        System.out.println("FIN mas cercano: " + finMasCercano);

        int[][] floodmap = laberinto.convertirAFlood();
        laberinto.printFloodMap();
        Punto inicio = laberinto.encontrarInicio();
        Punto fin[] = laberinto.encontrarFin();
        Raton raton = new Raton(mapa, laberinto.convertirAFlood(), inicio, laberinto.encontrarFinalMasCercano());
        List<Punto> lista = raton.encontrarMejorCamino();
        List<String> movimientos = Utils.puntosAMovimientos(lista);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        for (int i = 0; i < movimientos.size(); i++) {
            System.out.println(movimientos.get(i));
        }
        Utils.escribirMovimientos(movimientos, "test");
    }
}