package view.eventhandlers.ballhandler;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pathfinder.Pathfinder;
import pathfinder.utilsclass.Coord;
import view.Main;
import view.elements.Ball;
import view.elements.Cell;
import view.elements.enums.ImageCell;
import view.eventhandlers.gridplaneutils.PathSetterClass;

import java.util.List;


public class BallMouseEventHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        Ball selectedBall = Ball.getSelectedBall();

        if (selectedBall != null){
            selectedBall.setSelection(Boolean.FALSE);
        }

        Ball ball = (Ball)event.getSource();
        Ball.setSelectedBall(ball);

        ball.setSelection(Boolean.TRUE);

        Pathfinder pathfinder = Pathfinder.getPathfinder();

        pathfinder.setBallCoord(new Coord(ball.getMatrixX(), ball.getMatrixY()));

        PathSetterClass.clearPath();

        if (pathfinder.getCellCoord()!=null){
            List<Coord> coordList = pathfinder.calculateMinPath(Boolean.TRUE);
            if (coordList!=null){
                PathSetterClass.createPath(coordList);
                Cell.getSelectedCell().setStateForCell(ImageCell.acceptCell);
                Main.getMain().getDownText().setText("Path found!\nPath length: "+new Integer(coordList.size()).toString());
            }else{
                Cell.getSelectedCell().setStateForCell(ImageCell.declineCell);
            }
        }else{
            pathfinder.generatePathMap();
        }

    }
}
