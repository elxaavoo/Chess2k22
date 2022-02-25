package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.Queen;

public class BlackQueen extends Queen {
    public BlackQueen(Cell cell) {
        super(cell, ChessType.BLACK_QUEEN);
        place();
    }
}
