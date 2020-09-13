package com.intern.elitecabs;

public class CablistModel extends  CabslistId{
    String cabtype,endtime,starttime,seatprice,from,to,state,driverid,cabno,cabcolor,cabmodel;

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

    public CablistModel(String cabno, String cabcolor, String cabmodel) {
        this.cabno = cabno;
        this.cabcolor = cabcolor;
        this.cabmodel = cabmodel;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public CablistModel(String driverid) {
        this.driverid = driverid;
    }

    public CablistModel() {

    }

    public String getCabtype() {
        return cabtype;
    }

    public void setCabtype(String cabtype) {
        this.cabtype = cabtype;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getSeatprice() {
        return seatprice;
    }

    public void setSeatprice(String seatprice) {
        this.seatprice = seatprice;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CablistModel(String cabtype, String endtime, String starttime, String seatprice, String from, String to, String state) {
        this.cabtype = cabtype;
        this.endtime = endtime;
        this.starttime = starttime;
        this.seatprice = seatprice;
        this.from = from;
        this.to = to;
        this.state = state;
    }
}
