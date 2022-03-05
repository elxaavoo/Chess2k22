package Chess.model.Tools;

import Chess.model.Coord;

import java.io.Serializable;

public class Tool implements Serializable {
    /**
     * Metodo que sirva para saber si contiene una coordenada un array de coordenadas
     * @param coords
     * @param coord
     * @return Devuelve un booleano de si es correcto o no
     */
    public static boolean contains(Coord[] coords, Coord coord){
        int i = 0;
        while(i < coords.length)
            if (coords[i++].equals(coord))
                return true;

            return false;
    }

    /**
     * Metodo que sive para meter coordenada dentro de un array de coordenadas
     * @param array
     * @param coord
     * @return Devuelve array con cordenadas
     */
    public static Coord[] add(Coord[] array, Coord coord){
        Coord[] aux = new Coord[array.length+1];

        for (int i = 0; i< array.length;i++)
            aux[i]=array[i];

        aux[array.length]=coord;
        return aux;
    }

    /**
     * Metodo para mostrar las coordenadas de dentro de un array
     * @param coords
     */
    public static void show(Coord[] coords){
        for (Coord coord: coords)
            System.out.println(coord);
    }

    /**
     * Metodo que sirve para limpiar la pantalla en un terminal de linux o windows
     */
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
