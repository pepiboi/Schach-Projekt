package Pieces;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import javax.swing.text.html.ImageView;

import java.awt.*;
import java.util.Locale;

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

    public void moveRook(Node chesspiece, Node destination) {
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
            if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece)) {
                if (GridPane.getColumnIndex(destination) < GridPane.getColumnIndex(chesspiece)) {
                    for (int i = 1; i <= (GridPane.getColumnIndex(chesspiece) - GridPane.getColumnIndex(destination)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece) - i][GridPane.getRowIndex(chesspiece)] != null) {
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getColumnIndex(chesspiece) - i) == GridPane.getColumnIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                        }
                    }
                } else {
                    for (int i = 1; i <= (GridPane.getColumnIndex(destination) - GridPane.getColumnIndex(chesspiece)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece) + i][GridPane.getRowIndex(chesspiece)] != null) {
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getColumnIndex(chesspiece) + i) == GridPane.getColumnIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                        }
                    }
                }
            } else if (GridPane.getColumnIndex(destination).equals(GridPane.getColumnIndex(chesspiece))) {
                if (GridPane.getRowIndex(destination) < GridPane.getRowIndex(chesspiece)) {
                    for (int i = 1; i <= (GridPane.getRowIndex(chesspiece) - GridPane.getRowIndex(destination)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - i] != null) {
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getRowIndex(chesspiece) - i) == GridPane.getRowIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        }
                    }
                } else {
                    for (int i = 1; i <= (GridPane.getRowIndex(destination) - GridPane.getRowIndex(chesspiece)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) + i] != null) {
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getRowIndex(chesspiece) + i) == GridPane.getRowIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                        }
                    }
                }
            } else {
                System.out.println("Can't move there.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        try{
            if(     (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) - 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) + 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) + 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) - 1) ||
                    GridPane.getRowIndex(chesspiece) == (GridPane.getRowIndex(destination) - 1) ||
                    GridPane.getRowIndex(chesspiece) == (GridPane.getRowIndex(destination) + 1) ||
                    GridPane.getColumnIndex(chesspiece) == (GridPane.getColumnIndex(destination) + 1) ||
                    GridPane.getColumnIndex(chesspiece) == (GridPane.getColumnIndex(destination) - 1) )
            {
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
            }else{
                System.out.println("King can't be moved");
            }
        }catch (Exception e){
            e.printStackTrace();
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
                if (GridPane.getRowIndex(chesspiece) == 6) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 1] == null) {
                        if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) - 2) {
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 2] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setRowIndex(chesspiece, 4);
                        } else if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) - 1) {
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 1] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setRowIndex(chesspiece, (GridPane.getRowIndex(chesspiece) - 1));
                        } else {
                            System.out.println("Can't go there.");
                        }
                    }else{
                        System.out.println("Can't go there");
                    }
                } else if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) - 1) {
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 1] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setRowIndex(chesspiece, (GridPane.getRowIndex(chesspiece) - 1));
                } else {
                    System.out.println("Can't go there.");
                }
            } else {
                System.out.println("Can't go there.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveQueen(Node chesspiece, Node destination) {
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
        boolean upRight = false;
        boolean downRight = false;
        boolean downLeft = false;

        do {
            if((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null) {
                    upLeft = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j] != null) {
                    upRight = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j] != null) {
                    downLeft = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j] != null) {
                    downRight = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if(!upLeft) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j== GridPane.getRowIndex(destination))) {
                if (!upRight) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                if (!downLeft) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if (!downRight) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            }
            j++;
            if (j > 8) {
                moveRook(chesspiece, destination);
                break;
            }
        } while (true);
        j = 1;
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
        boolean upRight = false;
        boolean downRight = false;
        boolean downLeft = false;

        do {
                if((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null) {
                        upLeft = true;
                    }
                }
                if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j] != null) {
                        upRight = true;
                    }
                }
                if((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j] != null) {
                        downLeft = true;
                    }
                }
                if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j] != null) {
                        downRight = true;
                    }
                }
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if(!upLeft) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j== GridPane.getRowIndex(destination))) {
                if (!upRight) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                if (!downLeft) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if (!downRight) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    break;
                }
            }
            j++;
            if (j > 7) {
                System.out.println("bruh versuch a feld wo er hin kumma ko");
                break;
            }
        } while (true);
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
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();

            } else if ((GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public void killQueen(Node chesspiece, Node destination) {
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
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                break;
            } else if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                break;
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                break;
            }
            j++;
            if (j > 8) {
                killRook(chesspiece, destination);
                break;
            }
        } while (true);
        j = 1;
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
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
                        movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
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
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
            } else if ((GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains("black")) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
            }
        } catch (Exception e) {
            System.out.println("Killing with Knight exception");
        }
    }

    public void killBishoph(Node chesspiece, Node destination) {
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
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                break;
            } else if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                break;
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                break;
            }
            j++;
            if (j > 8) {
                System.out.println("bruh versuch a feld wo er hin kumma ko");
                break;
            }
        } while (true);
        j = 1;
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
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
            } else if ((GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) || GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece)) && (GridPane.getColumnIndex(p) == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
            } else if ((GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) && (GridPane.getRowIndex(p) == GridPane.getRowIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(chesspiece));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
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
