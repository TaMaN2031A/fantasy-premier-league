package com.fantasy.fantasyleague.Request;


import java.io.Serializable;
import java.util.Objects;

public class RequestKey implements Serializable {
    private int requestID;
    private String userName;
    private String email;

    public RequestKey() {
    }

    public RequestKey(int requestID, String userName, String email) {
        this.requestID = requestID;
        this.userName = userName;
        this.email = email;
    }

    // Getters and Setters
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // HashCode and Equals
    @Override
    public int hashCode() {
        return Objects.hash(requestID, userName, email);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestKey other = (RequestKey) obj;
        return requestID == other.requestID &&
                Objects.equals(userName, other.userName) &&
                Objects.equals(email, other.email);
    }
}