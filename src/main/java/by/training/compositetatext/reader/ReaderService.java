package by.training.compositetatext.reader;

import by.training.compositetatext.exception.TextComponentException;
import java.io.IOException;

public interface ReaderService {
    String read(String path) throws IOException, TextComponentException;
}
