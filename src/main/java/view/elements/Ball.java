package view.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.eventhandlers.ballhandler.BallMouseEventHandler;

import java.util.ArrayList;
import java.util.List;


public class Ball extends ImageView {

    private Integer x;
    private Integer y;
    final private String imgCirclePath = "file:src/main/resources/circle.png";
    final private String imgCircleSelectedPath = "file:src/main/resources/circleSelected.png";
    final private Integer squareSize = 40;

    private static Ball selectedBall;

    private static List<Ball> balls;

    public Ball(Integer x, Integer y) {
        super();

        if (balls == null){
            balls = new ArrayList<Ball>();
        }

        balls.add(this);

        Image image = new Image(imgCirclePath);
        setImage(image);
        setFitHeight(squareSize);
        setFitWidth(squareSize);
        this.x = x;
        this.y = y;
        addEventHandler(MouseEvent.MOUSE_CLICKED, new BallMouseEventHandler());

    }

    public static void setSelectedBall(Ball ball){
        selectedBall = ball;
    }

    public static Ball getSelectedBall(){
        return selectedBall;
    }

    public static void clearBall(){
        balls = new ArrayList<Ball>(); //create new ArrayList to destroy all balls because link on this balls will be lost
    }

    public static List<Ball> getBalls(){
        return balls;
    }

    public void setSelection(Boolean bool){
        Image image;
        if (bool.equals(Boolean.TRUE)){
            image = new Image(imgCircleSelectedPath);
        }else{
            image = new Image(imgCirclePath);
        }
        setImage(image);
    }

    public Integer getMatrixX() {
        return this.x;
    }

    public Integer getMatrixY(){
        return this.y;
    }
}
