package org.penzgtu.view.menu;

import io.bretty.console.view.ActionView;
import org.penzgtu.service.recursive.RecursiveFileCounter;

public class RecursiveFileCounterConsoleManager extends ActionView {
    public RecursiveFileCounterConsoleManager(String runningTitle, String nameInParentMenu) {
        super(runningTitle, nameInParentMenu);
    }

    @Override
    public void executeCustomAction() {
        String path = this.prompt("Введите путь к директории для подсчета файлов и папок: ", String.class);

        RecursiveFileCounter.calculateFileAndDirectoryCount(path);

        this.println("Подсчет файлов и папок завершен.");
    }
}
