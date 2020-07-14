package view.eventhandlers.cellhandler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pathfinder.Pathfinder;
import pathfinder.utilsclass.Coord;
import view.Main;
import view.elements.Cell;
import view.elements.enums.ImageCell;
import view.eventhandlers.gridplaneutils.PathSetterClass;

import java.util.List;


public class CellMouseEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {
        Cell selectedCell = Cell.getSelectedCell();

        if (selectedCell!=null){
            selectedCell.setStateForCell(ImageCell.freeCell);
        }

        Cell cell = (Cell)mouseEvent.getSource();
        Cell.setSelectedCell(cell);


        Pathfinder pathfinder = Pathfinder.getPathfinder();

        pathfinder.setCellCoord(new Coord(cell.getMatrixX(),cell.getMatrixY()));


        PathSetterClass.clearPath(); //clear oldPath

        if (pathfinder.getBallCoord()==null){
            cell.setStateForCell(ImageCell.selectedCell);
        }else{
            List<Coord> coordList = pathfinder.calculateMinPath(Boolean.FALSE);
            if (coordList == null){
                cell.setStateForCell(ImageCell.declineCell);
            }else{
                PathSetterClass.createPath(coordList);
                cell.setStateForCell(ImageCell.acceptCell);
                Main.getMain().getDownText().setText("Path found!\nPath length: "+new Integer(coordList.size()).toString());
            }
        }

    }
}
