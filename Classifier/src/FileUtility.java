import java.io.*;

public class FileUtility {

    private static FileUtility instance;

    public static FileUtility getInstance() {
        if (instance == null) {
            instance = new FileUtility();
        }
        return instance;
    }

    private final File getResource(String filepath) {
        try{
            File file = new File(this.getClass().getResource(filepath).getPath());
            return file;
        }catch (Exception e) {
           // e.printStackTrace();
            System.out.println("未找到文件！");
            return null;
        }
    }

    public String readFileLine(String filepath, int lineNumber){
        String str = "";
        File file = getResource(filepath);
        FileReader in = null;
        try {
            in = new FileReader(file);
            LineNumberReader reader = new LineNumberReader(in);
            String s = reader.readLine();
            if (lineNumber < 0 || lineNumber > getTotalLineNumber(filepath)) {
                System.out.println("不在文件的行数范围之内。");
            }
            {
                while (s != null) {
                    //System.out.println("当前行号为:" + reader.getLineNumber());
                    if (reader.getLineNumber() == lineNumber) {
                        //System.out.println(s);
                        if(s.contains(" ")){
                            System.out.println("【提示：字符间有空格，已自动删除。】");
                        }
                        s = s.replaceAll(" ","");
                        str = s;
                    }
                    s = reader.readLine();
                }
            }
            reader.close();
            in.close();
            return str;
        } catch (IOException e) {
            System.out.println("未找到文件！");
            return "";
        }
    }

    public int getTotalLineNumber(String filepath) throws IOException {
        File file = getResource(filepath);
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




