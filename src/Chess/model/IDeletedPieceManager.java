package Chess.model;

import Chess.model.ChessType;
import Chess.model.Piece;

public interface IDeletedPieceManager {
    void add(Piece piece);
    Piece getFirst();
    void remove(Piece piece);
    int count(ChessType chessType);
}
