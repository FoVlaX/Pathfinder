package view.eventhandlers.menubuttons;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pathfinder.Pathfinder;
import pathfinder.utilsclass.Coord;
import view.elements.Ball;
import view.elements.Cell;
import view.elements.enums.ImageCell;
import view.eventhandlers.gridplaneutils.PathSetterClass;

public class ClearBallsEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {

        try {
            Cell.getSelectedCell().setStateForCell(ImageCell.freeCell);
            Ball.getSelectedBall().setSelection(Boolean.FALSE);
        }catch (Exception ex){

        }

        PathSetterClass.clearPath();

        Pathfinder pathfinder = Pathfinder.getPathfinder();

        pathfinder.setCellCoord(null);
        pathfinder.setBallCoord(null);
        Cell.setPathCells(null);
    }
}
