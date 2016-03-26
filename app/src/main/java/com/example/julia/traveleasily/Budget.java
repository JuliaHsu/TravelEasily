package com.example.julia.traveleasily;

/**
 * Created by Julia on 2016/3/4.
 */
public class Budget implements java.io.Serializable  {
    private int id;
    private int totalIncome,totalExpense;
    private String item;
    private int amount;
    private String category;
    private long date;
    private String note;


    public Budget(){
        totalExpense=0;
        totalIncome=0;
        item="";
        amount=0;
        category="";
        date=0;
        note="";
    }

    public Budget(int id,int totalIncome,int totalExpense,String item,
                  int amount,String category,long date, String note){
        this.id=id;
        this.totalIncome=totalIncome;
        this.totalExpense=totalExpense;
        this.item=item;
        this.amount=amount;
        this.category=category;
        this.date=date;
        this.note=note;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getTotalIncome(){
        return totalIncome;
    }
    public void setTotalIncome(int totalIncome){
        this.totalIncome=totalIncome;
    }
    public int getTotalExpense(){
        return totalExpense;
    }
    public void setTotalExpense(int totalExpense){
        this.totalExpense=totalExpense;
    }
    public String getItem(){
        return item;
    }
    public void setItem(String item){
        this.item=item;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount= amount;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public long getDate(){
        return date;
    }
    public void setDate(long date){
        this.date=date;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note=note;
    }

}
