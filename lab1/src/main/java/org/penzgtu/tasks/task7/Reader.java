package org.penzgtu.tasks.task7;

public class Reader implements Runnable {
    private SharedFile sharedFile;

    public Reader(SharedFile sharedFile) {
        this.sharedFile = sharedFile;
    }

    @Override
    public void run() {
        sharedFile.readData();
    }
}
