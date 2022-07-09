import java.util.Random;
// Class to manage the rolling of the dices and print the result.
class Craps {
    private int diceOne;
    private int diceTwo;
    public Craps(){
        diceOne = 0;
        diceTwo = 0;
    }
    public int rollDice(){
        diceOne = new Random().nextInt(6) + 1;
        diceTwo = new Random().nextInt(6) + 1;
        System.out.println("You rolled " + diceOne + " + " + diceTwo + " = " + (diceOne + diceTwo));
        return diceOne + diceTwo;
    }
}
//Class contains the main function and the logic according to specifications requested in the task2.
class PlayCraps{
    public static void main(String[] args) {
        int result = 0;
        int newRoll = 0;
        Craps craps = new Craps();
        result = craps.rollDice();
        switch (result){
            case 2:
            case 3:
            case 12:
                System.out.println("Craps, Better Luck Next Time, You lose");
                break;
            case 7:
            case 11:
                System.out.println("Congratulations, You win");
                break;
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
                System.out.println("Point is (established) set to " + result);
                do{
                    newRoll = craps.rollDice();
                }while (newRoll != result && newRoll != 7);
                if (newRoll == result)
                    System.out.println("Congratulations, You win");
                else
                    System.out.println("Craps, Better Luck Next Time, You lose");
                break;
            default:
                break;
        }
    }
}