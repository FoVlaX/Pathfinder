package pathfinder;


import pathfinder.utilsclass.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//singleton pathfinder
public class Pathfinder {


    private static Pathfinder pathfinder;

    private final Integer height = 10;
    private final Integer width = 10;

    private Coord ballCoord;
    private Coord cellCoord;


    private Integer [][] map;

    private Integer [][] helpMap;

    private Pathfinder(){
        createMap();
    }

    public Integer getHeight() {
        return height;
    }

    public Integer[][] getMap(){
        return map;
    }

    private void createHelpMap(){
        helpMap = new Integer[height][width];
        for (int x = 0;x < height;x++){
            for (int y =0; y < width;y++){
                helpMap[x][y] = new Integer(0);
            }
        }
    }

    public List<Coord> calculateMinPath(Boolean reloadPathMap){
        if (reloadPathMap.equals(Boolean.TRUE)){
            generatePathMap();
        }
        return repairPathFromPathMap();
    }

    private List<Coord> repairPathFromPathMap(){
        if (helpMap[cellCoord.x][cellCoord.y] > 0) {

            List<Coord> coordList = new ArrayList<Coord>();

            Coord currentCoord = new Coord(cellCoord);

            while(helpMap[currentCoord.x][currentCoord.y]!=0){
                coordList.add(new Coord(currentCoord));
                Integer value = helpMap[currentCoord.x][currentCoord.y];

                if (currentCoord.x+1 < height){
                    if (value.equals(helpMap[currentCoord.x+1][currentCoord.y] + 1)){
                        currentCoord.x++;
                        continue;
                    }
                }

                if (currentCoord.x - 1 >= 0){
                    if (value.equals(helpMap[currentCoord.x-1][currentCoord.y] + 1)){
                        currentCoord.x--;
                        continue;
                    }
                }

                if (currentCoord.y +1 < width){
                    if (value.equals(helpMap[currentCoord.x][currentCoord.y+1] + 1)){
                        currentCoord.y++;
                        continue;
                    }
                }

                if (currentCoord.y-1 >=0){
                    if (value.equals(helpMap[currentCoord.x][currentCoord.y - 1] + 1)){
                        currentCoord.y--;
                        continue;
                    }
                }
            }



            return coordList;
        }
        return null;
    }


    public void generatePathMap(){
        createHelpMap();
        generateMinPathMap(ballCoord);

    }

    private void generateMinPathMap(Coord pos){


        Integer value = helpMap[pos.x][pos.y];
        if (pos.x + 1 < height) {

            pos.x++;
            goToNextStep(pos, value);
            pos.x--;
        }

        if (pos.y + 1 < width) {

            pos.y++;
            goToNextStep(pos, value);
            pos.y--;
        }

        if (pos.x - 1 >= 0) {

            pos.x--;
            goToNextStep(pos, value);
            pos.x++;
        }

        if (pos.y - 1 >= 0) {

            pos.y--;
            goToNextStep(pos, value);
            pos.y++;
        }
    }



    private void goToNextStep(Coord pos, Integer value){
        if (map[pos.x][pos.y].equals(0) && (helpMap[pos.x][pos.y].equals(0) || value+1 < helpMap[pos.x][pos.y])){
            helpMap[pos.x][pos.y] = value+1;
            generateMinPathMap(pos);
        }
    }

    public void createMap(){
        map = new Integer[height][width];
        for (int x =0;x<height;x++){
            for (int y =0;y<width;y++){
                map[x][y] = new Integer(Math.abs(new Random().nextInt()%2));
            }
        }
    }

    public void setBallCoord(Coord ballCoord){
        this.ballCoord = ballCoord;
    }

    public void setCellCoord(Coord cellCoord){
        this.cellCoord = cellCoord;
    }

    public Coord getCellCoord(){
        return this.cellCoord;
    }

    public Coord getBallCoord(){
        return this.ballCoord;
    }

    public static Pathfinder getPathfinder(){
        if (pathfinder == null){
            pathfinder = new Pathfinder();
        }
        return pathfinder;
    }
}
