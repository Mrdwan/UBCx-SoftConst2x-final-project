package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A filter that represents the logical not of its child filter
 */
public class OrFilter implements Filter {
    private final List<Filter> children;

    public OrFilter(List<Filter> children) {
        this.children = children;
    }

    /**
     * An Or filter matches when one of children matches
     * @param s     the tweet to check
     * @return      whether or not it matches
     */
    @Override
    public boolean matches(Status s) {
        for (Filter child: this.children) {
            if (child.matches(s)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<String> terms() {
        List<String> ans = new ArrayList<>();
        for (Filter child: this.children) {
            ans.add(child.terms().toString());
        }

        return ans;
    }

    public String toString() {
        return "(" + this.children.stream()
                .map(Filter::toString)
                .collect(Collectors.joining(" or ")) + ")";
    }
}
