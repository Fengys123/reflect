package com.dlut;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class IDE
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //\r表示回车  return到当前行的最左边
        //\n向下移动一行,并不移动左右
        String ln = "\r\n";

        //获取.class所在的位置
        String packageName = IDE.class.getPackage().getName();
        String ClassName = "Tom";
        String ClassFullName = packageName + "." + ClassName;

        StringBuffer sf = new StringBuffer();
        sf.append("package com.dlut;" + ln);
//        sf.append("import" + ln);
        sf.append("public class " + ClassName +"{" + ln);
        sf.append("public String name;" + ln);
        sf.append("public void say(){" + ln);
        sf.append("System.out.println(\"hello\");" + ln);
        sf.append("}" + ln);
        sf.append("}" + ln);


        System.out.println(packageName);
        String basePath = IDE.class.getResource("").getPath();
        //.replaceAll(packageName.replaceAll("\\.","/"),"");
        System.out.println(IDE.class.getResource("").getPath());
        System.out.println(basePath);
        File srcFile = new File(basePath,ClassName + ".java");

        //创造文件,将文件写入.java文件
        FileWriter fw = new FileWriter(srcFile);
        fw.write(sf.toString());
        fw.flush();
        fw.close();
        System.out.println(sf);



        //编译为.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        Iterable iterator = manager.getJavaFileObjects(srcFile);
//        Iterator iterator1 = (Iterator)manger.getJavaFileObjects(srcFile); 区别在哪儿

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterator);
        task.call();
        manager.close();


        System.out.println("basepath:" + basePath);
        //找到源代码,通过ClassLoader加载到我们的JVM
        Class clazz = new MyClassLoader(basePath).findClass(ClassName);

        Object a = clazz.newInstance();
        Method method = clazz.getMethod("say");
        method.invoke(a);
    }
}
