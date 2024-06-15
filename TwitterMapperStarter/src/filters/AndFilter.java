package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A filter that represents the logical not of its child filter
 */
public class AndFilter implements Filter {
    private final List<Filter> children;

    public AndFilter(List<Filter> children) {
        this.children = children;
    }

    /**
     * An And filter matches when all children match
     * @param s     the tweet to check
     * @return      whether it matches
     */
    @Override
    public boolean matches(Status s) {
        for (Filter child: this.children) {
            if (!child.matches(s)) {
                return false;
            }
        }

        return true;
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
                .collect(Collectors.joining(" and ")) + ")";
    }
}
