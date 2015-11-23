package com.priceminister.account.implementation;

import com.priceminister.account.AcccountService;
import com.priceminister.account.OperationType;
import com.priceminister.account.exception.IllegalAmountException;
import com.priceminister.account.exception.IllegalBalanceException;

/**
 * Created by Sedoud on 23/11/2015.
 */
public class CustomerAccountService implements AcccountService {

    /**
     * Dao for CustomerAccount
     */
    private CustomerDao customerDao;


    /**
     * Withdraw money from customer account
     * @param customerAccountId The customer account id
     * @param moneyToWithdraw The money to Withdraw
     * @return The balance
     * @throws IllegalBalanceException, if there is not enough money to withdraw
     */
    public double withdrawMoney(Long customerAccountId, Double moneyToWithdraw) throws IllegalBalanceException {

        CustomerAccount customerAccount =  customerDao.find(customerAccountId);

        Double balance = customerAccount.withdrawAndReportBalance(moneyToWithdraw);

        boolean isSaved = customerDao.save(customerAccount, OperationType.WITHDRAW);

        if(!isSaved){
            throw new IllegalStateException("The customer has not been saved");
        }
        return balance;


    }

    public boolean addMoney(Long customerAccountId, Double moneyToAdd) throws IllegalBalanceException {

        CustomerAccount customerAccount =  customerDao.find(customerAccountId);

        customerAccount.add(moneyToAdd);

        return customerDao.save(customerAccount, OperationType.DEPOSIT);

    }


}
