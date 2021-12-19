package HTMLResponse;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.*;
import static org.junit.Assert.*;

public class JSoup_eaby {
    Document doc;
    String _logoWidth = "250", _logoHeight = "200", _category = "All Categories";

    @BeforeClass
    public void startSession() throws IOException {
        doc = Jsoup.connect("https://www.ebay.com/").get();
    }

    @Test
    public void test01_logoWidth() {
        assertEquals(doc.getElementById("gh-logo").attr("width"), _logoWidth);
    }

    @Test
    public void test02_logoHeight() {
        assertEquals(doc.getElementById("gh-logo").attr("height"), _logoHeight);
    }

    @Test
    public void test03_AllCategories() {
        assertEquals(doc.getElementsByAttributeValue("id", "gh-cat").get(0).text(), _category);
    }
}
