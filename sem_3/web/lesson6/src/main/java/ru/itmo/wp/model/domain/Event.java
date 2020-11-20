package ru.itmo.wp.model.domain;

import java.util.Date;

public class Event {
    private long id;
    private Type type;
    private long userid;
    private Date creationTime;

    public long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public long getUserid() {
        return userid;
    }

    public enum Type {
        ENTER, LOGOUT
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

}
