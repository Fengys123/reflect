package com.dlut;

import java.io.*;

public class MyClassLoader extends ClassLoader
{
    File baseDir;

    public MyClassLoader(String basePath)
    {
        baseDir = new File(basePath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        if(baseDir != null)
        {
            File classFile = new File(baseDir,name.replaceAll("\\.","/") + ".class");
            System.out.println("name" + name);
            System.out.println("classFile" + classFile);
            System.out.println("baseDir" + baseDir);
            if(classFile.exists())
            {
                System.out.println("文件存在");
                FileInputStream in = null;
                ByteArrayOutputStream out;
                try
                {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte []buff = new byte[1024];
                    int len;
                    while((len = in.read(buff)) != -1)
                    {
                        out.write(buff,0,len);
                    }

                    //这里应该是全类名  包名+类名
                    return defineClass("com.dlut.Tom",out.toByteArray(),0,out.size());
                }
                catch (Exception e)
                {

                }
                finally
                {
                    if(in != null)
                    {
                        try
                        {
                            in.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }


            }
        }

        return super.findClass(name);
    }
}
