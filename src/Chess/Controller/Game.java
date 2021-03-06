package Chess.Controller;

import Chess.App;
import Chess.model.*;
import Chess.model.Tools.Input;
import Chess.model.Tools.Tool;
import Chess.view.Screen;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Game implements Serializable {

    private Board board;
    private PieceColor turno;

    /**
     * Constructor del juego
     * @param turno
     */
    public Game(PieceColor turno){
        this.board = new Board();
        if (turno==null)
            this.turno = PieceColor.WHITE;
        else
            this.turno = turno;
    }

    /**
     * Metodo que comienza el juego
     */
    public void start() {
        List<Coord> nextMovements = null;
        char option;
        do{
            Screen.show(this.board,this.turno);
            System.out.println("Turno del Jugador : " + this.turno);
            if (board.isJaque(turno))
                System.out.println("¡JAQUE!");
            option = Input.getOption();
            boolean seHaMovido = false;
            if (option == 'C' || option == 'G') {
                if (option == 'G'){
                    saveGameToFile("src/Chess/savedGames/save");
                    saved();
                    Screen.show(this.board,this.turno);
                }
                Coord coord;
                do{
                    do {
                        coord = Input.getCoordenada();
                        if (board.getCellAt(coord).getPiece() == null) {
                            System.out.println("Donde has indicado no hay ninguna ficha, corrige la coordenada.");
                        } else {
                            if (this.turno != this.board.getCellAt(coord).getPiece().getColor())
                                System.out.println("La ficha de la posicion indicada no es de tu color, corrige la coordenada.");
                        }
                    } while (board.getCellAt(coord).getPiece() == null || this.turno != this.board.getCellAt(coord).getPiece().getColor());
                    nextMovements = board.getCellAt(coord).getPiece().getNextMoveset();
                    if (nextMovements.isEmpty())
                        System.out.println("La coordenada indicada no tiene posibles movimientos.");
                }while(nextMovements.isEmpty());

                nextMovements = board.getCellAt(coord).getPiece().getNextMoveset();
                board.hightLight(nextMovements);
                Tool.limpiarPantalla();
                Screen.show(this.board, this.turno);

                Coord coordOfMove;
                do {
                    coordOfMove = Input.getCoordenada();
                    if (nextMovements.contains(coordOfMove)) {
                        board.getCellAt(coord).getPiece().moveTo(coordOfMove);
                        seHaMovido = true;
                    } else {
                        System.out.println("Esa cordenada no esta dentro de los posibles movimientos de la ficha.");
                    }
                } while (!seHaMovido);
                board.resetColors();
                Tool.limpiarPantalla();
                changeTurn();
            } else {
                Tool.limpiarPantalla();
                exit();
                break;
            }
        }while(!blackKingDeleted() && !whiteKingDeleted());
        if (blackKingDeleted())
            System.out.println("Jugador Negro a Ganado");
        else if(whiteKingDeleted())
            System.out.println("Jugador Blanco a Ganado");
        else
            System.out.println("");
    }

    /**
     * Sirve para saber si el rey negro a sido eliminado
     * @return boolean de si esta el rey o no
     */
    private boolean whiteKingDeleted() {
        if (board.getStore().count(ChessType.BLACK_KING)==1)
            return true;
        else
            return false;
    }
    /**
     * Sirve para saber si el rey blanco a sido eliminado
     * @return booleano de si esta el rey o no
     */
    private boolean blackKingDeleted() {
        if (board.getStore().count(ChessType.WHITE_KING)==1)
            return true;
        else
            return false;
    }

    /** Mensaje de salida de la aplicación*/
    public void exit(){
        System.out.println(" ██████╗ ██████╗  █████╗  ██████╗██╗ █████╗ ███████╗    ██████╗  ██████╗ ██████╗          ██╗██╗   ██╗ ██████╗  █████╗ ██████╗ ██╗");
        System.out.println("██╔════╝ ██╔══██╗██╔══██╗██╔════╝██║██╔══██╗██╔════╝    ██╔══██╗██╔═══██╗██╔══██╗         ██║██║   ██║██╔════╝ ██╔══██╗██╔══██╗██║");
        System.out.println("██║  ███╗██████╔╝███████║██║     ██║███████║███████╗    ██████╔╝██║   ██║██████╔╝         ██║██║   ██║██║  ███╗███████║██████╔╝██║");
        System.out.println("██║   ██║██╔══██╗██╔══██║██║     ██║██╔══██║╚════██║    ██╔═══╝ ██║   ██║██╔══██╗    ██   ██║██║   ██║██║   ██║██╔══██║██╔══██╗╚═╝");
        System.out.println("╚██████╔╝██║  ██║██║  ██║╚██████╗██║██║  ██║███████║    ██║     ╚██████╔╝██║  ██║    ╚█████╔╝╚██████╔╝╚██████╔╝██║  ██║██║  ██║██╗");
        System.out.println(" ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝╚═╝  ╚═╝╚══════╝    ╚═╝      ╚═════╝ ╚═╝  ╚═╝     ╚════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝");
    }
    /** Mensaje de que se ha guardado el juego*/
    public void saved(){
        System.out.println("Partida guardada correctamente!");
    }

    /**
     * Metodo para guardar la partida dentro de un fichero
     * @param file
     */
    public void saveGameToFile(String file){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

            out.writeObject(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar una partida guardada en la ruta de las Partidas Guardadas
     * @param file
     * @return Partida guardada del fichero donde se guarda la Partida
     */
    public Game loadGame(String file){

        Game g = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            g = (Game) ois.readObject();
            turno = g.turno;
            board = g.board;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return g;
    }

    /**Sirve para cambiar el turno del jugador que le toca jugar*/
    public void changeTurn() {
        if (this.turno == PieceColor.WHITE)
            this.turno = PieceColor.BLACK;
        else
            this.turno = PieceColor.WHITE;


    }
}
