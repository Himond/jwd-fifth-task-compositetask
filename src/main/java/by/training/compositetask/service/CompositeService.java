package by.training.compositetask.service;

import by.training.compositetask.entity.TextComponent;
import by.training.compositetask.exception.TextComponentException;

import java.util.List;
import java.util.Map;

public interface CompositeService {

    List<TextComponent> sortParagraphByNumberOfSentence(TextComponent text);
    List<TextComponent> findSentenceWithLongestWord(TextComponent text) throws TextComponentException;
    TextComponent removeAllSentWithWordsLessThanNeed(TextComponent textComposite, int countWords);
    Map<String, Integer> findAndCountSameWords(TextComponent textComposite);
    long countLowellLetter(TextComponent sentence);
    long countConsonantLetter(TextComponent sentence);





}
