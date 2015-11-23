package com.priceminister.account;

import com.priceminister.account.exception.IllegalAmountException;
import com.priceminister.account.exception.IllegalBalanceException;

/**
 * This class represents a simple account.
 * It doesn't handle different currencies, all money is supposed to be of standard currency EUR.
 */
public interface Account {

    /**
     * Adds money to this account.
     * @param addedAmount - the money to add
     * @throws com.priceminister.account.exception.IllegalAmountException - The added amount is null or negative
     */
    public void add(double addedAmount) throws IllegalAmountException;


    /**
     * Withdraws money from the account.
     * @param withdrawnAmount - the money to withdraw
     * @return the remaining account balance
     * @throws com.priceminister.account.exception.IllegalBalanceException if the withdrawal leaves the account with a forbidden balance
     * @throws com.priceminister.account.exception.IllegalAmountException if the withdrawal is negative
     */
    public double withdrawAndReportBalance(double withdrawnAmount) throws IllegalBalanceException, IllegalAmountException;
    
    /**
     * Gets the current account balance.
     * @return the account's balance
     */
    public double getBalance();

    /**
     * Set the customer Account rule strategy
     * @param accountRule Balance allowed rule strategy
     */
    public void setAccountRule(AccountRule accountRule);
}
