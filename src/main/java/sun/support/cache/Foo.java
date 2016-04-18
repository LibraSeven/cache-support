package sun.support.cache;

/**
 * Created by yamorn on 2016/4/18.
 */
public class Foo {
    String address;
    int num;

    public Foo(String address, int num) {
        this.address = address;
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
