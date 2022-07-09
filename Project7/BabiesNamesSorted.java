import java.util.Scanner;

public class BabiesNamesSorted {
    public static void main(String[] args) {
        int year = 0;
        boolean valid = false;
        System.out.print("Enter the year of the file name for baby name ranking: ");
        Scanner input = new Scanner(System.in);
            do {
                if (input.hasNextInt()) {
                    year = input.nextInt();
                    if(year >= 2009 && year <= 2018)
                        valid = true;
                    else {
                        System.out.print("ERROR. Please enter a year between 2009 to 2018: >> ");
                        input.nextLine();
                    }
                }
                else {
                    System.out.print("ERROR. Please, enter a integer number: >> ");
                    input.nextLine();
                }
            }while(!valid);
        BabiesNamesData bNames = new BabiesNamesData(Integer.toString(year));
        bNames.removeDuplicates();
        System.out.println("\n--------------------------");
        System.out.println("Boy Names for " + year + " are: ");
        System.out.println("--------------------------");
        bNames.printNames(bNames.getBoyNames());
        System.out.println("\n\n--------------------------");
        System.out.println("Girl Names for " + year + " are: ");
        System.out.println("--------------------------");
        bNames.printNames(bNames.getGirlNames());
        System.out.println();
    }
}
