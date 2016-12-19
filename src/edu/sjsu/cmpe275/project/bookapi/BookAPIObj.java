package edu.sjsu.cmpe275.project.bookapi;

public class BookAPIObj {
	

	    private Items[] items;

	    private String totalItems;

	    private String kind;

	    public Items[] getItems ()
	    {
	        return items;
	    }

	    public void setItems (Items[] items)
	    {
	        this.items = items;
	    }

	    public String getTotalItems ()
	    {
	        return totalItems;
	    }

	    public void setTotalItems (String totalItems)
	    {
	        this.totalItems = totalItems;
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
	        return "ClassPojo [items = "+items+", totalItems = "+totalItems+", kind = "+kind+"]";
	    }
	}
				
				

