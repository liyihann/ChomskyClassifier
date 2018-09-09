package pers.liyihan;

import java.io.*;

public class FileUtility {

    private static FileUtility instance;

    public static FileUtility getInstance() {
        if (instance == null) {
            instance = new FileUtility();
        }
        return instance;
    }

    public void readFileLine(String filepath, int lineNumber){
        File sourceFile = new File(filepath);
        FileReader in = null;
        try {
            in = new FileReader(sourceFile);
            LineNumberReader reader = new LineNumberReader(in);
            String s = reader.readLine();
            if (lineNumber < 0 || lineNumber > getTotalLineNumber(filepath)) {
                System.out.println("不在文件的行数范围之内。");
            }
            {
                while (s != null) {
                    //System.out.println("当前行号为:" + reader.getLineNumber());
                    if (reader.getLineNumber() == lineNumber) {
                        System.out.println(s);
                    }
                    s = reader.readLine();
                }
            }
            reader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalLineNumber(String filepath) throws IOException {
        File file = new File(filepath);
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }

}




