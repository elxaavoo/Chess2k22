package Chess.model;

import Chess.model.*;

import java.util.ArrayList;
import java.util.List;

public class WhitePawn extends Piece {
    /**
     * Constructor de Peon Blanco
     * @param cell
     */
    public WhitePawn(Cell cell) {
        super(cell, ChessType.WHITE_PAWN);
        place();
    }

    /**
     * Metodo que devuelve los posibles moviemientos en coordenadas
     * @return Lista de Coordenadas de posibles movimientos
     */
    @Override
    public List<Coord> getNextMoveset() {
        return getNextMovesetAsWhitePawn(this);
    }

    /**
     * Metodo que sirve para obtener los movimientos como si fueses un peon blanco
     * @param p
     * @return Lista de Coordenadas de movimientos como si fueses un peon blanco
     */
    public static List<Coord> getNextMovesetAsWhitePawn(Piece p){
        List<Coord> nextMovements = new ArrayList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        /** Movimientos de arriba*/
        aux = position.UpCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        if (p.getCell().getCoord().getNumber()==7){
            aux = position.UpCell().UpCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
        }

        /** Movimientos si hay ficha para matar*/
        aux = position.UpCell().LeftCell();
        if (board.containsPieceAt(aux)
                && (board.getCellAt(aux).getPiece() != null && board.getCellAt(aux).getPiece().getColor() != p.getColor()))
            nextMovements.add(aux);
        aux = position.UpCell().RightCell();
        if (board.containsPieceAt(aux)
                && (board.getCellAt(aux).getPiece() != null && board.getCellAt(aux).getPiece().getColor() != p.getColor()))
            nextMovements.add(aux);
        return nextMovements; 
    }

    /**
     * Metodo para saber si puede moverse la ficha
     * @param aux
     * @param p
     * @return Booleanado de si se puede mover o no
     */
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }
}
