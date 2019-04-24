package com.Attra.Payer.ServiceRequests.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDetails implements Parcelable {


    private String firstName;
    private String lastName;
    private String mobile;
    private int empId;
    private String Location;
    private int mPin;
    private String realm;
    private String username;
    private String email;
    private boolean emailVerified;
    private String id;
    private String password;
    private boolean forgotPasswordOTPVerified;
    private String message;

    public UserDetails(String firstName, String lastName, String mobile,
                       int empId, String location, int mPin, String realm,
                       String username, String email, boolean emailVerified,
                       String id, boolean forgotPasswordOTPVerified,
                       String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.empId = empId;
        Location = location;
        this.mPin = mPin;
        this.realm = realm;
        this.username = username;
        this.email = email;
        this.emailVerified = emailVerified;
        this.id = id;
        this.forgotPasswordOTPVerified = forgotPasswordOTPVerified;
        this.message = message;
    }

    protected UserDetails(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        mobile = in.readString();
        empId = in.readInt();
        Location = in.readString();
        mPin = in.readInt();
        realm = in.readString();
        username = in.readString();
        email = in.readString();
        emailVerified = in.readByte() != 0;
        id = in.readString();
        password = in.readString();
        forgotPasswordOTPVerified = in.readByte() != 0;
        message = in.readString();
    }

    public static final Creator<UserDetails> CREATOR = new Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel in) {
            return new UserDetails(in);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public int getEmpId() {
        return empId;
    }

    public String getLocation() {
        return Location;
    }

    public int getmPin() {
        return mPin;
    }

    public String getRealm() {
        return realm;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public String getId() {
        return id;
    }

    public boolean isForgotPasswordOTPVerified() {
        return forgotPasswordOTPVerified;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(mobile);
        dest.writeInt(empId);
        dest.writeString(Location);
        dest.writeInt(mPin);
        dest.writeString(realm);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeByte((byte) (emailVerified ? 1 : 0));
        dest.writeString(id);
        dest.writeString(password);
        dest.writeByte((byte) (forgotPasswordOTPVerified ? 1 : 0));
        dest.writeString(message);
    }
}
