package com.priceminister.account.implementation;

import com.priceminister.account.OperationType;

/**
 * Dao interface
 */
public interface CustomerDao {

    CustomerAccount find(Long customerAccountId);

    boolean save(CustomerAccount customerAccount,OperationType operationType);
}
