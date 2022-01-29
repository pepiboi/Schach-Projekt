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
    public Node pawnPane;
    public ListView whiteMovesID;
    public ListView blackMovesID;
    /*private void movePawn(Node selectedPane) {
        System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
    }*/

    private void clickedRectanglePawn(Node selectedPane) {
        try {
            if (GridPane.getRowIndex(selectedPane) != null && GridPane.getColumnIndex(selectedPane) == GridPane.getColumnIndex(pawnPane)) {

                if (GridPane.getRowIndex(selectedPane) + 2 == 6 || GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane)) {
                    whiteMovesID.getItems().add(pawnPane.getId() + " from: " + GridPane.getColumnIndex(pawnPane) + "|" + GridPane.getRowIndex(pawnPane));
                    whiteMovesID.getItems().add(pawnPane.getId() + " to: " + GridPane.getColumnIndex(selectedPane) + "|" + GridPane.getRowIndex(selectedPane));
                    System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                    GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                    GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(pawnPane));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown at moving Pawn");
        }
    }

    private void clickedBlackPawn(Node selectedPane) {
        try {
            if (GridPane.getColumnIndex(pawnPane) == null && GridPane.getRowIndex(selectedPane) != null && GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane) && GridPane.getColumnIndex(selectedPane) - 1 == 0) {
                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
                        selectedPane.setVisible(false);
                        GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                        GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(selectedPane));
                    }
                }
            } else if (GridPane.getColumnIndex(pawnPane) == 1 && GridPane.getRowIndex(selectedPane) != null && GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane) && GridPane.getColumnIndex(selectedPane) == null) {

                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
                        selectedPane.setVisible(false);
                        GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                        GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(selectedPane));
                    }
                }
            } else if (GridPane.getRowIndex(selectedPane) != null && GridPane.getRowIndex(selectedPane) + 1 == GridPane.getRowIndex(pawnPane) && (GridPane.getColumnIndex(selectedPane) + 1 == GridPane.getColumnIndex(pawnPane) || GridPane.getColumnIndex(selectedPane) - 1 == GridPane.getColumnIndex(pawnPane))) {

                if (selectedPane.toString().contains("ImageView")) {
                    if (selectedPane.getId().contains("black")) {
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

    public void onMouseClick(MouseEvent mouseEvent) {
        Node selectedPane = (Node) mouseEvent.getSource();
        //System.out.println(selectedPane.toString());
        if (selectedPane.toString().contains("ImageView")) {
            if (selectedPane.getId().contains("whitePawn")) {
                //System.out.println("WhitePawn");
                clickedWhitePawn = true;
                pawnPane = selectedPane;
            } else if (selectedPane.getId().contains("black")) {
                if (clickedWhitePawn == true) {
                    clickedBlackPawn(selectedPane);
                } else {
                    System.out.println("No Piece selected!");
                }
            }
        } else if (selectedPane.toString().contains("Rectangle")) {

            if (clickedWhitePawn == true) {
                clickedRectanglePawn(selectedPane);
            } else {
                System.out.println("No Piece selected!");
            }

        }
    }
}

