package com.codepath.kpu.feed.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kpu on 2/14/16.
 */
public class NFSearchSettingsModel implements Parcelable {

    public boolean selectedWorld;
    public boolean selectedUS;
    public boolean selectedBusiness;
    public boolean selectedTech;
    public boolean selectedScience;
    public boolean selectedSports;
    public boolean selectedArts;

    public int beginDateMonth;
    public int beginDateDay;
    public int beginDateYear;

    public SortOrder sortOrder;

    public NFSearchSettingsModel() {
        selectedWorld = false;
        selectedUS = false;
        selectedBusiness = false;
        selectedTech = false;
        selectedScience = false;
        selectedSports = false;
        selectedArts = false;

        beginDateDay = 1;
        beginDateMonth = 0;
        beginDateYear = 1970;

        sortOrder = SortOrder.DEFAULT;
    }

    public enum SortOrder {
        DEFAULT("Default", ""),
        NEWEST_FIRST ("Newest First", "newest"),
        OLDEST_FIRST ("Oldest First", "oldest");

        private String displayTitle;
        private String parameter;

        SortOrder(String displayTitle, String parameter) {
            this.displayTitle = displayTitle;
            this.parameter = parameter;
        }

        @Override
        public String toString() {
            return displayTitle;
        }

        public String getDisplayTitle() {
            return displayTitle;
        }

        public String getParameter() {
            return parameter;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(selectedWorld ? (byte) 1 : (byte) 0);
        dest.writeByte(selectedUS ? (byte) 1 : (byte) 0);
        dest.writeByte(selectedBusiness ? (byte) 1 : (byte) 0);
        dest.writeByte(selectedTech ? (byte) 1 : (byte) 0);
        dest.writeByte(selectedScience ? (byte) 1 : (byte) 0);
        dest.writeByte(selectedSports ? (byte) 1 : (byte) 0);
        dest.writeByte(selectedArts ? (byte) 1 : (byte) 0);
        dest.writeInt(this.beginDateMonth);
        dest.writeInt(this.beginDateDay);
        dest.writeInt(this.beginDateYear);
        dest.writeInt(this.sortOrder == null ? -1 : this.sortOrder.ordinal());
    }

    private NFSearchSettingsModel(Parcel in) {
        this.selectedWorld = in.readByte() != 0;
        this.selectedUS = in.readByte() != 0;
        this.selectedBusiness = in.readByte() != 0;
        this.selectedTech = in.readByte() != 0;
        this.selectedScience = in.readByte() != 0;
        this.selectedSports = in.readByte() != 0;
        this.selectedArts = in.readByte() != 0;
        this.beginDateMonth = in.readInt();
        this.beginDateDay = in.readInt();
        this.beginDateYear = in.readInt();
        int tmpSortOrder = in.readInt();
        this.sortOrder = tmpSortOrder == -1 ? null : SortOrder.values()[tmpSortOrder];
    }

    public static final Parcelable.Creator<NFSearchSettingsModel> CREATOR = new Parcelable.Creator<NFSearchSettingsModel>() {
        public NFSearchSettingsModel createFromParcel(Parcel source) {
            return new NFSearchSettingsModel(source);
        }

        public NFSearchSettingsModel[] newArray(int size) {
            return new NFSearchSettingsModel[size];
        }
    };
}
