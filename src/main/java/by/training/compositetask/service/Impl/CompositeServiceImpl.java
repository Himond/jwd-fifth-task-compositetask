package by.training.compositetask.service.Impl;

import by.training.compositetask.entity.TextComponent;
import by.training.compositetask.entity.TextComponentType;
import by.training.compositetask.entity.TextComposite;
import by.training.compositetask.exception.TextComponentException;
import by.training.compositetask.service.CompositeService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CompositeServiceImpl implements CompositeService {

    private static final String VOWEL_REGEX = "[aAeEiIoOuUyY]";

    @Override
    public List<TextComponent> sortParagraphByNumberOfSentence(TextComponent text) {

        List<TextComponent> sortParagraphs = text.getComponents()
                .stream()
                .sorted(new ParagraphComparator())
                .collect(Collectors.toList());

        return sortParagraphs;
    }

    @Override
    public List<TextComponent> findSentenceWithLongestWord(TextComponent text) throws TextComponentException {

        List<TextComponent> allSentences = text.getComponents()
                .stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .collect(Collectors.toList());

        int maxLength = allSentences.stream()
                .map(this::computeMaxWordLength)
                .max(Integer::compareTo)
                .orElseThrow(() -> new TextComponentException("Sentence contains no words"));

        return allSentences.stream()
                .filter(sentence -> computeMaxWordLength(sentence) == maxLength)
                .collect(Collectors.toList());
    }

    @Override
    public TextComponent removeAllSentWithWordsLessThanNeed(TextComponent textComposite, int countWords) {

        TextComponent newText = new TextComposite(TextComponentType.TEXT);

        textComposite.getComponents().forEach(paragraph -> {
            List<TextComponent> newSentences = new ArrayList<>(paragraph.getComponents());
            newSentences.removeIf(sentence -> checkCountWordsInSentence(sentence, countWords));
            TextComponent newParagraph = new TextComposite(TextComponentType.PARAGRAPH);
            newParagraph.addAll(newSentences);
            newText.add(newParagraph);
        });

        return newText;
    }

    @Override
    public Map<String, Integer> findAndCountSameWords(TextComponent textComposite) {
        Map<String, Integer> map = new HashMap<>();
        textComposite.getComponents()
                .stream()
                .flatMap(sentence -> sentence.getComponents().stream())
                .flatMap(lexeme -> lexeme.getComponents().stream())
                .flatMap(word -> word.getComponents().stream())
                .filter(component -> component.getComponentType().equals(TextComponentType.WORD))
                .forEach(component -> {
                    String key = component.toString().toLowerCase(Locale.ROOT);
                    int value = map.getOrDefault(key, 0);
                    map.put(key, ++value);
                });

        map.values().removeIf(val -> val == 1);
        return map;
    }

    @Override
    public long countLowellLetter(TextComponent sentence) {

        long countLetter = sentence.getComponents()
                .stream()
                .flatMap(lexeme -> lexeme.getComponents().stream())
                .flatMap(word -> word.getComponents().stream()) // stream of lexemes
                .filter(component -> component.getComponentType().equals(TextComponentType.WORD)) // stream of words
                .flatMap(component -> component.getComponents().stream()) // stream of letters
                .filter(letter -> Pattern.matches(VOWEL_REGEX, letter.toString()))
                .count();
        return countLetter;
    }

    @Override
    public long countConsonantLetter(TextComponent sentence) {
        long countLetter = sentence.getComponents()
                .stream()
                .flatMap(lexeme -> lexeme.getComponents().stream())
                .flatMap(word -> word.getComponents().stream()) // stream of lexemes
                .filter(component -> component.getComponentType().equals(TextComponentType.WORD)) // stream of words
                .flatMap(component -> component.getComponents().stream()) // stream of letters
                .filter(letter -> !Pattern.matches(VOWEL_REGEX, letter.toString()))
                .count();

        return countLetter;
    }



    private boolean checkCountWordsInSentence(TextComponent sentence, int countWords){

        AtomicInteger count = new AtomicInteger();
        sentence.getComponents().stream()
                .forEach(lexeme -> lexeme.getComponents().stream()
                        .forEach(word -> {
                            if(word.getComponentType().equals(TextComponentType.WORD)){
                                count.getAndIncrement();
                            }
                        }));
        return count.get() <= countWords;
    }

    private int computeMaxWordLength(TextComponent sentence) {

        int maxWordLength = sentence.getComponents()
                .stream()
                .flatMap(lexeme -> lexeme.getComponents().stream()
                        .filter(word -> word.getComponentType()
                                .equals(TextComponentType.WORD)))
                .map(TextComponent::count)
                .max(Integer::compareTo)
                .orElse(0);

        return maxWordLength;
    }


    private static class ParagraphComparator implements Comparator<TextComponent> {

        @Override
        public int compare(TextComponent first, TextComponent second) {
            long firstCount = first.getComponents().size();
            long secondCount = second.getComponents().size();
            return Long.compare(firstCount, secondCount);
        }
    }
}
