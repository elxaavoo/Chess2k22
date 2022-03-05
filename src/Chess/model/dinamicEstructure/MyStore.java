package Chess.model.dinamicEstructure;

import Chess.model.ChessType;
import Chess.model.IDeletedPieceManager;
import Chess.model.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyStore implements IDeletedPieceManager, Serializable {

    private List<Piece> pieces;

    public MyStore (){
        this.pieces = new ArrayList<>();
    }

    /**
     * Metodo que almacena una pieza
     * @param piece
     */
    @Override
    public void add(Piece piece) {
        pieces.add(piece);
    }

    /**
     * Metodo que devuelve la primera ficha
     * @return devuelve la primera pieza
     */
    @Override
    public Piece getFirst() {
        return pieces.get(0);
    }

    /**
     * Metodo para eliminar pieza
     * @param piece
     */
    @Override
    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Metodo para contar
     * @param chessType
     * @return Devuelve un entero de las pieza contadas.
     */
    @Override
    public int count(ChessType chessType) {
        return (int) pieces.stream().filter(p->p.getChessType()==chessType).count();
    }
}
