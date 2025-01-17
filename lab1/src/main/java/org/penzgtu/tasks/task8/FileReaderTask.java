package org.penzgtu.tasks.task8;

public class FileReaderTask implements Runnable {
    private FileManager fileManager;
    private int position;

    public FileReaderTask(FileManager fileManager, int position) {
        this.fileManager = fileManager;
        this.position = position;
    }

    @Override
    public void run() {
        fileManager.readData(position);
    }
}
