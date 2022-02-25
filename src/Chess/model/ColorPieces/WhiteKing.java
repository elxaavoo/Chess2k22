package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.King;

public class WhiteKing extends King {
    public WhiteKing(Cell cell) {
        super(cell, ChessType.WHITE_KING);
        place();
    }
}
