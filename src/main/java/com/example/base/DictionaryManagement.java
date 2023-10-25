package com.example.base;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scan = new Scanner(System.in);
    Dictionary dic = new Dictionary();
    public final String path = "src\\main\\java\\com\\example\\base\\Dictionary.txt";

    public void dictionaryLookup() {
        String s;
        System.out.print("\nNhập từ cần tra: ");
        s = scan.nextLine();
        boolean check = false;
        for (int i = 0; i < dic.words.size(); i++) {
            if (dic.words.get(i).getWord().equals(s)) {
                System.out.println(s + "    |   " + dic.words.get(i).getDef());
                check = true;
            }
        }
        if (!check) {
            System.out.println("Không tim thấy từ này!");
        }
    }

    public void addWord() {
        System.out.print("Nhập từ muốn thêm: ");
        String newWord = scan.nextLine();
        System.out.print("Nhập nghĩa của từ: ");
        String meanNw = scan.nextLine();
        Word newWords = new Word(newWord, meanNw);

        boolean check = true;
        for (Word i : dic.words) {
            if (i.getWord().equals(newWord)) {
                check = false;
                System.out.println("Từ này đã có!");
                break;
            }
        }
        if (check) {
            dic.words.add(newWords);
            System.out.println("Thêm từ thành công!");
        }
    }

    public void editWord() {
        System.out.println("Nhập từ muốn thay đổi: ");
        String word_e = scan.nextLine();
        boolean check = false;
        for (Word i : dic.words) {
            if (i.getWord().equals(word_e)) {
                System.out.print("Sửa từ thành: ");
                String target = scan.nextLine();
                i.setDef(target);
                System.out.print("Thay đổi nghĩa của từ thành: ");
                String explain = scan.nextLine();
                i.setWord(explain);
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy từ cần thay đổi!");
        }
    }

    public void removeWord() {
        System.out.print("Nhập từ cần xóa: ");
        String delete_word = scan.nextLine();
        boolean check = false;
        for (Word i : dic.words) {
            if (i.getWord().equals(delete_word)) {
                dic.words.remove(i);
                check = true;
                System.out.println("Xóa từ thành công!");
                break;
            }
        }
        if (!check) {
            System.out.println("Không có từ cần xóa trong từ điển!");
        }
    }

    public void insertFromFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String[] array = new String[2];
        int i = 0;
        while (line != null) {
            array[i] = line;
            i++;
            if (i == 2) {
                Word word = new Word();
                word.setWord(array[0]);
                word.setDef(array[1]);
                dic.words.add(word);
                i = 0;
            }
            line = br.readLine();
        }
        br.close();
    }
}
