package edu.sjsu.cmpe275.project.bookapi;

public class VolumeInfo
{
    private String pageCount;

    private String averageRating;

    private ReadingModes readingModes;

    private String infoLink;

    private String printType;

    private String allowAnonLogging;

    private String publisher;

    private String[] authors;

    private String canonicalVolumeLink;

    private String title;

    private String previewLink;

    private String ratingsCount;

    private String description;

    private ImageLinks imageLinks;

    private String subtitle;

    private String contentVersion;

    private String[] categories;

    private String publishedDate;

    private IndustryIdentifiers[] industryIdentifiers;

    private String language;

    private String maturityRating;

    public String getPageCount ()
    {
        return pageCount;
    }

    public void setPageCount (String pageCount)
    {
        this.pageCount = pageCount;
    }

    public String getAverageRating ()
    {
        return averageRating;
    }

    public void setAverageRating (String averageRating)
    {
        this.averageRating = averageRating;
    }

    public ReadingModes getReadingModes ()
    {
        return readingModes;
    }

    public void setReadingModes (ReadingModes readingModes)
    {
        this.readingModes = readingModes;
    }

    public String getInfoLink ()
    {
        return infoLink;
    }

    public void setInfoLink (String infoLink)
    {
        this.infoLink = infoLink;
    }

    public String getPrintType ()
    {
        return printType;
    }

    public void setPrintType (String printType)
    {
        this.printType = printType;
    }

    public String getAllowAnonLogging ()
    {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging (String allowAnonLogging)
    {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String[] getAuthors ()
    {
        return authors;
    }

    public void setAuthors (String[] authors)
    {
        this.authors = authors;
    }

    public String getCanonicalVolumeLink ()
    {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink (String canonicalVolumeLink)
    {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getPreviewLink ()
    {
        return previewLink;
    }

    public void setPreviewLink (String previewLink)
    {
        this.previewLink = previewLink;
    }

    public String getRatingsCount ()
    {
        return ratingsCount;
    }

    public void setRatingsCount (String ratingsCount)
    {
        this.ratingsCount = ratingsCount;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public ImageLinks getImageLinks ()
    {
        return imageLinks;
    }

    public void setImageLinks (ImageLinks imageLinks)
    {
        this.imageLinks = imageLinks;
    }

    public String getSubtitle ()
    {
        return subtitle;
    }

    public void setSubtitle (String subtitle)
    {
        this.subtitle = subtitle;
    }

    public String getContentVersion ()
    {
        return contentVersion;
    }

    public void setContentVersion (String contentVersion)
    {
        this.contentVersion = contentVersion;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public String getPublishedDate ()
    {
        return publishedDate;
    }

    public void setPublishedDate (String publishedDate)
    {
        this.publishedDate = publishedDate;
    }

    public IndustryIdentifiers[] getIndustryIdentifiers ()
    {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers (IndustryIdentifiers[] industryIdentifiers)
    {
        this.industryIdentifiers = industryIdentifiers;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getMaturityRating ()
    {
        return maturityRating;
    }

    public void setMaturityRating (String maturityRating)
    {
        this.maturityRating = maturityRating;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pageCount = "+pageCount+", averageRating = "+averageRating+", readingModes = "+readingModes+", infoLink = "+infoLink+", printType = "+printType+", allowAnonLogging = "+allowAnonLogging+", publisher = "+publisher+", authors = "+authors+", canonicalVolumeLink = "+canonicalVolumeLink+", title = "+title+", previewLink = "+previewLink+", ratingsCount = "+ratingsCount+", description = "+description+", imageLinks = "+imageLinks+", subtitle = "+subtitle+", contentVersion = "+contentVersion+", categories = "+categories+", publishedDate = "+publishedDate+", industryIdentifiers = "+industryIdentifiers+", language = "+language+", maturityRating = "+maturityRating+"]";
    }
}
