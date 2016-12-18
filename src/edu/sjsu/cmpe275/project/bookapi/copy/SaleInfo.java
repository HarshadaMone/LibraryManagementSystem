package edu.sjsu.cmpe275.project.bookapi.copy;

public class SaleInfo
{
    private String saleability;

    private String isEbook;

    private String country;

    public String getSaleability ()
    {
        return saleability;
    }

    public void setSaleability (String saleability)
    {
        this.saleability = saleability;
    }

    public String getIsEbook ()
    {
        return isEbook;
    }

    public void setIsEbook (String isEbook)
    {
        this.isEbook = isEbook;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [saleability = "+saleability+", isEbook = "+isEbook+", country = "+country+"]";
    }
}