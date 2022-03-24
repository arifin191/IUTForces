package com.example.iutforces_final;

import java.io.File;

//import static jdk.jpackage.internal.WixAppImageFragmentBuilder.ShortcutsFolder.Desktop;

public class rdfile {
    public static void main(String[] args) {
        File folder = new File("F://Problemset//");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {

            }
        }
        /*if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("D://ctf//lol//hid.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }*/
    }
}
