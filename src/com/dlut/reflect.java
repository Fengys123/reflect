package com.dlut;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            //获取字节码对象  直接获取内存中的class文件
            Class clazz = Class.forName("com.dlut.Student");
            //1.相当于new 了一个Student
            Student student = (Student) clazz.newInstance();



            clazz.getConstructor();

            Method method = clazz.getMethod("studyHard", String.class);
            Method method1 = clazz.getMethod("studyHard", new Class[]{String.class});
            Method method2 = clazz.getMethod("studyHard", String.class, String.class);
            method.invoke(student,"java");
            method1.invoke(student,"c++");
            method2.invoke(student,"C","123");

            Constructor c = clazz.getConstructor();
            //2.利用反射获取的构造函数 进行对象的创建
            Student objStudent = (Student) c.newInstance();
            method.invoke(objStudent,"java");

            System.out.println("Student中的方法名称");
            for(Method m :clazz.getMethods())
            {
                System.out.println(m.getName());
            }
            System.out.println("over!!!");

            //Field field = clazz.getField("school");
            //field.set(student,"Tom");

            Field field1 = clazz.getDeclaredField("school");
            field1.setAccessible(true);
            field1.set(student,"Tom");
            System.out.println("利用反射强行改变private对象" + student.getSchool());
            System.out.println("利用反射强行改变private对象" + field1.get(student));

            //他不能获取到父类的属性
            Field field2 = clazz.getSuperclass().getDeclaredField("name");
            field2.setAccessible(true);
            field2.set(student,"NameTom");
            System.out.println("利用反射强行改变private对象" + field1.get(student));

            //获取接口
            System.out.println(clazz.getInterfaces()[0].toString());
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
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }
}
