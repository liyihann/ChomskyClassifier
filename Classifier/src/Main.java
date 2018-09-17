public class Main {

    public static void main(String[] args) {
        String filename = "grammar";
        Grammar g = new Grammar(filename);
        g.getStartLine();
        g.getVn();
        g.getVt();
        g.getRuleLine();

        g.printGrammar();
        g.identifyGrammar();

    }
}
