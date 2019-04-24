package com.Attra.Payer.ServiceRequests.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginResponse implements Parcelable {

    private String id;
    private String ttl;
    private String created;
    private String userId;
    private UserDetails user;

    public LoginResponse(String id, String ttl, String created, String userId, UserDetails user) {
        this.id = id;
        this.ttl = ttl;
        this.created = created;
        this.userId = userId;
        this.user = user;
    }

    protected LoginResponse(Parcel in) {
        id = in.readString();
        ttl = in.readString();
        created = in.readString();
        userId = in.readString();
        user=(UserDetails)in.readParcelable(UserDetails.class.getClassLoader());
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTtl() {
        return ttl;
    }

    public String getCreated() {
        return created;
    }

    public String getUserId() {
        return userId;
    }

    public UserDetails getUser() {
        return user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(ttl);
        dest.writeString(created);
        dest.writeString(userId);
        dest.writeParcelable(user,1);
    }
}
