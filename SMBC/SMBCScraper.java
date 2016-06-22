package SMBC;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.security.CodeSource;
import java.util.HashMap;

/**
 * Created by manojkarthicks on 6/11/2016.
 */
public class SMBCScraper {
    public static int totalNoOfComics;
    public static int NUM_OF_THREADS = 100;
    public static String filesPath;

    public static void startPoint() throws Exception {
        CodeSource codeSource = SMBCScraper.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        String folderName = "SMBC";
        File file = new File(jarDir + "\\" + folderName);
        file.mkdir();
        filesPath = jarDir + "\\" + folderName; //Change filepath here
        System.out.println("Downloading to: " + filesPath);
        System.out.println("\n Downloading. This may take some time depending on your Internet Connection..");
        String latestURL = "http://www.smbc-comics.com/index.php?id=1";
        Document document = Jsoup.connect(latestURL).get();
        Element element = document.getElementById("buttonwidth");
        Element lastElement = element.getElementsByClass("last").first();
        String lastNumber = lastElement.absUrl("href");
        totalNoOfComics = Integer.parseInt(lastNumber.split("id=")[1]);
        float perThreadComicsFloat = ((float) totalNoOfComics) / NUM_OF_THREADS;
        int perThreadComics = (int) Math.ceil(perThreadComicsFloat);
        Thread[] threadList = new Thread[NUM_OF_THREADS + 1];
        for (int i = 0; i <= NUM_OF_THREADS; i++) {
            int start = i * perThreadComics;
            int end = start + perThreadComics;
            threadList[i] = new SMBCThread(i, filesPath, start, end);
        }

        for (int i = 0; i <= NUM_OF_THREADS; i++) {
            threadList[i].join();
        }
        System.out.println("Download Complete. Happy Reading!");
    }
}
