package controller;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class BoardController {
    public GridPane boardId;
    public ImageView onClickExit;

    public void onMouseClickedExit(MouseEvent mouseEvent) {
        System.out.println("Programm wird geschlossen");
        System.exit(1);
    }
}
