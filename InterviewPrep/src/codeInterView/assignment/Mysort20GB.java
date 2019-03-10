package codeInterView.assignment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

class SplitFile extends Thread {
    String fileName;
    int startLine, endLine;

    SplitFile(String fileName, int startLine, int endLine) {
        this.fileName = fileName;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public static void writeToFile(BufferedWriter writer, String line) {
        try {
            writer.write(line + "\r\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
            int totalLines = endLine + 1 - startLine;
            Stream<String> chunks = Files.lines(Paths.get(Mysort20GB.fPath))
                .skip(startLine - 1)
                .limit(totalLines)
                .sorted(Comparator.naturalOrder());

            chunks.forEach(line -> {
                writeToFile(writer, line);
            });
            System.out.println(" Done Writing " + Thread.currentThread()
                .getName());
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class MergeFiles extends Thread {
    String file1, file2, file3;

    MergeFiles(String file1, String file2, String file3) {
        this.file1 = file1;
        this.file2 = file2;
        this.file3 = file3;
    }

    public void run() {
        try {
            System.out.println(file1 + " Started Merging " + file2);
            FileReader fileReader1 = new FileReader(file1);
            FileReader fileReader2 = new FileReader(file2);
            FileWriter writer = new FileWriter(file3);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            String line1 = bufferedReader1.readLine();
            String line2 = bufferedReader2.readLine();
            // Merge 2 files based on which string is greater.
            while (line1 != null || line2 != null) {
                if (line1 == null || (line2 != null && line1.compareTo(line2) > 0)) {
                    writer.write(line2 + "\r\n");
                    line2 = bufferedReader2.readLine();
                } else {
                    writer.write(line1 + "\r\n");
                    line1 = bufferedReader1.readLine();
                }
            }
            System.out.println(file1 + " Done Merging " + file2);
            new File(file1).delete();
            new File(file2).delete();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class Mysort20GB {
    // public static final String fdir = "/Users/diesel/Desktop/";
    public static final String fdir = "C:\\temp\\1\\";
    public static final String shared = "C:\\temp\\1\\";
    public static final String fPath = "C:\\temp\\1\\logs.txt";
    public static final String opLog = shared + "Mysort20GB.log";

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        int threadCount = 16; // Number of threads
        int totalLines = 50000;
        int linesPerFile = totalLines / threadCount;
        ArrayList<Thread> activeThreads = new ArrayList<Thread>();

        for (int i = 1; i <= threadCount; i++) {
            int startLine = i == 1 ? i : (i - 1) * linesPerFile + 1;
            int endLine = i * linesPerFile;
            SplitFile mapThreads = new SplitFile(fdir + "op" + i, startLine, endLine);
            activeThreads.add(mapThreads);
            mapThreads.start();
        }
        activeThreads.stream()
            .forEach(t -> {
                try {
                    t.join();
                } catch (Exception e) {
                }
            });

        int treeHeight = (int) (Math.log(threadCount) / Math.log(2));

        for (int i = 0; i < treeHeight; i++) {
            ArrayList<Thread> actvThreads = new ArrayList<Thread>();

            for (int j = 1, itr = 1; j <= threadCount / (i + 1); j += 2, itr++) {
                int offset = i * 100;
                String tempFile1 = fdir + "op" + (j + offset);
                String tempFile2 = fdir + "op" + ((j + 1) + offset);
                String opFile = fdir + "op" + (itr + ((i + 1) * 50));

                MergeFiles reduceThreads = new MergeFiles(tempFile1, tempFile2, opFile);
                actvThreads.add(reduceThreads);
                reduceThreads.start();
            }
            actvThreads.stream()
                .forEach(t -> {
                    try {
                        t.join();
                    } catch (Exception e) {
                    }
                });
        }
        long endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1e9;
        System.out.println("timeTaken:: " + timeTaken);
        BufferedWriter logFile = new BufferedWriter(new FileWriter(opLog, true));
        logFile.write("Time Taken in seconds:" + timeTaken);
        Runtime.getRuntime()
            .exec("valsort  " + fdir + "op" + (treeHeight * 100) + 1 + " > " + opLog);
        logFile.close();
    }
}
