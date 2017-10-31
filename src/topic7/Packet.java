package topic7;

import java.io.Serializable;

public class Packet implements Serializable {
    public enum Type {
        SEND,
        GET,
        COUNT,
        CLEAR
    }

    private Type type;
    private char symbol;

    public Packet(Type type) {
        this.type = type;
    }

    public Packet(Type type, char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public Type getType() {
        return type;
    }

    public char getSymbol() {
        return symbol;
    }
}
