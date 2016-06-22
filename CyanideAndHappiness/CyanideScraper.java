package CyanideAndHappiness;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.security.CodeSource;

/**
 * Created by manojkarthicks on 5/30/2016.
 */
public class CyanideScraper
{
    public static int totalNoOfComics;
    public static int NUM_OF_THREADS=100;
    public static String filesPath;

    public static void startPoint() throws Exception
    {
            CodeSource codeSource = CyanideScraper.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            String folderName = "CyanideAndHappiness";
            File file = new File(jarDir + "\\" + folderName);
            file.mkdir();

            filesPath = jarDir + "\\" + folderName; //Change filepath here

            System.out.println("Downloading to: " + filesPath);
            System.out.println("\n Downloading. This may take some time depending on your Internet Connection..");
            String latestURL = "http://explosm.net/comics/latest/";
            Document document = Jsoup.connect(latestURL).get();
            Element element = document.getElementById("permalink");
            String latestURLValue = element.absUrl("value");
            String splitValues[] = latestURLValue.split("/");
            totalNoOfComics = Integer.parseInt(splitValues[splitValues.length-1]);
            int perThreadComics = totalNoOfComics / NUM_OF_THREADS;
            Thread[] threadList = new Thread[NUM_OF_THREADS+1];
            for (int i= 0; i<= NUM_OF_THREADS; i++)
            {
                int start = i*perThreadComics;
                int end = start + perThreadComics;
                threadList[i] = new CyanideThread(i,filesPath,start,end);
            }

            for(int i= 0; i<= NUM_OF_THREADS; i++)
            {
                threadList[i].join();
            }
            System.out.println("Download Complete. Happy Reading!");
    }
}
