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
    public static GridPane gp;
    public static ChessColor cc;
    public static Node[][] chessboard;
    public static boolean somethingMoved;
    public static String movedNodeToString;
    public static String positionMoved;
    public static String id;
    public static boolean kingEaten = false;
    public static boolean kingTargeted = false;

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

    public static void moveRook(Node chesspiece, Node destination) {
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
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
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
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
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
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
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
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                        }
                    }
                }
            } else {
                System.out.println("Can't move there.");
                somethingMoved = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveKing(Node chesspiece, Node destination) {
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
            if ((GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) - 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) + 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) + 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination) - 1) ||
                    GridPane.getRowIndex(chesspiece) == (GridPane.getRowIndex(destination) - 1) && (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination)) ||
                    GridPane.getRowIndex(chesspiece) == (GridPane.getRowIndex(destination) + 1) && (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(destination)) ||
                    GridPane.getColumnIndex(chesspiece) == (GridPane.getColumnIndex(destination) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination)) ||
                    GridPane.getColumnIndex(chesspiece) == (GridPane.getColumnIndex(destination) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(destination))) {
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
            } else {
                System.out.println("King can't be moved");
                somethingMoved = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                if (GridPane.getRowIndex(chesspiece) == 6) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 1] == null) {
                        if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) - 2) {
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 2] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setRowIndex(chesspiece, 4);
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                        } else if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) - 1) {
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 1] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setRowIndex(chesspiece, (GridPane.getRowIndex(chesspiece) - 1));
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                        } else {
                            System.out.println("Can't go there.");
                        }
                    } else {
                        System.out.println("Can't go there");
                    }
                } else if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) - 1) {
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - 1] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setRowIndex(chesspiece, (GridPane.getRowIndex(chesspiece) - 1));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
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

    public static void moveQueen(Node chesspiece, Node destination) {
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
            if ((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null) {
                    upLeft = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j] != null) {
                    upRight = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j] != null) {
                    downLeft = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j] != null) {
                    downRight = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if (!upLeft) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                if (!upRight) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
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


    public static void moveBishoph(Node chesspiece, Node destination) {
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
            if ((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null) {
                    upLeft = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j] != null) {
                    upRight = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j] != null) {
                    downLeft = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j] != null) {
                    downRight = true;
                }
            }
            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if (!upLeft) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    break;
                }
            } else if ((GridPane.getColumnIndex(chesspiece) + j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) + j == GridPane.getRowIndex(destination))) {
                if (!upRight) {
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    break;
                }
            }
            j++;
            if (j > 7) {
                System.out.println("bruh versuch a feld wo er hin kumma ko");
                somethingMoved = false;
                break;
            }
        } while (true);
        j = 1;
    }

    public static void moveKnight(Node chesspiece, Node destination) {
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
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();

            } else if ((GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece))) {
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
            } else {
                somethingMoved = false;
            }
        } catch (Exception e) {
            System.out.println("Movin Knight exception");
        }

    }

    public static void killPawn(Node chesspiece, Node p) {
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(p) == null){
            x = 0;
        }else if(GridPane.getRowIndex(p) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(p) != null){
            x = GridPane.getColumnIndex(p);
        }else if(GridPane.getRowIndex(p) != null){
            y = GridPane.getRowIndex(p);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
        if (GridPane.getColumnIndex(p) == null) {
            GridPane.setColumnIndex(p, 0);
        } else if (GridPane.getRowIndex(p) == null) {
            GridPane.setRowIndex(p, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }

        if ((GridPane.getColumnIndex(chesspiece) - 1) == GridPane.getColumnIndex(p) && (GridPane.getRowIndex(chesspiece) - 1) == GridPane.getRowIndex(p)){
            p.setVisible(false);
            chessboard[GridPane.getColumnIndex(p)][GridPane.getRowIndex(p)] = chesspiece;
            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
            somethingMoved = true;
            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
            positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
            id = chesspiece.getId();
            BoardController.destinationID = p.getId();
            if(kingTargeted){
                kingEaten = true;
            }
        } else if ((GridPane.getColumnIndex(chesspiece) + 1) == GridPane.getColumnIndex(p) && (GridPane.getRowIndex(chesspiece) - 1) == GridPane.getRowIndex(p)) {
            if (p.getId().contains(getOpposite())) {
                p.setVisible(false);
                chessboard[GridPane.getColumnIndex(p)][GridPane.getRowIndex(p)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                id = chesspiece.getId();
                BoardController.destinationID = p.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
            } else {
                kingTargeted = false;
                System.out.println("Cannot eat that.");
            }
        }else{
            if ((GridPane.getColumnIndex(chesspiece) + 1) == GridPane.getColumnIndex(p) && (GridPane.getRowIndex(chesspiece) + 1) == GridPane.getRowIndex(p)) {
                if (p.getId().contains(getOpposite())) {
                    p.setVisible(false);
                    chessboard[GridPane.getColumnIndex(p)][GridPane.getRowIndex(p)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                    positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                    id = chesspiece.getId();
                    BoardController.destinationID = p.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                }
            } else if ((GridPane.getColumnIndex(chesspiece) - 1) == GridPane.getColumnIndex(p) && (GridPane.getRowIndex(chesspiece) + 1) == GridPane.getRowIndex(p)) {
                if (p.getId().contains(getOpposite())) {
                    p.setVisible(false);
                    chessboard[GridPane.getColumnIndex(p)][GridPane.getRowIndex(p)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                    positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                    id = chesspiece.getId();
                    BoardController.destinationID = p.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                }
            } else {
                System.out.println("oppa gangnam style");
                kingTargeted = false;
                somethingMoved = false;
            }
        }
    }

    public static void killQueen(Node chesspiece, Node destination) {
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(destination) == null){
            x = 0;
        }else if(GridPane.getRowIndex(destination) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(destination) != null){
            x = GridPane.getColumnIndex(destination);
        }else if(GridPane.getRowIndex(destination) != null){
            y = GridPane.getRowIndex(destination);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
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
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j].toString().contains(getOpposite())) ) {
                    upLeft = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j].toString().contains(getOpposite()))) {
                    upRight = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j].toString().contains(getOpposite()))) {
                    downLeft = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j].toString().contains(getOpposite()))) {
                    downRight = true;
                }
            }

            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if(!upLeft) {
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
                    break;
                }
            }
            j++;
            if (j > 8) {
                killRook(chesspiece, destination);
                kingTargeted = false;
                break;
            }
        } while (true);
        j = 1;
    }


    public static void killRook(Node chesspiece, Node destination) {
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(destination) == null){
            x = 0;
        }else if(GridPane.getRowIndex(destination) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(destination) != null){
            x = GridPane.getColumnIndex(destination);
        }else if(GridPane.getRowIndex(destination) != null){
            y = GridPane.getRowIndex(destination);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
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
                        if (chessboard[GridPane.getColumnIndex(chesspiece) - i][GridPane.getRowIndex(chesspiece)] != null &&
                                GridPane.getColumnIndex(destination) != (GridPane.getColumnIndex(chesspiece) - i)){
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getColumnIndex(chesspiece) - i) == GridPane.getColumnIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                            if(kingTargeted){
                                kingEaten = true;
                            }
                            BoardController.destinationID = destination.getId();
                            destination.setVisible(false);
                        }
                    }
                } else {
                    for (int i = 1; i <= (GridPane.getColumnIndex(destination) - GridPane.getColumnIndex(chesspiece)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece) + i][GridPane.getRowIndex(chesspiece)] != null &&
                                GridPane.getColumnIndex(destination) != (GridPane.getColumnIndex(chesspiece) + i)){
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getColumnIndex(chesspiece) + i) == GridPane.getColumnIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                            if(kingTargeted){
                                kingEaten = true;
                            }
                            BoardController.destinationID = destination.getId();
                            destination.setVisible(false);
                        }
                    }
                }
            } else if (GridPane.getColumnIndex(destination).equals(GridPane.getColumnIndex(chesspiece))) {
                if (GridPane.getRowIndex(destination) < GridPane.getRowIndex(chesspiece)) {
                    for (int i = 1; i <= (GridPane.getRowIndex(chesspiece) - GridPane.getRowIndex(destination)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) - i] != null &&
                                GridPane.getRowIndex(destination) != (GridPane.getRowIndex(chesspiece) - i)){
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getRowIndex(chesspiece) - i) == GridPane.getRowIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                            if(kingTargeted){
                                kingEaten = true;
                            }
                            BoardController.destinationID = destination.getId();
                            destination.setVisible(false);
                        }
                    }
                } else {
                    for (int i = 1; i <= (GridPane.getRowIndex(destination) - GridPane.getRowIndex(chesspiece)); i++) {
                        if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) + i] != null &&
                                GridPane.getRowIndex(destination) != (GridPane.getRowIndex(chesspiece) + i)){
                            System.out.println("Something in the way.");
                            break;
                        } else if ((GridPane.getRowIndex(chesspiece) + i) == GridPane.getRowIndex(destination)) {
                            chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                            GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                            if(kingTargeted){
                                kingEaten = true;
                            }
                            BoardController.destinationID = destination.getId();
                            destination.setVisible(false);
                        }
                    }
                }
            } else {
                kingTargeted = false;
                System.out.println("Can't move there.");
                somethingMoved = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveBlackPawn(Node chesspiece, Node destination){
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }

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
                if (GridPane.getRowIndex(chesspiece) == 1) {
                    if (chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) + 1] == null) {
                        if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) + 2) {
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) + 2] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setRowIndex(chesspiece, 3);
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                            if(kingTargeted){
                                kingEaten = true;
                            }
                        } else if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) + 1) {
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) + 1] = chesspiece;
                            chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                            GridPane.setRowIndex(chesspiece, (GridPane.getRowIndex(chesspiece) + 1));
                            somethingMoved = true;
                            movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                            positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                            id = chesspiece.getId();
                            if(kingTargeted){
                                kingEaten = true;
                            }
                        } else {
                            kingTargeted = false;
                            System.out.println("Can't go there.");
                        }
                    }else{
                        kingTargeted = false;
                        System.out.println("Can't go there");
                    }
                } else if (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece) + 1) {
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece) + 1] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setRowIndex(chesspiece, (GridPane.getRowIndex(chesspiece) + 1));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                } else {
                    kingTargeted= false;
                    System.out.println("Can't go there.");
                }
            } else {
                kingTargeted = false;
                System.out.println("Can't go there.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void killBlackPawn(Node chesspiece, Node destination){
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(destination) == null){
            x = 0;
        }else if(GridPane.getRowIndex(destination) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(destination) != null){
            x = GridPane.getColumnIndex(destination);
        }else if(GridPane.getRowIndex(destination) != null){
            y = GridPane.getRowIndex(destination);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
        if (GridPane.getColumnIndex(destination) == null) {
            GridPane.setColumnIndex(destination, 0);
        } else if (GridPane.getRowIndex(destination) == null) {
            GridPane.setRowIndex(destination, 0);
        } else if (GridPane.getColumnIndex(chesspiece) == null) {
            GridPane.setColumnIndex(chesspiece, 0);
        } else if (GridPane.getRowIndex(chesspiece) == null) {
            GridPane.setRowIndex(chesspiece, 0);
        }

        if ((GridPane.getColumnIndex(chesspiece) + 1) == GridPane.getColumnIndex(destination) && (GridPane.getRowIndex(chesspiece) + 1) == GridPane.getRowIndex(destination)) {
            if (destination.getId().contains(getOpposite())) {
                destination.setVisible(false);
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
                BoardController.destinationID = destination.getId();
            }
        } else if ((GridPane.getColumnIndex(chesspiece) - 1) == GridPane.getColumnIndex(destination) && (GridPane.getRowIndex(chesspiece) + 1) == GridPane.getRowIndex(destination)) {
            if (destination.getId().contains(getOpposite())) {
                destination.setVisible(false);
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
                BoardController.destinationID = destination.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
            }
        } else {
            kingTargeted = false;
            System.out.println("Darf nicht moven");
            somethingMoved = false;
        }
    }

    public static void killKnight(Node chesspiece, Node destination) {
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(destination) == null){
            x = 0;
        }else if(GridPane.getRowIndex(destination) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(destination) != null){
            x = GridPane.getColumnIndex(destination);
        }else if(GridPane.getRowIndex(destination) != null){
            y = GridPane.getRowIndex(destination);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
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
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
                BoardController.destinationID = destination.getId();
            } else if ((GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) - 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
                BoardController.destinationID = destination.getId();
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 2 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 2 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
                BoardController.destinationID = destination.getId();
            } else if ((GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece)) || (GridPane.getRowIndex(destination) + 2 == GridPane.getRowIndex(chesspiece) && GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece))) {
                if (destination.toString().contains(getOpposite())) {
                    destination.setVisible(false);
                }
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                id = chesspiece.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
                BoardController.destinationID = destination.getId();
            }else{
                kingTargeted = false;
                somethingMoved = false;
            }
        } catch (Exception e) {
            kingTargeted = false;
            System.out.println("Killing with Knight exception");
        }
    }

    public static void killBishoph(Node chesspiece, Node destination) {
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(destination) == null){
            x = 0;
        }else if(GridPane.getRowIndex(destination) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(destination) != null){
            x = GridPane.getColumnIndex(destination);
        }else if(GridPane.getRowIndex(destination) != null){
            y = GridPane.getRowIndex(destination);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
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
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) - j].toString().contains(getOpposite())) ) {
                    upLeft = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) + j].toString().contains(getOpposite()))) {
                    upRight = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) - j) >= 0 && (GridPane.getRowIndex(chesspiece) + j) <= 7) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) - j][GridPane.getRowIndex(chesspiece) + j].toString().contains(getOpposite()))) {
                    downLeft = true;
                }
            }
            if((GridPane.getColumnIndex(chesspiece) + j) <= 7 && (GridPane.getRowIndex(chesspiece) - j) >= 0) {
                if (chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j] != null &&
                        !(chessboard[GridPane.getColumnIndex(chesspiece) + j][GridPane.getRowIndex(chesspiece) - j].toString().contains(getOpposite()))) {
                    downRight = true;
                }
            }

            if ((GridPane.getColumnIndex(chesspiece) - j == GridPane.getColumnIndex(destination)) && (GridPane.getRowIndex(chesspiece) - j == GridPane.getRowIndex(destination))) {
                if(!upLeft) {
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
                    chessboard[GridPane.getColumnIndex(destination)][GridPane.getRowIndex(destination)] = chesspiece;
                    chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    somethingMoved = true;
                    movedNodeToString = "From: " + chesspiece.toString() + " || To: " + destination.toString();
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
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
                    positionMoved = GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination);
                    id = chesspiece.getId();
                    if(kingTargeted){
                        kingEaten = true;
                    }
                    BoardController.destinationID = destination.getId();
                    if (destination.toString().contains(getOpposite())) {
                        destination.setVisible(false);
                    }
                    break;
                }
            }
            j++;
            if (j > 7) {
                System.out.println("bruh versuch a feld wo er hin kumma ko");
                somethingMoved = false;
                kingTargeted = false;
                break;
            }
        } while (true);
        j = 1;
    }

    public static void attackKing(Node chesspiece, Node p) {
        int x = 0;
        int y = 0;
        if(GridPane.getColumnIndex(p) == null){
            x = 0;
        }else if(GridPane.getRowIndex(p) == null){
            y = 0;
        }else if(GridPane.getColumnIndex(p) != null){
            x = GridPane.getColumnIndex(p);
        }else if(GridPane.getRowIndex(p) != null){
            y = GridPane.getRowIndex(p);
        }
        if(chessboard[x][y].getId().toLowerCase(Locale.ROOT).contains("king")){
            kingTargeted = true;
        }
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
            if(     (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(p) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(p) - 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(p) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(p) + 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(p) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(p) + 1) ||
                    (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(p) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(p) - 1) ||
                    GridPane.getRowIndex(chesspiece) == (GridPane.getRowIndex(p) - 1) && (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(p)) ||
                    GridPane.getRowIndex(chesspiece) == (GridPane.getRowIndex(p) + 1) && (GridPane.getColumnIndex(chesspiece)) == (GridPane.getColumnIndex(p)) ||
                    GridPane.getColumnIndex(chesspiece) == (GridPane.getColumnIndex(p) + 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(p)) ||
                    GridPane.getColumnIndex(chesspiece) == (GridPane.getColumnIndex(p) - 1) && (GridPane.getRowIndex(chesspiece)) == (GridPane.getRowIndex(p)))
            {
                chessboard[GridPane.getColumnIndex(p)][GridPane.getRowIndex(p)] = chesspiece;
                chessboard[GridPane.getColumnIndex(chesspiece)][GridPane.getRowIndex(chesspiece)] = null;
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
                somethingMoved = true;
                movedNodeToString = "From: " + chesspiece.toString() + " || To: " + p.toString();
                positionMoved = GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p);
                id = chesspiece.getId();
                if(kingTargeted){
                    kingEaten = true;
                }
                p.setVisible(false);
                BoardController.destinationID = p.getId();
            }else{
                System.out.println("King can't be moved");
                somethingMoved = false;
                kingTargeted = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setCc(ChessColor cc){
        Board.cc = cc;
    }

    public static String getOpposite() {
        if (cc.toString().equals("WHITE")) {
            return "black";
        } else {
            return "white";
        }
    }
}
