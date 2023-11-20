package com.example.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class NewDictionary {
    private final String PATH;
    private static final String SPLITTING_PATTERN = "<html>";
    private final String HISTORY_PATH;


    private final ArrayList<Word> vocab = new ArrayList<>();
    private final ArrayList<Word> historyVocab = new ArrayList<>();
    private final ArrayList<Word> bookmarkVocab = new ArrayList<>();


    public NewDictionary(String path, String historyPath) {
        this.PATH = path;
        this.HISTORY_PATH = historyPath;

        loadDataFromHTMLFile(path, vocab);
        loadDataFromHTMLFile(historyPath, historyVocab);

    }

    public String getPATH() {
        return PATH;
    }

    public String getHISTORY_PATH() {
        return HISTORY_PATH;
    }


    public ArrayList<Word> getVocab() {
        return vocab;
    }

    public ArrayList<Word> getHistoryVocab() {
        return historyVocab;
    }

    public ArrayList<Word> getBookmarkVocab() {
        return bookmarkVocab;
    }

    public void loadDataFromHistoryFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(HISTORY_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SPLITTING_PATTERN);
                String word = parts[0];
                String definition = SPLITTING_PATTERN + parts[1];
                Word wordObj = new Word(word, definition);
                historyVocab.add(wordObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWordToFile(String spelling, String meaning, String path) {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(spelling + meaning + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromHTMLFile(String path, ArrayList<Word> temp) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SPLITTING_PATTERN);
                if (parts.length == 2) {  // Check if the array has two parts
                    String word = parts[0];
                    String definition = SPLITTING_PATTERN + parts[1];
                    Word wordObj = new Word(word, definition);
                    temp.add(wordObj);
                }
            }
            Collections.sort(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int binaryLookup(int start, int end, String word, ArrayList<Word> temp) {
        if (end < start) {
            return -1;
        }
        int midWord = start + (end - start) / 2;

        int compareWord = DictionaryManager.isContain(word, temp.get(midWord).getWord());

        if (compareWord == -1) {
            return binaryLookup(start, midWord - 1, word, temp);

        } else if (compareWord == 1) {
            return binaryLookup(midWord + 1, end, word, temp);
        } else {
            return midWord;
        }
    }


    public void updateWordToFile(String path, ArrayList<Word> temp) {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);

            for (Word word : temp) {
                fileWriter.write(word.getWord() + word.getDef() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeWord(String searching, String path, ArrayList<Word> temp) {
        searching = searching.toLowerCase();

        int index = Collections.binarySearch(temp, new Word(searching, null));
        if (index >= 0) {
            temp.remove(temp.get(index));
        } else {
            return;
        }
        updateWordToFile(path, temp);
    }

    public void modifyWord(String searching, String meaning) {
        searching = searching.toLowerCase();
        meaning = meaning.toLowerCase();
        int pos = -1;
        pos = Collections.binarySearch(vocab, new Word(searching, null));
        if (pos >= 0) {
            vocab.get(pos).setDef(meaning);
        } else {
            System.out.println("Không tìm thấy từ bạn muốn sửa đổi!");
        }
        updateWordToFile(PATH, vocab);
    }


    public boolean addWord(String searching, String meaning) {
        searching = searching.toLowerCase();
        int posAddWord = binaryCheck(0, vocab.size(), searching);
        if (posAddWord == -1) {
            return false;
        }
        vocab.add(new Word());
        for (int i = vocab.size() - 2; i >= posAddWord; i--) {
            vocab.get(i + 1).setWord(vocab.get(i).getWord());
            vocab.get(i + 1).setDef(vocab.get(i).getDef());
        }
        vocab.get(posAddWord).setWord(searching);
        vocab.get(posAddWord).setDef(meaning);
        Collections.sort(vocab);
        updateWordToFile(PATH, vocab);
        return true;
    }

    public int binaryCheck(int start, int end, String word) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;

        int compareNext = word.compareTo(vocab.get(mid).getWord());
        if (mid == 0) {
            if (compareNext < 0) {
                return 0;
            } else if (compareNext > 0) {
                return binaryCheck(mid + 1, end, word);
            } else {
                return -1;
            }
        } else {
            int comparePrevious = word.compareTo(vocab.get(mid - 1).getWord());

            if (comparePrevious > 0 && compareNext < 0) {
                return mid;
            } else if (comparePrevious < 0) {
                return binaryCheck(start, mid - 1, word);

            } else if (compareNext > 0) {
                if (mid == vocab.size() - 1) {
                    return vocab.size();
                }

                return binaryCheck(mid + 1, end, word);
            } else {
                return -1;
            }
        }
    }
}
