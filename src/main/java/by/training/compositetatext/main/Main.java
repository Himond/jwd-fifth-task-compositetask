package by.training.compositetatext.main;

import by.training.compositetatext.entity.TextComponentIndent;
import by.training.compositetatext.entity.TextComposite;
import by.training.compositetatext.exception.TextComponentException;
import by.training.compositetatext.parser.ParagraphParser;
import by.training.compositetatext.reader.Impl.ReaderServiceImpl;
import by.training.compositetatext.reader.ReaderService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws TextComponentException, IOException {

        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource("data/text.txt");
        assert resource != null;
        String absolutePath = new File(resource.getFile()).getAbsolutePath();
        ReaderService service = new ReaderServiceImpl();

        ParagraphParser parser = new ParagraphParser();
        TextComposite composite = new TextComposite(TextComponentIndent.TEXT);
        parser.parse(composite, service.read(absolutePath));

        System.out.println(composite);


    }
}
