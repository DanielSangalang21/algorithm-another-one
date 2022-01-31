package tree;

public class Element implements Comparable<Element> {

    private int frequency;
    private char data;
    private String code;
    private int sum;
    private int bits;

    public Element(){
        frequency = 0;
        data = ' ';
        code = "";
    }

    public Element(char c, int freq){
        this.frequency = freq;
        this.data = c;
    }

    public Element(String code) {
        this.code = code;
    }

    public Element(int i) {

    }

    public int getFrequency() {
        return frequency;
    }

    public char getData(){
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Element{" +
                "frequency=" + frequency +
                ", data=" + data +
                '}';
    }

    @Override
    public int compareTo(Element o) {
        return 0;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public String getCode() {
        return code;
    }

    public int getBits() {
        return bits;
    }
}
