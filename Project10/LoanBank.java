public class LoanBank {
    private int bankId;
    private double loanedAmount;
    private boolean inRisk = false;
    public LoanBank(){
        bankId = 0;
        loanedAmount = 0.0;
    }
    public void setId(int id){
        this.bankId = id;
    }
    public void setLoan(double loan){
        this.loanedAmount = loan;
    }
    public int getId(){
        return this.bankId;
    }
    public double getLoanedAmount(){
        return this.loanedAmount;
    }
    public void setInRisk(boolean risk){
        this.inRisk = risk;
    }
    public boolean getRisk(){
        return this.inRisk;
    }
}
