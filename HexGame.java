import java.util.ArrayList;
import java.lang.Math;

public class HexGame {
    public DisjointSet set;
    public boolean[] redSet;
    public boolean[] blueSet;


    public HexGame(int size) {
        set = new DisjointSet(size*size+4);
        redSet = new boolean[size*size+4];
        blueSet = new boolean[size*size+4];

    }

    public boolean playBlue(int position, boolean displayNeighbors) {
        int left_edge = redSet.length-1;
        int right_edge = redSet.length;
        blueSet[left_edge-1] = true;
        blueSet[right_edge-1] = true;

        if (displayNeighbors) {
            System.out.printf("Cell %d: ", position);
            System.out.println(getNeighborsBlue(position));
        }
        if (isOccupied(position)) {
            return false;
        }
        else {
            blueSet[position-1] = true;
            for (int i=0; i<getNeighborsBlue(position).size(); i++) {
                if (blueSet[getNeighborsBlue(position).get(i)-1]) {
                    set.union(position-1, getNeighborsBlue(position).get(i)-1);
                }
            }
        }
        return set.find(left_edge - 1) == set.find(right_edge - 1);
    }

    public boolean playRed(int position, boolean displayNeighbors) {
        int top_edge = redSet.length-3;
        int bottom_edge = redSet.length-2;
        redSet[top_edge-1] = true;
        redSet[bottom_edge-1] = true;

        if (displayNeighbors) {
            System.out.printf("Cell %d: ", position);
            System.out.println(getNeighborsRed(position));
        }
        if (isOccupied(position)) {
            return false;
        }
        else {
            redSet[position-1] = true;
            for (int i=0; i<getNeighborsRed(position).size(); i++) {
                if (redSet[getNeighborsRed(position).get(i)-1]) {
                    set.union(position-1, getNeighborsRed(position).get(i)-1);
                }
            }
        }
        return set.find(top_edge - 1) == set.find(bottom_edge - 1);

    }

    //recommended

    //public Color[] getGrid() {
//
    //}

    public int getSize() {
        return (int) Math.sqrt(redSet.length-4);
    }

    private boolean isOccupied(int position) {
        return redSet[position-1] || blueSet[position-1];
    }

    private ArrayList<Integer> getNeighborsRed(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int top_edge = redSet.length-3;
        int bottom_edge = redSet.length-2;
        int bottom_left = (int) (redSet.length-3-(Math.sqrt(redSet.length-4)));
        int top_right = (int) Math.sqrt(redSet.length-4);
        int top_left = 1;
        int bottom_right = redSet.length-4;
        int row = (int) Math.sqrt(redSet.length-4);

        if (position == top_left) {
            neighbors.add(position+1);
            neighbors.add(position+row);
            neighbors.add(top_edge);
        }
        else if (position == bottom_right) {
            neighbors.add(position-1);
            neighbors.add(position-row);
            neighbors.add(bottom_edge);
        }
        else if (position==top_right){
            neighbors.add(position-1);
            neighbors.add(position+(row-1));
            neighbors.add(position+row);
            neighbors.add(top_edge);
        }

        else if (position==bottom_left) {
            neighbors.add(position+1);
            neighbors.add(position-(row-1));
            neighbors.add(position-row);
            neighbors.add(bottom_edge);
        }
        else if (position<=top_right){
            neighbors.add(position-1);
            neighbors.add(position+1);
            neighbors.add(position+row);
            neighbors.add(position+(row-1));
            neighbors.add(top_edge);
        }
        else if (position <= bottom_right && position>=bottom_left) {
            neighbors.add(position-1);
            neighbors.add(position+1);
            neighbors.add(position-row);
            neighbors.add(position-(row-1));
            neighbors.add(bottom_edge);
        }
        else if (position%row==0) {
            neighbors.add(position-1);
            neighbors.add(position-row);
            neighbors.add(position+(row-1));
            neighbors.add(position+row);
        }
        else if (position%row==1) {
            neighbors.add(position+1);
            neighbors.add(position+row);
            neighbors.add(position-(row-1));
            neighbors.add(position-row);
        }
        else {
            neighbors.add(position+1);
            neighbors.add(position-1);
            neighbors.add(position+(row-1));
            neighbors.add(position-(row-1));
            neighbors.add(position+row);
            neighbors.add(position-row);
        }
        return neighbors;
    }

    private ArrayList<Integer> getNeighborsBlue(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int left_edge = redSet.length-1;
        int right_edge = redSet.length;
        int bottom_left = (int) (redSet.length-3-(Math.sqrt(redSet.length-4)));
        int top_right = (int) Math.sqrt(redSet.length-4);
        int top_left = 1;
        int bottom_right = redSet.length-4;
        int row = (int) Math.sqrt(redSet.length-4);


        if (position == top_left) {
            neighbors.add(position+1);
            neighbors.add(position+row);
            neighbors.add(left_edge);
        }
        else if (position == bottom_right) {
            neighbors.add(position-1);
            neighbors.add(position-row);
            neighbors.add(right_edge);
        }
        else if (position==top_right){
            neighbors.add(position-1);
            neighbors.add(position+(row-1));
            neighbors.add(position+row);
            neighbors.add(right_edge);
        }

        else if (position==bottom_left) {
            neighbors.add(position+1);
            neighbors.add(position-(row-1));
            neighbors.add(position-row);
            neighbors.add(left_edge);
        }
        else if (position<=row){
            neighbors.add(position-1);
            neighbors.add(position+1);
            neighbors.add(position+row);
            neighbors.add(position+(row-1));

        }
        else if (position <= bottom_right && position>=bottom_left) {
            neighbors.add(position-1);
            neighbors.add(position+1);
            neighbors.add(position-row);
            neighbors.add(position-(row-1));

        }
        else if (position%row==0) {
            neighbors.add(position-1);
            neighbors.add(position-row);
            neighbors.add(position+(row-1));
            neighbors.add(position+row);
            neighbors.add(right_edge);
        }
        else if (position%row==1) {
            neighbors.add(position+1);
            neighbors.add(position-(row-1));
            neighbors.add(position-row);
            neighbors.add(position+row);
            neighbors.add(left_edge);
        }
        else {
            neighbors.add(position+1);
            neighbors.add(position-1);
            neighbors.add(position+(row-1));
            neighbors.add(position-(row-1));
            neighbors.add(position+row);
            neighbors.add(position-row);
        }
        return neighbors;
    }

}
