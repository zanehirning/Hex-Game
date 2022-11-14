import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment6Driver {
    public static void main(String[] args) {

        testGame();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        File file = new File(filename);
        ArrayList<Integer> moves = new ArrayList<>();
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                moves.add(input.nextInt());
            }

            // TODO: Write some good stuff here
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
        HexGame game = new HexGame(11);
        for (int i=0; i<moves.size(); i++) {
            if (i % 2==0) {
                if (game.playBlue(moves.get(i), false)) {
                    System.out.printf("Blue wins with move at position %d!\n", moves.get(i));
                    printGrid(game);
                    break;
                }
            }
            else {
                if (game.playRed(moves.get(i), false)) {
                    System.out.printf("Red wins with move at position %d!\n", moves.get(i));
                    printGrid(game);
                    break;
                }
            }
        }
    }

    //
    // TODO: You can use this to compare with the output show in the assignment while working on your code
    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        printGrid(game);
    }


    private static void printGrid(HexGame game) {

        StringBuilder grid = new StringBuilder();
        grid.append(" ");
        int row = 0;
        for (int i=0; i<game.getSize()*game.getSize(); i++) {
            if (game.redSet[i]) {
                grid.append("R");
            }
            else if (game.blueSet[i]) {
                grid.append("B");
            }
            else {
                grid.append("0");
            }
            if (i>=1) {
                if (i % game.getSize()==10) {
                    grid.append("\n");
                    row += 1;
                    for (int j=0; j<row; j++) {
                        grid.append(" ");
                    }
                }
            }
            grid.append(" ");
        }
        System.out.println(grid);
    }

}
