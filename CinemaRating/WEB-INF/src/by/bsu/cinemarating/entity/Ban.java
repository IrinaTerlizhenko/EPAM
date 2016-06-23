package by.bsu.cinemarating.entity;

import java.sql.Timestamp;

/**
 * Created by User on 30.05.2016.
 */
public class Ban extends Entity {
    int userId;
    BanType type;
    Timestamp expiration;
    String reason;

    public Ban() {
    }

    public Ban(int id, int userId, BanType type, Timestamp expiration, String reason) {
        super(id);
        this.userId = userId;
        this.type = type;
        this.expiration = expiration;
        this.reason = reason;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BanType getType() {
        return type;
    }

    public void setType(BanType type) {
        this.type = type;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
