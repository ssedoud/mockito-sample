package com.priceminister.account;


import com.priceminister.account.exception.IllegalBalanceException;

public interface AcccountService {

    double withdrawMoney(Long customerAccountId, Double moneyToWithdraw) throws IllegalBalanceException;

    boolean addMoney(Long customerAccountId, Double moneyToAdd) throws IllegalBalanceException;
}
