package LldProblems;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Actors:
 * ATM(Multiple CashDespenser)
 * Abstract class CashDespenser(UnitType, int maxUnit, int currentUnit)
 * UnitType(HUNDRED, 5HUNDRED)
 * Bank(Multiple users, address, IFSCcode)
 * User(Name, age, address, Account)
 * Account(balance, List<Tranascations>, List<Card>, )
 * Card(number, pin, cvv)
 * Transcation(id, openingBalance, closingBalance, date, description)
 */

public class AtmDemo {
    public static void main(String[] args) {
        CashDespenser oneHun = new OneHundred(1000);
        CashDespenser fiveHum = new FiveHundred(1000);
        Account account1 = new Account("12345", 1000);
        Account account2 = new Account("123456", 2000);
        List<CashDespenser> cashDespensers = new ArrayList<>();
        cashDespensers.add(fiveHum);
        cashDespensers.add(oneHun);
        BankService bankService = new BankService();
        bankService.addAccount(account1);
        bankService.addAccount(account2);
        Atm atm = new Atm(bankService, cashDespensers);
        atm.withDrawCash(account1.accountNumber, 500);
        atm.depositCash(account2.accountNumber, 2000);
        System.out.println(account1.balance);
        System.out.println(account2.balance);
    }
}

class Atm {
    public int findValueOfUnit(UnitType unitType) {
        switch (unitType) {
            case HUNDRED:
                return 100;
            case FIVEHUNDRED:
                return 500;
            default:
                return 1;
        }
    }

    List<CashDespenser> cashDespensers;
    BankService bankService;

    public Atm(BankService bankService, List<CashDespenser> cashDespensers) {
        this.cashDespensers = cashDespensers;
        this.bankService = bankService;
    }

    public void addDespense(CashDespenser cashDespenser) {
        cashDespensers.add(cashDespenser);
    }

    public boolean withDrawCash(String accountNumber, double amount) {
        Transcation transcation = new Debit(bankService.accounts.get(accountNumber), amount);
        return transcation.execute();
    }

    public boolean depositCash(String accountNumber, double balance) {
        return new Credit(bankService.accounts.get(accountNumber), balance).execute();
    }
}

abstract class CashDespenser {
    UnitType unitType;
    int maxUnit;
    int currentUnit;

    protected CashDespenser(int maxUnit, UnitType unitType) {
        this.maxUnit = maxUnit;
        this.unitType = unitType;
        currentUnit = 0;
    }

    protected boolean addUnit(int unit) {
        if ((currentUnit + unit) <= maxUnit) {
            currentUnit += unit;
            return true;
        }
        return false;
    }

    protected boolean removeUnit(int unit) {
        if (currentUnit < unit)
            return false;
        currentUnit -= unit;
        return true;
    }
}

class OneHundred extends CashDespenser {

    protected OneHundred(int maxUnit) {
        super(maxUnit, UnitType.HUNDRED);
    }

}

class FiveHundred extends CashDespenser {

    protected FiveHundred(int maxUnit) {
        super(maxUnit, UnitType.FIVEHUNDRED);
    }

}

enum UnitType {
    HUNDRED, FIVEHUNDRED
}

class BankService {
    Map<String, Account> accounts;

    public BankService() {
        accounts = new ConcurrentHashMap<>();
    }

    public boolean addAccount(Account account) {
        if (accounts.containsKey(account.accountNumber))
            return false;
        else {
            accounts.put(account.accountNumber, account);
            return true;
        }
    }
}

class Account {
    String accountNumber;
    double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

abstract class Transcation {
    protected final TranscationType type;
    protected final Account account;
    protected final double amount;

    protected Transcation(TranscationType type, Account account, double amount) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }

    public abstract boolean execute();
}

class Credit extends Transcation {

    protected Credit(Account account, double amount) {
        super(TranscationType.CREDIT, account, amount);
    }

    @Override
    public boolean execute() {
        if (account == null)
            return false;
        account.balance += amount;
        return true;
    }

}

class Debit extends Transcation {

    protected Debit(Account account, double amount) {
        super(TranscationType.DEBIT, account, amount);
    }

    @Override
    public boolean execute() {
        if (account == null)
            return false;
        if (account.balance < amount)
            return false;
        else {
            account.balance -= amount;
            return true;
        }
    }

}

enum TranscationType {
    CREDIT, DEBIT
}