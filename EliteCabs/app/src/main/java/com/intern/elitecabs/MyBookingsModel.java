package com.intern.elitecabs;

public class MyBookingsModel {
    String cabtype,date,seatno,seatprice,starttime,tofrom,cabno,cabcolor,cabmodel;

    public String getCabno() {
        return cabno;
    }

    public void setCabno(String cabno) {
        this.cabno = cabno;
    }

    public String getCabcolor() {
        return cabcolor;
    }

    public void setCabcolor(String cabcolor) {
        this.cabcolor = cabcolor;
    }

    public String getCabmodel() {
        return cabmodel;
    }

    public void setCabmodel(String cabmodel) {
        this.cabmodel = cabmodel;
    }

    public MyBookingsModel(String cabno, String cabcolor, String cabmodel) {
        this.cabno = cabno;
        this.cabcolor = cabcolor;
        this.cabmodel = cabmodel;
    }

    public  MyBookingsModel(){

    }
    public String getCabtype() {
        return cabtype;
    }

    public void setCabtype(String cabtype) {
        this.cabtype = cabtype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getSeatprice() {
        return seatprice;
    }

    public void setSeatprice(String seatprice) {
        this.seatprice = seatprice;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTofrom() {
        return tofrom;
    }

    public void setTofrom(String tofrom) {
        this.tofrom = tofrom;
    }

    public MyBookingsModel(String cabtype, String date, String seatno, String seatprice, String starttime, String tofrom) {
        this.cabtype = cabtype;
        this.date = date;
        this.seatno = seatno;
        this.seatprice = seatprice;
        this.starttime = starttime;
        this.tofrom = tofrom;
    }
}
