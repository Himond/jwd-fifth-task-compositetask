package by.training.compositetask;

import by.training.compositetask.entity.TextComponent;
import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;

import by.training.compositetask.parser.ParagraphParser;
import by.training.compositetask.reader.Impl.ReaderServiceImpl;
import by.training.compositetask.reader.ReaderService;
import by.training.compositetask.service.CompositeService;
import by.training.compositetask.service.Impl.CompositeServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CompositeServiceTest {

    private CompositeService serviceTest = new CompositeServiceImpl();
    private static TextComposite composite = new TextComposite(TextComponentType.TEXT);
    private static ReaderService readerService = new ReaderServiceImpl();
    private static ParagraphParser parser = new ParagraphParser();

    @BeforeClass
    public static void initTest()throws TextComponentException, IOException {
        ClassLoader classLoader = CompositeServiceTest.class.getClassLoader();
        URL resource = classLoader.getResource("data/test.txt");
        assert resource != null;
        String absolutePath = new File(resource.getFile()).getAbsolutePath();
        parser.parse(composite, readerService.read(absolutePath));
    }

    @Test
    public void testSortParagraphsBySentenceAmount() {
        List<Integer> expectedWordsCountList = new ArrayList<>() {{ add(1); add(1); add(2); add(2); }};
        List<Integer> actualWordsCountList = serviceTest.sortParagraphByNumberOfSentence(composite)
                .stream()
                .map(component -> component.getComponents().size())
                .collect(Collectors.toList());
        Assert.assertEquals(actualWordsCountList, expectedWordsCountList);
    }

    @Test
    public void testFindSentencesWithLongestWord() throws TextComponentException {
        List<TextComponent> sentences = serviceTest.findSentenceWithLongestWord(composite);
        int actualCount = sentences.size();
        int expectedCount = 1;
        Assert.assertEquals(actualCount, expectedCount);
    }


    @Test
    public void testRemoveSentenceWithWordCountLessThen(){

        TextComponent modifiedComposite = serviceTest.removeAllSentWithWordsLessThanNeed(composite, 2);
        long actualSentenceCount = modifiedComposite.getComponents()
                .stream()
                .mapToLong(component -> component.getComponents().size())
                .sum();
        long expectedSentenceCount = 5;
        Assert.assertEquals(actualSentenceCount, expectedSentenceCount);
    }

    @Test
    public void testCountWordsOccurrences() {
        Map<String, Integer> actualOccurrences = serviceTest.findAndCountSameWords(composite);
        int actual = actualOccurrences.size();
        int expect = 27;
        Assert.assertEquals(actual, expect);
    }

    @Test
    public void testCountVowels(){
        TextComponent sentenceComposite = composite.getComponents()
                .stream()
                .findFirst()
                .get();

        long actual = serviceTest.countLowellLetter(sentenceComposite);
        long expected = 108;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void  testCountConsonants(){
        TextComponent sentenceComposite = composite.getComponents()
                .stream()
                .findFirst()
                .get();

        long actual = serviceTest.countConsonantLetter(sentenceComposite);
        long expected = 163;
        Assert.assertEquals(actual, expected);
    }


}
