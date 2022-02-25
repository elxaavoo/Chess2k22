package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.Rook;

public class BlackRook extends Rook {
    public BlackRook(Cell cell) {
        super(cell, ChessType.BLACK_ROOK);
        place();
    }
}
