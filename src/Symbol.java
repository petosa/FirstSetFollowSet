import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 10/7/2016.
 */
public class Symbol {

    private String name;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {name = s;}


    public Set<String> getFirstSet() {
        return null;
    }

    public boolean allDescendantsHaveNull() {
        return false;
    }

    @Override
    public String toString() {
        return "{" + name + "}";
    }

    @Override
    public boolean equals(Object a) {
        if (a == null) {
            return false;
        } else if (a instanceof Symbol && ((Symbol) a).getName().equals(name)) {
            return true;
        }
        return false;
    }

}
