package org.penzgtu.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
public class FolderViewer {

    public static void runFolderViewer() {
        SwingUtilities.invokeLater(FolderViewer::openFileChooser);
    }

    private static void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            StringBuilder filePaths = new StringBuilder();
            for (File file : fileChooser.getSelectedFiles()) {
                filePaths.append(file.getAbsolutePath()).append("\n");
            }
            copyToClipboard(filePaths.toString());
            JOptionPane.showMessageDialog(null, "File paths copied to clipboard:\n" + filePaths);
        }
    }

    private static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }
}