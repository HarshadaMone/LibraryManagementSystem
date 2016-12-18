package edu.sjsu.cmpe275.project.bookapi.copy;


public class ReadingModes
{
    private String text;

    private String image;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", image = "+image+"]";
    }
}
			
