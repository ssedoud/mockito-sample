package com.priceminister.account.implementation;


import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.exception.IllegalAmountException;
import com.priceminister.account.exception.IllegalBalanceException;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Test class for CustomerAccount
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerAccountTest {


    /**
     * Class under test.
     * @Spy is used to partial mock some object.
     * It allows to stub method within the class under test.
     * It must be instanciated.
     * If replace by @InjectMoks, then a NotAMockException will be thrown because Argument passed to when() is not a mock!
     */
    @Spy
    private CustomerAccount customerAccount = new CustomerAccount();



    /**
     * GIVEN Customer account with a 400 euros balance
     * WHEN Withdraw 100 euros
     * THEN The new balance is 300 euros
     * @throws IllegalBalanceException
     */
    @Test
    public void
    withdraw_100_euros_on_customer_account() throws IllegalBalanceException {

        //Will not execute the when, it will just intercept the call and return the mocked value
        Mockito.doReturn(Double.valueOf(400)).when(customerAccount).affectNewBalance(Mockito.anyDouble());

        //Mockito.when(customerAccount.affectNewBalance(Mockito.anyDouble())).thenReturn(Double.parseDouble("400"));
        //when().thenRetrun will throw an null pointer exception because mockito will try to execute the when
        Double newBalance = customerAccount.withdrawAndReportBalance(100);

        Assert.assertEquals(Double.valueOf(400),newBalance);
    }

}
