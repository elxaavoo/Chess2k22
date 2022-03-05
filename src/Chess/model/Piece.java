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
     * Constructor de Pieza
     * @param cell
     * @param chessType
     */
    public Piece(Cell cell, ChessType chessType){
        this.cell=cell;
        this.chessType=chessType;
    }

    /**
     * Metodo que devuelve la celda de la pieza
     * @return La celda de la Pieza
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Metodo que devuelve el color de la pieza
     * @return Color de la pieza
     */
    public PieceColor getColor(){
        return chessType.getColor();
    }

    /**
     * Metodo que sirve para poner la ficha en el tablero
     */
    public void place(){
        cell.setPiece(this);
    }

    /**
     * Metodo abstracto para cada una de las piezas que heredan
     * @return Lista de los movimientos de la ficha indicada
     */
    public abstract List<Coord> getNextMoveset();

    /**
     * Metodo toString de Pieza
     * @return La fonma en que se representa una pieza
     */
    public String toString(){
        Attribute background = cell.getColor().getAttribute();
        Attribute textColor = chessType.getColor().getAttribute();
        Attribute[] myFormat = new Attribute[]{background,textColor};

        return colorize(" " + chessType.getShape() + " ",myFormat);
    }

    /**
     * Metodo sobreescribido para saber si dos piezas son iguales
     * @param o
     * @return Booleando de si lo son
     */
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

    /**
     * Metodo para poner una celda a una pieza
     * @param cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * Metodo que sirve para mover una pieza
     * @param coord
     * @return Un booleando de si se puede mover o no
     */
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

    /**
     * Metodo que sirve que cuando el peon llegue al final se convierta en reina
     */
    public void chagePawn(){
        if (this.getChessType()==ChessType.BLACK_PAWN && this.getCell().getCoord().getNumber()==8){
            cell.setPiece(new BlackQueen(cell));
            return;
        }
        if (this.getChessType()==ChessType.WHITE_PAWN && this.getCell().getCoord().getNumber()==1){
        cell.setPiece(new WhiteQueen(cell));
        }
    }

    /**
     * Metodo que sirve para obtener el tipo de ficha de la ficha
     * @return
     */
    public ChessType getChessType() {
        return chessType;
    }

    /**
     * Metodo que sirve para ver si una ficha se puede mover o no
     * @param aux
     * @return Un booleando de si se puede mover
     */
    protected boolean canMoveTo(Coord aux){
        Board board = cell.getBoard();
        return (board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=getColor());
    }
}
