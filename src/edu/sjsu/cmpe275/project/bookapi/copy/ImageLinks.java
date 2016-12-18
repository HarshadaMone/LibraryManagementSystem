
package edu.sjsu.cmpe275.project.bookapi.copy;

public class ImageLinks
{
    private String thumbnail;

    private String smallThumbnail;

    public String getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getSmallThumbnail ()
    {
        return smallThumbnail;
    }

    public void setSmallThumbnail (String smallThumbnail)
    {
        this.smallThumbnail = smallThumbnail;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [thumbnail = "+thumbnail+", smallThumbnail = "+smallThumbnail+"]";
    }
}
	