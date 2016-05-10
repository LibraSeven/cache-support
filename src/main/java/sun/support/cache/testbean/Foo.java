package sun.support.cache.testbean;

/**
 * Created by 264929 on 2016/5/9.
 */
public class Foo {
    private String address;
    private double money;

    public Foo(){}

    public Foo(String address, double money){
        this.address = address;
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
