package Pieces;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class ServerBoard {
    public static GridPane gp;
    public static ChessColor cc;
    public static Node[][] chessboard;
    public static boolean somethingMoved;
    public static String movedNodeToString;
    public static String positionMoved;
    public static String id;

    public ServerBoard(GridPane gp, ChessColor cc) {
        this.gp = gp;
        this.cc = cc;
        chessboard = generateBoard();
        somethingMoved = false;
        movedNodeToString = "NO";
    }

    public Node[][] generateBoard() {
        Node[][] board = new Node[8][8];
        for (Node node : gp.getChildren()) {
            if (node.toString().contains("ImageView")) {
                try {
                    if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null) {
                        board[0][0] = node;
                    } else if (GridPane.getRowIndex(node) == null) {
                        board[GridPane.getColumnIndex(node)][0] = node;
                    } else if (GridPane.getColumnIndex(node) == null) {
                        board[0][GridPane.getRowIndex(node)] = node;
                    } else {
                        board[GridPane.getColumnIndex(node)][GridPane.getRowIndex(node)] = node;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return board;
    }

    public static void movePawn(Node chesspiece, Node destination) {
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
                if (GridPane.getRowIndex(destination) - 2 == 1 || GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece)) {
                    /*BoardController.whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                    BoardController.whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                    if (GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece)) {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));/*
                        chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                        chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;*/
                        somethingMoved = true;
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                        positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                        id = chesspiece.getId();
                        BoardController.destinationID = destination.getId();
                    } else if ((GridPane.getRowIndex(destination) - 2 == 1)) {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                        somethingMoved = true;
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                        positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                        id = chesspiece.getId();
                        BoardController.destinationID = destination.getId();
                        /*if(chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(destination)+1] == null) {
                            somethingMoved = true;
                            movedNodeToString = "From: "+chesspiece.toString()+" || To: "+destination.toString();

                            System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                        }else{
                            System.out.println("Jemand steht vor dir du dummer hurensohn.");
                        }*/
                    }

                }
            } else if (GridPane.getRowIndex(destination) == 7) {
                //Dann darf sich der Spieler eine Figur zurückholen
            } else {
                System.out.println("Das darf der Bauer leider nicht!");
                somethingMoved = false;
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public static void killPawn(Node chesspiece, Node p) {
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
            if (GridPane.getColumnIndex(chesspiece) == null && GridPane.getRowIndex(p) != 0 && GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) + 1 == 7) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("white")) {
                        /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                        positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                        id = chesspiece.getId();
                        BoardController.destinationID = p.getId();
                    }
                }
            } else if (GridPane.getColumnIndex(chesspiece) == 1 && GridPane.getRowIndex(p) != 7 && GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(p) == 0 || GridPane.getRowIndex(p) == null) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("white")) {
                        /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                        positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                        id = chesspiece.getId();
                        BoardController.destinationID = p.getId();
                    }
                }
            } else if (GridPane.getRowIndex(p) != 0 && GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece) && (GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece)) || GridPane.getRowIndex(p) == null) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("white")) {
                       /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                        positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                        id = chesspiece.getId();
                        BoardController.destinationID = p.getId();
                    }
                }
            } else if (GridPane.getColumnIndex(p) == null && GridPane.getRowIndex(p) == null && (GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) == null) || GridPane.getRowIndex(p) == null) {
                if (p.toString().contains("ImageView")) {
                    if (p.getId().contains("white")) {
                        /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                        whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                        p.setVisible(false);
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                        GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                        somethingMoved = true;
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                        positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                        id = chesspiece.getId();
                        BoardController.destinationID = p.getId();
                    }
                }
            } else {
                somethingMoved = false;
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public static String getOpposite() {
        if (cc.toString().equals("BLACK")) {
            return "white";
        } else {
            return "black";
        }
    }
}
