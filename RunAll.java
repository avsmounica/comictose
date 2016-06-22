import AuntyAcid.AuntyAcidScraper;
import BigNate.BigNateScraper;
import CalvinAndHobbes.CalvinAndHobbesScraper;
import CyanideAndHappiness.CyanideScraper;
import Dilbert.DilbertScraper;
import Garfield.GarfieldScraper;
import Peanuts.PeanutsScraper;
import SMBC.SMBCScraper;
import XKCD.XKCDScraper;
import ZenPencils.ZenPencilsScraper;

/**
 * Created by manojkarthicks on 6/20/2016.
 */
public class RunAll {

    public static void main(String args[])
    {
        try
        {
            System.out.println("Downloading Aunty Acid Comics..");
            AuntyAcidScraper auntyAcidScraper = new AuntyAcidScraper();
            auntyAcidScraper.startPoint();

            System.out.println("Downloading Big Nate Comics..");
            BigNateScraper bigNateScraper = new BigNateScraper();
            bigNateScraper.startPoint();

            System.out.println("Downloading Calvin And Hobbes Comics..");
            CalvinAndHobbesScraper calvinAndHobbesScraper = new CalvinAndHobbesScraper();
            calvinAndHobbesScraper.startPoint();

            System.out.println("Downloading Cyanide and Happiness Comics..");
            CyanideScraper cyanideScraper = new CyanideScraper();
            cyanideScraper.startPoint();

            System.out.println("Downloading Dilbert Comics..");
            DilbertScraper dilbertScraper = new DilbertScraper();
            dilbertScraper.startPoint();

            System.out.println("Downloading Garfield Comics..");
            GarfieldScraper garfieldScraper = new GarfieldScraper();
            garfieldScraper.startPoint();

            System.out.println("Downloading Peanuts Comics..");
            PeanutsScraper peanutsScraper = new PeanutsScraper();
            peanutsScraper.startPoint();

            System.out.println("Downloading SMBC Comics..");
            SMBCScraper smbcScraper = new SMBCScraper();
            smbcScraper.startPoint();

            System.out.println("Downloading XKCD Comics..");
            XKCDScraper xkcdScraper = new XKCDScraper();
            xkcdScraper.startPoint();

            System.out.println("Downloading ZenPencils Comics..");
            ZenPencilsScraper zenPencilsScraper = new ZenPencilsScraper();
            zenPencilsScraper.startPoint();

        }
        catch (Exception e)
        {

        }

    }
}
