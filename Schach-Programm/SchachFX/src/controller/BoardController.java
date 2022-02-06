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
    public ChessColor cc = ChessColor.BLACK;
    public GridPane boardId;
    public Pieces.Board gameBoard;
    public static Node selectedPane;
    public static String destinationID;


    public void onMouseClick(MouseEvent mouseEvent) {
        selectedPane = (Node) mouseEvent.getSource();
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
                    Board.killPawn(pawnPane,selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPositionKill("pawnPane", Board.positionMoved, Board.id, destinationID);
                    }
                } else if (clickedWhiteKing) {
                    Board.attackKing(kingPane,selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPositionKill("kingPane", Board.positionMoved, Board.id, destinationID);
                    }
                } else if(clickedWhiteQueen){
                    Board.killQueen(queenPane,selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPositionKill("queenPane", Board.positionMoved, Board.id, destinationID);
                    }
                }else if(clickedWhiteKnight){
                    Board.killKnight(knightPane,selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPositionKill("knightPane", Board.positionMoved, Board.id, destinationID);
                    }
                }else if(clickedWhiteBishoph){
                    Board.killBishoph(bishophPane,selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPositionKill("bishophPane", Board.positionMoved, Board.id, destinationID);
                    }
                }else if(clickedWhiteRook){
                    Board.killRook(rookPane,selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPositionKill("rookPane", Board.positionMoved, Board.id, destinationID);
                    }
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
                Board.movePawn(pawnPane,selectedPane);
                if (LoginController.clientConnected == true && Board.somethingMoved == true){
                        Client.sendCurrentPosition("pawnPane", Board.positionMoved, Board.id);
                }
            } else if (clickedWhiteKing) {
                Board.moveKing(kingPane,selectedPane);
                if (LoginController.clientConnected == true && Board.somethingMoved == true){
                    Client.sendCurrentPosition("kingPane", Board.positionMoved, Board.id);
                }
            }else if (clickedWhiteRook) {
                Board.moveRook(rookPane,selectedPane);
                if (LoginController.clientConnected == true && Board.somethingMoved == true){
                    Client.sendCurrentPosition("rookPane", Board.positionMoved, Board.id);
                }
            }else if (clickedWhiteQueen) {
                Board.moveQueen(queenPane,selectedPane);
                if (LoginController.clientConnected == true && Board.somethingMoved == true){
                    Client.sendCurrentPosition("queenPane", Board.positionMoved, Board.id);
                }
            }else if (clickedWhiteBishoph) {
                Board.moveBishoph(bishophPane,selectedPane);
                if (LoginController.clientConnected == true && Board.somethingMoved == true){
                    Client.sendCurrentPosition("bishophPane", Board.positionMoved, Board.id);
                }
            }else if (clickedWhiteKnight) {
                Board.moveKnight(knightPane,selectedPane);
                if (LoginController.clientConnected == true && Board.somethingMoved == true){
                    Client.sendCurrentPosition("knightPane", Board.positionMoved, Board.id);
                }
            } else {
                System.out.println("No Piece selected!");
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameBoard = new Board(boardId,cc);
    }
}