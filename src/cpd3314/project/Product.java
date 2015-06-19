package cpd3314.project;

import java.io.StringWriter;
import java.util.Comparator;
import org.json.simple.JSONObject;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Ramasubbaiya Adaikkalam
 */
@Root
public class Product {

    /**
     *
     */
    @Element
    public Integer id;

    /**
     *
     */
    @Element
    public String name;

    /**
     *
     */
    @Element
    public String description;

    /**
     *
     */
    @Element
    public Integer quantity;

    /**
     *
     */
    @Element
    public String dateAdded;

    /**
     *
     */
    public Product() {
        id = 0;
        name = "";
        description = "";
        quantity = 0;
        dateAdded = "";
    }

    /**
     * Sorting Name done with comparator method
     */
    public static Comparator<Product> sortName = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.name.compareTo(product2.name);
        }
    };

    /**
     * Sorting ID done with comparator method
     *
     */
    public static Comparator<Product> sortID = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.id - (product2.id);
        }
    };

    /**
     * Sorting Date done with comparator method
     */
    public static Comparator<Product> sortDate = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.dateAdded.compareTo(product2.dateAdded);
        }
    };

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param quantity
     * @param dateAdded
     */
    public Product(Integer id, String name, String description, Integer quantity, String dateAdded) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.dateAdded = dateAdded;
    }

    /**
     *
     * @return getId method, of class Product.
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return setName method, of class Product.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return getDescription method, of class Product.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return getQuantity method, of class Product.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return getDateAdded method, of class Product.
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * @param dateAdded
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * Output the Product Record as a YAML Text Block
     *
     * @return - YAML Text Block
     * @throws java.lang.Exception
     */
    public String toYAML() throws Exception {
        return new Yaml().dumpAsMap(this);
    }

    /**
     * Output the Product Record as a XML Text Block
     *
     * @return - XML Text Block
     * @throws java.lang.Exception
     *
     * Converts the Text Block to XML
     *
     */
    public String toXML() throws Exception {
        Serializer serializer = new Persister();
        StringWriter strWriter = new StringWriter();
        serializer.write(this, strWriter);
        return strWriter.toString();
    }

    /**
     * Output the Employee's Record as an HTML Text Block
     *
     * @return - HTML Text Block
     *
     * Converts the Text Block to HTML
     */
    public String toHTML() {
        String output = "";
        output += "<div class=\"product\">" + "\n";
        output += "\t<h1>" + name + "</h1>\n";
        output += "\t<p>ID: " + id + "</p>\n";
        output += "\t<p>" + description + "</p>\n";
        output += "\t<p>Quantity: " + quantity + "</p>\n";
        output += "\t<p>Added: " + dateAdded + "</p>\n";
        output += "</div>" + "\n";
        return output;
    }

    /**
     *
     * @return JSON Text Block
     * @throws Exception
     *
     * Converts the Text Block to JSON
     *
     */
    public String toJSON() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("id", id);
        json.put("description", description);
        json.put("quantity", quantity);
        json.put("dateAdded", dateAdded);
        return json.toJSONString();
    }

    /**
     *
     * @return SQL Text Block
     *
     * Converts the Text Block to SQL
     *
     */
    public String toSQL() {
        return String.format("INSERT INTO Products VALUES (%d, \"%s\", \"%s\", %d, \"%s\");",
                id, name, description, quantity, dateAdded);
    }

}
