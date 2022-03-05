package Chess.model;


import Chess.model.ColorPieces.*;
import Chess.model.dinamicEstructure.MyStore;

import java.io.Serializable;
import java.util.*;

public class Board implements Serializable {
    private Map<Coord,Cell> cells;
    private IDeletedPieceManager store;
    private List<Piece> whitePiece;
    private List<Piece> blackPiece;
    /**
     * Constructor del Tablero*/
    public Board(){
        cells =new HashMap<>();
        store = new MyStore();
        whitePiece = new LinkedList<>();
        blackPiece = new LinkedList<>();

        Coord coord = null;
        Cell cell = null;

        for (int row = 1;row <= 8; row++) {
            for (int col = 0; col < 8; col++){
                coord = new Coord((char) ('A' + col), row);
                cell = new Cell(this, new Coord((char) ('A' + col), row));
                cells.put(coord,cell);
            }
        }
        placePieces();
    }

    /**
     * Metodo que sirve para saber si hay una celda en la coordenada indicada
     * @param coord
     * @return Booleano de si existe la celda o no
     */
    public boolean containsCellAt(Coord coord){
//        if (coord.getLetter()<'A' || coord.getLetter() > 'H')
//            return false;
//        if (coord.getNumber()<1 || coord.getNumber()>8)
//            return false;
        return cells.containsKey(coord);

    }

    /**
     * Metodo que sirve para saber si existe una pieza en la coordenada indicada
     * @param coord
     * @return Booleana de si existe esa pieza en la coordenada
     */
    public boolean containsPieceAt(Coord coord){
        Cell cell = getCellAt(coord);
        if (cell == null) return false;
        return (cell.getPiece()!=null);
    }

    /**
     * Metodo que sirve para obtener la celda de la coordenada indicada
     * @param coord
     * @return Celda donde he indicado con una coordenda
     */
    public Cell getCellAt (Coord coord){
        return cells.get(coord);
    }

    /**
     * Metodo que sirve para obtener el almacen de piezas eliminidas
     * @return devuelve el almacen de piezas eliminadas
     */
    public IDeletedPieceManager getStore() {
        return store;
    }

    /**
     * Metodo que sirve para poner las posicion de cada una de las fichas del tablero
     */
    private void placePieces() {

        /**Peones de cada color*/
        for (int i = 65; i <= 72; i++) {
            /**Peones Negros*/
            new BlackPawn(getCellAt(new Coord((char)i,2)));
            /**Peones Blancos*/
            new WhitePawn(getCellAt(new Coord((char)i,7)));
        }

        /** Fichas de color Blanco*/
        new WhiteHorse(getCellAt(new Coord('B',8)));
        new WhiteHorse(getCellAt(new Coord('G',8)));
        new WhiteRook(getCellAt(new Coord('A',8)));
        new WhiteRook(getCellAt(new Coord('H',8)));
        new WhiteBishop(getCellAt(new Coord('C',8)));
        new WhiteBishop(getCellAt(new Coord('F',8)));
        new WhiteKing(getCellAt(new Coord('E',8)));
        new WhiteQueen(getCellAt(new Coord('D',8)));
        /**Fichas de color Negro*/
        new BlackHorse(getCellAt(new Coord('B',1)));
        new BlackHorse(getCellAt(new Coord('G',1)));
        new BlackRook(getCellAt(new Coord('A',1)));
        new BlackRook(getCellAt(new Coord('H',1)));
        new BlackBishop(getCellAt(new Coord('C',1)));
        new BlackBishop(getCellAt(new Coord('F',1)));
        new BlackKing(getCellAt(new Coord('E',1)));
        new BlackQueen(getCellAt(new Coord('D',1)));
        /**Fichas de Prueba*/
        //new BlackKing(getCellAt(new Coord('B',6)));

    }

    /**
     * Metodo que sirve para saber si hay Jaque sobre tu rey
     * @param turno
     * @return Booleano de si hay Jaque o no lo hay.
     */
    public boolean isJaque(PieceColor turno){
        List<Piece> piezasRivales;
        Set<Coord> movimientosOponentes = new HashSet<>();
        if (turno==PieceColor.WHITE)
            piezasRivales = getBlackPiece();
        else
            piezasRivales = getWhitePiece();
        for (Piece p : piezasRivales){
            movimientosOponentes.addAll(p.getNextMoveset());
        }
        if (movimientosOponentes.contains(getCoordOfKingTurn(turno)))
            return true;
        return false;
    }

    /**
     * Metodo que sirve para obtener la coordenada del rey del color tu turno
     * @param turno
     * @return Coordenada del rey
     */
    public Coord getCoordOfKingTurn(PieceColor turno){
        List<Piece> misPiezas;
        Coord king = null;
        if (turno==PieceColor.WHITE){
            misPiezas = getWhitePiece();
            for (Piece p : misPiezas)
                if (p.getChessType()==ChessType.WHITE_KING)
                    king = p.cell.getCoord();
        } else {
            misPiezas = getBlackPiece();
            for (Piece p : misPiezas)
                if (p.getChessType()==ChessType.BLACK_KING)
                    king = p.cell.getCoord();
        }
        return king;
    }

    /**
     * Metodo que obtienes un Mapa de las coordenadas y las celdas que les corresponden
     * @return Un Mapa de Coordenadas y celdas
     */
    public Map<Coord, Cell> getCells() {
        return cells;
    }

    /**
     * Metodo para saber si una coordenada esta libre
     * @param aux
     * @return Booleando de si lo esta
     */
    public boolean isFree(Coord aux){
        return (this.containsCellAt(aux) && !this.containsPieceAt(aux));
    }

    /**
     * Metodo que te dice si la coordenada indicada tiene una ficha que es rival
     * @param aux
     * @return Booleando de si hay rival o no
     */
    public boolean haveRival(Coord aux){
        return (this.containsCellAt(aux) && this.containsPieceAt(aux));
    }

    /**
     * Metodo que sirve para cambiar el color de las celdas donde la ficha tiene posibles movimientos
     * @param coords
     */
    public void hightLight(List<Coord> coords){
        for (int i = 0; i<coords.size();i++){
            Coord aux = coords.get(i);
            if (isFree(aux)) {
                if (getCellAt(aux).getColor() == Cell.CellColor.BLACK_CELL)
                    getCellAt(aux).setColor(Cell.CellColor.HIGHTLIGHT_MOVE_BLACK);
                else
                    getCellAt(aux).setColor(Cell.CellColor.HIGHTLIGHT_MOVE_WHITE);
            } else if (haveRival(aux)){
                if (getCellAt(aux).getColor() == Cell.CellColor.BLACK_CELL)
                    getCellAt(aux).setColor(Cell.CellColor.HIGHTLIGHT_KILL_BLACK);
                else
                    getCellAt(aux).setColor(Cell.CellColor.HIGHTLIGHT_KILL_WHITE);
            }
        }
    }

    /**
     * Metodo que sirve para obtener las fichas blancas del tablero
     * @return Lista de las piezas que estan en el tablero
     */
    public List<Piece> getWhitePiece() {
        List<Piece> pieces = new ArrayList<>();
        for (Cell cell: cells.values())
            if (cell.getPiece()!=null && cell.getPiece().getColor() == PieceColor.WHITE)
                pieces.add(cell.getPiece());
        return pieces;
    }

    /**
     * Metodo que sirve para obtener las fichas negras del tablero
     * @return Lista de las piezas que estan en el tablero
     */
    public List<Piece> getBlackPiece() {
        List<Piece> pieces = new ArrayList<>();
        for (Cell cell: cells.values())
            if (cell.getPiece()!=null && cell.getPiece().getColor() == PieceColor.BLACK)
                pieces.add(cell.getPiece());
        return pieces;
    }

    /**
     * Metodo que resetea los colores del tablero a los originales
     */
    public void resetColors(){
        for (Cell cell : cells.values())
            cell.resetColor();

    }
}
