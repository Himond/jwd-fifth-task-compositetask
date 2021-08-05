package by.training.compositetask.reader.Impl;

import by.training.compositetask.exception.TextComponentException;
import by.training.compositetask.reader.ReaderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderServiceImpl implements ReaderService {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String read(String filePath) throws TextComponentException {
        Path path = Path.of(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            logger.log(Level.ERROR, "File not found: " + path.getFileName());
            throw new TextComponentException(e);
        }
    }
}
