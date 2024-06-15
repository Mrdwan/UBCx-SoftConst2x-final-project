package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void testAnd() throws SyntaxError {
        Filter f = new Parser("trump and evil").parse();
        assertTrue(f instanceof AndFilter);
        assertTrue(f.toString().equals("(trump and evil)"));
    }

    @Test
    public void testOr() throws SyntaxError {
        Filter f = new Parser("trump or evil").parse();
        assertTrue(f instanceof NotFilter);
        assertTrue(f.toString().equals("(trump or evil)"));
    }

    @Test
    public void testNot() throws SyntaxError {
        Filter f = new Parser("not trump").parse();
        assertTrue(f instanceof NotFilter);
        assertTrue(f.toString().equals("not trump"));
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }
}
