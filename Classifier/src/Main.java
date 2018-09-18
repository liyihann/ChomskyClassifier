import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("请输入文法所在的文件名：");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        Grammar g = new Grammar(filename);
        g.print();




    }
}
