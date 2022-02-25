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
     * Metodo que te da la coordenada de abajo*/
    public Coord DownCell(){
        return new Coord(x,y+1);
    }
    /**
     * Metodo que te da la coordenada de arriba*/
    public Coord UpCell(){
        return new Coord(x,y-1);
    }
    /**
     * Metodo que te da la coordenada de la derecha*/
    public Coord RightCell(){
        return new Coord((char) (x+1),y);
    }
    /**
     * Metodo que te da la coordenada de la izquierda*/
    public Coord LeftCell(){
        return new Coord((char) (x-1),y);
    }

    public void setX(char x) {
        this.x = x;
    }
    /**
     * Te da la letra de la coordenada*/
    public char getLetter() {
        return x;
    }
    /**
     * Te da el numero de la coordenada*/
    public int getNumber() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    /**
     * Metodo que sobreescribe el HashCode de la Coordenada*/
    @Override
    public int hashCode(){
        return y;
    }
    /**
     * Metodo que sobreescribe la manera de saber si dos coordenadas son iguales entre sí, sabiendo que
     * son iguales si el numero y la letra son el mismo que el de otra coordenada*/
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Coord)) return false;
        return ((Coord) o).getLetter() == this.getLetter() && ((Coord) o).getNumber() == this.getNumber();
    }
}
