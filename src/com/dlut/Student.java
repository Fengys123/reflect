package com.dlut;


public class Student extends Person implements GoodStudent
{
    private String school;
    int clazz;
    float score;

    public String getSchool()
    {
        return school;
    }

    @Override
    public void studyHard(String book)
    {
        System.out.println("正在刻苦学习" + book +"!!!");
    }

    public void studyHard(String book,String name)
    {
        System.out.println("正在刻苦学习" + book + name +"!!!");
    }
}
