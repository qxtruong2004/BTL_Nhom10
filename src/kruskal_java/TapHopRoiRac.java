package kruskal_java;
//quản lý các tập hợp rời rạc để kiểm tra xem hai đỉnh có thuộc cùng tập hợp hay không, 
//giúp tránh chu trình.
import java.util.List;

public class TapHopRoiRac {

    private final java.util.Map<String, String> cha = new java.util.HashMap<>();
    private final java.util.Map<String, Integer> bac = new java.util.HashMap<>();

    public TapHopRoiRac(List<String> dinhList) {
        for (String dinh : dinhList) {
            cha.put(dinh, dinh); // Mỗi đỉnh ban đầu là một tập hợp riêng biệt
            bac.put(dinh, 0);    // Khởi tạo bậc của mỗi đỉnh là 0
        }
    }

    // Phương thức "Find" - tìm đại diện của tập hợp chứa đỉnh
    public String timCha(String dinh) {
        if (!cha.get(dinh).equals(dinh)) {
            cha.put(dinh, timCha(cha.get(dinh))); // Tối ưu đường đi
        }
        return cha.get(dinh);
    }

    // Phương thức "Union" - gộp hai tập hợp đỉnh lại với nhau nếu chúng không thuộc cùng một tập hợp
    public void hop(String dinh1, String dinh2) {
        String goc1 = timCha(dinh1);
        String goc2 = timCha(dinh2);

        if (!goc1.equals(goc2)) {
            if (bac.get(goc1) > bac.get(goc2)) {
                cha.put(goc2, goc1);
            } else if (bac.get(goc1) < bac.get(goc2)) {
                cha.put(goc1, goc2);
            } else {
                cha.put(goc2, goc1);
                bac.put(goc1, bac.get(goc1) + 1);
            }
        }
    }
}
