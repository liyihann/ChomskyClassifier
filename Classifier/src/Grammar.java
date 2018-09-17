import java.io.CharConversionException;
import java.io.IOException;
import java.util.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Grammar {
    private char start;
    private ArrayList<Character> Vn;
    private ArrayList<String> rule;
    private String filename;
    private ArrayList<Character> Vt;

    public Grammar() { }

    public Grammar(String filename) {
        this.start = 0;
        this.Vn=new ArrayList<Character>();
        this.rule=new ArrayList<String>();
        this.filename = filename;
        this.Vt = new ArrayList<Character>();
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
        String startLine = FileUtility.getInstance().readFileLine(this.filename+".txt", lineNumber);
        System.out.println("文法："+startLine);

        //切割出文法开始符号
        int strStartIndex = startLine.indexOf("[");
        int strEndIndex = startLine.indexOf("]");// index 为负数 即表示该字符串中 没有该字符
        if (strStartIndex >= 0 && strEndIndex >= 0 ||strEndIndex>strStartIndex) {
            start = startLine.substring(strStartIndex, strEndIndex).substring("[".length()).charAt(0);
        }
        System.out.println("开始符号："+start);
    }


    public void getVn(){
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
        String VnLine = FileUtility.getInstance().readFileLine(this.filename+".txt", lineNumber);
        //System.out.println(VnLine);
        StringTokenizer st = new StringTokenizer(VnLine,",");
        while(st.hasMoreElements()){
            //System.out.println("Token:" + st.nextToken());
            Vn.add(st.nextToken().charAt(0));
        }
        System.out.println("非终结符："+Vn.toString());
    }

    public void getRuleLine(){
        // 获取文件的内容的总行数
        int total = 0;
        try {
            total = FileUtility.getInstance().getTotalLineNumber(this.filename+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "";
        // 指定读取的行号
        int lineNumber = 3;
        for(int i=lineNumber;i<=total;i++){
            // 读取指定的行
            lineNumber = i;
            str = FileUtility.getInstance().readFileLine(this.filename+".txt", lineNumber);
            rule.add(str);
        }
        System.out.println("规则："+rule.toString());
    }

    public void getVt(){
        ArrayList<Character> all = new ArrayList<>();
        for(String s : rule){
            String[] left_right = s.split("::=");

            //处理产生式左部字符
            for(int i = 0; i<left_right[0].trim().length();i++){
                if(!all.contains(left_right[0].charAt(i))){
                    all.add(left_right[0].charAt(i));
                }
            }
            //处理产生式右部字符
            String[] right = left_right[1].split("\\|");//右部各元素以"|"分隔
            for(String sr : right){
                sr = sr.trim();
                for(int i=0;i<sr.length();i++){
                    if(!all.contains(sr.charAt(i)) && sr.charAt(i)!='ε'){
                        all.add(sr.charAt(i));
                    }
                }
            }
        }
        try{
            all.removeAll(Vn);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<all.size();i++){
            Vt.add(all.get(i));
        }
        System.out.println("终结符："+Vt.toString());
    }

    public void printGrammar(){
        System.out.println();
        String g = "G["+start+"]";
        String vn = "{";
        for(int i=0;i<Vn.size()-1;i++){
            vn = vn + this.Vn.get(i).toString();
            vn+=",";
        }
        vn+=Vn.get(Vn.size()-1)+"}";

        String vt = "{";
        for(int i = 0;i<Vt.size()-1;i++){
            vt = vt + this.Vt.get(i).toString();
            vt+=",";
        }
        vt+=Vt.get(Vt.size()-1)+"}";

        System.out.println("文法 "+g+" = ("+vn+", "+vt+", P, "+start+")");

        String firstRule = this.rule.get(0);
        System.out.println("P:\t"+firstRule);
        for(int i = 1;i<this.rule.size();i++) {
            System.out.println("\t" + this.rule.get(i));
        }
    }

    public void identifyGrammar(){



        System.out.println("该文法是Chomsky？型文法");
    }

}
