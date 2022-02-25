package Chess.model.ColorPieces;

import Chess.model.Cell;
import Chess.model.ChessType;
import Chess.model.Horse;

public class BlackHorse extends Horse {

    public BlackHorse(Cell cell) {
        super(cell, ChessType.BLACK_HORSE);
        place();
    }
}
