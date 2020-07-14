package com.example.adrom.alibaba.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Chair implements Parcelable {
    private String left;
    private String leftSituation;
    private String right;
    private String rightSituation;
    private String rightOne;
    private String rightOneSituation;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getLeftSituation() {
        return leftSituation;
    }

    public void setLeftSituation(String leftSituation) {
        this.leftSituation = leftSituation;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getRightSituation() {
        return rightSituation;
    }

    public void setRightSituation(String rightSituation) {
        this.rightSituation = rightSituation;
    }

    public String getRightOne() {
        return rightOne;
    }

    public void setRightOne(String rightOne) {
        this.rightOne = rightOne;
    }

    public String getRightOneSituation() {
        return rightOneSituation;
    }

    public void setRightOneSituation(String rightOneSituation) {
        this.rightOneSituation = rightOneSituation;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.left);
        dest.writeString(this.leftSituation);
        dest.writeString(this.right);
        dest.writeString(this.rightSituation);
        dest.writeString(this.rightOne);
        dest.writeString(this.rightOneSituation);
    }

    public Chair() {
    }

    protected Chair(Parcel in) {
        this.left = in.readString();
        this.leftSituation = in.readString();
        this.right = in.readString();
        this.rightSituation = in.readString();
        this.rightOne = in.readString();
        this.rightOneSituation = in.readString();
    }

    public static final Parcelable.Creator<Chair> CREATOR = new Parcelable.Creator<Chair>() {
        @Override
        public Chair createFromParcel(Parcel source) {
            return new Chair(source);
        }

        @Override
        public Chair[] newArray(int size) {
            return new Chair[size];
        }
    };
}