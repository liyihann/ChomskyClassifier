package pers.liyihan;

import java.io.IOException;

public class Grammar {
    protected char start;
    protected char[] Vn;
    protected String[] rule;

    public void getStartLine(){
        // 读取文件

        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber("/Users/liyihan/Documents/PROJECT/IDEA/ChomskyClassifier/src/pers/liyihan/grammar.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("总行数："+total);

        // 指定读取的行号
        int lineNumber = 1;

        // 读取指定的行
        FileUtility.getInstance().readFileLine("/Users/liyihan/Documents/PROJECT/IDEA/ChomskyClassifier/src/pers/liyihan/grammar.txt", lineNumber);

    }

    public void getVnLine(){
        // 读取文件

        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber("/Users/liyihan/Documents/PROJECT/IDEA/ChomskyClassifier/src/pers/liyihan/grammar.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("总行数："+total);

        // 指定读取的行号
        int lineNumber = 2;

        // 读取指定的行
        FileUtility.getInstance().readFileLine("/Users/liyihan/Documents/PROJECT/IDEA/ChomskyClassifier/src/pers/liyihan/grammar.txt", lineNumber);

    }

    public void getRuleLine(){
        // 读取文件

        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber("/Users/liyihan/Documents/PROJECT/IDEA/ChomskyClassifier/src/pers/liyihan/grammar.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("总行数："+total);

        // 指定读取的行号
        int lineNumber = 3;

        for(int i=lineNumber;i<=total;i++){
            // 读取指定的行
            lineNumber = i;
            FileUtility.getInstance().readFileLine("/Users/liyihan/Documents/PROJECT/IDEA/ChomskyClassifier/src/pers/liyihan/grammar.txt", lineNumber);
        }
    }

}
