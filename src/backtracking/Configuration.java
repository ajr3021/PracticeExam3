package backtracking;

import java.util.Collection;

public interface Configuration {
    Collection<Configuration> getSuccessors();
    boolean isValid();
    boolean isGoal();
}
