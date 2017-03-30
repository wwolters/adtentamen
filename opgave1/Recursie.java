package opgave1;

/**
 * Created by wilco on 30-3-2017.
 */
public class Recursie {

    public static void main(String[] args) {
        System.out.println(kwadratenLijst(5));
    }

    public static String kwadratenLijst(int n) {
        if (n < 0) {
            return "_";
        }
        return kwadratenLijst(n - 1) + "/" + n * n + "\\_";
    }

}
