package qlbmt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FormMT extends JFrame implements ActionListener, MouseListener{
	private JTextField txtMaMay;
	private JTextField txtTenMay;
	private JComboBox cbNhaCC;
	private JTextField txtSoLuong;
	private JButton btnThem;
	private JTextField txtTim;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnLuu;
	private DefaultTableModel model;
	private JTable tbMT;
	//??????????????????????????????
	private final String TenFile = "./src/data/data.txt";
	public FormMT(){
		setSize(550,400);
		setTitle("Chương trình quản lí máy laptop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JPanel p = new JPanel();
		JLabel l1 = new JLabel("Mã máy: ");
		JLabel l2 = new JLabel("Tên máy: ");
		JLabel l3 = new JLabel("Nhà cung cấp: ");
		JLabel l4 = new JLabel("   Số lượng: ");
		String tenNcc[] = {"Nguyễn Kim", "Thế giới di động", "Chợ Lớn"};
		txtMaMay = new JTextField(40);
		txtTenMay = new JTextField(40);
		cbNhaCC = new JComboBox(tenNcc);
		txtSoLuong = new JTextField(40);
		cbNhaCC.setPreferredSize(new Dimension(410, 20));
	
		p.add(l1); p.add(txtMaMay);
		p.add(l2); p.add(txtTenMay);
		p.add(l3); p.add(cbNhaCC);
		p.add(l4); p.add(txtSoLuong);
		//???
		String[] col = {"Mã máy", "Tên máy", "Nhà cung cấp", "Số lượng"};
		//????
		model = new DefaultTableModel(null, col);
		tbMT = new JTable(model);
		JScrollPane scr = new JScrollPane(tbMT);
		scr.setPreferredSize(new Dimension(450,250));
		p.add(scr);
		add(p, BorderLayout.NORTH);
//------------------------
		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		JLabel l5 = new JLabel("Nhập mã máy cần tìm: ");
		txtTim = new JTextField(5);
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xoá");
		btnXoaTrang = new JButton("Xoá trắng");
		btnLuu = new JButton("Lưu");
		p2.add(l5); p2.add(txtTim); p2.add(btnThem);
		p2.add(btnXoa); p2.add(btnXoaTrang); p2.add(btnLuu);
		add(p2, BorderLayout.SOUTH);
		//----------------
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		tbMT.addMouseListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		txtTim.addActionListener(this);
		
		// Nạp dữ liệu từ file vào JTable khi khởi động
        QuanLy_MT.napDuLieuTuFile(TenFile);
        QuanLy_MT.napDuLieuVaoTable(model);
	}
	//???????
	public boolean valiData() {
		String maMay = txtMaMay.getText();
		String tenMay = txtTenMay.getText();
		String soLuong = txtSoLuong.getText();
		
		if(!(maMay.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Mã máy không được bỏ trống");
			return false;
		}
		if(!(tenMay.length() > 0 && tenMay.matches("^[a-zA-Z0-9]*$"))) {
			JOptionPane.showMessageDialog(this, "Tên máy không được chứa số và kí tự đặc biệt");
			return false;
		}
		if(!(soLuong.length() > 0 && soLuong.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
			return false;
		}
		return true;
	}
	//?????
	public void themMT() {
		String maMay = txtMaMay.getText();
		String tenMay = txtTenMay.getText();
		String nhaCC = cbNhaCC.getSelectedItem().toString();
		String soLuongStr = txtSoLuong.getText();
		int soLuong = Integer.parseInt(soLuongStr);
		
		MayTinh m = new MayTinh(maMay, tenMay, nhaCC, soLuong);
		//Them MT
			if(QuanLy_MT.themMayTinh(m)) {
				model.addRow(new Object[] { maMay, tenMay, nhaCC, soLuongStr } );
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			}else
				JOptionPane.showMessageDialog(this, "Thêm không thành công do trùng mã máy");			
	}
	//??????
		public void xoaRong() {
			txtMaMay.setText("");
			txtTenMay.setText("");
			cbNhaCC.setSelectedIndex(0);
			txtSoLuong.setText("");
			txtMaMay.requestFocus();
		}
	//???????
		public void hienThiForm() {
			int row = tbMT.getSelectedRow();
			
			if(row != -1) {
				String maMay = (String) tbMT.getValueAt(row, 0);
				String tenMay = (String) tbMT.getValueAt(row, 1);
				String nhaCC = (String) tbMT.getValueAt(row, 2);
				String soLuong = (String) tbMT.getValueAt(row, 3);
				
				txtMaMay.setText(maMay);
				txtTenMay.setText(tenMay);
				cbNhaCC.setSelectedItem(nhaCC);
				txtSoLuong.setText(soLuong);
			}
		}
		//?????
		private static void timKiem(JTable table, String searchText) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
			table.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			for (int i = 0; i < model.getColumnCount(); i++) {
				filters.add(RowFilter.regexFilter("(?i)" + searchText, i));
			}
			sorter.setRowFilter(RowFilter.orFilter(filters));
		}

	public static void main(String[] args) {
		new FormMT().setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			if(valiData()) {
				themMT();
				xoaRong();
				}			
			}else if(o.equals(btnXoa)) {	
				//?????
				 int selectedRow = tbMT.getSelectedRow();
	                if (selectedRow != -1) {
	                    // Lấy mã máy từ cột 0 (mã máy) của hàng được chọn
	                    String maMay = (String) tbMT.getValueAt(selectedRow, 0);
	                    // Gọi phương thức xoaMay với mã máy đã lấy được
	                   int chon = JOptionPane.showConfirmDialog(null, "Ban co muon xoa", "Xac nhan", JOptionPane.YES_NO_OPTION);
	                    if(chon == JOptionPane.YES_OPTION) {
	                    boolean xoaThanhCong = QuanLy_MT.xoaMay(maMay);
	                    if (xoaThanhCong) {
	                        // Nếu xóa thành công, cập nhật lại table
	                        model.removeRow(selectedRow);
	                        xoaRong();
	                    }else {
	                        JOptionPane.showMessageDialog(this, "Xóa máy tính không thành công!");
	                    }}
	                }else {
	                    JOptionPane.showMessageDialog(this, "Vui lòng chọn máy tính để xóa!");
	                	}               
		}else if(o.equals(btnXoaTrang)) {
			xoaRong();
		}else if(o.equals(btnLuu)) {
			
			try {
				QuanLy_MT.luuVaoFile(TenFile, model);
				xoaRong();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(o.equals(txtTim)) {
			String tim = txtTim.getText();
			timKiem(tbMT, tim);;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		hienThiForm();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
