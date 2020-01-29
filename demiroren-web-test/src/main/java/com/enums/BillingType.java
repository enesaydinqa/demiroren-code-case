package com.enums;

public enum BillingType
{
    INDIVIDUAL("Bireysel"),
    CORPORATE("Kurumsal");

    //-----

    public final String billingType;

    BillingType(String billingType)
    {
        this.billingType = billingType;
    }
}
