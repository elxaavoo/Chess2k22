package Chess.model;

import java.util.LinkedList;
import java.util.List;

public abstract class King extends Piece{
    /**
     * Constructor del rey
     * @param cell
     * @param chessType
     */
    public King(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Metodo para obtener movimientos del rey
     * @return Lista de Coordenadas
     */
    @Override
    public List<Coord> getNextMoveset() {
        return getNextMovementsAsKing(this);
    }

    /**
     * Metodo para obtener los movimientos como su fueses un rey
     * @param p
     * @return Lista de coordenadas
     */
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

    /**
     * Metodo que dice si se puede mover correctamente
     * @param aux
     * @param p
     * @return Booleano que indica si se puede mover o no se puede mover
     */
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }
}
