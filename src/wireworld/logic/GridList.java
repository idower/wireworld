package wireworld.logic;

import java.util.ArrayList;

public class GridList extends ArrayList<Grid> {

    private int currentId;

    public void next() {
        if(currentId  + 1 >= size()) {
            add(getCurrent().nextGen());
        }
        currentId++;
    }

    public Grid getCurrent() {
        return get(currentId);
    }

    public void prev() {
        if(currentId > 0)
            currentId--;
    }
}
