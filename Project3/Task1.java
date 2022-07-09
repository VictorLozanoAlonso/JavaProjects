import java.util.Scanner;

// Unit test for task-1. Prompt the sides with input validation. Then Triangle info is showed.
// Area and Perimeter are calculated
public class Task1 {
    public static void main(String[] args) {
        double s1 = 0.0, s2 = 0.0, s3 = 0.0;
        boolean valid = true;
        Scanner sides = new Scanner(System.in);
        //Input sides validation
        do {
            valid = true;
            System.out.print("Prompt the Triangle Sides(cm): ");
            if (sides.hasNextDouble())
                s1 = sides.nextDouble();
            else {
                System.out.println("   **** Invalid Side 1 Input. Try again ****");
                valid = false;
                sides.next();
            }
            if (sides.hasNextDouble())
                s2 = sides.nextDouble();
            else {
                System.out.println("   **** Invalid Side 2 Input. Try again ****");
                valid = false;
                sides.next();
            }
            if (sides.hasNextDouble())
                s3 = sides.nextDouble();
            else {
                System.out.println("   **** Invalid Side 3 Input. Try again ****");
                valid = false;
                sides.next();
            }
        } while (!valid);
        Triangle t1 = new Triangle(s1, s2, s3);
        System.out.print("Prompt the color: ");
        Scanner appearance = new Scanner(System.in);
        t1.setColor(appearance.nextLine());
        //Boolean input validation
        do {
            valid = false;
            System.out.print("The Triangle is filled(true or false): ");
            if (appearance.hasNextBoolean()) {
                t1.setFilled(appearance.nextBoolean());
                valid = true;
            } else {
                System.out.println("Invalid Input. Try Again");
                appearance.next();
            }
        } while (!valid);
        System.out.println("\n    --------------------------------");
        System.out.print(t1.toString());
        System.out.println("\n    --------------------------------");
    }
}