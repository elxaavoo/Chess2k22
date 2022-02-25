package Chess;

import Chess.Controller.Game;
import Chess.model.Coord;
import Chess.model.PieceColor;
import Chess.model.Tools.Input;

import java.io.*;
import java.util.Scanner;

public class App implements Serializable{
    public static void main(String[] args) {
        title();
        System.out.println("Elige una opción para poder empezar.");
        menu();
        int opcion = Input.getOptionGame();
        if (opcion==1){
            PieceColor turnoColor = Input.getPieceColorBoard();
            Game gameCreated = new Game(turnoColor);
            gameCreated.start();
        } else {
            Game gameloaded = new Game(PieceColor.WHITE);
            gameloaded.loadGame("src/Chess/savedGames/save");
            gameloaded.start();
        }
    }

    public static void title(){
        System.out.println("=============================================");
        System.out.println(" ██████╗██╗  ██╗███████╗███████╗███████╗██╗");
        System.out.println("██╔════╝██║  ██║██╔════╝██╔════╝██╔════╝██║");
        System.out.println("██║     ███████║█████╗  ███████╗███████╗██║");
        System.out.println("██║     ██╔══██║██╔══╝  ╚════██║╚════██║╚═╝");
        System.out.println("╚██████╗██║  ██║███████╗███████║███████║██╗");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝");
        System.out.println("=============================================");
    }

    public static void menu(){
        System.out.println("=============================================");
        System.out.println(" [1] - Empezar partida");
        System.out.println(" [2] - Cargar partida");
        System.out.println("=============================================");
    }
}

