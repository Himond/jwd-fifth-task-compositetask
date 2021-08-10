package by.training.compositetask;

import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;
import by.training.compositetask.parser.ParagraphParser;
import by.training.compositetask.reader.Impl.ReaderServiceImpl;
import by.training.compositetask.reader.ReaderService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CompositeParserTest {

    private static TextComposite composite = new TextComposite(TextComponentType.TEXT);
    private static ReaderService readerService = new ReaderServiceImpl();
    private static ParagraphParser parser = new ParagraphParser();

    @BeforeClass
    public static void initTest()throws TextComponentException, IOException {
        ClassLoader classLoader = CompositeParserTest.class.getClassLoader();
        URL resource = classLoader.getResource("data/test.txt");
        assert resource != null;
        String absolutePath = new File(resource.getFile()).getAbsolutePath();
        parser.parse(composite, readerService.read(absolutePath));
    }

    @Test
    public void testParserText() throws TextComponentException, IOException {
        String actualText = composite.toString();
        ClassLoader classLoader = CompositeParserTest.class.getClassLoader();
        URL resource = classLoader.getResource("data/expect.txt");
        assert resource != null;
        String testPath = new File(resource.getFile()).getAbsolutePath();
        String expectText = readerService.read(testPath);
        Assert.assertEquals(actualText, expectText);
    }
}
