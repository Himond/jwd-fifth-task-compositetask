package by.training.compositetatext.service.Impl;

import by.training.compositetatext.entity.TextComponent;
import by.training.compositetatext.service.CompositeService;

import java.util.List;
import java.util.Map;

public class CompositeServiceImpl implements CompositeService {

    @Override
    public List<TextComponent> sortParagraphByNumberOfSentence(TextComponent text) {
        return null;
    }

    @Override
    public List<TextComponent> findSentenceWithLongestWord(TextComponent text) {
        return null;
    }

    @Override
    public List<TextComponent> removeAllSentWithWordsLessThanNeed(TextComponent textComposite, int countWords) {
        return null;
    }

    @Override
    public Map<String, Integer> findAndCountSameWords(TextComponent textComposite) {
        return null;
    }

    @Override
    public int countLowellLetter(TextComponent sentence) {
        return 0;
    }

    @Override
    public int countConsonantLetter(TextComponent sentence) {
        return 0;
    }
}
