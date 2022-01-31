package Pieces;

import javafx.scene.layout.GridPane;

import java.util.Locale;

public class Piece{
    private int posX;
    private int posY;
    private ChessColor color;
    private ChessType type;
    private GridPane gp;

    public Piece(int posX, int posY, ChessColor color, ChessType type, GridPane gp) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.type = type;
        this.gp = gp;
    }

    public void move(int x, int y){
        switch(type.toString().toLowerCase()){
            case "rook":
                moveRook(x,y);
                break;
            case "queen":
                moveQueen(x,y);
                break;
            case "bishoph":
                moveBishoph(x,y);
                break;
            case "knight":
                moveKnight(x,y);
                break;
            case "king":
                moveKing(x,y);
                break;
            case "pawn":
                movePawn(x,y);
                break;
            default:
                System.out.println("Bruh");
                break;
        }
        return board;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public ChessColor getColor() {
        return color;
    }

    public void setColor(ChessColor color) {
        this.color = color;
    }

    public ChessType getType() {
        return type;
    }

    public void setType(ChessType type) {
        this.type = type;
    }

    private static void moveRook(int x, int y){

    }

    private static void moveQueen(int x, int y){

    }

    private static void moveKing(int x, int y){

    }

    private static void moveBishoph(int x, int y){

    }

    private static void movePawn(int x, int y){

    }

    private static void moveKnight(int x, int y){

    }

}
