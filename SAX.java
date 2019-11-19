import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAX {
    private static ArrayList<Product> products=new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("4.1.xml"), handler);

        for (Product product:products){
            System.out.println(String.format("Type:%s\n"+"Brand:%s\n"+"Product:%s\n"+"Size:%s\n"+"Price:%s\n",
                    product.getTypename(),product.getName(),product.getModel(),product.getSize(),product.getPrice()));
        }

    }
    private static class XMLHandler extends DefaultHandler {
        private String typename, name,model, size,price,lastElementName;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("typename"))
                    typename = information;
                if (lastElementName.equals("name"))
                    name = information;
                if (lastElementName.equals("model"))
                    model = information;
                if (lastElementName.equals("size"))
                    size = information;
                if (lastElementName.equals("price"))
                    price = information;

            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (typename != null && !typename.isEmpty()) && (name != null && !name.isEmpty()) &&
                    (model!= null && !model.isEmpty()) && (size != null && !size.isEmpty()) &&
                    (price!= null && !price.isEmpty())) {
                products.add(new Product(typename, name,model,size,price));


                model=null;
                size=null;
                price=null;

            }
        }
    }
    public static class Product {
        private String typename, name, model, size, price;


        public Product(String typename, String name, String model, String size, String price) {
            this.typename = typename;
            this.name = name;
            this.model = model;
            this.price = price;
            this.size = size;

        }
        public String getTypename() {
            return typename;
        }

        public String getName() {
            return name;
        }

        public String getModel() {
            return model;
        }

        public String getSize() {
            return size;
        }

        public String getPrice() {
            return price;
        }

    }
}



