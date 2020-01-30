package com.objects;

import com.enums.BillingType;

public class BillingInformation
{
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String town;
    private String district;
    private String address;
    private String addressName;
    private BillingType billingType;
    private String phone;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddressName()
    {
        return addressName;
    }

    public void setAddressName(String addressName)
    {
        this.addressName = addressName;
    }

    public BillingType getBillingType()
    {
        return billingType;
    }

    public void setBillingType(BillingType billingType)
    {
        this.billingType = billingType;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
