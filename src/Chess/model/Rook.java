package Chess.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Rook extends Piece {
    public Rook(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }
    /**
     * metodo para obetner los movimientos como si fuese una Torre*/
    public static List<Coord> getMovesetAsRook(Piece p){
        List<Coord> nextMovements = new LinkedList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        aux = position;
        boolean continuar = true;
        /**
         * Movimientos de arriba*/
        do {
            aux = aux.UpCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        /**
         * Movimientos de la izquierda*/
        aux = position;
        continuar = true;
        do {
            aux = aux.LeftCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        /**
         * Movimientos de la derecha*/
        aux = position;
        continuar = true;
        do {
            aux = aux.RightCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        /**
         * Movimientos de abajo*/
        aux = position;
        continuar = true;
        do {
            aux = aux.DownCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor() != p.getColor())
                continuar = false;
        } while (canMoveTo(aux,p) && continuar);
        return nextMovements;
    }
    /**
     * Da los movimientos para esta ficha*/
    @Override
    public List<Coord> getNextMoveset() {
        return getMovesetAsRook(this);
    }
    /**
     * Metodo que mira si se puede mover a las coordenadas indicadas*/
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }
}
