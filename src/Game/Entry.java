  
package Game;

public class Entry implements Comparable<Entry> {
    public String key;
    public String value;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // getters

    @Override
    public int compareTo(Entry other) {
        return this.key.compareTo(other.key);
    }
}