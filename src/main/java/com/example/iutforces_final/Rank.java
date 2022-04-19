package com.example.iutforces_final;

public class Rank {
    private Integer ranking, count;
    private String stname;
    public Rank(Integer ranking_, String name_, Integer count_) {
        ranking = ranking_;
        stname = name_;
        count = count_;
    }

    public void setRanking(Integer i) {ranking = i;}
    public void setStname(String s) {stname = s;}
    public void setCount(Integer i) {count = i;}

    public Integer getRanking() {return ranking;}
    public String getStname() {
        return stname;
    }
    public Integer getCount() {
        return count;
    }
}
