package controller;

import FunctionalClasses.Client;
import Pieces.Board;
import Pieces.ChessColor;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
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
    public static ListView<String> whiteMovesID;
    public static ListView<String> blackMovesID;
    public ChessColor cc = ChessColor.WHITE;
    public GridPane boardId;
    public Pieces.Board GameBoard;


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
                if (LoginController.clientConnected == true){
                    if (LoginController.clientConnected == true){
                        Client.sendCurrentPosition("pawnPane");
                    }else {
                        Client.sendCurrentPosition("pawnPane");
                    }
                }
            } else if (clickedWhiteKing) {
                GameBoard.moveKing(kingPane,selectedPane);
                if (LoginController.clientConnected == true){
                    if (LoginController.clientConnected == true){
                        Client.sendCurrentPosition("kingPane");
                    }else {
                        Client.sendCurrentPosition("kingPane");
                    }
                }
            }else if (clickedWhiteRook) {
                GameBoard.moveRook(rookPane,selectedPane);
                if (LoginController.clientConnected == true){
                    if (LoginController.clientConnected == true){
                        Client.sendCurrentPosition("rookPane");
                    }else {
                        Client.sendCurrentPosition("rookPane");
                    }
                }
            }else if (clickedWhiteQueen) {
                GameBoard.moveQueen(queenPane,selectedPane);
                if (LoginController.clientConnected == true){
                    if (LoginController.clientConnected == true){
                        Client.sendCurrentPosition("queenPane");
                    }else {
                        Client.sendCurrentPosition("queenPane");
                    }
                }
            }else if (clickedWhiteBishoph) {
                GameBoard.moveBishoph(bishophPane,selectedPane);
                if (LoginController.clientConnected == true){
                    if (LoginController.clientConnected == true){
                        Client.sendCurrentPosition("bishophPane");
                    }else {
                        Client.sendCurrentPosition("bishophPane");
                    }
                }
            }else if (clickedWhiteKnight) {
                GameBoard.moveKnight(knightPane,selectedPane);
                if (LoginController.clientConnected == true){
                    if (LoginController.clientConnected == true){
                        Client.sendCurrentPosition("knightPane");
                    }else {
                        Client.sendCurrentPosition("knightPane");
                    }
                }
            } else {
                System.out.println("No Piece selected!");
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameBoard = new Board(boardId,cc);
    }
}

