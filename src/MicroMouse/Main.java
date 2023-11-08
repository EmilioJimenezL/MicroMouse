package MicroMouse;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Utils a = new Utils();
        char[][] mapa = a.leerArchivoMapa();
        Laberinto laberinto = new Laberinto(mapa);
        Punto inicio = laberinto.encontrarInicio();
        Punto fin[] = laberinto.encontrarFin();
        System.out.println(inicio);
        for (int i = 0; i < fin.length; i++) {
            System.out.println(fin[i]);
        }
        System.out.println(laberinto.encontrarFinalMasCercano());

        laberinto.convertirAFlood();
        laberinto.printFloodMap();
        laberinto.printIsValidPointMap();

//        System.out.println(new Punto(-1,0).isValidPoint(mapa));
//        System.out.println(new Punto(1,19).isValidPoint(mapa));

        Raton raton = new Raton(mapa, laberinto.convertirAFlood(), inicio, laberinto.encontrarFinalMasCercano());
        List<Punto> vecinos = raton.getVecinos(mapa, new Punto(7,2));

        for (int i = 0; i < vecinos.size(); i++) {
            System.out.println(vecinos.get(i));
        }
        //System.out.println(new Punto(0,1).isValidPoint(mapa));

    }
}