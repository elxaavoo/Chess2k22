package Chess.model;

import java.io.Serializable;

public class Coord implements Serializable {
    private char x;
    private int y;

    public Coord(char x, int y){
        this.x=String.valueOf(x).toUpperCase().charAt(0);
        this.y=y;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    /**
     * Metodo que da la Coordenada de abajo
     * @return Coordenada
     */
    public Coord DownCell(){
        return new Coord(x,y+1);
    }

    /**
     * Metodo que da la coordenada de arriba
     * @return Coordenada
     */
    public Coord UpCell(){
        return new Coord(x,y-1);
    }

    /**
     * Metodo que da la coordenada de la derecha
     * @return Coordenada
     */
    public Coord RightCell(){
        return new Coord((char) (x+1),y);
    }

    /**
     * Metodo que da la coordenada de la izquieda
     * @return Coordenada
     */
    public Coord LeftCell(){
        return new Coord((char) (x-1),y);
    }

    /**
     * Setter de la X
     * @param x
     */
    public void setX(char x) {
        this.x = x;
    }

    /**
     * Getter de la letra de la Coordenada
     * @return X
     */
    public char getLetter() {
        return x;
    }

    /**
     * Getter del Numero de la Coordenada
     * @return Y
     */
    public int getNumber() {
        return y;
    }

    /**
     * Setter de la Y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * HashCode de la Coordenada
     * @return int
     */
    @Override
    public int hashCode(){
        return y;
    }

    /**
     * Metodo para saber si dos objetos son el mismo pero para coordenadas
     * @param o
     * @return Booleano de si son iguales o no lo son
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Coord)) return false;
        return ((Coord) o).getLetter() == this.getLetter() && ((Coord) o).getNumber() == this.getNumber();
    }
}
