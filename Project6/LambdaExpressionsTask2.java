import java.util.Arrays;
import java.util.Scanner;

@FunctionalInterface
interface ArrayProcessor {
    double apply( double[] array );
}

public class LambdaExpressionsTask2 {
    public static ArrayProcessor maximum = values -> {
        double max = values[0];
        for (int i = 0; i < values.length; i++) {
            if ( values[i] > max)
                max = values[i];
        }
        return max;
    };

    public static final ArrayProcessor minimum = values -> {
        double min = values[0];
        for (int i = 0; i < values.length; i++) {
            if ( values[i] < min)
                min = values[i];
        }
        return min;
    };

    public static final ArrayProcessor sum = values -> {
        double totalSum = 0;
        for (int i = 0; i < values.length; i++) {
            totalSum += values[i];
        }
        return totalSum;
    };

    public static final ArrayProcessor average =
            values -> {
                double sum = 0;
                for (int i = 0; i < values.length; i++) {
                    sum += values[i];
                }
                return sum/values.length;
            };

    public static ArrayProcessor counter( double value ) {
        return count -> {
            int index = 0;
            for (int i = 0; i < count.length; i++) {
                if ( count[i] == value )
                    index++;
            }
            return index;
        };
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double numberToSearch = 0;
        System.out.print("Enter double values to make the calculations: ");
        double[] values = Arrays.stream(input.nextLine()
                        .trim()
                        .split(" "))
                .filter(x -> !x.equals(""))
                .mapToDouble(Double::parseDouble)
                .toArray();

        System.out.println("Maximum value is: "
                + maximum.apply(values) );
        System.out.println("Minimum value is: "
                + minimum.apply(values) );
        System.out.println("Sum of all values is: "
                + sum.apply(values) );
        System.out.println("Average of the values prompted is: "
                + average.apply(values) );
        System.out.print("Enter a value to search and count: ");
        numberToSearch = input.nextDouble();
        System.out.println("\n" + numberToSearch + " is found "
                + (int)counter(numberToSearch).apply(values) + " times" );
    }
}

