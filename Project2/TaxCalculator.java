import java.util.Scanner;

public class TaxCalculator {
    public static void main(String[] args) {
        double[] rates01 = {15, 27.5, 30.5, 35.5, 39.1};
        double[] rates09 = {10, 15, 25, 28, 33, 35};
        int[][] intervals01 = {
                {27050, 65550, 136750, 297350},
                {45200, 109250, 166500, 297350},
                {22600, 54625, 83250, 148675},
                {36250, 93650, 151650, 297350}
        };
        int[][] intervals09 = {
                {8350, 33950, 82250, 171550, 372950},
                {16700, 67900, 137050, 208850, 372950},
                {8350, 33950, 68525, 104425, 186475},
                {11950, 45500, 117450, 190200, 372950}
        };
        int option = -1;
        do {
            System.out.println("1 - Compute personal income Tax");
            System.out.println("2 - Print the tax tables for taxable incomes (with range)");
            System.out.println("0 - Exit");
            Scanner dataIn = new Scanner(System.in);
            System.out.print("\nEnter the option: ");
            //Input validation. Only valid range is between 0 -3
            if (dataIn.hasNextDouble())
                option = dataIn.nextInt();
            else
                option = -1;
            switch (option) {
                case 1:
                    int filing = -1;
                    double income = 0.0;
                    System.out.println("\n0 - single filer");
                    System.out.println("1 - married jointly or qualifying widow(er)");
                    System.out.println("2 - married separately");
                    System.out.println("3 - head of household");
                    Scanner caseOne = new Scanner(System.in);
                    System.out.print("\nEnter the filing status: ");
                    //No number validation
                    if (caseOne.hasNextInt()) {
                        filing = caseOne.nextInt();
                        // Number in range validation
                        if (filing >= 0 && filing <= 3) {
                            System.out.print("\nEnter the Taxable Income: $");
                            //No number validation
                            if (caseOne.hasNextDouble()) {
                                income = caseOne.nextDouble();
                                IncomeTax tax09 = new IncomeTax(filing, intervals09, rates09, income);
                                System.out.println("\nTax is: $" + tax09.getIncomeTax() + "\n");
                            } else {
                                System.out.println("\n **** Invalid input. Try Again ****\n");
                            }
                        } else {
                            System.out.println("\n **** Invalid input. Try Again ****\n");
                        }
                    } else {
                        System.out.println("\n **** Invalid input. Try Again ****\n");
                    }
                    break;
                case 2:
                    double from, to;
                    Scanner caseTwo = new Scanner(System.in);
                    System.out.print("\nEnter the amount From: $");
                    //No number validation
                    if (caseTwo.hasNextDouble()) {
                        from = caseTwo.nextDouble();
                        System.out.print("\nEnter the amount To: $");
                        //No number validation
                        if (caseTwo.hasNextDouble()) {
                            to = caseTwo.nextDouble();
                            System.out.print("\n2001 tax tables for taxable income from $");
                            System.out.printf("%.0f to $%.0f", from, to);
                            System.out.println(
                                    "\n---------------------------------------------------------------------\n" +
                                            "Taxable       Single      Married Joint       Married        Head of\n" +
                                            "Income                    or Qualifying       Separate       a House\n" +
                                            "                          Widow(er)\n" +
                                            "---------------------------------------------------------------------");
                            double range = to - from;
                            double incomeBand = from;
                            IncomeTax tax01 = new IncomeTax();
                            tax01.setIntervals(intervals01);
                            tax01.setRates(rates01);
                            for (int i = 0; i <= range / 1000; i++) {
                                System.out.printf("%-13.0f", incomeBand);
                                tax01.setTaxableIncome(incomeBand);
                                tax01.setFilingStatus(IncomeTax.SINGLE_FILER);
                                System.out.printf("%8.2f", tax01.getIncomeTax());
                                tax01.setFilingStatus(IncomeTax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOWER);
                                System.out.printf("%14.2f", tax01.getIncomeTax());
                                tax01.setFilingStatus(IncomeTax.MARRIED_SEPARATELY);
                                System.out.printf("%19.2f", tax01.getIncomeTax());
                                tax01.setFilingStatus(IncomeTax.HEAD_OF_HOUSEHOLD);
                                System.out.printf("%14.2f\n", tax01.getIncomeTax());
                                incomeBand += 1000;
                            }
                            System.out.println();
                            System.out.print("\n2009 tax tables for taxable income from $");
                            System.out.printf("%.0f to $%.0f", from, to);
                            System.out.println(
                                    "\n---------------------------------------------------------------------\n" +
                                            "Taxable       Single      Married Joint       Married        Head of\n" +
                                            "Income                    or Qualifying       Separate       a House\n" +
                                            "                          Widow(er)\n" +
                                            "---------------------------------------------------------------------");
                            incomeBand = from;
                            IncomeTax tax2009 = new IncomeTax();
                            tax2009.setIntervals(intervals09);
                            tax2009.setRates(rates09);
                            for (int i = 0; i <= range / 1000; i++) {
                                System.out.printf("%-13.0f", incomeBand);
                                tax2009.setTaxableIncome(incomeBand);
                                tax2009.setFilingStatus(IncomeTax.SINGLE_FILER);
                                System.out.printf("%8.2f", tax2009.getIncomeTax());
                                tax2009.setFilingStatus(IncomeTax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOWER);
                                System.out.printf("%14.2f", tax2009.getIncomeTax());
                                tax2009.setFilingStatus(IncomeTax.MARRIED_SEPARATELY);
                                System.out.printf("%19.2f", tax2009.getIncomeTax());
                                tax2009.setFilingStatus(IncomeTax.HEAD_OF_HOUSEHOLD);
                                System.out.printf("%14.2f\n", tax2009.getIncomeTax());
                                incomeBand += 1000;
                            }
                            System.out.println();
                        } else {
                            System.out.println("\n **** Invalid input. Try Again ****\n");
                        }
                    } else {
                        System.out.println("\n **** Invalid input. Try Again ****\n");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n **** Invalid input. Try Again ****\n");
                    break;
            }
            ;
        } while (option != 0);
    }
}
