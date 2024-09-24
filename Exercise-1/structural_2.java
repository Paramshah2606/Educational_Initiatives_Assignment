// Facade Pattern is part of structural pattern.It provides simplified interface for to complex subsystem.
// Use case: Creating simple interface for managing differenet banking operations.
// Language used: Java

class Account {
    public void openAccount(String name) {
        System.out.println(name+" applying for opening account..");
    }
}


class Loan {
    public void applyForLoan(String name) {
        System.out.println(name+" applying for loan..");
    }
}

class Credit {
    public void checkCreditScore(int x,String name) {
        System.out.println("Checking credit score..");
        if(x>=750){
            System.out.println("Congratulations "+name+"! Your request is approved..");
        }else{
            System.out.println("Sorry " + name+"! Due to low credit score your request can't be processed..");
        }
    }
}

class BankFacade {
    private Account account;
    private Loan loan;
    private Credit credit;
    private int cscore=750;
    private String name;

    public BankFacade() {
        this.account = new Account();
        this.loan = new Loan();
        this.credit = new Credit();
    }

    public BankFacade(int cscore,String name){
        this.cscore=cscore;
        this.name=name;
        this.account = new Account();
        this.loan = new Loan();
        this.credit = new Credit();
    }

    public void openBankAccount() {
        account.openAccount(name);
        credit.checkCreditScore(cscore,name);
    }

    public void applyForLoan() {
        loan.applyForLoan(name);
        credit.checkCreditScore(cscore,name);
    }
}

public class structural_2 {
    public static void main(String[] args) {
        BankFacade user1 = new BankFacade(650,"Param");
        user1.openBankAccount();
        user1.applyForLoan();
        BankFacade user2 = new BankFacade(850,"Rahul");
        user2.openBankAccount();
    }
}

