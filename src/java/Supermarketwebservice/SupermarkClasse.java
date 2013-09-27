/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Supermarketwebservice;

/*import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;*/
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Sonia
 */
public class SupermarkClasse {
     //public static String url = "http://www.authenticjobs.com/api/?";
    public static String url = "http://www.supermarketapi.com/api.asmx/CitiesByState?";
    public static final String APIKEY = "2010bfe658";

    public static ArrayList<String> getJobOffers(String keyword) {
        HttpClient client = new DefaultHttpClient();
        //keyword = getRandomKeyword();
        String responseBody = null;

    //  HttpGet request = new HttpGet(url + "api_key=" + APIKEY + "&method=aj.jobs.search&keywords=" + keyword + "&perpage=3");
      HttpGet request = new HttpGet(url + "APIKEY=" +APIKEY + "&SelectedState="+keyword);
        
        try {
            HttpResponse response = client.execute(request);
            responseBody = EntityUtils.toString(response.getEntity());
      } catch (IOException ex) {}
            /*  Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);*/
        
        
     ArrayList<String> cities = processXML(responseBody);
        return cities;
    }

    public static ArrayList <String> processXML(String xml) {
        
        ArrayList<String> cities = new ArrayList<String>();
        try {
            //        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //        DocumentBuilder db;
            //        String jobOffer = null;
            //        String companyName = null;
            //        Random rand = new Random();
            //        int value = rand.nextInt(3);
                    
                    
                     DOMParser parser = new DOMParser();
                    
                        parser.parse(new InputSource(new java.io.StringReader(xml)));
                        Document doc = parser.getDocument();
                    NodeList nodeLst = doc.getElementsByTagName("StoreCity");
                    
                    
                    
                    for (int i = 0; i<= nodeLst.getLength(); i++) {
                        Element eleProducto = (Element) nodeLst.item(i);


                        NodeList nlsNombre = eleProducto.getElementsByTagName("City");
                        Element eleNombre = (Element) nlsNombre.item(0);
                        String strNombre = eleNombre.getFirstChild().getNodeValue();


                        NodeList nlsPrecio = eleProducto.getElementsByTagName("State");
                        Element elePrecio = (Element) nlsPrecio.item(0);
                        String strPrecio = elePrecio.getFirstChild().getNodeValue();

                        cities.add(strNombre);
                        System.out.println(strNombre);

                    }
                    return cities;
        }
        /*public static String getRandomKeyword() {
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("software");
        keywords.add("java");
        keywords.add("web");
        keywords.add("oracle");
        keywords.add("google");
        Random rand = new Random();
        int value = rand.nextInt(5);
        return keywords.get(value);
        }*/ catch (SAXException ex) {
            Logger.getLogger(SupermarkClasse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SupermarkClasse.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            return cities;
        }
        
        
        return null;
    }


    /*public static String getRandomKeyword() {
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("software");
        keywords.add("java");
        keywords.add("web");
        keywords.add("oracle");
        keywords.add("google");

        Random rand = new Random();
        int value = rand.nextInt(5);

        return keywords.get(value);
    }*/
    
    
}
