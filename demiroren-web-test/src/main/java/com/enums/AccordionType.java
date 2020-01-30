package com.enums;

public enum AccordionType
{
    CREDIT_CARD("Kredi / Banka Kartı"),
    REMIT("Havale");

    //-----

    public final String accordionType;

    AccordionType(String accordionType)
    {
        this.accordionType = accordionType;
    }
}
