package com.example.datebasetest;

public class Booking { //請根據資料來建立自己的資料型態
    private String userID;
    private String  telephone;
    private String date;
    private String booking_date;

    public String getUserID(){
        return userID;
    } //取得當前user的ID
    public void setUserID(String userID){
        this.userID = userID;
    }
    public String getTelephone(){
        return telephone;
    }//取得當前user的電話
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getDate(){
        return date;
    } //取得這個user是哪一天訂的位
    public void setDate(String date){
        this.date = date;
    }
    public  String getBooking_date(){
        return  booking_date;
    } //取得當前user訂了哪一天的位置
    public  void setBooking_date(String booking_date){
        this.booking_date = booking_date;
    }

    public Booking(String userID,String telephone,String date,String booking_date){ //建構子
        this.userID =userID;
        this.telephone = telephone;
        this.date = date;
        this.booking_date = booking_date;
    }


}
