package kruskal_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoThi {

    private final List<Canh> danhSachCanh;
    private final List<String> dinhList;

    public DoThi(List<String> dinhList) {
        this.dinhList = dinhList;
        this.danhSachCanh = new ArrayList<>();
    }

    public void themCanh(String dinhNguon, String dinhDich, int trongSo) throws InvalidVertexException, InvalidWeightException, 
            VertexNotFoundException {
        // Kiểm tra nếu tên đỉnh không hợp lệ
        if (dinhNguon == null || dinhDich == null || dinhNguon.isEmpty() || dinhDich.isEmpty()) {
            throw new InvalidVertexException("Tên đỉnh không được để trống hoặc null");
        }
        // Kiểm tra nếu trọng số không hợp lệ
        if (trongSo <= 0) {
            throw new InvalidWeightException("Trọng số phải lớn hơn 0");
        }
        // Kiểm tra nếu đỉnh không tồn tại trong danh sách
        if (!dinhList.contains(dinhNguon)) {
            throw new VertexNotFoundException("Đỉnh nguồn không tồn tại trong danh sách đỉnh.");
        }
        if (!dinhList.contains(dinhDich)) {
            throw new VertexNotFoundException("Đỉnh đích không tồn tại trong danh sách đỉnh.");
        }
        danhSachCanh.add(new Canh(dinhNguon, dinhDich, trongSo));
    }

    public List<Canh> timCayKhungNhoNhat() {
        List<Canh> cayKhung = new ArrayList<>();
        Collections.sort(danhSachCanh); // Sắp xếp các cạnh theo trọng số từ nhỏ đến lớn
        TapHopRoiRac tapHop = new TapHopRoiRac(dinhList);
        for (Canh canh : danhSachCanh) {
            String gocNguon = tapHop.timCha(canh.getDinhNguon());
            String gocDich = tapHop.timCha(canh.getDinhDich());
            // Nếu cây khung đã đủ n - 1 cạnh, dừng vòng lặp
            if (cayKhung.size() == dinhList.size() - 1) {
                break;
            }
            // Kiểm tra nếu u và v không thuộc cùng tập hợp
            if (!gocNguon.equals(gocDich)) {
                cayKhung.add(canh); // Thêm cạnh vào cây khung nhỏ nhất và gộp hai tập hợp
                tapHop.hop(gocNguon, gocDich);
            }
            // Nếu u và v thuộc cùng tập hợp, bỏ qua cạnh này để tránh tạo chu trình
        }
        // Kiểm tra nếu không đủ n - 1 cạnh, đồ thị không liên thông
        if (cayKhung.size() < dinhList.size() - 1) {
            System.out.println("Đồ thị không liên thông, không tồn tại cây khung nhỏ nhất.");
            return null; // Hoặc trả về một giá trị khác để chỉ định không tồn tại cây khung
        }
        return cayKhung;
    }
}
