package dictionary_commandline;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    public static void showAllWords(Dictionary dic) {
        if (dic.words.isEmpty()) {
            System.out.println("Không có từ nào trong từ điển!");
        } else {
            System.out.println("\nNo\t|Eng\t\t|Vie");
            for (int i = 0; i < dic.words.size(); i++) {
                Word word = dic.words.get(i);
                System.out.println(i + "\t| " + word.getWord() + "\t\t| " + word.getDef());
            }
        }
    }

    static DictionaryManagement Management = new DictionaryManagement();
    public static void dictionarySeacher() {
        System.out.print("Nhập từ bạn muốn tìm kiếm: ");
        String s;
        Scanner scan = new Scanner(System.in);
        s = scan.nextLine();
        System.out.println("Các từ bắt đầu bằng " + s + " là: ");
        Management.dic.words.forEach((i) -> {
            int index = i.getWord().indexOf(s);
            if (index == 0) {
                System.out.println(i.getWord() + "\t| " + i.getDef());
            }
        });
    }

    public static void dictionaryBasic() throws IOException {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n_____ Dictionary Command Line _____\n");
            System.out.println("1: Tra cứu từ");
            System.out.println("2: Hiển thị danh sách dữ liệu của từ điển");
            System.out.println("3: Thêm 1 từ vào từ điển");
            System.out.println("4: Xóa 1 từ khỏi từ điển");
            System.out.println("5: Sửa 1 từ ở từ điển");
            System.out.println("6: Tìm từ chứa từ bạn nhập");
            System.out.println("0: Exit");
            System.out.print("\nChọn chức năng: ");
            choice = scan.nextInt();
            Management.insertFromFile();

            switch (choice) {
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng từ điển!");
                    System.exit(0);
                    break;
                case 1:
                    Management.dictionaryLookup();
                    break;
                case 2:
                    showAllWords(Management.dic);
                    break;
                case 3:
                    Management.addWord();
                    break;
                case 4:
                    Management.removeWord();
                    break;
                case 5:
                    Management.editWord();
                    break;
                case 6:
                    dictionarySeacher();
                default:
                    break;
            }
        } while (choice != 0);
    }

    public static void main(String[] args) throws IOException {
        dictionaryBasic();
    }
}
