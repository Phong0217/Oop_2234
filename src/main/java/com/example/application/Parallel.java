package com.example.application;

import java.util.ArrayList;

public class Parallel implements Runnable {

	private String threadName;

	public Parallel(String name) {
		threadName = name;
		//System.out.println("Creating " + threadName);

	}

	public Parallel() {
	}

	@Override
	public void run() {

		try {
			if (threadName == "1") {
				FileHandling.wordsInF1 = FileHandling.chooseRandomWords(FileHandling.file1, FileHandling.wordsInF1);
				FileHandling.masterList.add(FileHandling.wordsInF1);
				// System.out.println(MiniProject.wordsInF1);
			} else if (threadName == "2") {
				FileHandling.wordsInF2 = FileHandling.chooseRandomWords(FileHandling.file2, FileHandling.wordsInF2);
				FileHandling.masterList.add(FileHandling.wordsInF2);
				// System.out.println(MiniProject.wordsInF2);
			} else if (threadName == "3") {
				FileHandling.wordsInF3 = FileHandling.chooseRandomWords(FileHandling.file3, FileHandling.wordsInF3);
				FileHandling.masterList.add(FileHandling.wordsInF3);
				// System.out.println(MiniProject.wordsInF3);
			} else if (threadName == "4") {
				FileHandling.wordsInF4 = FileHandling.chooseRandomWords(FileHandling.file4, FileHandling.wordsInF4);
				FileHandling.masterList.add(FileHandling.wordsInF4);
				// System.out.println(MiniProject.wordsInF4);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		//System.out.println(threadName + " executed the run method");

	}

	public void start() {
		//System.out.println("Starting " + threadName);
	}

	public static void parallel() {

		// Creating an 4 ThreadingOne objects
		Parallel t1 = new Parallel("1");
		Parallel t2 = new Parallel("2");
		Parallel t3 = new Parallel("3");
		Parallel t4 = new Parallel("4");

		// Adding threads with the ThreadingOne object as the parameter to an
		// array to be executed
		ArrayList<Thread> threads = new ArrayList<Thread>();
		threads.add(new Thread(t1));
		threads.add(new Thread(t2));
		threads.add(new Thread(t3));
		threads.add(new Thread(t4));

		for (int i = 0; i < threads.size(); i++) { // Starting the threads
			threads.get(i).start();
		}

		try {
			for (Thread thread1 : threads) { // wating for the threads to finish
				thread1.join();
			}
		} catch (InterruptedException e) {
			e.getMessage();
		}

	}
}
