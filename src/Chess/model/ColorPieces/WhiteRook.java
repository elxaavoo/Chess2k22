package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.Rook;

public class WhiteRook extends Rook {
    public WhiteRook(Cell cell) {
        super(cell, ChessType.WHITE_ROOK);
        place();
    }
}
