import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bankkata.Account;
import bankkata.Console;
import bankkata.DateProvider;

public class AccountAcceptance {

  private Account account;
  private Console console = Mockito.mock(Console.class);
  private DateProvider dateProvider = Mockito.mock(DateProvider.class);
  
  @Before
  public void setup() {
    account = new Account(dateProvider, console);
  }
  
  @Test
  public void accountPrintStatement() throws Exception {
    Mockito.doReturn("24.12.2015").when(dateProvider).getCurrentDate();
    account.deposit(500);
    
    Mockito.doReturn("23.8.2016").when(dateProvider).getCurrentDate();
    account.withdraw(100);
    
    account.printStatement();
    
    Mockito.verify(console).print("Date | Amount | Balance");
    Mockito.verify(console).print("23.8.2016 | -100 | 400");
    Mockito.verify(console).print("24.12.2015 | +500 | 500");
    
  }
}
