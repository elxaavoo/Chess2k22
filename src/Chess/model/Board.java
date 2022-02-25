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
     * Comprueba si hay una celda en la coordenada indicada*/
    public boolean containsCellAt(Coord coord){
//        if (coord.getLetter()<'A' || coord.getLetter() > 'H')
//            return false;
//        if (coord.getNumber()<1 || coord.getNumber()>8)
//            return false;
        return cells.containsKey(coord);

    }
    /**
     * Mira a ver si contiene una pieza en la coordenada indicada*/
    public boolean containsPieceAt(Coord coord){
        Cell cell = getCellAt(coord);
        if (cell == null) return false;
        return (cell.getPiece()!=null);
    }

    public Cell getCellAt (Coord coord){
        return cells.get(coord);
    }

    public IDeletedPieceManager getStore() {
        return store;
    }
    /**
     * Pone las fichas en el tablero*/
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


    public Map<Coord, Cell> getCells() {
        return cells;
    }

    /**
     * Mira si una coordenada esta libre*/
    public boolean isFree(Coord aux){
        return (this.containsCellAt(aux) && !this.containsPieceAt(aux));
    }
    /**
     * Mira si en la coordenada hay un ficha contraria*/
    public boolean haveRival(Coord aux){
        return (this.containsCellAt(aux) && this.containsPieceAt(aux));
    }
    /**
     * Marca las celdas donde se puede mover la ficha*/
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

    public List<Piece> getWhitePiece() {
        List<Piece> pieces = new ArrayList<>();
        for (Cell cell: cells.values())
            if (cell.getPiece()!=null && cell.getPiece().getColor() == PieceColor.WHITE)
                pieces.add(cell.getPiece());
        return pieces;
    }

    public List<Piece> getBlackPiece() {
        List<Piece> pieces = new ArrayList<>();
        for (Cell cell: cells.values())
            if (cell.getPiece()!=null && cell.getPiece().getColor() == PieceColor.BLACK)
                pieces.add(cell.getPiece());
        return pieces;
    }
    /**
     * Resetea los colores del tablero a normal, para que no esten marcados por el hightlight*/
    public void resetColors(){
        for (Cell cell : cells.values())
            cell.resetColor();

    }
}
