package Chess.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Bishop extends Piece {
    public Bishop(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Da los movimientos de el Bishop a la ficha que le indiques. */
    public static List<Coord> getMovementsAsBishop(Piece p){
        List<Coord> nextMovements = new LinkedList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        aux = position;
        boolean continuar = true;
        /**
         * Movimientos de arriba*/
        do {
            aux = aux.UpCell().LeftCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        aux = position;
        continuar = true;
        do {
            aux = aux.UpCell().RightCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        aux = position;
        continuar = true;
        /**
         * Movimientos de abajo*/
        do {
            aux = aux.DownCell().LeftCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        aux = position;
        continuar = true;
        do {
            aux = aux.DownCell().RightCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);

        return nextMovements;
    }
    /**
     * Recibe los movimientos del Bishop como si fuese un Bishop*/
    @Override
    public List<Coord> getNextMoveset() {
        return getMovementsAsBishop(this);
    }

    /**
     * Mira para ver si se puede mover la ficha*/
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }
}
