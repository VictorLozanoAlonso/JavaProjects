import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeSystemBank {
    private int nBanks;
    private int limit;
    private List<Bank> banks;
    public TradeSystemBank(){
        nBanks = 0;
        limit = 0;
        banks = new ArrayList<Bank>();
    }
    public TradeSystemBank(int banks, int limit){
        this.nBanks = banks;
        this.limit = limit;
        this.banks = new ArrayList<Bank>();
        for(int i = 0; i < nBanks; i++){
            addBank(i);
        }
    }
    public void addBank(int id){
        boolean isValid = false;
        double balance = 0.0;
        int loanedBanks = 0;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("\nEnter initial Bank " + id + " balance: ");
            if(input.hasNextDouble()){
                balance = input.nextDouble();
                isValid = true;
            }else{
                System.out.println("Please enter an integer value");
                input.next();
            }
        }while(!isValid);
        Bank bank = new Bank(id,balance);
        isValid = false;
        do {
            System.out.print("Enter the number of banks loaned: ");
            if(input.hasNextInt()){
                loanedBanks = input.nextInt();
                isValid = true;
            }else{
                System.out.println("Please enter an integer value");
                input.next();
            }
        }while(!isValid);
        for(int i = 0; i < loanedBanks; i++){
            bank.addLoan();
        }
        banks.add(bank);
    }
    public void setSafety(){
        for(Bank bank : banks){
            if((bank.getBalance() + bank.getLoansAmount()) < limit)
                bank.setSafety(false);
        }
    }
    public List<Integer> getRiskyBanksId(){
        List<Integer> riskyBanksIds = new ArrayList<Integer>();
        for(Bank bank : banks){
            if(!bank.getSafety())
                riskyBanksIds.add(bank.getId());
        }
        return riskyBanksIds;
    }
    public void setSafetyNetwork(){
        boolean globalRiskChanged = false;
        List<Integer> riskyBanksIds;
        setSafety();
        do{
            globalRiskChanged = false;
            riskyBanksIds = getRiskyBanksId();
            for(int id : riskyBanksIds) {
                for (Bank bank : banks) {
                    if (bank.getLoansNumber() > 0) {
                        bank.setLoansInRisk(id);
                    }
                }
            }
            setSafety();
            if(riskyBanksIds == getRiskyBanksId())
                globalRiskChanged = true;
            riskyBanksIds.clear();
        }while (globalRiskChanged);
    }
    public static void main(String[] args) {
        int banks = 0, limit = 0;
        boolean isValid = false;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter a number of banks: ");
            if(input.hasNextInt()){
                banks = input.nextInt();
                isValid = true;
            }else{
                System.out.println("Please enter an integer value");
                input.next();
            }
        }while(!isValid);
        isValid = false;
        do {
            System.out.print("Enter a minimum value to set a safe bank: ");
            if(input.hasNextInt()){
                limit = input.nextInt();
                isValid = true;
            }else{
                System.out.println("Please enter an integer value");
                input.next();
            }
        }while(!isValid);
        TradeSystemBank netBank = new TradeSystemBank(banks, limit);
        netBank.setSafetyNetwork();
        List<Integer> riskyBanksId = netBank.getRiskyBanksId();
        if(riskyBanksId.size() > 0 ) {
            System.out.print("Unsafe bank" + ((riskyBanksId.size() == 1) ? " is":"s are"));
            for(int i = 0; i < riskyBanksId.size(); i++){
                if(i != (riskyBanksId.size() - 1))
                    System.out.print(" bank " + riskyBanksId.get(i) + ",");
                else
                    System.out.print(" and Bank " + riskyBanksId.get(i) + ".");
            }
        }
    }
}
