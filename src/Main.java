import java.io.*;
import java.util.*;

/**
 * Created by Nick on 10/7/2016.
 */
public class Main {

    public static final Map<String, NonTerminal> nonterminalHash = new HashMap<>();

    public static void main(String args[]) throws IOException {
        File f = new File("expanded-grammar.txt");
        List<String> grammar = getGrammar(f);
        //Add Each nonterminal to the global set
        for (String production : grammar) {
            String name = production.substring(0, production.indexOf(" "));
            NonTerminal nt = new NonTerminal(name);
            nonterminalHash.put(name, nt);
        }

        //Link all the nonterminals together, along with their terminals.
        for (String production : grammar) {
            String[] parts = production.split(" ");
            NonTerminal nt = nonterminalHash.get(parts[0]);
            List<Symbol> arr = new ArrayList<>();
            for (int i = 2; i < parts.length; i++) {
                Symbol symb = null;
                if (parts[i].charAt(0) == '<') {
                    symb = nonterminalHash.get(parts[i]);
                } else {
                    symb = new Terminal(parts[i]);
                }
                arr.add(symb);
            }
            nt.addProduction(arr);
        }

        for (NonTerminal n : nonterminalHash.values()) {
            Set<String> ls = n.getFirstSet();
            System.out.print(n.getName() + "  -->  {");
            for (String t : ls) {
                System.out.print(t + " ");
            }
            System.out.println("}");
        }

    }

    private static List<String> getGrammar(File f) throws IOException {
        List<String> grammar = new ArrayList<>();
        FileInputStream fis = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                grammar.add(line);
            }
        }
        br.close();
        return grammar;
    }


}
