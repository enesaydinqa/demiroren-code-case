package com.enums;

public enum OrderSteps
{
    DELIVERY_INFORMATION("Teslimat Bilgileri"),
    ORDER_SUMMARY("Sipariş Özeti");

    //-----

    public final String orderSteps;

    OrderSteps(String orderSteps)
    {
        this.orderSteps = orderSteps;
    }
}
