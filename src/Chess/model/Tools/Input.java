package Chess.model.Tools;

import Chess.model.Coord;
import Chess.model.PieceColor;

import java.io.Serializable;
import java.util.Locale;
import java.util.Scanner;

public class Input implements Serializable {
    /**
     * Metodo para obtener el color de turno
     * @param color
     * @return Color del turno
     */
    public static PieceColor getColorTurno(String color){
        if (color.toLowerCase()=="blanco")
            return PieceColor.WHITE;
        else if (color.toLowerCase()=="negro")
            return PieceColor.BLACK;
        return null;
    }

    /**
     * Metodo para obtener la opción de juego
     * @return Opcion del juego
     */
    public static int getOptionGame(){
        Scanner sc = new Scanner(System.in);
        boolean pasarWhile = false;
        int opcion;
        do {
            System.out.print("Que opcion vas a elegir? ");
            opcion = sc.nextInt();
            if (opcion==1 || opcion==2)
                pasarWhile = true;
            else{
                System.out.println("=============================================");
                System.out.println("Esa opcion no esta permitida, repitela.");
                System.out.println("=============================================");
            }
        }while(!pasarWhile);
        return opcion;
    }

    /**
     * Metodo para obtener coordenada
     * @return Coordenada indicada
     */
    public static Coord getCoordenada(){
        Scanner sc = new Scanner(System.in);
        String coord = "";
        boolean pasarWhile = false;
        do {
            System.out.print("Dame la Coordenada: ");
            coord = sc.nextLine();
            coord = coord.toUpperCase();
            if (coord.length()==2) {
                if (coord.charAt(0) >= 'A' && coord.charAt(0) <= 'H' && coord.charAt(1) >= 49 && coord.charAt(1) <= 56)
                    pasarWhile = true;
                else
                    System.out.println("La coordenada obtenida no es valida");
            }else {
                System.out.println("La coordenada obtenida no es valida");
            }

        }while (!pasarWhile);
        char x = coord.charAt(0);
        int y = coord.charAt(1) - 48;
        return new Coord(x,y);
    }

    /**
     * Metodo para obtener opciones cuando ya estas jugando
     * @return Opcion de juego
     */
    public static char getOption(){
        Scanner sc = new Scanner(System.in);
        char option;
        boolean pasarWhile = false;
        do {
            System.out.println("【Coordenada: [c]】【Guardar:[g]】【Exit:[e]】");
            option = sc.nextLine().toUpperCase().charAt(0);
            if (option == 'C' || option == 'G' || option == 'E')
                pasarWhile=true;
            else
                System.out.println("No es correcto lo que has introducido, repitelo");
        }while(!pasarWhile);
        return option;
    }

    /**
     * Metodo para obtener el color con el que quieres jugar
     * @return Color con el que empiezas
     */
    public static PieceColor getPieceColorBoard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println("Con que color quieres jugar? (SI el color es erroneo, por defecto jugador BLANCO) ");
        System.out.println("=============================================");
        String color = sc.nextLine();
        color = color.toUpperCase();
        if (color.equals("NEGRO") || color.equals("N"))
            return PieceColor.BLACK;
        return PieceColor.WHITE;

    }

    /**
     * Metodo para obtener archivo de juego
     * @return String
     */
    public static String getFile(){
        String file = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Dime el nombre del fichero que quieres cargar:  ");
        file = sc.nextLine();
        return file;
    }

    /**
     * Metodo para conseguir  String
     * @return String
     */
    public static String getString() {
        Scanner sc = new Scanner(System.in);
        String out = "";
        System.out.print("Como quieres que se llame el archivo donde guardar el juego? ");
        out = sc.nextLine();
        return out;

    }
}
