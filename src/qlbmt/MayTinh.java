package qlbmt;

import java.io.Serializable;

public class MayTinh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maMay;
	private String tenMay;
	private String nhaCC;
	private int soLuong;
	public String getMaMay() {
		return maMay;
	}
	public void setMaMay(String maMay) {
		this.maMay = maMay;
	}
	public String getTenMay() {
		return tenMay;
	}
	public void setTenMay(String tenMay) {
		this.tenMay = tenMay;
	}
	public String getNhaCC() {
		return nhaCC;
	}
	public void setNhaCC(String nhaCC) {
		this.nhaCC = nhaCC;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	/**
	 * @param maMay
	 */
	public MayTinh(String maMay) {
		super();
		this.maMay = maMay;
	}
	/**
	 * @param maMay
	 * @param tenMay
	 * @param nhaCC
	 * @param soLuong
	 */
	public MayTinh(String maMay, String tenMay, String nhaCC, int soLuong) {
		super();
		this.maMay = maMay;
		this.tenMay = tenMay;
		this.nhaCC = nhaCC;
		this.soLuong = soLuong;
	}
	/**
	 * 
	 */
	public MayTinh() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maMay == null) ? 0 : maMay.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MayTinh other = (MayTinh) obj;
		if (maMay == null) {
			if (other.maMay != null)
				return false;
		} else if (!maMay.equals(other.maMay))
			return false;
		return true;
	}
	
}
