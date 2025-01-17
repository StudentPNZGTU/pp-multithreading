package org.penzgtu.service.recursive;

import java.io.File;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class RecursiveFileCounter extends RecursiveTask<FileCount> {
    private final File directory;

    public RecursiveFileCounter(File directory) {
        this.directory = directory;
    }

    @Override
    protected FileCount compute() {
        int fileCount = 0;
        int directoryCount = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    directoryCount++;
                    RecursiveFileCounter subTask = new RecursiveFileCounter(file);
                    subTask.fork();
                    FileCount subResult = subTask.join();
                    fileCount += subResult.fileCount();
                    directoryCount += subResult.directoryCount();
                } else {
                    fileCount++;
                }
            }
        }

        return new FileCount(fileCount, directoryCount);
    }

    public static void calculateFileAndDirectoryCount(String path) {
        ForkJoinPool pool = new ForkJoinPool();
        File directory = new File(path);
        RecursiveFileCounter task = new RecursiveFileCounter(directory);
        FileCount result = pool.invoke(task);
        System.out.println("Total files: " + result.fileCount());
        System.out.println("Total directories: " + result.directoryCount());
    }
}
/*
    Java 14
 */
record FileCount(int fileCount, int directoryCount) {
}