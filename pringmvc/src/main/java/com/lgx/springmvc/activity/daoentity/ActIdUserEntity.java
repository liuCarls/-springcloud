package com.lgx.springmvc.activity.daoentity;

//import javax.persistence.Column;
//import javax.persistence.Table;

/**
 * Created by Administrator on 2018/9/5.
 */
//@Table(name = "act_id_user")
public class ActIdUserEntity {

//    @Column(name = "ID_")
    private String id;

//    @Column(name = "REV_")
    private int rev;
//    @Column(name = "FIRST_")
    private String first;
//    @Column(name = "LAST_")
    private String last;
//    @Column(name = "EMAIL_")
    private String email;
//    @Column(name = "PWD_")
    private String pwd;
//    @Column(name = "PICTURE_ID_")
    private String picture_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    @Override
    public String toString() {
        return "ActIdUserEntity{" +
                "id='" + id + '\'' +
                ", rev=" + rev +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", picture_id='" + picture_id + '\'' +
                '}';
    }
}
