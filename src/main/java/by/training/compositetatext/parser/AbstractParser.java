package by.training.compositetatext.parser;

import by.training.compositetatext.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser {

    private AbstractParser handler = DefaultParser.getDefaultHandler();

    public AbstractParser() {
    }

    public AbstractParser(AbstractParser handler){
        this.handler = handler;
    }

    public AbstractParser getHandler(){
        return handler;
    }

    public abstract void parse(TextComposite composite, String part);

    private static class DefaultParser extends AbstractParser{

        private static Logger logger = LogManager.getLogger();
        private static final DefaultParser defaultHandler = new DefaultParser();

        public static DefaultParser getDefaultHandler(){
            return defaultHandler;
        }

        @Override
        public void parse(TextComposite composite, String part){
            logger.info("The last item in the parser chain");
            throw new UnsupportedOperationException("parse impossible");
        }
    }
}
