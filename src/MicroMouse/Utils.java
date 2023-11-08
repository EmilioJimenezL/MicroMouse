package MicroMouse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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


}
