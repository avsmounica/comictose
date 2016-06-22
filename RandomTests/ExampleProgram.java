package RandomTests;

import com.sun.org.apache.xpath.internal.NodeSet;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by manojkarthicks on 5/30/2016.
 */
public class ExampleProgram {

    public static void func()
    {

        String baseURL = "http://XKCD.com/";
        int baseID = 1;
        System.out.println("Starting Downloads.");
        for(int i=0;i<1;i++)
        {
            int ID = baseID + i;
            String URL = baseURL + String.valueOf(ID) + "/";
            Document document;
            try
            {
                document = Jsoup.connect(URL).get();
            }
            catch (Exception e)
            {
                System.out.println("Skipping Comic Number-" + ID);
                continue;
            }
            System.out.println("Downloading Comic Number-" + ID);
            Element element = document.getElementById("comic");
            Element link = element.select("img").first();
            System.out.println(link.absUrl("src"));


            System.out.println(document.getElementById("middleContainer").textNodes());
            java.util.List<TextNode> nodes = document.getElementById("middleContainer").textNodes();
            for(Node node: nodes)
            {
                String textContent  = node.toString();
                if(textContent.startsWith(" Permanent"))
                {
                    String objects[] = textContent.split("xkcd.com/");
                    System.out.println(objects[1].substring(0,objects[1].length()-1));
                }

            }

            /*String absoluteUrl = imageElement.absUrl("src");
            //System.out.println("Element:    " + imageElement.toString());
            //System.out.println("Image URL:    " + absoluteUrl);
            try
            {
                URL imageURL = new URL(absoluteUrl);
                BufferedImage image = ImageIO.read(imageURL);
                ImageIO.write(image,"png",new File("C:\\Users\\manojkarthicks\\Documents\\Cyanide and Happiness\\" + String.valueOf(ID) + ".png"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }*/
        }

    }
}