package org.penzgtu.view.swing;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.penzgtu.utils.FileUtils.extractFileName;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ImageViewer {

    private static final Logger LOGGER = LogManager.getLogger(ImageViewer.class);

    public static void runImage(String imagePath) {
        String filename = extractFileName(imagePath);
        JFrame frame = new JFrame(filename);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                LOGGER.error("Image file does not exist: " + imagePath);
                return;
            }
            Image image = ImageIO.read(imageFile);
            if (image == null) {
                LOGGER.error("Failed to read image: " + imagePath);
                return;
            }
            ImageIcon imageIcon = new ImageIcon(image);
            frame.getContentPane().add(new JLabel(imageIcon), BorderLayout.CENTER);
            // installing image size
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            LOGGER.error("Error loading image: " + filename, e);
        }
    }
}
