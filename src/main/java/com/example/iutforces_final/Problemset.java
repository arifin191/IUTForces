package com.example.iutforces_final;

public class Problemset {
    static  private Integer problemID;
    static private String problem_name , problem_author;


    public Problemset(Integer pID, String pname, String pauthor) {
        problemID = pID;
        problem_name = pname;
        problem_author = pauthor;
    }

    public void setProblemID(int pid)
    {
        this.problemID=pid;
    }
    public void setProblem_name(String p_name)
    {
        this.problem_name=p_name;
    }
    public void setProblem_author(String p_author)
    {
        this.problem_author = p_author;
    }

    public int getProblemID()
    {
        return problemID;
    }
    public String getProblem_name()
    {
        return problem_name;
    }
    public String getProblem_author()
    {
        return problem_author;
    }




}


