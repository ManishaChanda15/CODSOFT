import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        //  System.out.println("Random number is " + randomNumber);
        int tryCount = 0;
        int n= 5;   // Given n trials
       for (randomNumber=0;randomNumber<n;randomNumber++){
            System.out.println("Enter your guess (1-100):");
            Scanner sc = new Scanner(System.in);
            int playerGuess = sc.nextInt();
            tryCount++;
            if (playerGuess == randomNumber) {
                System.out.println("Correct! You Win!");
                break;
            } else if (randomNumber > playerGuess && randomNumber!= n-1) {
                System.out.println("The number is too high than guess! Guess again!");
            } else if(randomNumber<playerGuess && randomNumber!= n-1){
                System.out.println("The number is too low than guess! Guess again!");
            }
        }
       if (randomNumber==n){
           System.out.println(" Trials over");
       }
    }
}