package edu.sjsu.cmpe275.project.bookapi.copy;


public class Pdf
{
    private String isAvailable;

    public String getIsAvailable ()
    {
        return isAvailable;
    }

    public void setIsAvailable (String isAvailable)
    {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isAvailable = "+isAvailable+"]";
    }
}