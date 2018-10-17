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
            if(classFile.exists())
            {
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
                    return defineClass(name,out.toByteArray(),0,out.size());
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
