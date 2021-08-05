package by.training.compositetask.entity;

public enum TextComponentType {
    TEXT,
    PARAGRAPH("    ", "\r\n"),
    SENTENCE(" ", ""),
    LEXEME(" ", ""),
    WORD,
    EXPRESSION,
    PUNCTUATION,
    LETTER;

    private String prefix = "";
    private String suffix = "";

    TextComponentType() {
    }

    TextComponentType(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }



}
