package futbol;

import java.io.Serializable;

/**
 * @author Enrique Gómez
 * Volem guardar informació d'una lliga de Futbol a) Creem la classe
 * PartitFutbol per a gestionar cada partit, amb els atributs i mètodes que es
 * proposen:
 */
public class PartidoFutbol implements Serializable {

    //atributos
    private String fecha;
    private String equipo_loc;
    private String equipo_vis;
    private int goles_loc;
    private int goles_vis;

    //constructor con todos los parametros (no lo utilizo, no me hace falta, pero lo pongo porque esta en garantia se puede devolver xD )
    public PartidoFutbol(String fecha, String equipo_loc, String equipo_vis, int goles_loc, int goles_vis) throws PartidoException{
        if(fecha.isEmpty() || equipo_loc.isEmpty() || equipo_vis.isEmpty()){
            throw new PartidoException("La fecha, o los equipos no pueden estar vacios.");
        }else{
            this.fecha = fecha;
            this.equipo_loc = equipo_loc;
            this.equipo_vis = equipo_vis;
        }
          
        if(goles_loc < 0 || goles_vis < 0){
            throw new PartidoException("El mínimo es 0");
        }else{
            this.goles_loc = goles_loc;
            this.goles_vis = goles_vis;
        }
    }

    //Contructor pre partido
    public PartidoFutbol(String fecha, String equipo_loc, String equipo_vis) throws PartidoException {
        if(fecha.isEmpty() || equipo_loc.isEmpty() || equipo_vis.isEmpty()){
            throw new PartidoException("La fecha, o los equipos no pueden estar vacios.");
        }else{
            this.fecha = fecha;
            this.equipo_loc = equipo_loc;
            this.equipo_vis = equipo_vis;
        }
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEquipo_loc(String equipo_loc) {
        this.equipo_loc = equipo_loc;
    }

    public void setEquipo_vis(String equipo_vis) {
        this.equipo_vis = equipo_vis;
    }

    public void setGoles_loc(int goles_loc) {
        this.goles_loc = goles_loc;
    }

    public void setGoles_vis(int goles_vis) {
        this.goles_vis = goles_vis;
    }

    // Identificar si un partido es diferente a otro partido
    public boolean equals(PartidoFutbol un_partido) {
        if (un_partido instanceof PartidoFutbol) {  //Comprobar que un_partido es un objeto de PartidoFutbol

            if (un_partido.getFecha().equalsIgnoreCase(this.getFecha())
                    && un_partido.getLocal().equalsIgnoreCase(this.getLocal())
                    && un_partido.getVisitante().equalsIgnoreCase(this.getVisitante())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // escribir los datos del partido
    @Override
    public String toString() {
        return "Fecha: " + fecha + "\nEquipo local: " + equipo_loc + "\nEquipo visitante: " + equipo_vis + "\nGoles equipo local: " + goles_loc + "\nGoles equipo visitante: " + goles_vis;
    }

    //obtener la fecha del partido
    public String getFecha() {
        return fecha;
    }

    //obtener el equipo local
    public String getLocal() {
        return equipo_loc;
    }

    //obtener el equipo visitante
    public String getVisitante() {
        return equipo_vis;
    }
}
