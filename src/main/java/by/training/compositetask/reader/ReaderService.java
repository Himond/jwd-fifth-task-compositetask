package by.training.compositetask.reader;

import by.training.compositetask.exception.TextComponentException;
import java.io.IOException;

public interface ReaderService {
    String read(String path) throws IOException, TextComponentException;
}
