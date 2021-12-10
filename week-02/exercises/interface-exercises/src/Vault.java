public class Vault implements MoneyStorage {

    private double balance;
    private String description;


    public Vault(double beginningBalance, String description) {
        this.balance = beginningBalance;
        this.description = description;

    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0){
            balance += amount;
        }
        return false;
    }

    @Override
    public double withdraw(double amount) {
        if (amount <= 0.0) {
            return 0.0;
        }

        double result = Math.min(amount, balance);
        balance -= result;
        return result;
    }
}

