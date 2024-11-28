package kruskal_java;
public class Canh implements Comparable<Canh> {
    private String dinhNguon;
    private String dinhDich;
    private int trongSo;
    public Canh(String dinhNguon, String dinhDich, int trongSo) {
        this.dinhNguon = dinhNguon;
        this.dinhDich = dinhDich;
        this.trongSo = trongSo;
    }
    public String getDinhNguon() {
        return dinhNguon;
    }
    public String getDinhDich() {
        return dinhDich;
    }
    public int getTrongSo() {
        return trongSo;
    }  
    // Phương thức so sánh để sắp xếp cạnh theo trọng số
     @Override
    public int compareTo(Canh canhKhac) {
        return this.trongSo - canhKhac.trongSo;
    }
    
}

