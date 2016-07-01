package Assignment4;


public class Solver {

    private Board initial;

    /*
    find a solution to the initial board (using A* algorithm)
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        this.initial = initial;

        while (initial.manhattan()!=0){
            //...
        }

    }

    /*
    is the initial board solvable
     */
    public boolean isSolvable() {
        if (initial.hamming() == 0) {
            return false;
        }
        return true;
    }

    /*
    min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return 0;
    }

    /*
    sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return null;
    }


    public static void main(String[] args) {

    }
}
