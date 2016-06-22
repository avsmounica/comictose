package Garfield;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class GarfieldThread extends Thread {
    private int threadID;
    private String filesPath;
    private org.joda.time.LocalDate start;
    private org.joda.time.LocalDate end;
    private static String baseURL = "http://www.gocomics.com/garfield/";

    public GarfieldThread(int threadID, String filesPath, org.joda.time.LocalDate start, org.joda.time.LocalDate end) {
        this.threadID = threadID;
        this.filesPath = filesPath;
        this.start = start;
        this.end = end;
        this.start();
    }

    public void run() {
        org.joda.time.LocalDate start = this.start;
        org.joda.time.LocalDate end = this.end;
        for (org.joda.time.LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            String URL = baseURL + date.getYear() + "/" + date.getMonthOfYear() + "/" + date.getDayOfMonth();
            Document document;
            try {
                document = Jsoup.connect(URL).get();
            } catch (Exception e) {
                continue;
            }
            try {
                Element element = document.getElementsByClass("strip").first();
                String absoluteUrl = element.absUrl("src");
                java.net.URL imageURL = new URL(absoluteUrl);
                BufferedImage image = ImageIO.read(imageURL);
                if (imageURL.toString().endsWith("png")) {
                    ImageIO.write(image, "png", new File(filesPath + "\\" + date.toString() + ".png"));
                } else if (imageURL.toString().endsWith("jpg")) {
                    ImageIO.write(image, "jpg", new File(filesPath + "\\" + date.toString() + ".jpg"));
                } else {
                    ImageIO.write(image, "gif", new File(filesPath + "\\" + date.toString() + ".gif"));
                }
            } catch (Exception e) {

            }
        }
    }
}
