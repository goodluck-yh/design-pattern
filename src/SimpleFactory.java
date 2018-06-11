/**
 * 简单工厂可以解耦客户类和具体子类
 */
public class SimpleFactory {
    public Product getProduct (int type) {
        if (type == 1) {
            return new Product1();
        } else if (type == 2) {
            return new Product2();
        }
        return new Product3();
    }
}

interface Product{

}

class Product1 implements Product {

}

class Product2 implements Product {

}

class Product3 implements Product {

}
