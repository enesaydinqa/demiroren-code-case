package com.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country
{
    private String name;
    private String[] altSpellings;
    private Currencies[] currencies;
    private String area;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getAltSpellings()
    {
        return altSpellings;
    }

    public void setAltSpellings(String[] altSpellings)
    {
        this.altSpellings = altSpellings;
    }

    public Currencies[] getCurrencies()
    {
        return currencies;
    }

    public void setCurrencies(Currencies[] currencies)
    {
        this.currencies = currencies;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }
}
