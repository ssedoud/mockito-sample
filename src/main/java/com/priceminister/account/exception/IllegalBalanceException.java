package com.priceminister.account.exception;


public class IllegalBalanceException extends Exception {
    
    private static final long serialVersionUID = -9204191749972551939L;

    /**
     * Illegal balance value
     */
	private Double balance;

    /**
     * Constructor
     * @param illegalBalance - Illegal balance value
     */
    public IllegalBalanceException(final Double illegalBalance) {
        balance = illegalBalance;
    }
    
    public String toString() {
        return "Illegal account balance: " + balance;
    }
}
