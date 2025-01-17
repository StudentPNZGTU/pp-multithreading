package org.penzgtu.tasks.task7;

public class Writer implements Runnable {
    private SharedFile sharedFile;
    private String data;

    public Writer(SharedFile sharedFile, String data) {
        this.sharedFile = sharedFile;
        this.data = data;
    }

    @Override
    public void run() {
        sharedFile.writeData(data);
    }
}