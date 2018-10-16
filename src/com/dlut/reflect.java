package com.dlut;

/**
 * .class 静态属性，直接拿到字节码内容
 * 。forname("com.dlut.test")
 *
 *  字节码文件的抽象
 */
public class reflect
{
    public static void main(String[] args)
    {
        //Student student = new Student();

        //使用反射
        try
        {
            //获取字节码对象
            Class clazz = Class.forName("com.dlut.Student");
            //new 了一个Student
            Object student = clazz.newInstance();
            clazz.getConstructor();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
