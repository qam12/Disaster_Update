package com.example.qamber.disaster_update.ModelClass;

/**
 * Created by qamber.haider on 6/28/2018.
 */

public class Result
{
    private String start;

    private String maxScore;

    private String name;


    private String numFound;

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    public String getMaxScore ()
    {
        return maxScore;
    }

    public void setMaxScore (String maxScore)
    {
        this.maxScore = maxScore;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }




    public String getNumFound ()
    {
        return numFound;
    }

    public void setNumFound (String numFound)
    {
        this.numFound = numFound;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [start = "+start+", maxScore = "+maxScore+", name = "+name+", numFound = "+numFound+"]";
    }
}