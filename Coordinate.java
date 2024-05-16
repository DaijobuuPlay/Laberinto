
public class Coordinate {


    public int i;
    public int j;
    public int direction;

    public Coordinate() {

    }

    public Coordinate(int i, int j, int direction) {
        this.i = i;
        this.j = j;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o){

        if(o == null){
            return false;
        }

        if(o.getClass() != this.getClass() ){
            return false;
        }

        if(this == o){
            return true;
        }
        
        Coordinate c = (Coordinate) o;

        if(this.i == c.i && this.j == c.j){
            return true;
        }else{
            return false;
        }
    }
}
