/**
 * Created by mary on 9/10/16.
 */
public class Account {

    protected long amount;

    public Account(long new_amount) {
        amount = new_amount;
    }

    public String toString() {
        return "Account Balance: $" + amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
        if (acc1.amount > acc2.amount)
            return acc1;
        else
            return acc2;
    }

    public long getAmount(){ //getting variable values returns the variable
        return amount;
    }

    public void setAmount(long new_amount){ //setting variables returns nothing
        amount = new_amount;
    }

    public void deposit_money(long deposit){
        amount += deposit;
    }

}

class CheckingAccount extends Account {

    public CheckingAccount(long new_amount) { //returns null -- why doesn't it return the toString method?
        super(new_amount); //doesn't seem to do anything, but I get errors when I take this line aways
    }

    public String toString() {
        return "Checking Account Balance: $" + amount;
    }

    public void withdraw_moolah(long withdraw_amount) {
        setAmount(amount - withdraw_amount);
    }
}