package Chess.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

public enum PieceColor implements Serializable {
    WHITE(Attribute.TEXT_COLOR(255,255,255)),
    BLACK(Attribute.TEXT_COLOR(0,0,0));

    private Attribute color;

    PieceColor(Attribute color){
        this.color=color;
    }

    public PieceColor next(){
        // return PieceColor.values()[((ordinal()+1)%PieceColor.values().length)];
        if (ordinal()==0)
            return BLACK;
        else
            return WHITE;
    }

    public Attribute getAttribute() {
        return color;
    }


}
