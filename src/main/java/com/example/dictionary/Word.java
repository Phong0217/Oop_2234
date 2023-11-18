package com.example.dictionary;
public class Word implements Comparable<Word> {
    private String word;
    private String def;

    public Word(String word, String def) {
        this.word = word;
        this.def = def;
    }

    public Word(){
        word = "";
        def = "";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public int compareTo(Word o) {
        return this.word.compareToIgnoreCase(o.word);
    }
}