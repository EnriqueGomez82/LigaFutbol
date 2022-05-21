package futbol;

import java.util.Scanner;

/**
 * @author Enrique Gómez
 * 
 * Version 2.0 de la App Liga de Futbol, con guardado serializable y algunas mejoras más, como borrado de partidos, multiples menus 
 * y control de Excepciones en el constructor como en el examen. 
 */
public class FutbolApp {

    public static void main(String[] args) {
        Historial his = new Historial();
        //Leemos el fichero para rellenar el ArrayList de Historial, si no existe usamos el ArrayList vacio.
        his.leerFichero();
        FutbolApp menu = new FutbolApp();
        //Si no hay partidos guardados el arraylist está vacio llamamos a MenuInicial
        if (his.tamanyo() <= 0) {
            menu.MenuInicial(his, true);
        } else {
            menu.MenuCompleto(his, true);
        }
    }

    public void MenuInicial(Historial his, Boolean guardados) {
        boolean salir = false;
        boolean guardadostmp = guardados;
        Scanner tcl = new Scanner(System.in);
        do {
            if (his.tamanyo() > 0) {
                FutbolApp menu = new FutbolApp();
                menu.MenuCompleto(his, guardadostmp);
                break;
            }
            System.out.println("1: Añadir partido\n2: Guardar datos (Si el listado está vacío, se perderán los datos guardados) \n3: SALIR");
            int op = tcl.nextInt();
            switch (op) {
                case 1:
                    his.anyadir();
                    guardadostmp = false;
                    System.out.println("PARTIDO AÑÄDIDO\n");
                    break;
                case 2:
                    his.guardarFichero();
                    System.out.println("DATOS GUARDADOS EN partidos.dat\n");
                    guardadostmp = true;
                    break;
                case 3:
                    salir = salirPrograma(guardadostmp);
                    break;
            }
        } while (!salir);
    }

    public void MenuCompleto(Historial his, Boolean guardados) {
        boolean salir = false;
        boolean guardadostmp = guardados;
        Scanner tcl = new Scanner(System.in);
        do {
            if (his.tamanyo() == 0) {
                FutbolApp menu = new FutbolApp();
                menu.MenuInicial(his, guardadostmp);
                break;
            }
            System.out.println("1: Añadir partido\n2: Mostrar los partidos jugados\n3: Mostrar partidos por fecha\n4: Mostrar partidos hechos y fecha\n5: Borrar partido\n6: Guardar datos\n7: SALIR");
            int op = tcl.nextInt();

            switch (op) {
                case 1:
                    his.anyadir();
                    guardadostmp = false;
                    System.out.println("PARTIDO AÑÄDIDO\n");
                    break;
                case 2:
                    System.out.println(his.toString());
                    break;
                case 3:
                    tcl.nextLine();
                    System.out.println("Introduce una fecha a buscar");
                    String fechatmp = tcl.nextLine();
                    if (!his.mostrarFecha(fechatmp)) {
                        System.out.println("No se han encontrado partidos con esta fecha");
                    }
                    break;
                case 4:
                    tcl.nextLine();
                    System.out.println("Introduce una fecha a buscar");
                    String fechatmp2 = tcl.nextLine();
                    System.out.println("Introduce equipo local a buscar");
                    String loctmp2 = tcl.nextLine();
                    System.out.println("Introduce equipo visitante a buscar");
                    String vistmp2 = tcl.nextLine();

                    if (!his.mostrarPartido(fechatmp2, loctmp2, vistmp2)) {
                        System.out.println("No se han encontrado partidos con esos datos\n");
                    }
                    break;
                case 5:
                    his.borrarPartido();
                    guardadostmp = false;
                    break;
                case 6:
                    his.guardarFichero();
                    System.out.println("DATOS GUARDADOS EN partidos.dat\n");
                    guardadostmp = true;
                    break;
                case 7:
                    salir = salirPrograma(guardadostmp);

                    break;
            }
        } while (!salir);
    }

    public Boolean salirPrograma(Boolean guardados) {
        Scanner tcl = new Scanner(System.in);
        Boolean salir = false;
        if (!guardados) {
            System.out.println("Los datos que no hayan sido guardados, se perderán.");
            boolean opout = false;
            do {
                System.out.println("¿Está seguro de salir sin guardar? (y/n)");

                String texto = tcl.nextLine();
                if (texto.equalsIgnoreCase("y")) {
                    opout = true;
                    salir = true;
                    System.out.println("Saliendo del programa");
                } else if (texto.equalsIgnoreCase("n")) {
                    opout = true;
                    System.out.println("Volviendo al menú....\n");
                }
            } while (!opout);
        } else {
            System.out.println("Saliendo del programa");
            salir = true;
        }

        return salir;
    }
}
