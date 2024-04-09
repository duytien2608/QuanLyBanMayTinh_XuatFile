package qlbmt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class QuanLy_MT {
	
	private static ArrayList<MayTinh> dsMT = new ArrayList<MayTinh>();
	
	public static boolean themMayTinh(MayTinh m) {
	    for (MayTinh mayTinh : dsMT) {
	        if (mayTinh.getMaMay().equals(m.getMaMay())) {
	            return false;
	        }
	    }
	    dsMT.add(m);
	    return true;
	}
	
	  public static boolean xoaMay(String maMay) {
	        for (MayTinh mayTinh : dsMT) {
	            if (mayTinh.getMaMay().equals(maMay)) {	       
	                dsMT.remove(mayTinh);
	                return true;
	            }
	        }
	        return false;
	    }
	  
	  public static void luuVaoFile(String tenFile, DefaultTableModel model) throws IOException {
		  try {
			FileOutputStream fileOut = new FileOutputStream(tenFile);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			dsMT.clear();
			for(int i = 0; i < model.getRowCount(); i++) {
				  String maMay = (String) model.getValueAt(i, 0);
	              String tenMay = (String) model.getValueAt(i, 1);
	              String nhaCC = (String) model.getValueAt(i, 2);
	              String soLuongStr = (String) model.getValueAt(i, 3);
	              int soLuong = Integer.parseInt(soLuongStr);
	                dsMT.add(new MayTinh(maMay, tenMay, nhaCC, soLuong));
			}
			objOut.writeObject(dsMT);
			
			objOut.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  public static void napDuLieuTuFile(String tenFile) {
	        try {
	            FileInputStream fileIn = new FileInputStream(tenFile);
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	            dsMT = (ArrayList<MayTinh>) objectIn.readObject();
	            objectIn.close();
	            fileIn.close();
	        } catch (IOException | ClassNotFoundException e) {         
	        }
	    }
	  public static void napDuLieuVaoTable(DefaultTableModel model) {
		  for(MayTinh m : dsMT) {
			  model.addRow(new Object[] { m.getMaMay(), m.getTenMay(), m.getNhaCC(), String.valueOf(m.getSoLuong()) });
		  }
	  }
}
