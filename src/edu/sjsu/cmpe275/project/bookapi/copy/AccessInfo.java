package edu.sjsu.cmpe275.project.bookapi.copy;

public class AccessInfo
{
    private String textToSpeechPermission;

    private String webReaderLink;

    private String publicDomain;

    private String viewability;

    private String embeddable;

    private Epub epub;

    private Pdf pdf;

    private String accessViewStatus;

    private String quoteSharingAllowed;

    private String country;

    public String getTextToSpeechPermission ()
    {
        return textToSpeechPermission;
    }

    public void setTextToSpeechPermission (String textToSpeechPermission)
    {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    public String getWebReaderLink ()
    {
        return webReaderLink;
    }

    public void setWebReaderLink (String webReaderLink)
    {
        this.webReaderLink = webReaderLink;
    }

    public String getPublicDomain ()
    {
        return publicDomain;
    }

    public void setPublicDomain (String publicDomain)
    {
        this.publicDomain = publicDomain;
    }

    public String getViewability ()
    {
        return viewability;
    }

    public void setViewability (String viewability)
    {
        this.viewability = viewability;
    }

    public String getEmbeddable ()
    {
        return embeddable;
    }

    public void setEmbeddable (String embeddable)
    {
        this.embeddable = embeddable;
    }

    public Epub getEpub ()
    {
        return epub;
    }

    public void setEpub (Epub epub)
    {
        this.epub = epub;
    }

    public Pdf getPdf ()
    {
        return pdf;
    }

    public void setPdf (Pdf pdf)
    {
        this.pdf = pdf;
    }

    public String getAccessViewStatus ()
    {
        return accessViewStatus;
    }

    public void setAccessViewStatus (String accessViewStatus)
    {
        this.accessViewStatus = accessViewStatus;
    }

    public String getQuoteSharingAllowed ()
    {
        return quoteSharingAllowed;
    }

    public void setQuoteSharingAllowed (String quoteSharingAllowed)
    {
        this.quoteSharingAllowed = quoteSharingAllowed;
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
        return "ClassPojo [textToSpeechPermission = "+textToSpeechPermission+", webReaderLink = "+webReaderLink+", publicDomain = "+publicDomain+", viewability = "+viewability+", embeddable = "+embeddable+", epub = "+epub+", pdf = "+pdf+", accessViewStatus = "+accessViewStatus+", quoteSharingAllowed = "+quoteSharingAllowed+", country = "+country+"]";
    }
}
		
