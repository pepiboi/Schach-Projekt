package controller;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.InvocationTargetException;

public class BoardController {
    public GridPane boardId;
    public ImageView onClickExit;
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
    public Node pawnPane;
    public Node kingPane;
    public ListView whiteMovesID;
    public ListView blackMovesID;
    /*private void movePawn(Node selectedPane) {
        System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
    }*/

    private void clickedRectanglePawn(Node selectedPane) {
        if (GridPane.getColumnIndex(selectedPane) == null){
            GridPane.setColumnIndex(selectedPane, 0);
        }else if(GridPane.getRowIndex(selectedPane) == null){
            GridPane.setRowIndex(selectedPane, 0);
        }else if(GridPane.getColumnIndex(pawnPane) == null){
            GridPane.setColumnIndex(pawnPane, 0);
        }else if (GridPane.getRowIndex( pawnPane)== null){
            GridPane.setRowIndex(pawnPane, 0);
        }

        try {
            if (GridPane.getColumnIndex(selectedPane) == GridPane.getColumnIndex(pawnPane)) {

                if (GridPane.getRowIndex(selectedPane) + 2 == 6 || GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane)) {
                    whiteMovesID.getItems().add(pawnPane.getId() + " from: " + GridPane.getColumnIndex(pawnPane) + "|" + GridPane.getRowIndex(pawnPane));
                    whiteMovesID.getItems().add(pawnPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                    System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                    GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                    GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(pawnPane));
                }
            } else if (GridPane.getRowIndex(selectedPane) == null) {
                //Dann darf sich der Spieler eine Figur zurückholen
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    private void clickedBlackPawn(Node selectedPane) {
        if (GridPane.getColumnIndex(selectedPane) == null){
            GridPane.setColumnIndex(selectedPane, 0);
        }else if(GridPane.getRowIndex(selectedPane) == null){
            GridPane.setRowIndex(selectedPane, 0);
        }else if(GridPane.getColumnIndex(pawnPane) == null){
            GridPane.setColumnIndex(pawnPane, 0);
        }else if (GridPane.getRowIndex( pawnPane)== null){
            GridPane.setRowIndex(pawnPane, 0);
        }
        try {
            if (GridPane.getColumnIndex(pawnPane) == null && GridPane.getRowIndex(selectedPane) != 7 && GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane) && GridPane.getColumnIndex(selectedPane) - 1 == 0) {
                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
                        whiteMovesID.getItems().add(pawnPane.getId() + " from: " + GridPane.getColumnIndex(pawnPane) + "|" + GridPane.getRowIndex(pawnPane));
                        whiteMovesID.getItems().add(pawnPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                        selectedPane.setVisible(false);
                        GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                        GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(selectedPane));
                    }
                }
            } else if (GridPane.getColumnIndex(pawnPane) == 1 && GridPane.getRowIndex(selectedPane) != 7 && GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane) && GridPane.getColumnIndex(selectedPane) == 0 || GridPane.getRowIndex(selectedPane) == null) {
                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
                        whiteMovesID.getItems().add(pawnPane.getId() + " from: " + GridPane.getColumnIndex(pawnPane) + "|" + GridPane.getRowIndex(pawnPane));
                        whiteMovesID.getItems().add(pawnPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                        selectedPane.setVisible(false);
                        GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                        GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(selectedPane));
                    }
                }
            } else if (GridPane.getRowIndex(selectedPane) != 7 && GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane) && (GridPane.getColumnIndex(selectedPane) + 1 == GridPane.getColumnIndex(pawnPane) || GridPane.getColumnIndex(selectedPane) - 1 == GridPane.getColumnIndex(pawnPane)) || GridPane.getRowIndex(selectedPane) == null) {
                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
                        whiteMovesID.getItems().add(pawnPane.getId() + " from: " + GridPane.getColumnIndex(pawnPane) + "|" + GridPane.getRowIndex(pawnPane));
                        whiteMovesID.getItems().add(pawnPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                        selectedPane.setVisible(false);
                        GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                        GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(selectedPane));
                    }
                }
            } else if (GridPane.getColumnIndex(selectedPane) == null && GridPane.getRowIndex(selectedPane) == null && (GridPane.getColumnIndex(selectedPane) + 1 == GridPane.getColumnIndex(pawnPane) || GridPane.getColumnIndex(selectedPane) - 1 == GridPane.getColumnIndex(pawnPane) || GridPane.getColumnIndex(selectedPane) == null) || GridPane.getRowIndex(selectedPane) == null) {
                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
                        whiteMovesID.getItems().add(pawnPane.getId() + " from: " + GridPane.getColumnIndex(pawnPane) + "|" + GridPane.getRowIndex(pawnPane));
                        whiteMovesID.getItems().add(pawnPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                        selectedPane.setVisible(false);
                        GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                        GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(selectedPane));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    private void clickedRectangleKing(Node selectedPane) {
        if (GridPane.getColumnIndex(selectedPane) == null){
            GridPane.setColumnIndex(selectedPane, 0);
        }else if(GridPane.getRowIndex(selectedPane) == null){
            GridPane.setRowIndex(selectedPane, 0);
        }else if(GridPane.getColumnIndex(kingPane) == null){
            GridPane.setColumnIndex(kingPane, 0);
        }else if (GridPane.getRowIndex( kingPane)== null){
            GridPane.setRowIndex(kingPane, 0);
        }
        try {
            if ((GridPane.getRowIndex(selectedPane)-1==GridPane.getRowIndex(kingPane)&&GridPane.getColumnIndex(selectedPane)-2 == GridPane.getColumnIndex(kingPane))||(GridPane.getRowIndex(selectedPane)-1 == GridPane.getRowIndex(kingPane)&&GridPane.getColumnIndex(selectedPane)+2 == GridPane.getColumnIndex(kingPane))) {

                System.out.println("Der König ist kein fucking pferd du idiot");
            }if ((GridPane.getRowIndex(selectedPane)+1==GridPane.getRowIndex(kingPane)&&GridPane.getColumnIndex(selectedPane)+1 == GridPane.getColumnIndex(kingPane))|| (GridPane.getRowIndex(selectedPane)-1 == GridPane.getRowIndex(kingPane) && GridPane.getColumnIndex(selectedPane)-1 == GridPane.getColumnIndex(kingPane))||(GridPane.getRowIndex(selectedPane)+1 == GridPane.getRowIndex(kingPane) && GridPane.getColumnIndex(selectedPane)-1 == GridPane.getColumnIndex(kingPane))||(GridPane.getRowIndex(selectedPane)-1 == GridPane.getRowIndex(kingPane)&&GridPane.getColumnIndex(selectedPane)+1 == GridPane.getColumnIndex(kingPane))) {
                whiteMovesID.getItems().add(kingPane.getId() + " from: " + GridPane.getColumnIndex(kingPane) + "|" + GridPane.getRowIndex(kingPane));
                whiteMovesID.getItems().add(kingPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                System.out.println("Warum macht der King das");
                GridPane.setRowIndex(kingPane, GridPane.getRowIndex(selectedPane));
                GridPane.setColumnIndex(kingPane, GridPane.getColumnIndex(selectedPane));
            }else if (GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(kingPane) || GridPane.getRowIndex(selectedPane) -1 == GridPane.getRowIndex(kingPane) && GridPane.getColumnIndex(selectedPane) == GridPane.getColumnIndex(kingPane) ) {

                whiteMovesID.getItems().add(kingPane.getId() + " from: " + GridPane.getColumnIndex(kingPane) + "|" + GridPane.getRowIndex(kingPane));
                whiteMovesID.getItems().add(kingPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                GridPane.setRowIndex(kingPane, GridPane.getRowIndex(selectedPane));
                GridPane.setColumnIndex(kingPane, GridPane.getColumnIndex(kingPane));
            }else if ( GridPane.getColumnIndex(selectedPane) + 1 == GridPane.getColumnIndex(kingPane) || GridPane.getColumnIndex(selectedPane) -1 == GridPane.getColumnIndex(kingPane) && GridPane.getRowIndex(selectedPane) == GridPane.getRowIndex(kingPane)) {
                whiteMovesID.getItems().add(kingPane.getId() + " from: " + GridPane.getColumnIndex(kingPane) + "|" + GridPane.getRowIndex(kingPane));
                whiteMovesID.getItems().add(kingPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                GridPane.setRowIndex(kingPane, GridPane.getRowIndex(kingPane));
                GridPane.setColumnIndex(kingPane, GridPane.getColumnIndex(selectedPane));
            }else{
                System.out.println("King can not be moved at: "+ GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving King");
        }
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Node selectedPane = (Node) mouseEvent.getSource();
        //System.out.println(selectedPane.toString());
        if (selectedPane.toString().contains("ImageView")) {
            if (selectedPane.getId().contains("whitePawn")) {
                //System.out.println("WhitePawn");
                clickedWhitePawn = true;
                clickedWhiteKing = false;
                pawnPane = selectedPane;
            } else if (selectedPane.getId().contains("black")) {
                if (clickedWhitePawn == true) {
                    clickedBlackPawn(selectedPane);
                } else if (clickedWhiteKing == true) {

                } else {
                    System.out.println("No Piece selected!");
                }
            } else if (selectedPane.getId().contains("whiteKing")) {
                clickedWhiteKing = true;
                clickedWhitePawn = false;
                kingPane = selectedPane;
            }
        } else if (selectedPane.toString().contains("Rectangle")) {

            if (clickedWhitePawn == true) {
                clickedRectanglePawn(selectedPane);
            } else if (clickedWhiteKing == true) {
                clickedRectangleKing(selectedPane);
            } else {
                System.out.println("No Piece selected!");
            }

        }
    }
}

