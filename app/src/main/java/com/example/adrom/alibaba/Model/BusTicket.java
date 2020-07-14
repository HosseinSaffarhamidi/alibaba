package com.example.adrom.alibaba.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BusTicket implements Parcelable { //همه parcelable است بحز chair
    private String id;
    private String ticketId;
    private String origin;
    private String originTerminal;
    private String destination;
    private String destinationTermianl;
    private String type;
    private String date;
    private String time;
    private String price;
    private String distance;
    private String capacity;
    private List<Chair> chairs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginTerminal() {
        return originTerminal;
    }

    public void setOriginTerminal(String originTerminal) {
        this.originTerminal = originTerminal;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationTermianl() {
        return destinationTermianl;
    }

    public void setDestinationTermianl(String destinationTermianl) {
        this.destinationTermianl = destinationTermianl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ticketId);
        dest.writeString(this.origin);
        dest.writeString(this.originTerminal);
        dest.writeString(this.destination);
        dest.writeString(this.destinationTermianl);
        dest.writeString(this.type);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.price);
        dest.writeString(this.distance);
        dest.writeString(this.capacity);
//        dest.writeList(this.chairs);
    }

    public BusTicket() {
    }

    protected BusTicket(Parcel in) {
        this.id = in.readString();
        this.ticketId = in.readString();
        this.origin = in.readString();
        this.originTerminal = in.readString();
        this.destination = in.readString();
        this.destinationTermianl = in.readString();
        this.type = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.price = in.readString();
        this.distance = in.readString();
        this.capacity = in.readString();
//        this.chairs = new ArrayList<Chair>();
//        in.readList(this.chairs, Chair.class.getClassLoader());
    }

    public static final Parcelable.Creator<BusTicket> CREATOR = new Parcelable.Creator<BusTicket>() {
        @Override
        public BusTicket createFromParcel(Parcel source) {
            return new BusTicket(source);
        }

        @Override
        public BusTicket[] newArray(int size) {
            return new BusTicket[size];
        }
    };
}
