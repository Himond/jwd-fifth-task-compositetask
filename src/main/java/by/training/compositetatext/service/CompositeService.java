package by.training.compositetatext.service;

import by.training.compositetatext.entity.TextComponent;

import java.util.List;
import java.util.Map;

public interface CompositeService {

    List<TextComponent> sortParagraphByNumberOfSentence(TextComponent text);
    List<TextComponent> findSentenceWithLongestWord(TextComponent text);
    List<TextComponent> removeAllSentWithWordsLessThanNeed(TextComponent textComposite, int countWords);
    Map<String, Integer> findAndCountSameWords(TextComponent textComposite);
    int countLowellLetter(TextComponent sentence);
    int countConsonantLetter(TextComponent sentence);





}
