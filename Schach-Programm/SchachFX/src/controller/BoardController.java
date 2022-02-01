package controller;

import Pieces.Board;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.InvocationTargetException;

public class BoardController {
    public ColumnConstraints ABoardID;
    public ColumnConstraints BBoardID;
    public ColumnConstraints CBoardID;
    public ImageView blackKnightLeft;
    public ImageView blackRookLeft;
    public ImageView blackBishopLeft;
    public ImageView blackQueen;
    public ImageView blackKing;
    public ImageView blackBishopRight;
    public ImageView blackKnightRight;
    public ImageView blackRookRight;
    public ImageView blackPawnZero;
    public ImageView blackPawnOne;
    public ImageView blackPawnTwo;
    public ImageView blackPawnThree;
    public ImageView blackPawnFour;
    public ImageView blackPawnFive;
    public ImageView blackPawnSix;
    public ImageView blackPawnSeven;
    public ImageView whiteRookLeft;
    public ImageView whiteKnightLeft;
    public ImageView whiteBishopLeft;
    public ImageView whiteQueen;
    public ImageView whiteKing;
    public ImageView whiteBishopRight;
    public ImageView whiteKnightRight;
    public ImageView whiteRookRight;
    public ImageView whitePawnZero;
    public ImageView whitePawnOne;
    public ImageView whitePawnTwo;
    public ImageView whitePawnThree;
    public ImageView whitePawnFour;
    public ImageView whitePawnFive;
    public ImageView whitePawnSix;
    public ImageView whitePawnSeven;
    public boolean clickedWhitePawn;
    public boolean clickedWhiteKing;
    public boolean clickedWhiteQueen;
    public boolean clickedWhiteBishoph;
    public boolean clickedWhiteKnight;
    public boolean clickedWhiteRook;
    public Node pawnPane;
    public Node kingPane;
    public Node bishophPane;
    public Node queenPane;
    public Node knightPane;
    public Node rookPane;
    public static ListView whiteMovesID;
    public static ListView blackMovesID;
    public GridPane boardId = new GridPane();
    public Pieces.Board GameBoard = new Board(boardId);
    /*private void movePawn(Node selectedPane) {
        System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
    }*/

    public void onMouseClick(MouseEvent mouseEvent) {
        Node selectedPane = (Node) mouseEvent.getSource();
        if (selectedPane.toString().contains("ImageView")) {
            if (selectedPane.getId().contains("whitePawn")) {
                clickedWhitePawn = true;
                clickedWhiteKing = false;
                clickedWhiteKnight = false;
                clickedWhiteBishoph = false;
                clickedWhiteQueen = false;
                clickedWhiteRook = false;
                pawnPane = selectedPane;

            } else if (selectedPane.getId().contains("black")) {
                if (clickedWhitePawn) {
                    GameBoard.killPawn(pawnPane,selectedPane);
                } else if (clickedWhiteKing) {
                    GameBoard.attackKing(kingPane,selectedPane);
                } else if(clickedWhiteQueen){
                    GameBoard.killQueen(queenPane,selectedPane);
                }else if(clickedWhiteKnight){
                    GameBoard.killKnight(knightPane,selectedPane);
                }else if(clickedWhiteBishoph){
                    GameBoard.killBishoph(bishophPane,selectedPane);
                }else if(clickedWhiteRook){
                    GameBoard.killRook(rookPane,selectedPane);
                }else{
                    System.out.println("No Piece selected!");
                }
            } else if (selectedPane.getId().contains("whiteKing")) {
                clickedWhiteKing = true;
                clickedWhitePawn = false;
                clickedWhiteQueen = false;
                clickedWhiteBishoph = false;
                clickedWhiteKnight = false;
                clickedWhiteRook = false;
                kingPane = selectedPane;
            } else if (selectedPane.getId().contains("whiteQueen")){
                clickedWhiteQueen = true;
                clickedWhiteKing = false;
                clickedWhitePawn = false;
                clickedWhiteBishoph = false;
                clickedWhiteKnight = false;
                clickedWhiteRook = false;
                queenPane = selectedPane;
            } else if (selectedPane.getId().contains("whiteBishoph")){
                clickedWhiteBishoph = true;
                clickedWhitePawn = false;
                clickedWhiteKing = false;
                clickedWhiteQueen = false;
                clickedWhiteKnight = false;
                clickedWhiteRook = false;
                bishophPane = selectedPane;
            } else if (selectedPane.getId().contains("whiteKnight")){
                clickedWhiteKnight = true;
                clickedWhitePawn = false;
                clickedWhiteKing = false;
                clickedWhiteBishoph = false;
                clickedWhiteQueen = false;
                clickedWhiteRook = false;
                knightPane = selectedPane;
            }else if (selectedPane.getId().contains("whiteRook")){
                clickedWhiteRook = true;
                clickedWhitePawn = false;
                clickedWhiteKing = false;
                clickedWhiteBishoph = false;
                clickedWhiteQueen = false;
                clickedWhiteKnight = false;
                rookPane = selectedPane;
            }
        } else if (selectedPane.toString().contains("Rectangle")) {

            if (clickedWhitePawn) {
                GameBoard.movePawn(pawnPane,selectedPane);
            } else if (clickedWhiteKing) {
                GameBoard.moveKing(kingPane,selectedPane);
            }else if (clickedWhiteRook) {
                GameBoard.moveRook(rookPane,selectedPane);
            }else if (clickedWhiteQueen) {
                GameBoard.moveQueen(queenPane,selectedPane);
            }else if (clickedWhiteBishoph) {
                GameBoard.moveBishoph(bishophPane,selectedPane);
            }else if (clickedWhiteKnight) {
                GameBoard.moveKnight(knightPane,selectedPane);
            } else {
                System.out.println("No Piece selected!");
            }

        }
    }
}

