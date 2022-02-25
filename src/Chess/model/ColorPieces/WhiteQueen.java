package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.Queen;

public class WhiteQueen extends Queen {
    public WhiteQueen(Cell cell) {
        super(cell, ChessType.WHITE_QUEEN);
        place();
    }
}
