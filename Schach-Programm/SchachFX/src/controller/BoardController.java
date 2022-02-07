package controller;

import FunctionalClasses.Client;
import FunctionalClasses.Server;
import Pieces.Board;
import Pieces.ChessColor;
import Pieces.ServerBoard;
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
import java.util.Locale;
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
    public boolean clickedBlackPawn;
    public boolean clickedBlackKing;
    public boolean clickedBlackQueen;
    public boolean clickedBlackBishoph;
    public boolean clickedBlackKnight;
    public boolean clickedBlackRook;
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
    public Pieces.Board gameBoard;
    public static Node selectedPane;
    public static String destinationID;
    public static String serverOrClient;


    public void onMouseClick(MouseEvent mouseEvent) {
        if (serverOrClient.equals("Client")) {
            selectedPane = (Node) mouseEvent.getSource();
            if (selectedPane.toString().contains("ImageView")) {
                if (selectedPane.getId().contains(cc.toString().toLowerCase(Locale.ROOT) + "Pawn")) {
                    clickedWhitePawn = true;
                    clickedWhiteKing = false;
                    clickedWhiteKnight = false;
                    clickedWhiteBishoph = false;
                    clickedWhiteQueen = false;
                    clickedWhiteRook = false;
                    pawnPane = selectedPane;
                } else if (selectedPane.getId().contains("black")) {
                    if (clickedWhitePawn) {
                        Board.killPawn(pawnPane, selectedPane);
                        if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                            Client.sendCurrentPositionKill("pawnPane", Board.positionMoved, Board.id, destinationID);
                        }
                    } else if (clickedWhiteKing) {
                        Board.attackKing(kingPane, selectedPane);
                        if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                            Client.sendCurrentPositionKill("kingPane", Board.positionMoved, Board.id, destinationID);
                        }
                    } else if (clickedWhiteQueen) {
                        Board.killQueen(queenPane, selectedPane);
                        if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                            Client.sendCurrentPositionKill("queenPane", Board.positionMoved, Board.id, destinationID);
                        }
                    } else if (clickedWhiteKnight) {
                        Board.killKnight(knightPane, selectedPane);
                        if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                            Client.sendCurrentPositionKill("knightPane", Board.positionMoved, Board.id, destinationID);
                        }
                    } else if (clickedWhiteBishoph) {
                        Board.killBishoph(bishophPane, selectedPane);
                        if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                            Client.sendCurrentPositionKill("bishophPane", Board.positionMoved, Board.id, destinationID);
                        }
                    } else if (clickedWhiteRook) {
                        Board.killRook(rookPane, selectedPane);
                        if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                            Client.sendCurrentPositionKill("rookPane", Board.positionMoved, Board.id, destinationID);
                        }
                    } else {
                        System.out.println("No Piece selected!");
                    }
                } else if (selectedPane.getId().contains(cc.toString().toLowerCase(Locale.ROOT) + "King")) {
                    clickedWhiteKing = true;
                    clickedWhitePawn = false;
                    clickedWhiteQueen = false;
                    clickedWhiteBishoph = false;
                    clickedWhiteKnight = false;
                    clickedWhiteRook = false;
                    kingPane = selectedPane;
                } else if (selectedPane.getId().contains(cc.toString().toLowerCase(Locale.ROOT) + "Queen")) {
                    clickedWhiteQueen = true;
                    clickedWhiteKing = false;
                    clickedWhitePawn = false;
                    clickedWhiteBishoph = false;
                    clickedWhiteKnight = false;
                    clickedWhiteRook = false;
                    queenPane = selectedPane;
                } else if (selectedPane.getId().contains(cc.toString().toLowerCase(Locale.ROOT) + "Bishoph")) {
                    clickedWhiteBishoph = true;
                    clickedWhitePawn = false;
                    clickedWhiteKing = false;
                    clickedWhiteQueen = false;
                    clickedWhiteKnight = false;
                    clickedWhiteRook = false;
                    bishophPane = selectedPane;
                } else if (selectedPane.getId().contains(cc.toString().toLowerCase(Locale.ROOT) + "Knight")) {
                    clickedWhiteKnight = true;
                    clickedWhitePawn = false;
                    clickedWhiteKing = false;
                    clickedWhiteBishoph = false;
                    clickedWhiteQueen = false;
                    clickedWhiteRook = false;
                    knightPane = selectedPane;
                } else if (selectedPane.getId().contains(cc.toString().toLowerCase(Locale.ROOT) + "Rook")) {
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
                    Board.movePawn(pawnPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Client.sendCurrentPosition("pawnPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedWhiteKing) {
                    Board.moveKing(kingPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Client.sendCurrentPosition("kingPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedWhiteRook) {
                    Board.moveRook(rookPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Client.sendCurrentPosition("rookPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedWhiteQueen) {
                    Board.moveQueen(queenPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Client.sendCurrentPosition("queenPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedWhiteBishoph) {
                    Board.moveBishoph(bishophPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Client.sendCurrentPosition("bishophPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedWhiteKnight) {
                    Board.moveKnight(knightPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Client.sendCurrentPosition("knightPane", Board.positionMoved, Board.id);
                    }
                } else {
                    System.out.println("No Piece selected!");
                }

            }
        }else if(serverOrClient.equals("Server")){
            cc = ChessColor.BLACK;
            selectedPane = (Node) mouseEvent.getSource();
            if (selectedPane.toString().contains("ImageView")) {
                if (selectedPane.getId().contains("black"+ "Pawn")) {
                    clickedBlackPawn = true;
                    clickedBlackKing = false;
                    clickedBlackKnight = false;
                    clickedBlackBishoph = false;
                    clickedBlackQueen = false;
                    clickedBlackRook = false;
                    pawnPane = selectedPane;
                } else if (selectedPane.getId().contains("white")) {
                    if (clickedBlackPawn) {
                        ServerBoard.killPawn(pawnPane, selectedPane);
                        if (LoginController.clientConnected == true && ServerBoard.somethingMoved == true) {
                            Server.sendCurrentPositionKillS("pawnPane", ServerBoard.positionMoved, ServerBoard.id, destinationID);
                        }
                    } else if (clickedBlackKing) {
                        Board.attackKing(kingPane, selectedPane);
                        if (LoginController.clientConnected == true && ServerBoard.somethingMoved == true) {
                            Server.sendCurrentPositionKillS("kingPane", ServerBoard.positionMoved, ServerBoard.id, destinationID);
                        }
                    } else if (clickedBlackQueen) {
                        Board.killQueen(queenPane, selectedPane);
                        if (LoginController.clientConnected == true && ServerBoard.somethingMoved == true) {
                            Server.sendCurrentPositionKillS("queenPane", ServerBoard.positionMoved, ServerBoard.id, destinationID);
                        }
                    } else if (clickedBlackKnight) {
                        Board.killKnight(knightPane, selectedPane);
                        if (LoginController.clientConnected == true && ServerBoard.somethingMoved == true) {
                            Server.sendCurrentPositionKillS("knightPane", ServerBoard.positionMoved, ServerBoard.id, destinationID);
                        }
                    } else if (clickedBlackBishoph) {
                        Board.killBishoph(bishophPane, selectedPane);
                        if (LoginController.clientConnected == true && ServerBoard.somethingMoved == true) {
                            Server.sendCurrentPositionKillS("bishophPane", ServerBoard.positionMoved, ServerBoard.id, destinationID);
                        }
                    } else if (clickedBlackRook) {
                        Board.killRook(rookPane, selectedPane);
                        if (LoginController.clientConnected == true && ServerBoard.somethingMoved == true) {
                            Server.sendCurrentPositionKillS("rookPane", ServerBoard.positionMoved, ServerBoard.id, destinationID);
                        }
                    } else {
                        System.out.println("No Piece selected!");
                    }
                } else if (selectedPane.getId().contains("black" + "King")) {
                    clickedBlackKing = true;
                    clickedBlackPawn = false;
                    clickedBlackQueen = false;
                    clickedBlackBishoph = false;
                    clickedBlackKnight = false;
                    clickedBlackRook = false;
                    kingPane = selectedPane;
                } else if (selectedPane.getId().contains("black"+"Queen")) {
                    clickedBlackQueen = true;
                    clickedBlackKing = false;
                    clickedBlackPawn = false;
                    clickedBlackBishoph = false;
                    clickedBlackKnight = false;
                    clickedBlackRook = false;
                    queenPane = selectedPane;
                } else if (selectedPane.getId().contains("black"+ "Bishoph")) {
                    clickedBlackBishoph = true;
                    clickedBlackPawn = false;
                    clickedBlackKing = false;
                    clickedBlackQueen = false;
                    clickedBlackKnight = false;
                    clickedBlackRook = false;
                    bishophPane = selectedPane;
                } else if (selectedPane.getId().contains("black"+ "Knight")) {
                    clickedBlackKnight = true;
                    clickedBlackPawn = false;
                    clickedBlackKing = false;
                    clickedBlackBishoph = false;
                    clickedBlackQueen = false;
                    clickedBlackRook = false;
                    knightPane = selectedPane;
                } else if (selectedPane.getId().contains("black"+ "Rook")) {
                    clickedBlackRook = true;
                    clickedBlackPawn = false;
                    clickedBlackKing = false;
                    clickedBlackBishoph = false;
                    clickedBlackQueen = false;
                    clickedBlackKnight = false;
                    rookPane = selectedPane;
                }
            } else if (selectedPane.toString().contains("Rectangle")) {
                if (clickedBlackPawn) {
                    ServerBoard.movePawn(pawnPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Server.sendCurrentPositionS("pawnPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedBlackKing) {
                    Board.moveKing(kingPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Server.sendCurrentPositionS("kingPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedBlackRook) {
                    Board.moveRook(rookPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Server.sendCurrentPositionS("rookPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedBlackQueen) {
                    Board.moveQueen(queenPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Server.sendCurrentPositionS("queenPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedBlackBishoph) {
                    Board.moveBishoph(bishophPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Server.sendCurrentPositionS("bishophPane", Board.positionMoved, Board.id);
                    }
                } else if (clickedBlackKnight) {
                    Board.moveKnight(knightPane, selectedPane);
                    if (LoginController.clientConnected == true && Board.somethingMoved == true) {
                        Server.sendCurrentPositionS("knightPane", Board.positionMoved, Board.id);
                    }
                } else {
                    System.out.println("No Piece selected!");
                }

            }
        }else{
            System.out.println("error at Server or Client");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameBoard = new Board(boardId, cc);
    }
}