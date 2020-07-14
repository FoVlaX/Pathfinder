package pathfinder.utilsclass;

public class Coord {

    public Integer x;
    public Integer y;

    public Coord(){

    }

    public Coord(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Coord(Coord coord){
        this.x = coord.x;
        this.y = coord.y;
    }
}
