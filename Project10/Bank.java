import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private int id;
    private double balance;
    private List<LoanBank> loans;
    private int nLoans;
    private boolean isSafe;
    public Bank(){
        id = 0;
        balance = 0.0;
        isSafe = true;
        loans = new ArrayList<LoanBank>();
        nLoans = 0;
    }
    public Bank(int id, double b){
        this.id = id;
        this.balance = b;
        isSafe = true;
        loans = new ArrayList<LoanBank>();
        nLoans = 0;
    }
    public void addLoan(){
        boolean isValid = false;
        Scanner input = new Scanner(System.in);
        LoanBank loan = new LoanBank();
        do {
            System.out.print("\nEnter the Bank ID who receives the loan: ");
            if(input.hasNextInt()){
                loan.setId(input.nextInt());
                isValid = true;
            }else{
                System.out.println("Please enter an integer value");
                input.next();
            }
        }while(!isValid);
        isValid = false;
        do {
            System.out.print("Enter the Loan Amount: ");
            if(input.hasNextDouble()){
                loan.setLoan(input.nextDouble());
                isValid = true;
            }else{
                System.out.println("Please enter an double value");
                input.next();
            }
        }while(!isValid);
        loans.add(loan);
        nLoans = loans.size();
    }
    public List<LoanBank> getLoans(){
        List<LoanBank> loanBankList = new ArrayList<LoanBank>();
        if(nLoans > 0) {
            for (LoanBank loan : loans) {
                loanBankList.add(loan);
            }
        }
        return loanBankList;
    }
    public int getLoansNumber(){
        return this.nLoans;
    }
    public void setSafety(boolean safe){
        this.isSafe = safe;
    }
    public boolean getSafety(){
        return this.isSafe;
    }
    public int getId(){
        return this.id;
    }
    public double getBalance(){
        return this.balance;
    }
    public double getLoansAmount(){
        double totalLoaned = 0.0;
        for(LoanBank loan : loans){
            if(!loan.getRisk())
                totalLoaned += loan.getLoanedAmount();
        }
        return totalLoaned;
    }
    public void setLoansInRisk(int id){
        for(LoanBank loan : loans){
            if(loan.getId() == id) {
                loan.setInRisk(true);
            }
        }
    }
}
