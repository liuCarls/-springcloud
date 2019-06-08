package com.lgx.springmvc.activity.entity;

public class Person {
    private Long personId;
    private String personName;
    private Comp comp;

    public Person(String personName) {
        this.personName = personName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Comp getComp() {
        return comp;
    }

    public void setComp(Comp comp) {
        this.comp = comp;
    }
}
