public class Chomsky {

    public static void main(String[] args) {
        String filename = "grammar";
        Grammar g = new Grammar(filename);
        g.getStartLine();
        g.getVnLine();
        g.getRuleLine();

        g.printGrammar();
        g.identifyGrammar();

    }
}
