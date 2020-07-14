package view.eventhandlers.menubuttons;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pathfinder.Pathfinder;
import view.Main;
import view.elements.Cell;

public class GenerateBallsEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {
        Pathfinder pathfinder = Pathfinder.getPathfinder();

        pathfinder.setCellCoord(null);
        pathfinder.setBallCoord(null);
        Cell.setPathCells(null);
        Main.getMain().assembleGridPlane();
    }
}
