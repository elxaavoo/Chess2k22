package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.King;

public class BlackKing extends King {
    public BlackKing(Cell cell) {
        super(cell, ChessType.BLACK_KING);
        place();
    }
}
