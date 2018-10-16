package com.dlut;


public class Student extends Person implements GoodStudent
{
    String school;
    int clazz;
    float score;

    @Override
    public void studyHard()
    {
        System.out.println("正在刻苦学习!!!");
    }
}
