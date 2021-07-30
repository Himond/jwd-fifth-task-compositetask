package by.training.compositetatext.entity;

public enum TextComponentIndent {
    TEXT,
    PARAGRAPH("    ", "\r\n"),
    SENTENCE(" ", ""),
    LEXEME(" ", ""),
    PUNCTUATION,
    LETTER;

    private String prefix = "";
    private String suffix = "";

    TextComponentIndent() {
    }

    TextComponentIndent(String prefix, String suffix) {
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
