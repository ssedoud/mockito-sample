package com.priceminister.account.exception;

/**
 * Illegal amount exception when you try to manupulate an invalid amount.
 */
public class IllegalAmountException extends IllegalArgumentException {

    private static final long serialVersionUID = -354054054054L;

    /**
     * Illegal amount value
     */
    private Double amount;

    /**
     * Constructor
     * @param illegalAmount - Illegal amount value
     */
    public IllegalAmountException(final Double illegalAmount) {
        amount = illegalAmount;
    }

    public String toString() {
        return "Illegal amount : " + amount;
    }

}
