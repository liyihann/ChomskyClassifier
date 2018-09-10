import java.io.IOException;

public class Grammar {
    private char start;
    private char[] Vn;
    private String[] rule;
    private String filename="";

    public Grammar() {
    }
    public Grammar(String filename) {
        this.filename = filename;
    }

    public char getStart() {
        return start;
    }
    public void setStart(char start) {
        this.start = start;
    }
    public char[] getVn() {
        return Vn;
    }
    public void setVn(char[] vn) {
        Vn = vn;
    }
    public String[] getRule() {
        return rule;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public void getStartLine(){
        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber(this.filename+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 指定读取的行号
        int lineNumber = 1;
        // 读取指定的行
        FileUtility.getInstance().readFileLine(this.filename+".txt", lineNumber);

    }

    public void getVnLine(){
        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber(this.filename+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 指定读取的行号
        int lineNumber = 2;
        // 读取指定的行
        FileUtility.getInstance().readFileLine(this.filename+".txt", lineNumber);

    }

    public void getRuleLine(){
        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber(this.filename+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 指定读取的行号
        int lineNumber = 3;
        for(int i=lineNumber;i<=total;i++){
            // 读取指定的行
            lineNumber = i;
            FileUtility.getInstance().readFileLine(this.filename+".txt", lineNumber);
        }
    }

}
