package dictionary_commandline;
public class Word {
    private String word;
    private String def;

    public Word(String word, String def) {
        this.word = word;
        this.def = def;
    }
    public Word(){

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        if (word.equals("")) {
            return;
        }
        this.word = word;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        if(def.equals("")) {
            return;
        }
        this.def = def;
    }
}