package bingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class AmericanBingo {

    public static void main(String[] args) throws InterruptedException {

        String[][] card1 = new String[5][5];
        String[][] card2 = new String[5][5];
        String[][] card3 = new String[5][5];
        String[][] card1Copy = new String[5][5];
        String[][] card2Copy = new String[5][5];
        String[][] card3Copy = new String[5][5];

        ArrayList<Integer> saidNumbers = new ArrayList<Integer>();

        int nSaid, lineCounter = 0, cardWinner = 0;
        boolean bingo = false;

        Methods.GenerateCard(card1);
        Methods.GenerateCard(card2);
        Methods.GenerateCard(card3);

        card1Copy = Methods.CopyArray(card1);
        card2Copy = Methods.CopyArray(card2);
        card3Copy = Methods.CopyArray(card3);

        System.out.println("\nSTART!!");
        do {
            nSaid = (int) ((Math.random() * 75) + 1);
            while (saidNumbers.contains(nSaid)) {
                nSaid = (int) ((Math.random() * 75) + 1);
            }

            saidNumbers.add(nSaid);

            System.out.println("\n" + nSaid + "!");

            if (Methods.Bingo(card1, nSaid) == false) {
                Methods.MarkNumber(nSaid, card1);

            } else {
                cardWinner = 1;
                break;
            }
            
            if (Methods.Bingo(card2, nSaid) == false) {
                Methods.MarkNumber(nSaid, card2);
            } else {
                cardWinner = 2;
                break;
            }

            if (Methods.Bingo(card3, nSaid) == false) {
                Methods.MarkNumber(nSaid, card3);
            } else {
                cardWinner = 3;
                break;
            }

            System.out.println();
            Methods.ShowCard(card1);
            Methods.ShowCard(card2);
            Methods.ShowCard(card3);
            
            
            TimeUnit.SECONDS.sleep(3);
        }
        while (!bingo);

        Collections.sort(saidNumbers);

        System.out.println(
                "\nSAID NUMBERS: ");

        for (byte i = 0; i < saidNumbers.size(); i++) {
            if (lineCounter == 10) {
                lineCounter = 0;
                System.out.println();
            } else {
                lineCounter++;
                System.out.printf("%d ", saidNumbers.get(i));
            }
        }
        
        System.out.println("\n\nWINNER CARD: \n");
        switch (cardWinner) {
            case 1:
                System.out.println("CARD 1");
                Methods.ShowCard(card1Copy);
                Methods.ShowCard(card1);
                break;

            case 2:
                System.out.println("CARD 2");
                Methods.ShowCard(card2Copy);
                Methods.ShowCard(card2);
                break;

            case 3:
                System.out.println("CARD 3");
                Methods.ShowCard(card3Copy);
                Methods.ShowCard(card3);
                break;

            default:
                Methods.ShowCard(card1);
                Methods.ShowCard(card2);
                Methods.ShowCard(card3);
                break;
        }
    }
}
