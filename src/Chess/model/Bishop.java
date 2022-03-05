package Chess.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Bishop extends Piece {
    public Bishop(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Metodo que da los siguientes movimientos como si fuese un alfil
     * @param p
     * @return Lista de Coordenadas
     */
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
     * Metodo para obtener siguientes movimientos del Alfil
     * @return
     */
    @Override
    public List<Coord> getNextMoveset() {
        return getMovementsAsBishop(this);
    }

    /**
     * Metodo que te dice si se puede mover
     * @param aux
     * @param p
     * @return Booleano de si se puede mover o no
     */
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }
}
