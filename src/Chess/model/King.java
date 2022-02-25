package Chess.model;

import java.util.LinkedList;
import java.util.List;

public abstract class King extends Piece{
    public King(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    @Override
    public List<Coord> getNextMoveset() {
        return getNextMovementsAsKing(this);
    }
    
    public static List<Coord> getNextMovementsAsKing(Piece p){
        List<Coord> nextMovements = new LinkedList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        /**
         * Movimientos de arriba*/
        aux = position.UpCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        aux = position.UpCell().LeftCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        aux = position.UpCell().RightCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        /**
         * Movimientos de abajo*/
        aux = position.DownCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        aux = position.DownCell().LeftCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        aux = position.DownCell().RightCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        /**
         * Movimientos de izquierda*/
        aux = position.LeftCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        aux = position.LeftCell().DownCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        /**
         * Movimientos de derecha*/
        aux = position.RightCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        aux = position.RightCell().DownCell();
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
