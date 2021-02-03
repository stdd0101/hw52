import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseXML("data.xml");
        //String json = listToJson(list);
       // Type listType = new TypeToken<List<Employee>>() {}.getType();
    }

    private static List<Employee> parseXML(String fileName) throws IOException, ParserConfigurationException, SAXException {
        try {
            List<Employee> list = new ArrayList<Employee>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("data.xml"));
            Node root = doc.getDocumentElement();
            System.out.println("Корневой элемент: " + root.getNodeName());
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("Teкyщий элeмeнт: " + node.getNodeName());
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element employee = (Element) node;
                    Employee employee1 = new Employee(employee.getAttribute(String.valueOf(Long.valueOf("id"))),
                            employee.getAttribute("firstName"),
                            employee.getAttribute("lastName"),
                            employee.getAttribute("country"),
                            employee.getAttribute("age"));
                    list.add(employee1);
                }
            }
            return list;
        } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
            return null;
        }
    }

    private static String listToJson(List<Employee> list) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(list));
        //gson.toJson(list);
        //gson.toJson(list, new FileWriter("C://Users//stdd0//IdeaProjects//hw51//result.json"));
        writeString(gson, list);
        return null;
    }

    private static void writeString(Gson gson, List<Employee> list) throws IOException {
        FileWriter filewriter = new FileWriter("C://Users//stdd0//IdeaProjects//hw51//result.json");
        filewriter.write(gson.toJson(list));
        filewriter.close();
    }

}
