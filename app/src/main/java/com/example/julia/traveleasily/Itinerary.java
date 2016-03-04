package com.example.julia.traveleasily;

/**
 * Created by Julia on 2016/3/2.
 */
public class Itinerary implements java.io.Serializable{
    private long id;
    private long createDataDate;
    private long dateFrom;
    private long dateTo;
    private String departText;
    private String destText;
    private String AirBusText;
    private String note;
    private boolean selected;

    public Itinerary(){
        departText="";
        destText="";
        AirBusText="";
        note="";
    }

    public Itinerary(long id,long dateFrom,long dateTo,String departText,
                     String destText,String AirBusText,String note){
        this.id = id;

        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.departText = departText;
        this.destText = destText;
        this.AirBusText = AirBusText;
        this.note = note;

    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public long getDateFrom(){
        return dateFrom;
    }
    public void setDateFrom(long dateFrom){
        this.dateFrom=dateFrom;
    }
    public long getDateTo(){
        return dateTo;
    }
    public void setDateTo(long dateTo){
        this.dateTo = dateTo;
    }
    public String getDepartText(){
        return departText;
    }
    public void setDepartText(String departText){
        this.departText=departText;
    }
    public String getDestText(){
        return destText;
    }
    public void setDestText(String destText){
        this.destText = destText;
    }
    public String getAirBusText(){
        return AirBusText;
    }
    public void setAirBusText(String AirBusText){
        this.AirBusText= AirBusText;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public boolean isSeleced(){
        return selected;
    }
    public void setSelected(boolean selected){
        this.selected = selected;
    }


}
