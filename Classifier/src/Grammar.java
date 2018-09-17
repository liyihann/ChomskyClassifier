import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Grammar {
    private char start;
    private ArrayList<Character> Vn;
    private ArrayList<String> rule;
    private String filename;
    private ArrayList<Character> Vt;
    int type;
    private ArrayList<String> left;
    private ArrayList<String> right;

    public Grammar() { }

    public Grammar(String filename) {
        this.start = 0;
        this.Vn=new ArrayList<Character>();
        this.rule=new ArrayList<String>();
        this.filename = filename;
        this.Vt = new ArrayList<Character>();
        this.type = -1;
        this.left = new ArrayList<String>();
        this.right = new ArrayList<String>();
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
            left.add(left_right[0]);

            //处理产生式左部字符
            for(int i = 0; i<left_right[0].length();i++){
                if(!all.contains(left_right[0].charAt(i))){
                    all.add(left_right[0].charAt(i));
                }
            }
            //处理产生式右部字符
            String[] rightStr = left_right[1].split("\\|");//右部各元素以"|"分隔
            for(String sr : rightStr){
                right.add(sr);
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
        System.out.println("left："+left.toString());
        System.out.println("right："+right.toString());

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


    //1.若左部有多于一个字符或含有终结符，则为0/1型，否则为2/3型
    //2.若每个产生式中，左部字符串长度均小于等于去除空产生式的右部字符串长度，则为1型，否则为0型
    //3.若每个产生式中，右部含有不超过两个字符，则为3型，否则为2型
    //4.若每个产生式中，只要有一个含有空产生式，即为扩展的2/3型
    //5.若对于三型文法，若每个右部含有两个字符的产生式，其非终结符均在左部，则为左线性3型；若非终结符均在右部，则为右线性3型。若有的在左部有的在右部为2型。

    public void getChomskyType(){
        int t = -1;
        boolean isZerothOrFirst = false;
        boolean isExtended = false;
        boolean isLeft = false;
        boolean isLeftChanged = false;

        for(String l : left){
            if(l.length()!=1||!Vn.contains(l.charAt(0))){
                isZerothOrFirst = true;
                break;
            }
        }

        if(isZerothOrFirst){//0型或1型文法
            for(String r : rule){
                String[] left_right = r.split("::=");
                String[] rightStr = left_right[1].split("\\|");
                for(String sr:rightStr){
                    if(sr.contains("ε")){
                        if(left_right[0].length() > sr.length()-1){
                            t = 0;
                            break;
                        }
                    }else{
                        if(left_right[0].length() > sr.length()){
                            t = 0;
                            break;
                        }
                    }
                }
                if(t == 0){
                    break;
                }
                t = 1;
            }
        }else{//2型或3型文法

            /*
            文法判断有误，无法判断出3型
            此处需要修改！
            改为嵌套if语句直接判断3型？
            if...{
                if...{
                    ...
                    t = 3;
                }
            }
            t = 2;
             */
            for(String sr:this.right){
                if(sr.equals("ε")){
                    continue;
                }
                if(sr.length()!=1||sr.length()!=2){
                    t = 2;
                    break;
                }
                if(sr.length()==1 && !Vt.contains(sr.charAt(0))){
                    t = 2;
                    break;
                }
                if(sr.length()==2){
                    if((Vn.contains(sr.charAt(0))&&Vn.contains(sr.charAt(1)))||(Vt.contains(sr.charAt(0))&&Vt.contains(sr.charAt(1)))){
                        t = 2;
                        break;
                    }
                    if(Vn.contains(sr.charAt(0))&&Vt.contains(sr.charAt(1))){

                        if(!isLeftChanged){
                            isLeft = true;
                            isLeftChanged = false;
                            continue;
                        }else {
                            if(!isLeft){
                                t = 2;
                                break;

                            }
                        }
                    }else if(Vn.contains(sr.charAt(1))&&Vt.contains(sr.charAt(0))){
                        if(isLeftChanged){
                            isLeft = false;
                            isLeftChanged = false;
                            continue;
                        }else {
                            if(isLeft){
                                t = 2;
                                break;

                            }
                        }
                    }
                }
                t = 3;
            }
        }

        for(String r:right){
            if(r.contains("ε")){
                isExtended = true;
                break;
            }
        }

        System.out.println(t);
        switch (t){
            case 0:
                this.type = 0;
                break;
            case 1:
                if(isExtended){
                    this.type = 0;
                    break;
                }else{
                    this.type = 1;
                    break;
                }
            case 2:
                if(isExtended){
                    this.type = 4;
                    break;
                }else{
                    this.type = 2;
                    break;
                }
            case 3:
                if(!isExtended && isLeftChanged){
                    this.type = 3;
                    break;
                }
                if(isExtended && isLeftChanged){
                    this.type = 5;
                    break;
                }
                if(!isExtended && isLeft){
                    this.type = 6;
                    break;
                }
                if(!isExtended && !isLeft){
                    this.type = 7;
                    break;
                }
                if(isExtended && isLeft){
                    this.type = 8;
                    break;
                }
                if(isExtended && !isLeft){
                    this.type = 9;
                    break;
                }
            default:
                this.type = -1;
                break;

        }

    }

    public void printType(){
        getChomskyType();
        System.out.println(this.type);
        switch (this.type){
            case 0:
                System.out.println("该文法是Chomsky0型文法。");
                return;
            case 1:
                System.out.println("该文法是Chomsky1型文法(即上下文有关文法)。");
                return;
            case 2:
                System.out.println("该文法是Chomsky2型文法(即上下文无关文法)。");
                return;
            case 3:
                System.out.println("该文法是Chomsky3型文法(即正规文法)。");
                return;
            case 4:
                System.out.println("该文法是扩展的Chomsky2型文法。");
                return;
            case 5:
                System.out.println("该文法是扩展的Chomsky3型文法。");
                return;
            case 6:
                System.out.println("该文法是Chomsky3型左线性文法。");
                return;
            case 7:
                System.out.println("该文法是Chomsky3型右线性文法。");
                return;
            case 8:
                System.out.println("该文法是扩展的Chomsky3型左线性文法。");
                return;
            case 9:
                System.out.println("该文法是扩展的Chomsky3型右线性文法。");
                return;
            default:
                System.out.println("该输入不是合法的Chomsky文法。");
        }
    }
}
