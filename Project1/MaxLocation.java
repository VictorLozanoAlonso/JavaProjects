// System libray
import java.util.Scanner;
// Class to find the maximum value of 2D-array. Also, it saved the value and the coordinates of the value has in
// the array passed to the method "largestElement".
class MaxLocation {
    private int row;
    private int column;
    private double maxValue;
    public MaxLocation() {
        row = 0;
        column = 0;
        maxValue = 0.0;
    }
    public MaxLocation(int rows, int columns, double _maxValue) {
        row = rows;
        column = columns;
        maxValue = _maxValue;
    }
    public MaxLocation largestElement(double[][] arrayValues) {
        for(int i = 0; i < arrayValues.length; i++) {
            for (int j = 0; j < arrayValues[i].length; j++) {
                if(maxValue < arrayValues[i][j]) {
                    maxValue = arrayValues[i][j];
                    row = i;
                    column = j;
                }
            }
        }
        return new MaxLocation(row, column, maxValue);
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public double getMaxValue() {
        return maxValue;
    }
}

// Class that contains the main function. Also, it has the logic to create and saved the 2D-Array.
// Finally, it prints the largest value of the array and the coordinates of it is saved.
class FindMaximum{
    public static void main(String[] args) {
        Scanner dataIn = new Scanner(System.in);
        System.out.print("Enter the number of rows and columns in the array: ");
        int row = dataIn.nextInt();
        int column = dataIn.nextInt();
        double[][] arrayContainer = new double[row][column];
        System.out.println("\nEnter the array:");
        for (int i = 0; i < arrayContainer.length; i++) {
            for (int j = 0; j < arrayContainer[i].length; j++) {
                arrayContainer[i][j] = dataIn.nextDouble();
            }
        }
        MaxLocation maxLocationArray = new MaxLocation();
        maxLocationArray.largestElement(arrayContainer);
        System.out.println("The location of the largest element is " + maxLocationArray.getMaxValue() + " at (" + maxLocationArray.getRow() + ", " + maxLocationArray.getColumn() + ")");
    }
}