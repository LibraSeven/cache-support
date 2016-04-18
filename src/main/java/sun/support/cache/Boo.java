package sun.support.cache;

/**
 * Created by yamorn on 2016/4/18.
 */
public class Boo<T> {
    String booName;
    int booAge;
    T t;

    public Boo(String booName, int booAge, T t) {
        this.booName = booName;
        this.booAge = booAge;
        this.t = t;
    }

    public T getType() {
        return this.t;
    }

    public String getBooName() {
        return booName;
    }

    public void setBooName(String booName) {
        this.booName = booName;
    }

    public int getBooAge() {
        return booAge;
    }

    public void setBooAge(int booAge) {
        this.booAge = booAge;
    }
}
