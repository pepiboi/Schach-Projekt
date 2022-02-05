package Pieces;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import static controller.BoardController.whiteMovesID;

public class Board {
    GridPane gp = new GridPane();

    public Board(GridPane gp) {
        this.gp = gp;
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
            if ((GridPane.getRowIndex(chesspiece) == GridPane.getRowIndex(destination))) {
                System.out.println("Reihe");
                if (GridPane.getColumnIndex(chesspiece) > GridPane.getColumnIndex(destination)) {
                    //- rechnen um nach oben zu gehen
                    for (int i = GridPane.getColumnIndex(chesspiece); i >= GridPane.getColumnIndex(destination); i--) {
                        GridPane.setColumnIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(chesspiece));
                        GridPane.setColumnIndex(destination, GridPane.getColumnIndex(destination));
                    }
                } else {
                    for (int i = GridPane.getColumnIndex(chesspiece); i <= GridPane.getColumnIndex(destination); i++) {
                        GridPane.setColumnIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setColumnIndex(destination, GridPane.getColumnIndex(destination));
                    }
                }
            } else if (GridPane.getColumnIndex(chesspiece) == GridPane.getColumnIndex(chesspiece)) {
                if (GridPane.getRowIndex(chesspiece) > GridPane.getRowIndex(destination)) {
                    //- rechnen um nach oben zu gehen
                    for (int i = GridPane.getRowIndex(chesspiece); i >= GridPane.getRowIndex(destination); i--) {
                        GridPane.setRowIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(destination, GridPane.getRowIndex(destination));
                    }
                } else {
                    for (int i = GridPane.getRowIndex(chesspiece); i <= GridPane.getRowIndex(destination); i++) {
                        GridPane.setRowIndex(destiPlusI, i);
                        if (destiPlusI.toString().contains("white")) {
                            whiteInfront = true;
                        }
                    }
                    if (whiteInfront == true) {
                        System.out.println("White Piecec can not be overtaken with Rook");
                    } else {
                        System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                        GridPane.setRowIndex(destination, GridPane.getRowIndex(destination));
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
            } else if ((GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece) || GridPane.getRowIndex(destination) - 1 == GridPane.getRowIndex(chesspiece)) && (GridPane.getColumnIndex(destination) == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
            } else if ((GridPane.getColumnIndex(destination) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(destination) - 1 == GridPane.getColumnIndex(chesspiece)) && (GridPane.getRowIndex(destination) == GridPane.getRowIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(chesspiece));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(destination));
            } else {
                System.out.println("King can not be moved at: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving King");
        }
    }

    public void movePawn(Node chesspiece, Node destination){
        if (GridPane.getColumnIndex(destination) == null){
            GridPane.setColumnIndex(destination, 0);
        }else if(GridPane.getRowIndex(destination) == null){
            GridPane.setRowIndex(destination, 0);
        }else if(GridPane.getColumnIndex(chesspiece) == null){
            GridPane.setColumnIndex(chesspiece, 0);
        }else if (GridPane.getRowIndex( chesspiece)== null){
            GridPane.setRowIndex(chesspiece, 0);
        }

        try {
            if (GridPane.getColumnIndex(destination) == GridPane.getColumnIndex(chesspiece)) {
                if (GridPane.getRowIndex(destination) + 2 == 6 || GridPane.getRowIndex(destination) + 1 == GridPane.getRowIndex(chesspiece)) {
                    /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                    whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(destination) + "|" + GridPane.getRowIndex(destination));*/
                    System.out.println(GridPane.getColumnIndex(destination) + " " + GridPane.getRowIndex(destination));
                    GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(destination));
                    GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
                }
            } else if (GridPane.getRowIndex(destination) == null) {
                //Dann darf sich der Spieler eine Figur zurückholen
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public void moveQueen(Node chesspiece, Node destination) {

    }

    public void moveBishoph(Node chesspiece, Node destination){
        if (GridPane.getColumnIndex(destination) == null){
            GridPane.setColumnIndex(destination, 0);
        }else if(GridPane.getRowIndex(destination) == null){
            GridPane.setRowIndex(destination, 0);
        }else if(GridPane.getColumnIndex(chesspiece) == null){
            GridPane.setColumnIndex(chesspiece, 0);
        }else if (GridPane.getRowIndex(chesspiece)== null){
            GridPane.setRowIndex(chesspiece, 0);
        }

        boolean found = false;
        boolean obsticle = false;

        int j = 0;

        try {
                for (int i = 0; i < 4; i++) {
                    switch(i){
                        case 1:
                            while(!obsticle){
                                try{

                                }catch(Exception e){
                                    obsticle = true;
                                    System.out.println("yall are trying out of bounds shit.");
                                }
                            }
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                    }
                    obsticle = false;
                }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Bishoph");
        }
    }

    public void moveKnight(Node chesspiece, Node destination){

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
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    public void killQueen(Node chesspiece, Node p) {

    }

    public void killRook(Node chesspiece, Node p) {

    }

    public void killKnight(Node chesspiece, Node p) {

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
            } else if ((GridPane.getRowIndex(p) + 1 == GridPane.getRowIndex(chesspiece) || GridPane.getRowIndex(p) - 1 == GridPane.getRowIndex(chesspiece)) && (GridPane.getColumnIndex(p) == GridPane.getColumnIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(p));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(chesspiece));
            } else if ((GridPane.getColumnIndex(p) + 1 == GridPane.getColumnIndex(chesspiece) || GridPane.getColumnIndex(p) - 1 == GridPane.getColumnIndex(chesspiece)) && (GridPane.getRowIndex(p) == GridPane.getRowIndex(chesspiece))) {
                /*whiteMovesID.getItems().add(chesspiece.getId() + " from: " + GridPane.getColumnIndex(chesspiece) + "|" + GridPane.getRowIndex(chesspiece));
                whiteMovesID.getItems().add(chesspiece.getId() + " to: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));*/
                System.out.println(GridPane.getColumnIndex(p) + " " + GridPane.getRowIndex(p));
                p.setVisible(false);
                GridPane.setRowIndex(chesspiece, GridPane.getRowIndex(chesspiece));
                GridPane.setColumnIndex(chesspiece, GridPane.getColumnIndex(p));
            } else {
                System.out.println("King can not be moved at: " + GridPane.getColumnIndex(p) + "|" + GridPane.getRowIndex(p));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }
}
