package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.Horse;

public class WhiteHorse extends Horse {

    public WhiteHorse(Cell cell) {
        super(cell, ChessType.WHITE_HORSE);
        place();
    }
}
