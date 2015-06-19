package cpd3314.project;

/**
 * Final Term Project for CPD-3314 Java Development I
 *
 * @author Ramasubbaiya Adaikkalam
 */
import static cpd3314.project.Product.sortDate;
import static cpd3314.project.Product.sortID;
import static cpd3314.project.Product.sortName;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author c0652863
 *
 */
public class CPD3314Project {

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws Exception
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        /* Creates ArrayList for Product */
        ArrayList<Product> aray = new ArrayList<Product>();
        File newFile = new File("ORIGINALS.xml");
        Scanner kb = new Scanner(newFile);

        while (kb.hasNext()) {

            String list = kb.nextLine();
            
            if (list.contains("<product>")) {
                /* Creates Object for Product Class */
                Product obj = new Product();
                list = kb.nextLine();

                if (list.contains("<id>")) {
                    int id_Start = list.indexOf("<id>");
                    int id_End = list.indexOf("</id>");
                    obj.id = Integer.parseInt(list.substring(id_Start + 4, id_End));
                    list = kb.nextLine();
                }

                if (list.contains("<name>")) {
                    int namStart = list.indexOf("<name>");
                    int namEnd = list.indexOf("</name>");
                    obj.name = list.substring(namStart + 6, namEnd);
                    list = kb.nextLine();
                }

                if (list.contains("<description>")) {
                    int desStart = list.indexOf("<description>");
                    int desEnd = list.indexOf("</description>");
                    obj.description = list.substring(desStart + 13, desEnd);
                    list = kb.nextLine();
                }

                if (list.contains("<quantity>")) {
                    int quantStart = list.indexOf("<quantity>");
                    int quantEnd = list.indexOf("</quantity>");
                    obj.quantity = Integer.parseInt(list.substring(quantStart + 10, quantEnd));
                    list = kb.nextLine();
                }

                if (list.contains("<dateAdded>")) {
                    int dateStart = list.indexOf("<dateAdded>");
                    int dateEnd = list.indexOf("</dateAdded>");
                    obj.dateAdded = list.substring(dateStart + 11, dateEnd);
                    list = kb.nextLine();
                }

                aray.add(obj);
            }
        }

        String format = "XML";
        String sort = "I";
        String limit = "";
        String getID = "";
        String getDate = "";
        String find = "";
        String fileName = "cpd3314";
        int start;

        for (String a : args) {

            if (a.contains("-format=")) {
                start = a.indexOf("=");
                format = a.substring(start + 1);
            }

            if (a.contains("-sort=")) {
                start = a.indexOf("=");
                sort = a.substring(start + 1);
            }

            if (a.contains("-limit=")) {
                start = a.indexOf("=");
                limit = a.substring(start + 1);
            }

            if (a.contains("-o=")) {
                start = a.indexOf("=");
                fileName = a.substring(start + 1);
            }

            if (a.contains("-getID=")) {
                start = a.indexOf("=");
                getID = a.substring(start + 1);
            }

            if (a.contains("-getDate=")) {
                start = a.indexOf("=");
                getDate = a.substring(start + 1);
            }

            if (a.contains("-find=")) {
                start = a.indexOf("=");
                find = a.substring(start + 1);
            }
        }

        ArrayList<Product> foutput = new ArrayList<Product>();
        Product data = new Product();
        /* Sorts Name by Ascending Order  */
        if (sort.equals("A")) {
            Collections.sort(aray, sortName);
            System.out.println(aray.get(0).id);
        }
        /* Sorts Date by Ascending Order  */
        if (sort.equals("D")) {
            Collections.sort(aray, sortDate);
            System.out.println(aray.get(0).id);
        }
        /* Sorts ID by Ascending Order  */
        if (sort.equals("I")) {
            Collections.sort(aray, sortID);
            System.out.println(aray.get(0).id);
        }

        if (!getID.isEmpty()) {
            foutput.add(aray.get(Integer.parseInt(getID) - 1));
        }

        if (!limit.isEmpty()) {
            for (int i = 0; i < Integer.parseInt(limit); i++) {
                foutput.add(aray.get(i));
            }
        }

        if (!getDate.isEmpty()) {
            for (int i = 0; i < aray.size(); i++) {
                data = aray.get(i);
                if (getDate.equals(data.dateAdded)) {
                    foutput.add(data);
                }
            }
        }

        if (!find.isEmpty()) {
            for (int i = 0; i < aray.size(); i++) {
                data = aray.get(i);
                if (data.name.contains(find) || data.description.contains(find)) {
                    foutput.add(data);
                }
            }
        }

        if (getID.isEmpty() && getDate.isEmpty() && limit.isEmpty() && find.isEmpty()) {
            for (int i = 0; i < aray.size(); i++) {
                data = aray.get(i);
                foutput.add(data);
            }
        }

        String line = "";

        /* Check this string, if XML then Writes into the File */
        if (format.equals("XML")) {
            line += "<products>";
            line += "\n";

            for (int i = 0; i < foutput.size(); i++) {
                data = foutput.get(i);
                line += data.toXML() + "\n";
            }
            line += "</products>";
        }
        /* Check this string, if YAML then Writes into the File */
        else if (format.equals("YAML")) {
            for (int i = 0; i < foutput.size(); i++) {
                data = foutput.get(i);
                line += data.toYAML() + "---\n";
            }

        }
        /* Check this string, if JSON then Writes into the File */
        else if (format.equals("JSON")) {
            line += "{ \"products\":[ ";
            for (int i = 0; i < foutput.size(); i++) {
                data = foutput.get(i);
                line += data.toJSON() + ",";
            }
            line += "] }";
        } 
        /* Check this string, if SQL then Writes into the File */
        else if (format.equals("SQL")) {
            line += "CREATE TABLE Products (id INT, name VARCHAR(50), description TEXT, quantity INT, dateAdded DATE);\n";
            for (int i = 0; i < foutput.size(); i++) {
                data = foutput.get(i);
                line += data.toSQL();
                line += "\n";
            }
        } 
        /* Check this string, if HTML then Writes into the File */
        else if (format.equals("HTML")) {
            line += "<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n";
            for (int i = 0; i < foutput.size(); i++) {
                data = foutput.get(i);
                line += data.toHTML();
            }
            line += "</body>\n</html>\n";
        }

        PrintWriter pw = new PrintWriter(fileName + "." + format.toLowerCase());
        pw.print(line);
        pw.close();
    }

}
