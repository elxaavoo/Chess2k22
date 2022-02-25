package Chess.view;

import Chess.model.*;
import Chess.model.dinamicEstructure.MyList;

import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Screen {
    public static void show(Board board, PieceColor color) {
        if(color==PieceColor.WHITE)
            showWhiteView(board);
        else
            showBlackView(board);

    }
    private static void showWhiteView(Board board) {

        StringBuilder salida = new StringBuilder();

        salida.append(getLettersWhiteView());

        for (int fila = 1; fila <= 8; fila++) {
            salida.append(getMiddlePartWhiteView(board, fila));

        }
        salida.append(getLettersWhiteView());

        salida.append("                  DELETED PIECES");
        salida.append("\n");
        salida.append(deletedPieces(board.getStore()));
        salida.append("\n\n");
        salida.append("                 REMAINING PIECES");
        salida.append("\n");
        salida.append(remainingPieces(board));
        salida.append("\n\n");


        System.out.print(salida);
    }
    private static void showBlackView(Board board) {

        StringBuilder salida = new StringBuilder();

        salida.append(getLettersBlackView());

        for (int fila = 8; fila >= 1; fila--) {
            salida.append(getMiddlePartBlackView(board, fila));

        }
        salida.append(getLettersBlackView());

        salida.append("                  DELETED PIECES");
        salida.append("\n");
        salida.append(deletedPieces(board.getStore()));
        salida.append("\n\n");
        salida.append("                 REMAINING PIECES");
        salida.append("\n");
        salida.append(remainingPieces(board));
        salida.append("\n\n");

        System.out.print(salida);
    }

    private static String getLettersWhiteView() {
        return "               A  B  C  D  E  F  G  H\n";
    }
    private static String getLettersBlackView() {
        return "               H  G  F  E  D  C  B  A\n";
    }
    private static String getMiddlePartWhiteView(Board board, int fila) {

        StringBuilder salida = new StringBuilder("            " + fila + " ");

        for (int col = 0; col < 8; col++) {
            salida.append(board.getCellAt(new Coord((char) ('A' + col), fila)));
        }

        return salida.append(" ").append(fila).append("\n").toString();
    }
    private static String getMiddlePartBlackView(Board board, int fila) {

        StringBuilder salida = new StringBuilder("            " + fila + " ");

        for (int col = 7; col >= 0; col--) {
            salida.append(board.getCellAt(new Coord((char) ('A' + col), fila)));
        }

        return salida.append(" ").append(fila).append("\n").toString();
    }

    /**
     * Muestra el tablero de piezas eliminadas*/
    public static String deletedPieces(IDeletedPieceManager storage){
        String out = "";
        ChessType[] chessTypes = ChessType.values();
        out += "       ";
        for (ChessType chessType : chessTypes)
            out += colorize(" " + chessType.getShape() + " ",Cell.CellColor.BLACK_CELL.getAttribute(),chessType.getColor().getAttribute());
        out+="\n";
        out += "       ";
        for (ChessType chessType : chessTypes)
            out += colorize(" " + storage.count(chessType) + " ",Cell.CellColor.WHITE_CELL.getAttribute(),chessType.getColor().getAttribute());
        return out;
    }

    public static String remainingPieces(Board board){
        String out = "";
        ChessType[] chessTypes = ChessType.values();
        out += "       ";
        for (ChessType chessType : chessTypes)
            out += colorize(" " + chessType.getShape() + " ",Cell.CellColor.BLACK_CELL.getAttribute(),chessType.getColor().getAttribute());
        out+="\n";
        out += "       ";
        for (ChessType chessType : chessTypes)
            out += colorize(" " + count(board,chessType) + " ",Cell.CellColor.WHITE_CELL.getAttribute(),chessType.getColor().getAttribute());
        return out;
    }


public static long count(Board board, ChessType chessType){
    return board.getCells().values().stream()
            .filter (c -> c.getPiece()!=null && c.getPiece().getChessType()==chessType)
            .count();
    }
}
