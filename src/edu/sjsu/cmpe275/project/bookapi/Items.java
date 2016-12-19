package edu.sjsu.cmpe275.project.bookapi;

public class Items
{
    private String id;

    private SaleInfo saleInfo;

    private String etag;

    private SearchInfo searchInfo;

    private VolumeInfo volumeInfo;

    private String selfLink;

    private AccessInfo accessInfo;

    private String kind;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public SaleInfo getSaleInfo ()
    {
        return saleInfo;
    }

    public void setSaleInfo (SaleInfo saleInfo)
    {
        this.saleInfo = saleInfo;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public SearchInfo getSearchInfo ()
    {
        return searchInfo;
    }

    public void setSearchInfo (SearchInfo searchInfo)
    {
        this.searchInfo = searchInfo;
    }

    public VolumeInfo getVolumeInfo ()
    {
        return volumeInfo;
    }

    public void setVolumeInfo (VolumeInfo volumeInfo)
    {
        this.volumeInfo = volumeInfo;
    }

    public String getSelfLink ()
    {
        return selfLink;
    }

    public void setSelfLink (String selfLink)
    {
        this.selfLink = selfLink;
    }

    public AccessInfo getAccessInfo ()
    {
        return accessInfo;
    }

    public void setAccessInfo (AccessInfo accessInfo)
    {
        this.accessInfo = accessInfo;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", saleInfo = "+saleInfo+", etag = "+etag+", searchInfo = "+searchInfo+", volumeInfo = "+volumeInfo+", selfLink = "+selfLink+", accessInfo = "+accessInfo+", kind = "+kind+"]";
    }
}
			
			