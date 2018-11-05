import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class fileTest {
    public static File pack(List<File> sources, File target){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(target);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        for (File file : sources) {
            try {
                os.putArchiveEntry(new TarArchiveEntry(file));
                IOUtils.copy(new FileInputStream(file), os);
                os.closeArchiveEntry();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return target;
    }

    public static File packFile(File file, File target){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(target);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);


        try {
            os.putArchiveEntry(new TarArchiveEntry(file));
            IOUtils.copy(new FileInputStream(file), os);
            os.closeArchiveEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return target;
    }

    public static File compress(File source,String filePath) {
        File target = new File(filePath+"/"+source.getName() + ".gz");
        FileInputStream in = null;
        GZIPOutputStream out = null;

        try {
            in = new FileInputStream(source);
            out = new GZIPOutputStream(new FileOutputStream(target));
            byte[] array = new byte[1024];
            int number = -1;
            while((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }

            out.close();
            System.out.println("ѹ  "+target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

        }
        return target;
    }

    public static void main(String[] args) throws Exception{
        File file = new File("/home/fys/IdeaProjects/reflect/out/production/Reflect-demo/com/dlut/Tom.class");
        if(file.exists()) {
            System.out.println("文件存在!!!");
        }

        File file1 = new File("/home/fys/IdeaProjects/reflect/out/production/Reflect-demo/com/dlut/Tom.java");
        if(file.exists()) {
            System.out.println("文件存在!!!");
        }

        List<File> list = new ArrayList<>();
        list.add(file);
        list.add(file1);

        File file2 = new File("/home/fys/IdeaProjects/reflect/out/production/Reflect-demo/com/dlut/Tom1.tar");
        if(!file2.exists()){
            file2.createNewFile();

        }
        //fileTest.packFile(file,file2);

        //fileTest.packFile(file1,file2);
        //file.delete();


        String filePath = new String("/home/fys/IdeaProjects/reflect/out/production/Reflect-demo/com/dlut");
        fileTest.pack(list,file2);
        fileTest.compress(file2,filePath);
        file.delete();
        file1.delete();
    }
}
