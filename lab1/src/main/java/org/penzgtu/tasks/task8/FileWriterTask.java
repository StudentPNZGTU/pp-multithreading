package org.penzgtu.tasks.task8;

class FileWriterTask implements Runnable {
    private FileManager fileManager;
    private int position;
    private String data;

    public FileWriterTask(FileManager fileManager, int position, String data) {
        this.fileManager = fileManager;
        this.position = position;
        this.data = data;
    }

    @Override
    public void run() {
        fileManager.writeData(position, data);
    }
}
