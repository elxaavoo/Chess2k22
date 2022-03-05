package Chess.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Horse extends Piece {
    /**
     * Constructor de Caballo
     * @param cell
     * @param chessType
     */
    public Horse(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Metodo que da los movimientos del caballo
     * @return Lista de movimientos del caballo
     */
    public List<Coord> getNextMoveset() {
        return getNextMovesetAsHorse(this);
    }

    /**
     * Metodo que sirve para obtener las coordenadas como si fueses un caballo
     * @param p
     * @return Lista de coordenadas
     */
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

    /**
     * Metodo para saber si la ficha se puede mover correctamente
     * @param aux
     * @param p
     * @return Booleano de si se puede mover
     */
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }

}
