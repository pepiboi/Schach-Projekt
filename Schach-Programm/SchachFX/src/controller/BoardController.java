package controller;

import javafx.scene.Node;
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
    public String pieceKoordinates;
    public Node pawnPane;
    /*private void movePawn(Node selectedPane) {
        System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
    }*/

    private void clickedRectanglePawn(Node selectedPane) {
        if (GridPane.getRowIndex(selectedPane) != null && GridPane.getColumnIndex(selectedPane) == GridPane.getColumnIndex(pawnPane)) {
            System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
            if ((GridPane.getRowIndex(selectedPane)+1) == GridPane.getRowIndex(pawnPane)) {
                System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                GridPane.setRowIndex(pawnPane, GridPane.getRowIndex(selectedPane));
                GridPane.setColumnIndex(pawnPane, GridPane.getColumnIndex(pawnPane));
            }
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
                pieceKoordinates = GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane);
                System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
                //movePawn(selectedPane);
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

