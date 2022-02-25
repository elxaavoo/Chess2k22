package Chess.model.Tools;

import Chess.model.Coord;

import java.io.Serializable;

public class Tool implements Serializable {

    public static boolean contains(Coord[] coords, Coord coord){
        int i = 0;
        while(i < coords.length)
            if (coords[i++].equals(coord))
                return true;

            return false;
    }
    public static Coord[] add(Coord[] array, Coord coord){
        Coord[] aux = new Coord[array.length+1];

        for (int i = 0; i< array.length;i++)
            aux[i]=array[i];

        aux[array.length]=coord;
        return aux;
    }
    public static void show(Coord[] coords){
        for (Coord coord: coords)
            System.out.println(coord);
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
