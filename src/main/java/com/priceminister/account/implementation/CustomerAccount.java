package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.exception.IllegalAmountException;
import com.priceminister.account.exception.IllegalBalanceException;


public class CustomerAccount implements Account {

    /**
     * Customer account balance
     */
    private double balance;

    /**
     * Customer Account rule strategy that defines which balance is allowed for this account
     */
    private AccountRule accountRule;

    public CustomerAccount() {};

    /**
     * Constructor for the customer account
     * @param accountRule  Balance allowed rule strategy
     */
    public CustomerAccount(final AccountRule accountRule) {
        if(accountRule == null){
            throw new IllegalArgumentException("The account rule should not be null");
        }
        this.accountRule = accountRule;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setAccountRule(final AccountRule accountRule) {
        if(accountRule == null){
            throw new IllegalArgumentException("The account rule should not be null");
        }
        this.accountRule = accountRule;
    }

    public void add(final double addedAmount) throws IllegalAmountException {
        if(addedAmount < 0){
            throw new IllegalAmountException(addedAmount);
        }
        this.balance += addedAmount;

    }

    public double withdrawAndReportBalance(final double withdrawnAmount) throws IllegalBalanceException, IllegalAmountException {

        if(withdrawnAmount < 0){
            throw new IllegalAmountException(withdrawnAmount);
        }

        final double newBalance = this.balance - withdrawnAmount;

        this.balance = affectNewBalance(newBalance);

        return this.balance;
    }

    /**
     * Affect the new balance if allowed by the accountRule
     * @param newBalance - New account balance
     * @throws IllegalBalanceException - If the withdraw is not permitted
     */
    public Double affectNewBalance(double newBalance) throws IllegalBalanceException {
        final Double balance = newBalance;

        if (!this.accountRule.withdrawPermitted(newBalance)) {
            throw new IllegalBalanceException(this.balance);
        }
        return balance;
    }

}
