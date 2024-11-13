package com.training.dat.Wallet.domain.model;

public enum AccountType {
    SELF,
    CUSTOMER;

    public static AccountType fromId(String id) {
        switch (id) {
            case "1":
                return SELF;
            case "2":
                return CUSTOMER;
            default:
                throw new IllegalArgumentException("Invalid Account Type ID: " + id);
        }
    }
}
