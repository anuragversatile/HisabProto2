package com.mycompany.hisabproto2;
/**
 * Created by Abhishek PC on 10-Dec-15.
 */
public class DataRecord {
   private String from;
   private String to;
   private int amount;
    public DataRecord(String from,String to,int amount){
        this.from=from;
        this.to=to;
        this.amount=amount;
    }
    public String getFrom(){return from;}
    public String getTo(){return to;}
    public int getAmount(){return amount;}
}
