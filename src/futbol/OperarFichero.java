package futbol;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Enrique Gómez
 */
public class OperarFichero {

    public ArrayList<PartidoFutbol> leerDatos() throws FileNotFoundException {
        ObjectInputStream ois = null;
        ArrayList<PartidoFutbol> Partido = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("partidos.dat")));
            Partido = (ArrayList) ois.readObject();
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
            //La primera vez que se ejecuta no existirá el fichero. No queremos imprimir el mensaje, solo controlar la excepción fuera.
            //Sacamos la excepción fuera para capturarla en Historial.
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        if (Partido != null) {
            try {
                ois.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return Partido;
    }

    public void escribirDatos(ArrayList<PartidoFutbol> Partido) {
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("partidos.dat")));
            oos.writeObject(Partido);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if (Partido != null) {
            try {
                oos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
