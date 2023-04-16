package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
public class SpellCheckerTester {
    private SpellChecker dictionary;
    private List<String> inputList;
    @BeforeEach
    void setUp(){
        inputList = new ArrayList<>();
        inputList.add("bruh");
        inputList.add("dude");
        inputList.add("easy");
        inputList.add("Apple");
        inputList.add("easter");
        
        dictionary = new SpellChecker(inputList);
    }
    @Test
    void addToDictionaryAndRemove(){
        dictionary.addToDictionary("TaSty");
        assertTrue(dictionary.dictionary.contains("tasty"));
        dictionary.removeFromDictionary("taSty");
        assertFalse(dictionary.dictionary.contains("tasty"));
    }
    @Test
    void buildDictionary(){
        dictionary.buildDictionary(inputList);
        assertFalse(dictionary.dictionary.containsAll(inputList));
    }
    @Test
    void spellCheckerTest(){
        dictionary.buildDictionary(inputList);
        System.out.println(dictionary.spellCheck(new File("src/assign08/TestFile")));
    }
}
