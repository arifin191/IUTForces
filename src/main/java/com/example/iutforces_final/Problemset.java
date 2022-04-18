package com.example.iutforces_final;

public class Problemset {
      private Integer problemID, time_limit, memory_limit;
     private String problem_name ;


    public Problemset(Integer pID, String pname, Integer time, Integer memory) {
        problemID = pID;
        problem_name = pname;
        time_limit = time;
        memory_limit = memory;
    }

    public void setProblemID(int pid)
    {
        this.problemID=pid;
    }
    public void setProblem_name(String p_name)
    {
        this.problem_name=p_name;
    }
    public void setTime_limit(int time)
    {
        this.time_limit = time;
    }
    public void setMemory_limit(int memory) { this.memory_limit = memory;}

    public int getProblemID()
    {
        return problemID;
    }
    public String getProblem_name()
    {
        return problem_name;
    }
    public int getTime_limit()
    {
        return time_limit;
    }
    public int getMemory_limit()
    {
        return memory_limit;
    }




}


