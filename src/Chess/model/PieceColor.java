package Chess.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

public enum PieceColor implements Serializable {
    WHITE(Attribute.TEXT_COLOR(255,255,255)),
    BLACK(Attribute.TEXT_COLOR(0,0,0));

    private Attribute color;

    /**
     * Constructor de Color de Pieza
     * @param color
     */
    PieceColor(Attribute color){
        this.color=color;
    }

    /**
     * Siguiente color
     * @return Color de la pieza
     */
    public PieceColor next(){
        // return PieceColor.values()[((ordinal()+1)%PieceColor.values().length)];
        if (ordinal()==0)
            return BLACK;
        else
            return WHITE;
    }

    /**
     * Metodo que obtiene el atributo de la Pieza de color
     * @return Atributo color
     */
    public Attribute getAttribute() {
        return color;
    }


}
