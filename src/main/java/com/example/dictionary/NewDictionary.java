package com.example.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Đối tượng NewDictionary quản lý dữ liệu từ điển và cung cấp các phương thức để thực hiện các thao tác liên quan.
 * Dữ liệu từ điển được lưu trữ trong các ArrayList và có thể được cập nhật thông qua các phương thức của lớp này.
 *
 * <p>
 * <strong>Lưu ý:</strong> Mẫu phân chia giữa từ và định nghĩa trong tệp HTML được định nghĩa là {@code "<html>"}.
 * </p>

 */
public class NewDictionary {

    // Đường dẫn đến tệp dữ liệu từ điển Anh-Việt
    private final String PATH;

    // Đường dẫn đến tệp lưu trữ lịch sử từ điển
    private final String HISTORY_PATH;

    // Mẫu phân chia từ và định nghĩa trong tệp HTML
    private static final String SPLITTING_PATTERN = "<html>";

    // Danh sách từ vựng chính của từ điển
    private final ArrayList<Word> vocab = new ArrayList<>();

    // Danh sách từ vựng lịch sử (các từ đã tra cứu trước đó)
    private final ArrayList<Word> historyVocab = new ArrayList<>();

    // Danh sách từ vựng được đánh dấu (các từ được người dùng đánh dấu)
    private final ArrayList<Word> bookmarkVocab = new ArrayList<>();

    /**
     * Constructor để khởi tạo đối tượng NewDictionary.
     *
     * @param path       Đường dẫn đến tệp dữ liệu từ điển Anh-Việt.
     * @param historyPath Đường dẫn đến tệp lịch sử từ điển.
     */
    public NewDictionary(String path, String historyPath) {
        this.PATH = path;
        this.HISTORY_PATH = historyPath;

        // Tải dữ liệu từ tệp HTML vào danh sách từ vựng
        loadDataFromHTMLFile(path, vocab);
        loadDataFromHTMLFile(historyPath, historyVocab);
    }

    /**
     * Phương thức trả về đường dẫn đến tệp dữ liệu từ điển Anh-Việt.
     *
     * @return Đường dẫn đến tệp dữ liệu từ điển.
     */
    public String getPATH() {
        return PATH;
    }

    /**
     * Phương thức trả về đường dẫn đến tệp lịch sử từ điển.
     *
     * @return Đường dẫn đến tệp lịch sử từ điển.
     */
    public String getHISTORY_PATH() {
        return HISTORY_PATH;
    }

    /**
     * Phương thức trả về danh sách từ vựng chính của từ điển.
     *
     * @return Danh sách từ vựng chính.
     */
    public ArrayList<Word> getVocab() {
        return vocab;
    }

    /**
     * Phương thức trả về danh sách từ vựng lịch sử của từ điển.
     *
     * @return Danh sách từ vựng lịch sử.
     */
    public ArrayList<Word> getHistoryVocab() {
        return historyVocab;
    }

    /**
     * Phương thức trả về danh sách từ vựng được đánh dấu của từ điển.
     *
     * @return Danh sách từ vựng được đánh dấu.
     */
    public ArrayList<Word> getBookmarkVocab() {
        return bookmarkVocab;
    }

    /**
     * Phương thức thêm một từ mới vào tệp từ điển.
     *
     * @param spelling Từ cần thêm.
     * @param meaning  Định nghĩa của từ.
     * @param path     Đường dẫn đến tệp từ điển cần cập nhật.
     */
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

    /**
     * Phương thức tải dữ liệu từ tệp HTML vào danh sách từ vựng.
     *
     * @param path Đường dẫn đến tệp HTML.
     * @param temp Danh sách tạm thời để lưu trữ dữ liệu.
     */
    public void loadDataFromHTMLFile(String path, ArrayList<Word> temp) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SPLITTING_PATTERN);
                if (parts.length == 2) {  // Kiểm tra xem mảng có hai phần không
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

    /**
     * Phương thức thực hiện tìm kiếm nhị phân trên danh sách từ vựng.
     *
     * @param start Vị trí bắt đầu của danh sách từ vựng.
     * @param end   Vị trí kết thúc của danh sách từ vựng.
     * @param word  Từ cần tìm kiếm.
     * @param temp  Danh sách từ vựng cần tìm kiếm.
     * @return Chỉ số của từ cần tìm kiếm trong danh sách hoặc -1 nếu không tìm thấy.
     */
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

    /**
     * Phương thức cập nhật danh sách từ vựng vào tệp sau khi thay đổi.
     *
     * @param path Đường dẫn đến tệp cần cập nhật.
     * @param temp Danh sách từ vựng cần cập nhật.
     */
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

    /**
     * Phương thức xóa một từ khỏi danh sách từ vựng và cập nhật vào tệp.
     *
     * @param searching Từ cần xóa.
     * @param path      Đường dẫn đến tệp cần cập nhật.
     * @param temp      Danh sách từ vựng cần xóa từ.
     */
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

    /**
     * Phương thức sửa đổi định nghĩa của một từ trong danh sách từ vựng và cập nhật vào tệp.
     *
     * @param searching Từ cần sửa đổi.
     * @param meaning   Định nghĩa mới của từ.
     */
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

    /**
     * Phương thức thêm một từ mới vào danh sách từ vựng và cập nhật vào tệp.
     *
     * @param searching Từ cần thêm.
     * @param meaning   Định nghĩa của từ.
     * @return {@code true} nếu thêm thành công, {@code false} nếu từ đã tồn tại trong danh sách.
     */
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

    /**
     * Phương thức kiểm tra vị trí thích hợp để chèn một từ mới vào danh sách từ vựng.
     *
     * @param start Vị trí bắt đầu của danh sách từ vựng.
     * @param end   Vị trí kết thúc của danh sách từ vựng.
     * @param word  Từ cần kiểm tra.
     * @return Vị trí thích hợp để chèn từ mới hoặc -1 nếu từ đã tồn tại trong danh sách.
     */
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

