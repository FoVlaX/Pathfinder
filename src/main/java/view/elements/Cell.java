package view.elements;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.elements.enums.ImageCell;
import view.eventhandlers.cellhandler.CellMouseEventHandler;

import java.util.ArrayList;
import java.util.List;


public class Cell extends ImageView {
    private Integer x;
    private Integer y;
    final private String imgFreeCellPath = "file:src/main/resources/freeCell.png";
    final private String imgAcceptCellPath = "file:src/main/resources/acceptPoint.png";
    final private String imgPointCellPath = "file:src/main/resources/pathPoint.png";
    final private String imgDeclineCellPath = "file:src/main/resources/declinePoint.png";
    final private String imgSelectedCellPath = "file:src/main/resources/selectCell.png";
    final private Integer squareSize = 40;

    private static List<Cell> cells;
    private static List<Cell> pathCells;


    private static Cell selectedCell;

    public Cell(Integer x, Integer y) {
        super();

        if (cells == null){
            cells = new ArrayList<Cell>();
        }

        cells.add(this);

        Image image = new Image(imgFreeCellPath);
        setImage(image);
        setFitHeight(squareSize);
        setFitWidth(squareSize);
        this.x = x;
        this.y = y;
        addEventHandler(MouseEvent.MOUSE_CLICKED, new CellMouseEventHandler());
    }



    public static List<Cell> getPathCells(){
        return pathCells;
    }

    public static void setPathCells(List<Cell> list){
        pathCells = list;
    }

    public static void setSelectedCell(Cell cell){
        selectedCell = cell;
    }

    public static Cell getSelectedCell(){
        return selectedCell;
    }

    public static void clearCells(){
        cells = new ArrayList<Cell>(); //create new ArrayList to destroy all cells because link on this cells will be lost
    }

    public static List<Cell> getCells(){
        return cells;
    }

    public Integer getMatrixX() {
        return this.x;
    }

    public Integer getMatrixY(){
        return this.y;
    }

    public void setStateForCell(ImageCell imageCell){

            Image image = new Image(imgAcceptCellPath);

            switch (imageCell){

                case freeCell: image = new Image(imgFreeCellPath); break;
                case pointPathCell: image = new Image(imgPointCellPath); break;
                case declineCell: image = new Image(imgDeclineCellPath); break;
                case selectedCell: image = new Image(imgSelectedCellPath); break;
            }

            setImage(image);

    }

}
