package view.eventhandlers.gridplaneutils;

import javafx.scene.layout.GridPane;
import pathfinder.Pathfinder;
import pathfinder.utilsclass.Coord;
import view.Main;
import view.elements.Ball;
import view.elements.Cell;
import view.elements.enums.ImageCell;

import java.util.ArrayList;
import java.util.List;

public class PathSetterClass {
    public static void clearPath(){

        List<Cell> pathCells = Cell.getPathCells();
        if (pathCells!=null) {
            for (Cell cell : pathCells) {
                cell.setStateForCell(ImageCell.freeCell);
            }
            Cell.setPathCells(null);
        }
        Main.getMain().getDownText().setText("Path not found");
    }
    public static void createPath(List<Coord> coordList){
        try {
            Object [][]objects = Main.getMain().getObjects();

            Integer height = Pathfinder.getPathfinder().getHeight();

            List<Cell> pathCells = new ArrayList<Cell>();

            for (Coord c : coordList) {

                try {
                    Cell cell = (Cell) objects[c.x][c.y];

                    cell.setStateForCell(ImageCell.pointPathCell);
                    pathCells.add(cell);
                }catch(Exception ex){
                    Ball ball = (Ball) objects[c.x][c.y];
                    ball.setSelection(Boolean.TRUE);
                }

            }

            Cell.setPathCells(pathCells);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
