// Unit test for task 2. Two triangles are created. The first one has sides meeting the rule
// of each side separately always is lower than the sum of the other ones. The Triangle 2 violates the rule.
// Therefore, an exception will be through.
public class Task2 {
    public static void main(String[] args) {
        try {
            System.out.println("Creating Triangle 1 with sides 12.5 x 12.5 x 12.5cm");
            TriangleTask2 t1 = new TriangleTask2(12.5, 12.5, 12.5);
            t1.setColor("Red");
            t1.setFilled(true);
            System.out.println("\nTriangle 1 was created");
            System.out.println("    --------------------------------");
            System.out.println(t1.toString());
            System.out.println("    --------------------------------\n");
            System.out.println("Creating Triangle 2 with sides 12.5 x 31.5 x 12.5cm");
            TriangleTask2 t2 = new TriangleTask2(12.5, 31.5, 12.5);
            t1.setColor("Black");
            t1.setFilled(false);
            System.out.println("Triangle 2 was created");
            System.out.println("\n    --------------------------------");
            System.out.println(t2.toString());
            System.out.println("\n    --------------------------------\n");
        } catch (Exception e) {
            System.out.println("\n    " + e);
        }
    }
}
