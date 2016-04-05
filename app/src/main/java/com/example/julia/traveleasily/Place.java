package com.example.julia.traveleasily;

/**
 * Created by Julia on 2016/4/4.
 */
    public class Place implements java.io.Serializable {
        private long id;
        private String placeName;
        private String placeAddress;
        private String phoneNumber;
        private String website;
        private String fileName;
        private String note;

        private boolean selected;


        public Place(){
            id=0;
            placeName="";
            placeAddress="";
            phoneNumber="";
            website="";
            note="";
        }

        public Place(long id,String placeName,String placeAddress,String phoneNumber,String website,String note){
            this.id = id;
            this.placeName=placeName;
            this.placeAddress=placeAddress;
            this.phoneNumber=phoneNumber;
            this.website=website;
            this.note=note;

        }

        public long getId(){
            return id;
        }
        public void setId(long id){
            this.id=id;
        }
        public String getPlaceName(){
            return placeName;
        }
        public void setPlaceName(String placeName){
            this.placeName=placeName;
        }
        public String getPlaceAddress(){
            return placeAddress;
        }
        public void setPlaceAddress(String placeAddress){
            this.placeAddress=placeAddress;
        }
        public String getPhoneNumber(){
            return phoneNumber;
        }
        public void setPhoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
        }
        public String getFileName(){
            return fileName;
        }
        public void setFileName(String fileName){
            this.fileName=fileName;
        }
        public String getWebsite(){
            return website;
        }
        public void setWebsite(String website){
            this.website=website;
        }
        public String getNote(){
            return note;
        }
        public void setNote(String note){
            this.note = note;
        }
         public boolean isSelected(){
        return selected;
    }
        public void setSelected(boolean selected){
        this.selected = selected;
    }

    }


