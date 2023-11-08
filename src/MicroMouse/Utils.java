package MicroMouse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private String pathCarpeta = "maps/";
    private String nombreArchivo = "LABTest.txt";
    private String linea;
    private List<String> buffer =  new ArrayList<String>();
    private File archivoMapa;
    private FileReader fileReader =  null;
    private BufferedReader bufferedReader = null;
    private char[][] mapa;
    public char[][] leerArchivoMapa(){
        archivoMapa = new File(pathCarpeta + nombreArchivo);
        if (archivoMapa.exists()) {
            System.out.println("El archivo existe");
            try {
                fileReader = new FileReader(archivoMapa);
                bufferedReader = new BufferedReader(fileReader);
                linea = bufferedReader.readLine();
                while (linea != null){
                    this.buffer.add(linea);
                    linea = bufferedReader.readLine();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            mapa = new char[buffer.size()][buffer.get(1).length()];
            for (int i = 0; i < buffer.size(); i++) {
                for (int j = 0; j < buffer.get(i).length(); j++) {
                    this.mapa[i][j] = buffer.get(i).charAt(j);
                }
            }
        }else {
            System.out.println("El archivo no existe, ingrese un nombre valido");
        }
        return this.mapa;
    }
    public static char encontrarCaracter (char[][] mapa, Punto coordenada){
        return mapa[coordenada.getY()][coordenada.getX()];
    }
    public static List<String> puntosAMovimientos(List<Punto> puntos){
        List<String> movimientos = new ArrayList<>();
        for (int i = 0; i < puntos.size()-1; i++) {
            if (puntos.get(i+1).getX()-puntos.get(i).getX() == 1) {
                movimientos.add("derecha");
            } else if (puntos.get(i+1).getX()-puntos.get(i).getX() == -1) {
                movimientos.add("izquierda");
            } else if (puntos.get(i+1).getY()-puntos.get(i).getY() == 1) {
                movimientos.add("abajo");
            } else if (puntos.get(i+1).getY()-puntos.get(i).getY() == -1) {
                movimientos.add("arriba");
            }
        }
        return movimientos;
    }
    public static void escribirMovimientos(List<String> movimientos, String nombreArchivo){
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        File archivo = new File("maps\\"+ nombreArchivo + ".txt");
        if (archivo.exists()) {
            System.out.println("Ya existe el archivo, sobreescribiendo...");
            try {
                archivo.createNewFile();
                fileWriter = new FileWriter(archivo);
                printWriter = new PrintWriter(fileWriter);
                for (int i = 0; i < movimientos.size(); i++) {
                    printWriter.println(movimientos.get(i));
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    printWriter.close();
                    fileWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("Creando nuevo archivo");
            try {
                archivo.createNewFile();
                fileWriter = new FileWriter(archivo);
                printWriter = new PrintWriter(fileWriter);
                for (int i = 0; i < movimientos.size(); i++) {
                    printWriter.println(movimientos.get(i));
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    printWriter.close();
                    fileWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
