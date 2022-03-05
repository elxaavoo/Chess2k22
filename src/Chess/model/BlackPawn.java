package Chess.model;

import Chess.model.*;

import java.util.ArrayList;
import java.util.List;

public class BlackPawn extends Piece {
    /**
     * Constructor de Peon Negro
     * @param cell
     */
    public BlackPawn(Cell cell) {
        super(cell, ChessType.BLACK_PAWN);
        place();
    }

    /**
     * Metodo que devuelve los movimientos como Peon Negro
     * @return Lista de coordenadas de movimientos de Peones negros
     */
    @Override
    public List<Coord> getNextMoveset() {
        return getNextMovesetAsBlackPawn(this);
    }

    /**
     * Metodo que da los movimientos como si fueses un peon negro
     * @param p
     * @return Lista de Coordenadas de movimientos como si fueses un peon negro
     */
    public static List<Coord> getNextMovesetAsBlackPawn(Piece p){
        List<Coord> nextMovements = new ArrayList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        //up
        aux = position.DownCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        if (p.getCell().getCoord().getNumber()==2){
            aux = position.DownCell().DownCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
        }
        //kill
        aux = position.DownCell().LeftCell();
        if (board.containsPieceAt(aux)
                && (board.getCellAt(aux).getPiece() != null && board.getCellAt(aux).getPiece().getColor() != p.getColor()))
            nextMovements.add(aux);
        aux = position.DownCell().RightCell();
        if (board.containsPieceAt(aux)
                && (board.getCellAt(aux).getPiece() != null && board.getCellAt(aux).getPiece().getColor() != p.getColor()))
            nextMovements.add(aux);
        return nextMovements;
    }

    /**
     * Metodo para saber si la ficha se puede mover o no se puede mover
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
