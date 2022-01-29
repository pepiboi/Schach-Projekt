package controller;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

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

    /*private void movePawn(Node selectedPane) {
        System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
    }*/

    private void clickedRectanglePawn(Node selectedPane){
        System.out.println(GridPane.getColumnIndex(selectedPane) + " " + GridPane.getRowIndex(selectedPane));
        
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Node selectedPane = (Node) mouseEvent.getSource();
        if (selectedPane.getId().equals("whitePawnZero") || selectedPane.getId().equals("whitePawnOne") || selectedPane.getId().equals("whitePawnTwo") || selectedPane.getId().equals("whitePawnThree") || selectedPane.getId().equals("whitePawnFour") || selectedPane.getId().equals("whitePawnFive") || selectedPane.getId().equals("whitePawnSix") || selectedPane.getId().equals("whitePawnSeven")) {
            clickedWhitePawn = true;
            //movePawn(selectedPane);
        }else if (selectedPane instanceof Rectangle) {
            if (clickedWhitePawn == true) {
                clickedRectanglePawn(selectedPane);
            }else{
                System.out.println("No Piece selected!");
            }
        }
    }
}

