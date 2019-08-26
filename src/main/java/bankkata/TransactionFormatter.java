package bankkata;

public class TransactionFormatter {

  public String format(Transaction tr, int currentBalance) {
    return String.format("%s | %s%s | %s", 
        tr.getDate(), 
        sign(tr.getAmount()), 
        ""+tr.getAmount(), 
        ""+currentBalance);
  }
  
  private String sign(int amount) {
    return amount>=0 ? "+" :"";
  }

}
