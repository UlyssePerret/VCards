import java.io.*;

public class Vcard_nik
{
    public static void main(String[] args) throws IOException
    {
        File f=new File("contact.vcf");
        FileOutputStream fop=new FileOutputStream(f);

        if(f.exists())
        {
            String str="BEGIN:VCARD\n" +
                    "VERSION:3.0\n" +
                    "N:Marinkovic;Nikola;;;\n" +
                    "FN:Nikola Marinkovic\n"+
                    "ORG:Université Cergy-Pontoise\n"+
                    "TITLE:Étudiant\n"+
                    "TEL;TYPE=work,voice;VALUE=uri:tel:+33-7-6793-4236\n"+
                    "TEL;TYPE=home,voice;VALUE=uri:tel:+33-7-6793-4236\n"+
                    "EMAIL:nikola.marinkovic.pro@gmail.com\n"+
                    "REV:20080424T195243Z\n"+
                    "END:VCARD";
            fop.write(str.getBytes());
            //Maintenant, on doit lire le contenu de la Vcard après avoir écrit les données sur la Vcard
            BufferedReader br = null;
            String sCurrentLine;
            br = new BufferedReader(new FileReader("contact.vcf"));
            while ((sCurrentLine = br.readLine()) != null)
            {
                System.out.println(sCurrentLine);
            }
            //Close the output stream and buffer reader
            fop.flush();
            fop.close();
            System.out.println("The data has been written");
        } else
            System.out.println("This file does not exist");
    }
}