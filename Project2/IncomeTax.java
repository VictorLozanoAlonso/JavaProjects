/********************************************************
 *                  UML Diagram                         *
 *                   IncomeTax                          *
 *----------------------------------------------------- *
 * -filingStatus: int                                   *
 * +SINGLE_FILER: int                                   *
 * ---------------------------------------------        *
 * +MARRIED_JOINTLY_OR_QUALIFYING_WIDOW(ER): int        *
 * ---------------------------------------------        *
 * +MARRIED_SEPARATELY: int                             *
 * ---------------------------------------------        *
 * +HEAD_OF_HOUSEHOLD: int                              *
 * ---------------------------------------------        *
 * -intervals: int[][]                                  *
 * -rates: double[]                                     *
 * -taxableIncome: double                               *
 * +IncomeTax()                                         *
 * +IncomeTax(filingStatus: int, intervals: int[][],    *
 *      rates: double[], taxableIncome: double)         *
 * +getFilingStatus(): int                              *
 * +setFilingStatus(status: int)                        *
 * +getIntervals(): int[][]                             *
 * +setIntervals(Intervals: int[][])                    *
 * +getRates(): int[]                                   *
 * +setRates(rates: int[])                              *
 * +getTaxableIncome(): double                          *
 * +setTaxableIncome(taxableIncome: double)             *
 * +getIncomeTax(): double                              *
 ********************************************************/

public class IncomeTax {
    private int filingStatus;
    public static final int SINGLE_FILER = 0;
    public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOWER = 1;
    public static final int MARRIED_SEPARATELY = 2;
    public static final int HEAD_OF_HOUSEHOLD = 3;
    private int[][] intervals;
    private double[] rates;
    private double taxableIncome;

    IncomeTax() {
        filingStatus = 0;
        intervals = new int[0][0];
        rates = new double[0];
        taxableIncome = 0.0;
    }

    IncomeTax(int filingStatus, int[][] intervals, double[] rates, double taxableIncome) {
        setFilingStatus(filingStatus);
        setIntervals(intervals);
        setRates(rates);
        setTaxableIncome(taxableIncome);
    }

    public int getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(int filingStatus) {
        this.filingStatus = filingStatus;
    }

    public int[][] getIntervals() {
        return intervals;
    }

    public void setIntervals(int[][] intervals) {
        this.intervals = new int[intervals.length][intervals[0].length];
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[i].length; j++)
                this.intervals[i][j] = intervals[i][j];
        }
    }

    public double[] getRates() {
        return rates;
    }

    public void setRates(double[] rates) {
        this.rates = new double[rates.length];
        for (int i = 0; i < rates.length; i++)
            this.rates[i] = rates[i];
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getIncomeTax() {
        double taxTotal = 0.0;
        double income = getTaxableIncome();
        for (int i = 0; income > 0; i++) {
            if (getTaxableIncome() >= intervals[getFilingStatus()][i]) {
                if (i == 0) {
                    taxTotal += intervals[getFilingStatus()][i] * (rates[i] / 100);
                    income -= intervals[getFilingStatus()][i];
                } else {
                    taxTotal += (intervals[getFilingStatus()][i] - intervals[getFilingStatus()][i - 1]) * (rates[i] / 100);
                    income -= (intervals[getFilingStatus()][i] - intervals[getFilingStatus()][i - 1]);
                }
            } else {
                taxTotal += income * (rates[i] / 100);
                income = -1;
            }
        }
        return taxTotal;
    }
}
