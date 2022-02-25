package Chess.model;

import Chess.model.*;

import java.util.ArrayList;
import java.util.List;

public class WhitePawn extends Piece {
    public WhitePawn(Cell cell) {
        super(cell, ChessType.WHITE_PAWN);
        place();
    }
    /**
     * Metodo para obtener los movimientos del Peon Blanco*/
    @Override
    public List<Coord> getNextMoveset() {
        return getNextMovesetAsWhitePawn(this);
    }
    
    public static List<Coord> getNextMovesetAsWhitePawn(Piece p){
        List<Coord> nextMovements = new ArrayList<>();
        Coord aux;
        Board board = p.cell.getBoard();
        Coord position = p.cell.getCoord();
        //up
        aux = position.UpCell();
        if (canMoveTo(aux,p))
            nextMovements.add(aux);
        if (p.getCell().getCoord().getNumber()==7){
            aux = position.UpCell().UpCell();
            if (canMoveTo(aux,p))
                nextMovements.add(aux);
        }

        //kill
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
    
    protected static boolean canMoveTo(Coord aux, Piece p){
        Board board = p.cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=p.getColor());
    }
}
