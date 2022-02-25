package Chess.model;

import Chess.model.ColorPieces.BlackQueen;
import Chess.model.ColorPieces.WhiteQueen;
import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

public abstract class Piece implements Serializable {

    private ChessType chessType;
    protected Cell cell;
    /**
     * Constructor de Pieza*/
    public Piece(Cell cell, ChessType chessType){
        this.cell=cell;
        this.chessType=chessType;
    }

    public Cell getCell() {
        return cell;
    }

    public PieceColor getColor(){
        return chessType.getColor();
    }
    /**
     * Metodo para poner la ficha en el lugar*/
    public void place(){
        cell.setPiece(this);
    }
    /**
     * Metodo abstracto que lo tienen todos los hijos de esta clase padre*/
    public abstract List<Coord> getNextMoveset();

    public String toString(){
        Attribute background = cell.getColor().getAttribute();
        Attribute textColor = chessType.getColor().getAttribute();
        Attribute[] myFormat = new Attribute[]{background,textColor};

        return colorize(" " + chessType.getShape() + " ",myFormat);
    }

    /**
     * Metodo sobreescribido de equals para saber si dos piezas son las misma*/
    @Override
    public boolean equals(Object o){
        if (o instanceof Piece){
            Piece p = (Piece) o;
            if (p.cell == this.cell && p.chessType == this.chessType)
                return true;
            else
                return false;
        }else
            return false;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
    /**
     * Metodo para poder mover una ficha a una nueva coordenada*/
    public boolean moveTo(Coord coord) {
        if (cell == null || !cell.getBoard().containsCellAt(coord))
            return false;
        if (getNextMoveset().contains(coord)) {
            Board board = cell.getBoard();
            if (board.containsPieceAt(coord)) {
                //kill
                Piece piece = board.getCellAt(coord).getPiece();
                piece.cell = null;
                board.getStore().add(piece);
            }
            cell.setPiece(null);
            cell = board.getCellAt(coord);
            place();
            chagePawn();
            return true;
        } else {
            return false;
        }
    }

    public void chagePawn(){
        if (this.getChessType()==ChessType.BLACK_PAWN && this.getCell().getCoord().getNumber()==8){
            cell.setPiece(new BlackQueen(cell));
            return;
        }
        if (this.getChessType()==ChessType.WHITE_PAWN && this.getCell().getCoord().getNumber()==1){
        cell.setPiece(new WhiteQueen(cell));
        }
    }

    public ChessType getChessType() {
        return chessType;
    }
    /**
     * Metodo para poder comprobar si te puedes mover a las coordenadas*/
    protected boolean canMoveTo(Coord aux){
        Board board = cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=getColor());
    }
}
