package Chess.model.ColorPieces;

import Chess.model.Bishop;
import Chess.model.Cell;
import Chess.model.ChessType;

public class BlackBishop extends Bishop {
    public BlackBishop(Cell cell) {
        super(cell, ChessType.BLACK_BISHOP);
        place();
    }
}
