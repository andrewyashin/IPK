import org.junit.Test;
import sample.Tools;

import static org.junit.Assert.*;

/**
 * Created by andrew_yashin on 2/21/17.
 */
public class ToolsTest {
    private String stringForTest = "string";
    private String expectedString = "\"string\"";

    @Test
    public void testAddQuotesNull(){
        assertNotNull(Tools.addQuotes(null));
    }

    @Test
    public void testCreateStringNull(){
        assertNotNull(Tools.createString(null));
    }

    @Test
    public void testAddQuotesByTestString(){
        assertEquals(expectedString, Tools.addQuotes(stringForTest));
    }

}
