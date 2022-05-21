package futbol;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Enrique Gómez
 
 * b) Creem la classe Historial per a gestionar tots els partits que es van
 * jugant. Podria ser similar al següent esquema
 */
public class Historial implements Serializable {

    private ArrayList<PartidoFutbol> lista_partidos = new ArrayList();

    public Historial() {

    }

    // creo un objeto OperarFichero para poder usar el metodo sobre el arraylist desde su clase porque el arrayList es private
    public void leerFichero() {
        OperarFichero op = new OperarFichero();
        try {
            lista_partidos = op.leerDatos();
        } catch (FileNotFoundException e) {
            //El fichero no existe, no imprimimos nada. Se usará el ArrayList vacio de lista_partidos.
        }
    }

    // creo objeto OperarFichero y utilizo el metodo escribir datos ( por parametro el ArrayList )
    public void guardarFichero() {
        OperarFichero op = new OperarFichero();
        op.escribirDatos(lista_partidos);
    }

    public void anyadir() {

        Scanner tcl = new Scanner(System.in);
        Integer gol_loc, gol_vis;
        String fecha, vis, loc;
        boolean salir = false;
        do {

            System.out.println("Introduce Fecha");
            fecha = tcl.nextLine();

            System.out.println("Introduce Equipo Visitante");
            vis = tcl.nextLine();

            System.out.println("Introduce Equipo Local");
            loc = tcl.nextLine();
            try {
                System.out.println("Introduce Goles Visitante");
                gol_vis = tcl.nextInt();

                System.out.println("Introduce Goles Local");
                gol_loc = tcl.nextInt();


                PartidoFutbol partido1 = null;
                try {
                    partido1 = new PartidoFutbol(fecha, loc, vis, gol_loc, gol_vis);
                } catch (PartidoException ex) {
                    System.out.println(ex);
                    System.out.println("POR FAVOR VUELVE A INTRODUCIR TODOS LOS DATOS DE NUEVO.\n");
                    tcl.nextLine(); //limpiamos buffer
                }

                if (partido1 != null) {
                    lista_partidos.add(partido1);
                    salir = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduce un entero");
                System.out.println("POR FAVOR VUELVE A INTRODUCIR TODOS LOS DATOS DE NUEVO.\n");
                tcl.nextLine(); //Limpiamos buffer
            }

        } while (!salir);
    }

    //OPCION 3 - metodo que muestra todos los partidos de una determinada fecha. Devuelve true si ha encontrado algun partido con esa fecha.
    public boolean mostrarFecha(String fecha) {
        boolean encontrado = false;
        for (PartidoFutbol partitTmp : lista_partidos) {
            if (fecha.equals(partitTmp.getFecha())) {
                System.out.println("ID:"+lista_partidos.indexOf(partitTmp)+"\n"+partitTmp);
                encontrado = true;
            }
        }
        return encontrado;
    }

    //OPCION 4 - metodo que muestra el resultado de un determinado partido. Devuelve true si ha encontrado el partido buscado.
    public boolean mostrarPartido(String fecha, String equipo_loc, String equipo_vis) {
        boolean encontrado = false;
        try {

            //Instancia un nuevo partido con los datos buscados
            PartidoFutbol partidoBuscado = new PartidoFutbol(fecha, equipo_loc, equipo_vis);

            for (PartidoFutbol partido : lista_partidos) {

                //Para cada partido de lista_partidos, comprobamos si es igual a partidoBuscado.
                if (partido.equals(partidoBuscado)) {

                    System.out.println("ID:"+lista_partidos.indexOf(partido)+"\n"+partido);
                    encontrado = true;
                }
            }
        } catch (PartidoException ex) {
            System.out.println(ex);
        }
        return encontrado;
    }
    public boolean buscarPorId(int idPartido){
        boolean encontrado = false;
        if(idPartido>=0 && idPartido<tamanyo()){
            System.out.println(lista_partidos.get(idPartido));
            encontrado = true;
        }
        
        
        
        return encontrado;
    }

    public int tamanyo() {
        return lista_partidos.size();
    }
    
    public boolean borrarPartido(){
        Scanner tcl = new Scanner(System.in);
        boolean encontrado=false;
        try{
            System.out.println("Por favor introduce un ID entre 0 y "+ (tamanyo()-1) );
        
            int idPartido = tcl.nextInt();
            if(buscarPorId(idPartido)){
                lista_partidos.remove(idPartido);
                System.out.println("PARTIDO ID: "+idPartido+" ELIMINADO.\n");
                encontrado = true;
            }else{
                System.out.println("Partido no encontrado");
            }
        }catch(InputMismatchException ex){
            System.out.println("Por favor introduce un número");
        }
        
        return encontrado;
    }

    @Override
    public String toString() {
        String listado = "";
        if (lista_partidos.size() > 0) {
            for (PartidoFutbol partido : lista_partidos) {
                listado += "ID: "+lista_partidos.indexOf(partido);
                listado += "\n";
                listado += partido.toString();
                listado += "\n";
            }
        } else {
            listado = "No existen partidos.\n";
        }
        return listado;
    }
}
