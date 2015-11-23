package com.priceminister.account.implementation;

import com.priceminister.account.OperationType;
import com.priceminister.account.exception.IllegalBalanceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Mockito test for CustomerAccountService
 * Add @RunWith(MockitoJUnitRunner.class) to enable mockito annotation engine
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerAccountServiceTest {

    /**
     * The class that we want to test must have the @InjectMocks annotation.
     * This class will be instantiated and have all its mocks (declared below) injected before running the tests.
     * This is equivalent to CustomerAccountService underTest = Mockito.mock(CustomerAccountService.class);
     */
    @InjectMocks
    CustomerAccountService underTest;

    /**
     * This is a CustomerAccountService dependency, the @Mock annotation allow to create a mock
     * in order to simulate the customerDao call.
     */
    @Mock
    CustomerDao customerDao;

    /**
     * GIVEN Customer account with a 400 euros balance
     * WHEN Withdraw 100 euros
     * THEN The new balance is 300 euros
     * @throws IllegalBalanceException
     */
    @Test
    public void
    withdraw_100_euros_on_customer_account() throws IllegalBalanceException {

        //Test parameters
        final Long customerAccountId = 10L;
        final Double money = Double.valueOf(100);


        //Creating a customer account with a positive balance
        CustomerAccount customerAccount = new CustomerAccount(new CustomerAccountRule());
        customerAccount.add(Double.valueOf(400));

        //Mocking the dao call to the database and make it return the customerAccount we've just created
        //For all call on customerDao.find() with any Long parameter return the given customerAccount object

        Mockito.when(customerDao.find(Mockito.anyLong())).thenReturn(customerAccount);

        //Mockito.any(Clazz.class) method is a matcher,
        //If one parameter of the method use a matcher we have to use a matcher for all the other parameter
        Mockito.when(customerDao.save(Mockito.any(CustomerAccount.class),Mockito.eq(OperationType.WITHDRAW)))
                .thenReturn(true);

        //The call for the method we want to test
        double newBalance =  underTest.withdrawMoney(customerAccountId, money);

        //The verify method can check is a particular method has been or not invoked
        //This statement check that the method save() from customerDao has been call exactly 1 time
        //with the customerAccount we have created before.
        //Mockito provides other method than times for verification. For example :
        // never(), atLeast(), atMost()
        Mockito.verify(customerDao,Mockito.times(1)).save(customerAccount, OperationType.WITHDRAW);

        // InOrder object can check that some method have been called in a particular order
        InOrder inOrder = Mockito.inOrder(customerDao);
        //If we inverse th two following lines the test will fail
        inOrder.verify(customerDao).find(customerAccountId);
        inOrder.verify(customerDao).save(customerAccount,OperationType.WITHDRAW);



    }

    /**
     * GIVEN Customer account with a 400 euros balance
     * WHEN add 100 euros
     * THEN The new balance is 300 euros
     * @throws IllegalBalanceException
     */
    @Test
    public void
    add_100_euros_on_customer_account() throws IllegalBalanceException {

        //Test parameters
        final Long customerAccountId = 10L;
        final Double money = Double.valueOf(100);


        //Creating a customer account with a positive balance
        CustomerAccount customerAccount = new CustomerAccount(new CustomerAccountRule());
        customerAccount.add(Double.valueOf(400));

        //Mocking the dao call to the database and make it return the customerAccount we've just created
        //For all call on customerDao.find() with any Long parameter return the given customerAccount object
        Mockito.when(customerDao.find(Mockito.anyLong())).thenReturn(customerAccount);

        //Mockito.any(Clazz.class) method is a matcher,
        //If one parameter of the method use a matcher we have to use a matcher for all the other parameter
        Mockito.when(customerDao.save(Mockito.any(CustomerAccount.class),Mockito.eq(OperationType.DEPOSIT)))
                .thenReturn(true);

        //The call for the method we want to test
        boolean isSaved =  underTest.addMoney(customerAccountId, money);


        final ArgumentCaptor<CustomerAccount> argumentCaptor = ArgumentCaptor.forClass(CustomerAccount.class);
        Mockito.verify(customerDao,Mockito.times(1)).save(argumentCaptor.capture(),Mockito.eq(OperationType.DEPOSIT));
        Assert.assertEquals(Double.valueOf(500),(Double)((CustomerAccount)argumentCaptor.getValue()).getBalance());
    }
}