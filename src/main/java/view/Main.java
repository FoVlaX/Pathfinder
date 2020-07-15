package view;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import pathfinder.Pathfinder;
import view.elements.Ball;
import view.elements.Cell;
import view.eventhandlers.menubuttons.ClearBallsEventHandler;
import view.eventhandlers.menubuttons.GenerateBallsEventHandler;

import java.util.Random;


public class Main extends Application {


    final private String iconFileName = "file:src/main/resources/icon.png";
    final private Integer height = 500;
    final private Integer width = 600;


    private BorderPane borderPane;
    private GridPane gridPane;
    private Scene scene;
    private TilePane tilePane;

    private Text downText;
    private static Main main;

    public static Main getMain(){
        return main;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {

            main = this;
            borderPane = new BorderPane();
            scene = new Scene(borderPane, width, height);
            downText = new Text();
            downText.setText("Path not found");
            assembleTilePlane();

            assembleGridPlane();

            stage.setScene(scene);
            stage.setTitle("Pathfinder");
            stage.getIcons().add(new Image(iconFileName));

            Text text = new Text("Pathfinder");
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
            text.setTextAlignment(TextAlignment.CENTER);
            TilePane tpForTop = new TilePane();
            tpForTop.setOrientation(Orientation.VERTICAL);
            tpForTop.getChildren().add(text);
            tpForTop.setAlignment(Pos.CENTER);


            borderPane.setTop(tpForTop);
            stage.setMinWidth(width);
            stage.setMinHeight(height);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.setMaxWidth(width);
            stage.setMaxHeight(height);

            stage.show();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Text getDownText(){
        return this.downText;
    }

    public GridPane getGridPane() {
        return gridPane;
    }



    public void assembleGridPlane(){
        Ball.clearBall();
        Cell.clearCells();
        Pathfinder pathfinder = Pathfinder.getPathfinder();
        pathfinder.createMap();
        Integer [][] map = pathfinder.getMap();

        gridPane = new GridPane();

        for (int x = 0;x < map.length;x++){
            for (int y = 0;y < map[x].length;y++){
                if (map[x][y].equals(1)){
                    Ball ball = new Ball(x,y);
                    gridPane.add(ball,x,y);

                }else{
                    Cell cell = new Cell(x,y);
                    gridPane.add(cell,x,y);

                }
            }
        }

        TilePane tpForGrid = new TilePane();

        tpForGrid.setAlignment(Pos.TOP_CENTER);
        tpForGrid.getChildren().add(gridPane);


        borderPane.setCenter(tpForGrid);

    }

    private void assembleTilePlane(){
        tilePane = new TilePane();
        tilePane.setOrientation(Orientation.VERTICAL);
        tilePane.setTileAlignment(Pos.CENTER);
        tilePane.setPrefTileHeight(50);
        tilePane.setMinWidth(130);
        tilePane.setAlignment(Pos.TOP_CENTER);
        Button button = new Button("Generate balls");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new GenerateBallsEventHandler());
        tilePane.getChildren().add(button);

        Button buttonClear = new Button("Clear Selections");
        buttonClear.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClearBallsEventHandler());
        tilePane.getChildren().add(buttonClear);
        tilePane.getChildren().add(downText);

        borderPane.setLeft(tilePane);

    }

}
