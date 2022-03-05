package Chess.model;

import Chess.model.ChessType;
import Chess.model.Piece;

public interface IDeletedPieceManager {
    /**
     * Metodo para añar una ficha
     * @param piece
     */
    void add(Piece piece);

    /**
     * Metodo para obtener la primera ficha
     * @return
     */
    Piece getFirst();

    /**
     * Metodo para eliminar un ficha
     * @param piece
     */
    void remove(Piece piece);

    /**
     * Metodo para contar la fichas de un tipo de ficha
     * @param chessType
     * @return
     */
    int count(ChessType chessType);
}
