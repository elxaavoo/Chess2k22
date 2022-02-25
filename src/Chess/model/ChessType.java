package Chess.model;

import java.io.Serializable;

public enum ChessType implements Serializable {
    /**
     * Todos los tipos de piezas que hay*/
    BLACK_KING("♚", PieceColor.BLACK),
    BLACK_QUEEN("♛",PieceColor.BLACK),
    BLACK_HORSE("♞",PieceColor.BLACK),
    BLACK_PAWN("♟",PieceColor.BLACK),
    BLACK_ROOK("♜",PieceColor.BLACK),
    BLACK_BISHOP("♝",PieceColor.BLACK),
    WHITE_KING("♚",PieceColor.WHITE),
    WHITE_QUEEN("♛",PieceColor.WHITE),
    WHITE_HORSE("♞",PieceColor.WHITE),
    WHITE_PAWN("♟",PieceColor.WHITE),
    WHITE_ROOK("♜",PieceColor.WHITE),
    WHITE_BISHOP("♝",PieceColor.WHITE);

    private final String shape;
    private PieceColor color;

    ChessType(String shape, PieceColor color){
        this.shape=shape;
        this.color=color;
    }

    public String getShape() {
        return shape;
    }

    public PieceColor getColor() {
        return color;
    }
}
