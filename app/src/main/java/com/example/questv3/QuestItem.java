package com.example.questv3;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestItem implements Parcelable {

    private static final String TAG = "QuestItem";
    String title, content, date;
    int icon;

    public QuestItem() {
    }

    public QuestItem(String title, String content, String date, int icon) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.icon = icon;
    }

    protected QuestItem(Parcel in) {
        title = in.readString();
        content = in.readString();
        date = in.readString();
        icon = in.readInt();
    }

    public static final Creator<QuestItem> CREATOR = new Creator<QuestItem>() {
        @Override
        public QuestItem createFromParcel(Parcel in) {
            return new QuestItem(in);
        }

        @Override
        public QuestItem[] newArray(int size) {
            return new QuestItem[size];
        }
    };

    // ===== Setters =====
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    // ===== Getters =====
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Quest[" +
                "title: " + title +
                ", date: " + date +
                ", img: " + icon + ']';
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(date);
        parcel.writeInt(icon);
    }
}
