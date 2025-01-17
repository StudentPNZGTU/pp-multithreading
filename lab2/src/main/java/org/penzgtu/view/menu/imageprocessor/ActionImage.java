package org.penzgtu.view.menu.imageprocessor;

public interface ActionImage {
    void runImageViewer(String pathToImage);

    void runFolderViewer();

    void setDefaultPathInputFile(String pathInputFile);

    void setDefaultPathOutputFile(String pathOutputFile);
}
