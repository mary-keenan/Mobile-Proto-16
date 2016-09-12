/**
 * Created by mary on 9/10/16.
 */
public class MoneySaver {

    private long myMoney;
    private CheckingAccount myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String getName() {
        return name;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public String toString() {
        return name + ", " + "My balance is: "+ myMoney;
    }

    public void deposit(long deposit_amount) {
        myAccount.deposit_money(deposit_amount);
    }

    public void withdraw(long withdraw_amount) {
         myAccount.withdraw_moolah(withdraw_amount);
        //((CheckingAccount(myAccount)).withdraw_moolah(withdraw_amount);

    }

    public void signUpForChecking(long amount) {
        myAccount = new CheckingAccount(amount);
        myMoney -= amount;
    }

    public static void main(String[] args) {
        MoneySaver jim = new MoneySaver("Jim", 100);
        jim.signUpForChecking(30);
        CheckingAccount acc = (CheckingAccount) jim.getMyAccount();
        System.out.println(jim);
        System.out.println(acc);
        jim.deposit(40);
        System.out.println(jim);
        System.out.println(acc);
        jim.withdraw(60);
        System.out.println(jim);
        System.out.println(acc);

        //Account a = new Account(100);
        //System.out.println(a);
        //a.setAmount(20);
        //a.deposit_money(10);
        //System.out.println("New amount: " + a.getAmount());
        //Account small = new Account(20);
        //Account big = new Account(30);
        //System.out.println(Account.largerAccount(small,big));
//        MoneySaver david = new MoneySaver("David", 100);
//        david.signUpForChecking(50);  // put 50 dollars in a checking account
//        System.out.println(david);
//        System.out.println(david.getMyAccount()); //returns null because my constructor returns null. No idea why.
    }
}