package bankkata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
  // services
  private Console console;
  private DateProvider dateProvider;

  int balance = 0;
  List<Transaction> transactions = new ArrayList<>();
  private TransactionFormatter transactionFormatter;
  
  public Account() {
    this(() -> new Date().toString(),
         (String str) -> System.out.println(str));
  }
  
  public Account(DateProvider dateProvider, Console console) {
    this.dateProvider = dateProvider;
    this.console = console;
    transactionFormatter = new TransactionFormatter();
  }

  public void deposit(int amount) {
    if (amount < 0) {
      throw new NumberFormatException("Error in deposit : Negative amount");
    }
    addTransaction(amount);
  }

  private void addTransaction(int amount) {
    balance += amount;
    transactions.add(0, new Transaction(dateProvider.getCurrentDate(), amount));
  }


  public void withdraw(int amount) {
    if (amount < 0) {
      throw new NumberFormatException("Error in withdraw : Negative amount");
    }
    addTransaction(-amount);
  }

  public void printStatement() {
    console.print("Date | Amount | Balance");
    
    int tempBalance = balance;
    for (Transaction tr : transactions) {
      console.print(transactionFormatter.format(tr, tempBalance));

      tempBalance = tr.unApply(tempBalance);
    }
  }

  public int getBalance() {
    return balance;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }
  
}
