package Chess.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Horse extends Piece {
    public Horse(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }
    /**
     * Metodo que te da los siguiente movimientos del caballo*/
    public List<Coord> getNextMoveset() {
        return getNextMovesetAsHorse(this);
    }

    public static List<Coord> getNextMovesetAsHorse(Piece p){
        List<Coord> nextMovements = new ArrayList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        /**
         * Movimientos de arriba*/
        aux = position.UpCell().UpCell().LeftCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);

        aux = position.UpCell().UpCell().RightCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        /**
         * Moviimentos de abajo*/
        aux = position.DownCell().DownCell().LeftCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);

        aux = position.DownCell().DownCell().RightCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        /**
         * Movimientos de la izquierda*/
        aux = position.LeftCell().LeftCell().UpCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);

        aux = position.LeftCell().LeftCell().DownCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        /**
         * Movimientos de la derecha*/
        aux = position.RightCell().RightCell().UpCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);

        aux = position.RightCell().RightCell().DownCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);

        return nextMovements;
    }

    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }

}
