package MicroMouse;
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
    }
}