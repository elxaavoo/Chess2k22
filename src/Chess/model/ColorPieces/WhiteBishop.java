package Chess.model.ColorPieces;

import Chess.model.Bishop;
import Chess.model.Cell;
import Chess.model.ChessType;

public class WhiteBishop extends Bishop {
    public WhiteBishop(Cell cell) {
        super(cell, ChessType.WHITE_BISHOP);
        place();
    }
}
