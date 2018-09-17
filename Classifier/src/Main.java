public class Main {

    public static void main(String[] args) {
        String filename = "grammar";
        Grammar g = new Grammar(filename);
        g.getStartLine();
        g.getRuleLine();
        g.getVn();
        g.getVt();

        g.printGrammar();
        g.identifyGrammar();

    }
}
