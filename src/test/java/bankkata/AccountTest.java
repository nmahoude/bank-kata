package bankkata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class AccountTest {
  private DateProvider dateProvider;
  private Console console;
  private Account account;

  @Before
  public void setup() {
    dateProvider = Mockito.mock(DateProvider.class);
    console = Mockito.mock(Console.class);

    account = new Account(dateProvider, console);
  }
  
  
  @Test
  public void shouldAddTransactionWhenDeposit100() throws Exception {
    Mockito.doReturn("2012.0.0").when(dateProvider).getCurrentDate();
    account.deposit(100);
    
    Assertions.assertThat(account.getBalance()).isEqualTo(100);
    assertThat(account.getTransactions().get(0).getAmount()).isEqualTo(100);
    assertThat(account.getTransactions().get(0).getDate()).isEqualTo("2012.0.0");
  }
  
  @Test(expected = NumberFormatException.class)
  public void shouldThrowExceptionWhenDepositNegativeAmount() throws Exception {
    
    account.deposit(-1);
  }
  
  @Test
  public void shouldAddTransactionWhenWitdraw50() throws Exception {
    Mockito.doReturn("2012.1.0").when(dateProvider).getCurrentDate();
    account.withdraw(50);
    
    assertThat(account.getBalance()).isEqualTo(-50);
    assertThat(account.getTransactions().get(0).getAmount()).isEqualTo(-50);
    assertThat(account.getTransactions().get(0).getDate()).isEqualTo("2012.1.0");
  }
  
  @Test(expected = NumberFormatException.class)
  public void shouldThrowExceptionWhenWithdrawNegativeAmount() throws Exception {
    
    account.withdraw(-1);
  }
  
  @Test
  public void shouldAddMultipleDepositAndWithdrawal() throws Exception {
    account.deposit(100);
    account.withdraw(50);
    account.deposit(200);
    
    assertThat(account.getBalance()).isEqualTo(250);
  }
  
  
  @Test
  public void shouldPrintStatement() throws Exception {
    Mockito.doReturn("-date-").when(dateProvider).getCurrentDate();
    
    account.deposit(100);
    account.withdraw(50);
    
    account.printStatement();

    InOrder inOrder = Mockito.inOrder(console);
    inOrder.verify(console).print("Date | Amount | Balance");
    inOrder.verify(console).print("-date- | -50 | 50");
    inOrder.verify(console).print("-date- | +100 | 100");
  }
}
