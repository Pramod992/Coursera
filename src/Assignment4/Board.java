package Assignment4;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Board {

    private int N;
    private int[][] blocks;
    private Map<Integer, String> individualGoalPosition = new HashMap<>();

    /*
     construct a board from an N-by-N array of blocks
     (where blocks[i][j] = block in row i, column j)
      */
    public Board(int[][] blocks) {
        this.blocks = blocks;
        N = blocks.length;
        //find original position of each element
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                individualGoalPosition.put(j + i * dimension() + 1, i + "-" + j);
            }
        }
    }

    /*
     board dimension N
      */
    public int dimension() {
        return N;
    }

    /*
     number of blocks out of place
      */
    public int hamming() {
        int hamming = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (blocks[row][col] != col + row * dimension() + 1) {
                    hamming++;
                }
            }
        }
        return hamming - 1;
    }

    /*
     sum of Manhattan distances between blocks and goal
      */
    public int manhattan() {
        int manhattan = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (blocks[row][col] != col + row * dimension() + 1 && blocks[row][col] != 0) {
                    manhattan += calcIndividualMoves(row, col);
                }
            }
        }
        return manhattan;
    }

    private int calcIndividualMoves(int row, int col) {
        String originalPos = individualGoalPosition.get(blocks[row][col]);
        String[] originalRowCol = originalPos.split("-");
        int originalRow = Integer.parseInt(originalRowCol[0]);
        int originalCol = Integer.parseInt(originalRowCol[1]);

        //formula of Manhattan distance : Math.abs(x1-x0) + Math.abs(y1-y0);
        return Math.abs(originalRow - row) + Math.abs(originalCol - col);
    }

    /*
     is this board the goal board?
      */
    public boolean isGoal() {
        return manhattan() == 0;
    }

    /*
     a board that is obtained by exchanging any pair of blocks
      */
    public Board twin() {
        // copy 2D array
        int[][] copyBolcks = new int[dimension()][];
        for (int i = 0; i < N; i++) {
            copyBolcks[i] = blocks[i].clone();
        }

        if (blocks[0][0] * blocks[0][1] != 0) {
            //exchangeBlocks 0,0 <-> 0,1
            exchangeBlocks(copyBolcks, 0, 0, 0, 1);
        } else {
            //exchangeBlocks 1,0 <-> 1,1
            exchangeBlocks(copyBolcks, 1, 0, 1, 1);
        }
        return new Board(copyBolcks);
    }

    private void exchangeBlocks(int[][] copyBlocks, int i, int j, int x, int y) {
        int temp;
        temp = copyBlocks[i][j];
        copyBlocks[i][j] = copyBlocks[x][y];
        copyBlocks[x][y] = temp;
    }

    /*
     does this board equal y?
      */
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }

        if (this == y) {
            return true;
        }

        Board obj = (Board) y;
        return obj.dimension() == this.dimension() && Arrays.deepEquals(obj.blocks, this.blocks);
    }

    /*
     all neighboring boards
      */
    public Iterable<Board> neighbors() {

        return null;
    }


    /*
     string representation of this board (in the output format specified below)
      */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    /*
     unit tests (not graded)
      */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("src/Assignment4/puzzle3x3-08.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board board = new Board(blocks);
        System.out.println("Dimension: " + board.dimension());
        System.out.println("hamming: " + board.hamming());
        System.out.println("manhattan: " + board.manhattan());

        in = new In("src/Assignment4/puzzle4x4-35.txt");
        N = in.readInt();
        blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();

        Board board2 = new Board(blocks);
        System.out.println(board2.toString());

        System.out.println(board.equals(board));

        board = board.twin();
        System.out.println(board.toString());
    }
}
