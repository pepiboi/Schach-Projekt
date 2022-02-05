package Pieces;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import javax.swing.text.html.ImageView;

import java.awt.*;

import static controller.BoardController.whiteMovesID;


public class Board {
    GridPane gp;
    ChessColor cc;
    Node[][] chessboard;
    public static boolean somethingMoved;
    public static String movedNodeToString;

    public Board(GridPane gp, ChessColor cc) {
        this.gp = gp;
        this.cc = cc;
        chessboard = generateBoard();
        somethingMoved = false;
        movedNodeToString = "NO";
    }

    public Node[][] generateBoard() {
        Node[][] board = new Node[8][8];
        for (Node node : gp.getChildren()) {
            if(node.toString().contains("ImageView")){
                try {
                    board[GridPane.getColumnIndex(node)][GridPane.getRowIndex(node)] = node;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return board;
    }

    public void moveRook(Node chesspiece, Node destination) {
        //Hier geht der Rook(Turm) nach vorne/hinten und links/rechts
        //Es wird nicht implementiert, dass der Turm jemanden schmeißt
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }
        Node destiPlusI = chesspiece;
        boolean whiteInfront = false;
        try {
            if ((GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println("Der Turm ist kein Pferd");
            } else if ((GridPane.getRowIndex(chesspiece) != GridPane.getRowIndex(destination)) && (GridPane.getColumnIndex(chesspiece) != GridPane.getColumnIndex(destination))) {
                System.out.println("Rook can not move to: " + GridPane.getRowIndex(destination) + " | " + GridPane.getColumnIndex(destination));
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println("Der Turm ist kein Pferd");
            } else if ((GridPane.getRowIndex(chesspiece) == GridPane.getRowIndex(destination))) {
                System.out.println("Reihe");
                if (GridPane.getColumnIndex(chesspiece) > GridPane.getColumnIndex(destination)) {
                    //links
                    for (int i = GridPane.getColumnIndex(chesspiece) - 1; i >= GridPane.getColumnIndex(destination); i--) {
                        GridPane.setColumnIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                } else {
                    for (int i = GridPane.getColumnIndex(chesspiece) + 1; i <= GridPane.getColumnIndex(destination); i++) {
                        GridPane.setColumnIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                }
            } else if (GridPane.getColumnIndex(chesspiece) == GridPane.getColumnIndex(chesspiece)) {
                if (GridPane.getRowIndex(chesspiece) > GridPane.getRowIndex(destination)) {
                    //- rechnen um nach oben zu gehen
                    for (int i = GridPane.getRowIndex(chesspiece) - 1; i >= GridPane.getRowIndex(destination); i--) {
                        GridPane.setRowIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                } else {
                    for (int i = GridPane.getRowIndex(chesspiece) + 1; i <= GridPane.getRowIndex(destination); i++) {
                        GridPane.setRowIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                }
            } else {
                System.out.println("Rook can not move to: " + GridPane.getRowIndex(destination) + " | " + GridPane.getColumnIndex(destination));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Rook");
        }
    }

    public void moveKing(Node chesspiece, Node destination) {
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }
        try {
            if ((GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println("Der König ist kein Pferd");
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {

                System.out.println("Der König ist kein Pferd");
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) || GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece)) && (GridPane.getColumnIndex(destination) == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
            } else if ((GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) && (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(chesspiece));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else {
                System.out.println("King can not be moved at: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving King");
        }
    }

    public void movePawn(Node chesspiece, Node destination) {
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }

        try {
            if (GridPane.getColumnIndex(destination) == GridPane.getColumnIndex(chesspiece)) {
                if (GridPane.getRowIndex(destination) + 2 == 6 || GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece)) {
                    /*BoardController.whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                    BoardController.whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                    if (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece)) {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                        chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                        chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    } else if ((GridPane.getRowIndex(destination) + 2 == 6)) {


                        if(chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(destination)+1] == null) {
                            somethingMoved = true;
                            movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();

                            System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                        }else{
                            System.out.println("Jemand steht vor dir du dummer hurensohn.");
                        }
                    }

                }
            } else if (GridPane.getRowIndex(destination) == 0) {
                //Dann darf sich der Spieler eine Figur zurückholen
            } else{
                System.out.println("Das darf der Bauer leider nicht!");
                somethingMoved = false;
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public void moveQueen(Node chesspiece, Node destination) {

    }

    public void moveBishoph(Node chesspiece, Node destination) {
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }

        int j = 1;

        boolean upLeft = false;

        do {
            if(chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null){
                upLeft = true;
            }
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if (!upLeft) {
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                break;
            }else if((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))){
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                }
            }else if((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))){
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                break;
            }else if((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))){
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            }
            j++;
            if (j > 8) {
                System.out.println("bruh versuch a feld wo er hin kumma ko");
                break;
            }
        } while (true);
        upLeft = false;
        j = 1;
    }

    public void moveKnight(Node chesspiece, Node destination) {
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }

        try {
            if ((GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();

            } else if ((GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            }
        } catch (Exception e) {
            System.out.println("Movin Knight exception");
        }

    }

    public void killPawn(Node chesspiece, Node p) {
        if (GridPane.getColumnIndex(p) == null) {
            GridPane.setColumnIndex(p, 0);
        } else if (GridPane.getRowIndex(p) == null) {
            GridPane.setRowIndex(p, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }
        try {
            if (GridPane.getColumnIndex(chesspiece) == null && GridPane.getRowIndex(p) != 7 && GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) - 1 == 0) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("black")) {
                        /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
                    }
                }
            } else if (GridPane.getColumnIndex(chesspiece) == 1 && GridPane.getRowIndex(p) != 7 && GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) == 0 || GridPane.getRowIndex(p) == null) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("black")) {
                        /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
                    }
                }
            } else if (GridPane.getRowIndex(p) != 7 && GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && (GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) || GridPane.getRowIndex(p) == null) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("black")) {
                       /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
                    }
                }
            } else if (GridPane.getColumnIndex(p) == null && GridPane.getRowIndex(p) == null && (GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) == null) || GridPane.getRowIndex(p) == null) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("black")) {
                        /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public void killQueen(Node chesspiece, Node p) {

    }

    public void killRook(Node chesspiece, Node destination) {
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }
        Node destiPlusI = chesspiece;
        boolean whiteInfront = false;
        try {
            if ((GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println("Der Turm ist kein Pferd");
            } else if ((GridPane.getRowIndex(chesspiece) != GridPane.getRowIndex(destination)) && (GridPane.getColumnIndex(chesspiece) != GridPane.getColumnIndex(destination))) {
                System.out.println("Rook can not move to: " + GridPane.getRowIndex(destination) + " | " + GridPane.getColumnIndex(destination));
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {

                System.out.println("Der Turm ist kein Pferd");
            } else if ((GridPane.getRowIndex(chesspiece) == GridPane.getRowIndex(destination))) {
                System.out.println("Reihe");
                if (GridPane.getColumnIndex(chesspiece) > GridPane.getColumnIndex(destination)) {
                    //- rechnen um nach oben zu gehen
                    for (int i = GridPane.getColumnIndex(chesspiece) - 1; i >= GridPane.getColumnIndex(destination); i--) {
                        GridPane.setColumnIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("ImageView")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }
                    } else {
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                } else {
                    for (int i = GridPane.getColumnIndex(chesspiece) + 1; i <= GridPane.getColumnIndex(destination); i++) {
                        GridPane.setColumnIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("ImageView")) {
                            whiteInfront = true;
                        }
                    }


                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }

                    } else {
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);

                        }
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                }
            } else if (GridPane.getColumnIndex(chesspiece) == GridPane.getColumnIndex(chesspiece)) {
                if (GridPane.getRowIndex(chesspiece) > GridPane.getRowIndex(destination)) {
                    //- rechnen um nach oben zu gehen
                    for (int i = GridPane.getRowIndex(chesspiece) - 1; i >= GridPane.getRowIndex(destination); i--) {
                        GridPane.setRowIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("ImageView")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piece can not be overtaken with Rook");
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }
                    } else {
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                } else {
                    for (int i = GridPane.getRowIndex(chesspiece) + 1; i <= GridPane.getRowIndex(destination); i++) {
                        GridPane.setRowIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("ImageView")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }
                    } else {
                        if (destination.toString().contains("black")) {
                            destination.setVisible(false);
                        }
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        somethingMoved = true;
                        movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
                    }
                }
            } else {
                System.out.println("Rook can not move to: " + GridPane.getRowIndex(destination) + " | " + GridPane.getColumnIndex(destination));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Rook");
        }
    }

    public void killKnight(Node chesspiece, Node destination) {
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }
        try {
            if ((GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();
            }
        } catch (Exception e) {
            System.out.println("Killing with Knight exception");
        }
    }

    public void killBishoph(Node chesspiece, Node p) {

    }

    public void attackKing(Node chesspiece, Node p) {
        if (GridPane.getColumnIndex(p) == null) {
            GridPane.setColumnIndex(p, 0);
        } else if (GridPane.getRowIndex(p) == null) {
            GridPane.setRowIndex(p, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }
        try {
            if ((GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) - 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) + 2 == GridPane.getColumnIndex(chesspiece))) {

                System.out.println("Der König ist kein Pferd");
            } else if ((GridPane.getRowIndex(p) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(p) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece))) {

                System.out.println("Der König ist kein Pferd");
            } else if ((GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
            } else if ((GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) || GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece)) && (GridPane.getColumnIndex(p) == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
            } else if ((GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) && (GridPane.getRowIndex(p) == GridPane.getRowIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(chesspiece));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                somethingMoved = true;
                movedNodeToString = "From: "+chesspiece.toString()+" || To: "+p.toString();
            } else {
                System.out.println("King can not be moved at: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public String getOpposite() {
        if (cc.toString().equals("WHITE")) {
            return "black";
        } else {
            return "white";
        }
    }
}
