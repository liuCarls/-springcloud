package com.lgx.springmvc.activity.entity;

import java.util.ArrayList;
import java.util.List;

public class Comp {
    private Long compId;
    private String compName;
    private List<Person> people;
    public Comp(String compName){
        this.compName = compName;
        this.people = new ArrayList<>();
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPeople(Person people) {
        this.people.add(people);
    }
}
