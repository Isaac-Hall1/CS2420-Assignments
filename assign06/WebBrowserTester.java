package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class WebBrowserTester {
    private WebBrowser testBrowser;
    private SinglyLinkedList<URL> testList;
    @BeforeEach
    public void setUp() throws MalformedURLException {
        SinglyLinkedList<URL> testList;
        testBrowser = new WebBrowser();
        testBrowser.visit(new URL("https://a"));
        testBrowser.visit(new URL("https://b"));
        testBrowser.visit(new URL("https://c"));
        testBrowser.visit(new URL("https://d"));
        testBrowser.visit(new URL("https://e"));
        testBrowser.visit(new URL("https://f"));

    }
    @Test
    public void backTest(){
        assertEquals("https://e",testBrowser.back().toString());
    }
    @Test
    public void forwardTest(){
        testBrowser.back();
        assertEquals("https://f",testBrowser.forward().toString());
    }
    @Test
    public void forwardThrows(){
        assertThrows(NoSuchElementException.class, () -> {testBrowser.forward();});
    }
    @Test
    public void backThrows(){
        testBrowser = new WebBrowser();
        assertThrows(NoSuchElementException.class, () -> {testBrowser.back();});
    }
    @Test
    public void visitTest() throws MalformedURLException {
        testBrowser.visit(new URL("https://x"));
        testBrowser.visit(new URL("https://y"));
        testBrowser.visit(new URL("https://z"));
        assertEquals("https://y",testBrowser.back().toString());
        assertEquals("https://x",testBrowser.back().toString());
    }

    @Test
    public void consutructorWithHistoryTest() throws MalformedURLException {
        testList = new SinglyLinkedList<URL>();
        testList.insertFirst(new URL("https://a"));
        testList.insertFirst(new URL("https://b"));
        testList.insertFirst(new URL("https://c"));
        testBrowser = new WebBrowser(testList);
        testBrowser.back();
        assertEquals("https://c",testBrowser.forward().toString());
        assertEquals("https://b",testBrowser.back().toString());
        assertEquals("https://a",testBrowser.back().toString());
    }
    @Test
    public void historyTest(){
        SinglyLinkedList<URL> compare = testBrowser.history();
        assertEquals("https://f", compare.get(0).toString());
        assertEquals("https://e", compare.get(1).toString());
        assertEquals("https://d", compare.get(2).toString());
        assertEquals("https://c", compare.get(3).toString());
        assertEquals("https://b", compare.get(4).toString());
        assertEquals("https://a", compare.get(5).toString());

        testBrowser.back();
        assertEquals("https://f",testBrowser.forward().toString());
    }


}
