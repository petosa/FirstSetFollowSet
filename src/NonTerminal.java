import java.util.*;

/**
 * Created by Nick on 10/7/2016.
 */
public class NonTerminal extends Symbol {

    private List<List<Symbol>> productions;
    private Set<String> firstSet = null;
    private Boolean desc;
    private Map<List<Symbol>, Integer> map = new HashMap<>();


    public NonTerminal(String name) {
        super(name);
        productions = new ArrayList<>();
    }

    public void addProduction(List<Symbol> list) {
        productions.add(list);
    }

    public List<List<Symbol>> getProductions() {
        return productions;
    }

    @Override
    public Set<String> getFirstSet() {
        if (firstSet != null) {
            return firstSet;
        }
        firstSet = new HashSet<>();
        for (List<Symbol> r : productions) {
            firstSet.addAll(r.get(0).getFirstSet());
            map.put(r, 0);
            for(int i = 1; i < r.size() && firstSet.contains("\"NULL\""); i++) {
                map.put(r, i);
                firstSet.addAll(r.get(i).getFirstSet());
            }
        }
        if (allDescendantsHaveNull()) {
            firstSet.add("\"NULL\"");
        }
        return firstSet;
    }

    @Override
    public boolean allDescendantsHaveNull() {
        if (desc != null) {
            return desc;
        }
        desc = true;
        for (List<Symbol> r : productions) {
            Integer i = map.get(r);
            for (int j = 0; j <= i; j++) {
                if (!r.get(j).allDescendantsHaveNull()) {
                    desc = false;
                }
            }
        }
        return desc;
    }

    @Override
    public String toString() {
        return "{" + super.getName() + ", production with " + productions.size() + " rule(s)}";
    }

}
