import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

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


        public Product(String typename, String name, String model, String s, String price) {
            this.typename = typename;
            this.name = name;
            this.model = model;
            this.price = price;
            this.s = s;

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



