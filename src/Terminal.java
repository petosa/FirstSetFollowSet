import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 10/7/2016.
 */
public class Terminal extends Symbol {

    public Terminal(String name) {
        super(name);
    }

    @Override
    public Set<String> getFirstSet() {
        Set<String> arr = new HashSet<>();
        arr.add(this.getName());
        return arr;
    }

    @Override
    public boolean allDescendantsHaveNull() {
        return getName().contains("NULL");
    }

}
