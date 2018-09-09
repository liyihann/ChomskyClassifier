package pers.liyihan;

public class Chomsky {

    public static void main(String[] args) {
        Grammar g = new Grammar();
        System.out.println("文法：");
        g.getStartLine();
        System.out.println("非终结符：");
        g.getVnLine();
        System.out.println("规则：");
        g.getRuleLine();


    }
}
