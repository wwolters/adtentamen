package opgave3;

import java.util.regex.Pattern;

/**
 * Created by wilco on 30-3-2017.
 */
public class HashSet012 {

    private final int initCapacity;
    private String[][] tabel;

    public static void main(String[] args) {
        HashSet012 set = new HashSet012(5,  3);
        System.out.println(set);
        set.add("12");
        set.add("2");
        set.add("10");
        set.add("101");
        set.add("1012");
        set.add("10122");
        set.add("2100");
        System.out.println(set);
    }

    public HashSet012(int tabelGrootte, int initCapacity) {
        if (tabelGrootte <= 0) {
            throw new IllegalArgumentException("tabelGrootte moet groter zijn dan 0");
        }
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("capaciteit moet groter zijn dan 0");
        }

        this.initCapacity = initCapacity;
        this.tabel = new String[tabelGrootte][];
    }

    public static final Pattern regex = Pattern.compile("^[0-2]*$");

    public boolean add(String t) {
        if (!regex.matcher(t).matches()) {
            throw new IllegalArgumentException("t bevat illegale karakters");
        }

        int hash = hashValue(t);
        String[] rij = tabel[hash];

        int firstNullPosition;
        if (rij != null) {
            firstNullPosition = rij.length;
            for (int i = 0; i < rij.length; i++) {
                String value = rij[i];
                if (value == null) {
                    firstNullPosition = i;
                    break;
                } else if (value.equals(t)) {
                    return false;
                }
            }
        } else {
            rij = new String[initCapacity];
            tabel[hash] = rij;
            firstNullPosition = 0;
        }

        if (firstNullPosition == rij.length) {
            String[] newRij = new String[rij.length * 2];
            System.arraycopy(rij, 0, newRij, 0, rij.length);
            rij = newRij;
            tabel[hash] = rij;
        }
        rij[firstNullPosition] = t;
        return true;
    }

    private int hashValue(String t) {
        int value = 0;
        for (char c : t.toCharArray()) {
            value = value * 37 + c - '0' + 1;
        }
        value %= tabel.length;
        if (value < 0) {
            value += tabel.length;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        for (int i = 0; i < tabel.length; i++) {
            String[] list = tabel[i];

            if (i != 0) {
                toStringBuilder.append("\n");
            }
            toStringBuilder.append("[").append(i).append("]: ");
            appendList(list, toStringBuilder);
        }

        return toStringBuilder.toString();
    }

    private void appendList(String[] list, StringBuilder stringBuilder) {
        if (list == null) {
            stringBuilder.append("null");
            return;
        }

        stringBuilder.append("[");
        for (int i = 0; i < list.length; i++) {
            String string = list[i];
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(string == null ? "#" : string);
        }
        stringBuilder.append("]");
    }
}
