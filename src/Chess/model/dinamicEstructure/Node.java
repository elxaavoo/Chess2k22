package Chess.model.dinamicEstructure;

import Chess.model.Piece;

import java.io.Serializable;

public class Node implements Serializable {
    private Piece info;
    private Node next;
    public Node (Piece piece){
        info = piece;
        next = null;
    }

    public Piece getInfo() {
        return info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    @Override
    public String toString(){
        return info.toString();
    }
}
