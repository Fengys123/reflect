package com.dlut;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class IDE
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        String ln = "\r\n";
        String packageName = IDE.class.getPackage().getName();
        String ClassName = "Tom";

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

        //找到源代码,通过ClassLoader加载到我们的JVM
        Class clazz = new MyClassLoader(basePath).findClass(ClassName);
    }
}
