package Chess.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static Chess.model.Bishop.*;
import static Chess.model.Rook.*;

public abstract class Queen extends Piece{
    public Queen(Cell cell, ChessType chessType) {
        super(cell, chessType);
    }

    /**
     * Metodo que da los movimientos de la reina
     * @return Lista de Coordendas
     */
    @Override
    public List<Coord> getNextMoveset() {
        return getNextMovesetAsQueen(this);
    }

    /**
     * Lista de los siguientes movimientos como si fueses una reina
     * @param p
     * @return Lista de coordenadas
     */
    public static List<Coord> getNextMovesetAsQueen(Piece p){
        List<Coord> movesetAsBishop = getMovementsAsBishop(p);
        List<Coord> movesetAsRook = getMovesetAsRook(p);
        List<Coord> nextMoveset = new LinkedList<>();
        nextMoveset.addAll(movesetAsRook);
        nextMoveset.addAll(movesetAsBishop);
        return nextMoveset;
    }
}
