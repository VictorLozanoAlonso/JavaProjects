import java.lang.Math;
import java.text.DecimalFormat;

public class TriangleTask2 extends GeometricObject {
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    public TriangleTask2() {
        side1 = 1.0;
        side2 = 1.0;
        side3 = 1.0;
    }

    public TriangleTask2(double side1, double side2, double side3) throws TriangleException {
        if (side1 < (side2 + side3) && side2 < (side1 + side3) && side3 < (side1 + side2)) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        } else {
            throw new TriangleException("Illegal Triangle. \n " +
                    "   *****  A side cannot be larger than the sum of the other two ones  *****");
        }
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        return area;
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public String toString() {
        String description = "";
        DecimalFormat df = new DecimalFormat("###,###.##");
        description = "    The Triangle is " + getSide1() + " x " + getSide2() + " x " + getSide3() + " cm" +
                "\n    The Area is: " + df.format(getArea()) + " cm^2" +
                "\n    The Perimeter is: " + df.format(getPerimeter()) + " cm" +
                "\n    The color is: " + getColor() +
                "\n    Is Filled? : " + (getFilled() == true ? "Yes" : "No");
        return description;
    }
}
