package Chess.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Cell implements Serializable {
    private Coord coord;
    private CellColor color;
    private CellColor originalColor;
    private Piece piece;
    private Board board;

    /**
     * Constructor de Celda
     * @param board
     * @param coord
     */
    public Cell(Board board,Coord coord){
        this.board=board;
        this.coord=coord;
        if (((coord.getLetter()-'A')+coord.getNumber())%2==0){
            originalColor = CellColor.BLACK_CELL;
        }else{
            originalColor = CellColor.WHITE_CELL;
        }
        color= originalColor;
    }

    /**
     * Metodo para saber si una celda esta vacia
     * @return Booleano de si lo esta
     */
    public boolean isEmpty(){
       return piece == null;
    }

    /**
     * Getter del tablero
     * @return Tablero
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter de la Pieza
     * @return Pieza
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Getter de la Coordenada
     * @return Coordenada
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Getter del color de la celda
     * @return Color de la celda
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * Setter de Color
     * @param color
     */
    public void setColor(CellColor color) {
        this.color = color;
    }

    /**
     * Setter de la pieza
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * ToString de la Celda
     * @return Forma en la que se ve una celda
     */
    public String toString(){
        Attribute[] myFormat = new Attribute[]{color.getAttribute()};
        if (piece!=null)
            return this.piece.toString();
        else
            return colorize("   ",myFormat);
    }

    /**
     * Metodo que resetea el color de la celda*/
    public void resetColor() {
        if (((coord.getLetter()-'A')+coord.getNumber())%2==0){
            originalColor = CellColor.BLACK_CELL;
        }else{
            originalColor = CellColor.WHITE_CELL;
        }
        color = originalColor;
    }
    /**
     * Enumerador de CellColor, donde le damos los colores a las celdas, además de su constructor y un metodo que da el atributo*/
    public enum CellColor{
        WHITE_CELL(Attribute.BACK_COLOR(180,180,180)),
        BLACK_CELL(Attribute.BACK_COLOR(100,100,100)),
        HIGHTLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180,0,0)),
        HIGHTLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130,0,0)),
        HIGHTLIGHT_MOVE_WHITE(Attribute.BACK_COLOR(253,255,147)),
        HIGHTLIGHT_MOVE_BLACK(Attribute.BACK_COLOR(206,204,76));

        private Attribute color;
        CellColor(Attribute color){
            this.color=color;
        }
        public Attribute getAttribute(){
            return color;
        }
    }
}
