package bingo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Methods {

    private static ArrayList<Integer> markedNumbers = new ArrayList();
    private static boolean bingo = false;

    public static void ShowCard(String[][] card) {
        System.out.println("B\tI\tN\tG\tO");
        System.out.print("----------------------------------");
        for (byte i = 0; i < card.length; i++) {
            System.out.println();

            for (byte j = 0; j < card[i].length; j++) {
                if ((i == 5 && j == 0) || (i == 10 & j == 0)) {
                    System.out.println("----------------------------------");
                }
                if (j == 5) {
                    System.out.println("");
                }
                System.out.printf("%s\t", card[i][j]);
            }

        }
        System.out.println();
    }

    public static void MarkNumber(int number, String[][] card)
            throws InterruptedException {

        for (byte i = 0; i < card.length; i++) {
            for (byte j = 0; j < card[i].length; j++) {
                if (card[i][j].equals(String.valueOf(number))) {
                    card[i][j] = "X";
                }
            }
        }
    }

    public static boolean Bingo(String[][] card, int number) throws InterruptedException {
        for (byte i = 0; i < card.length; i++) {
            int yCounter = 0, xCounter = 0;
            for (byte j = 0; j < card[i].length; j++) {
                
                //Horizontal.
                if (card[j][i].equals("X")) {
                    xCounter++;

                    if (!markedNumbers.contains(number)) {
                        markedNumbers.add(number);
                    }
                }
                
                //Vertical
                if (card[i][j].equals("X")) {
                    yCounter++;
                    if (!markedNumbers.contains(number)) {
                        markedNumbers.add(number);
                    }
                }

                if (xCounter == 5 || yCounter == 5) {
                    bingo = true;
                    System.out.println("BINGO!!");
                    System.out.println("WE PROCEED TO CHECK BINGO: ");
                    TimeUnit.SECONDS.sleep(5);
                }
            }
        }
        return bingo;
    }

    public static String[][] CopyArray(String[][] arrayA) {
        String[][] arrayB = new String[arrayA.length][];
        
        for (byte i = 0; i < arrayA.length; i++) {
            arrayB[i] = new String[arrayA[i].length];
            
            for (byte j = 0; j < arrayA[i].length; j++) {
                arrayB[i][j] = arrayA[i][j];

            }
        }
        return arrayB;

    }

    public static String[][] GenerateCard(String[][] card) {
        int numberUsed, auxiliar = 1;
        ArrayList<Integer> usedNumbers = new ArrayList();

        for (int j = 0; j < card.length; j++) {
            for (int i = 0; i < card.length; i++) {

                numberUsed = (int) ((Math.random() * 15) + auxiliar);
                while (usedNumbers.contains(numberUsed)) {
                    numberUsed = (int) ((Math.random() * 15) + auxiliar);
                }

                usedNumbers.add(numberUsed);

                card[i][j] = String.valueOf(numberUsed);
            }
            usedNumbers.clear();
            auxiliar += 15;

        }
        card[2][2] = String.valueOf("X");
        return card;
    }
}
