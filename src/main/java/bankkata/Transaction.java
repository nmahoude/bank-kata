package bankkata;

public class Transaction {
  private String date;
  private int amount;

  public Transaction(String date, int amount) {
    super();
    this.date = date;
    this.amount = amount;
  }

  public int apply(int balance) {
    return balance + amount;
  }

  public String getDate() {
    return date;
  }
  
  public int getAmount() {
    return amount;
  }

  public int unApply(int balance) {
    return balance - amount;
  }
  
}
