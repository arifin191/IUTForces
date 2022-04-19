package com.example.iutforces_final;

public class Status {
    private String stname, pname, sbtime, etime, verdict, lang;
    public Status(String _stname, String _pname, String _sbtime, String _etime, String _verdict, String _lang) {
        stname = _stname;
        pname = _pname;
        sbtime = _sbtime;
        etime = _etime;
        verdict = _verdict;
        lang = _lang;
    }

    public void setStname(String s) {this.stname = s;}
    public void setPname(String s) {this.pname = s;}
    public void setStime(String s) {this.sbtime = s;}
    public void setEtime(String s) {this.etime = s;}

    public void setLanguage(String s) {this.lang = s;}
    public void setVerdict(String s) {this.verdict = s;}


    public String getStname() {return stname;}
    public String getPname() {return pname;}
    public String getStime() {return sbtime;}
    public String getEtime() {return etime;}
    public String getLanguage() {return lang;}
    public String getVerdict() {return verdict;}



}
