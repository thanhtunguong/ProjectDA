package com.doan.database_handle;

public class ColumnName {
	
	//tbl_CTDTMon
	public static String CTDTmon_TABLE = "tbl_CTDTMon";
	public static String CTDTmon_MA_MON_CTDT = "PK_CTDT_Mon";
	public static String CTDTmon_MA_MON_HOC = "FK_MonHoc";
	public static String CTDTmon_MA_CTDT = "FK_CTDT";
	
	//tbl_CaHoc
	public static String CAHOC_TABLE = "tbl_CaHoc";
	public static String CAHOC_MA_CAHOC = "PK_CaHoc";
	public static String CAHOC_THU = "Thu";
	public static String CAHOC_BUOI = "Buoi";
	
	//tbl_ChuongTrinhDaoTao
	public static String CTDT_TABLE = "tbl_ChuongTrinhDaoTao";
	public static String CTDT_MA_CTDT = "PK_CTDT";
	public static String CTDT_TEN_CTDT = "TenCTDT";
	public static String CTDT_NAM = "Nam";
	public static String CTDT_NGANH_HOC = "FK_NganhHoc";
	public static String CTDT_HE = "FK_He";
	public static String CTDT_TRINH_DO = "FK_TrinhDo";
	
	//tbl_Diem
	public static String DIEM_TABLE = "tbl_Diem";
	public static String DIEM_MA_DIEM = "PK_Diem";
	public static String DIEM_DIEM_1 = "Diem1";
	public static String DIEM_DIEM_2 = "Diem2";
	public static String DIEM_DIEM_7 = "Diem3";
	public static String DIEM_TEN_MON_HOC = "TenMonHoc";
	public static String DIEM_SO_TIN_CHI = "SoTinChi";
	public static String DIEM_MA_LOP_TIN_CHI = "FK_LopTinChi";
	//public static String DIEM_MA_SV = "FK_SV";
	//public static String DIEM_MA_LICH_THI = "FK_LichThi";
	public static String DIEM_TRANG_THAI_DK = "TrangThaiDK";
	public static String DIEM_THOI_GIAN_DK = "ThoiGianDK";
	
	//tbl_DieuLe
	public static String DIEULE_TABLE = "tbl_DieuLe";
	public static String DIEULE_MA_DIEU_LE = "PK_DieuLe";
	public static String DIEULE_TEN_DIEU_LE = "TenDieuLe";
	public static String DIEULE_NOI_DUNG = "NoiDung";
	public static String DIEULE_MA_HE = "FK_He";
	public static String DIEULE_MA_CHUONG = "FK_Chuong";
	
	//tbl_DieuLeTag
	public static String DIEULEtag_TABLE = "tbl_DieuLeTag";
	public static String DIEULEtag_MA_TAG = "PK_Tag";
	public static String DIEULEtag_TAG = "Tag";
	public static String DIEULEtag_MA_DIEU_LE = "FK_DieuLe";
	public static String DIEULEtag_MUC_UU_TIEN = "MucUuTien";
	
	//tbl_GhiChu
	public static String GHI_CHU_TABLE = "tbl_GhiChu";
	public static String GHI_CHU_MA_GHI_CHU = "PK_GhiChu";
	public static String GHI_CHU_TIEU_DE_GHI_CHU = "TieuDeGhiChu";
	public static String GHI_CHU_NOI_DUNG_GHI_CHU = "NoiDungGhiChu";
	public static String GHI_CHU_THOI_GIAN_NHAC = "ThoiGianNhac";
	public static String GHI_CHU_THOI_GIAN_CHINH_SUA = "ThoiGianChinhSua";
	public static String GHI_CHU_MA_SV = "FK_SV";
	
	//tbl_GiangVien
	public static String GIANG_VIEN_TABLE = "tbl_GiangVien";
	public static String GIANG_VIEN_MA_GV = "PK_GV";
	public static String GIANG_VIEN_TEN_GV = "HoTenGV";
	public static String GIANG_VIEN_NGAY_SINH_GV = "NgaySinhGV";
	public static String GIANG_VIEN_GIOI_TINH_GV = "GioiTinhGV";
	public static String GIANG_VIEN_DIA_CHI_GV = "DiaChiGV";
	public static String GIANG_VIEN_SDT_GV = "SDTGV";
	public static String GIANG_VIEN_EMAIL_GV = "EmailGV";
	public static String GIANG_VIEN_PWD_GV = "PwdGV";
	public static String GIANG_VIEN_SO_LAN_DANG_NHAP = "SoLanDangNhapGV";
	
	//tbl_He
	public static String HE_TABLE = "tbl_He";
	public static String HE_MA_HE = "PK_He";
	public static String HE_TEN_HE = "TenHe";
	
	//tbl_KhoaHoc
	public static String KHOA_HOC_TABLE = "tbl_KhoaHoc";
	public static String KHOA_HOC_MA_KHOA_HOC = "PK_Khoahoc";
	public static String KHOA_HOC_TEN_KHOA_HOC = "TenKhoaHoc";
	public static String KHOA_HOC_NAM_BAT_DAU = "NamBatDau";
	public static String KHOA_HOC_NAM_KET_THUC = "NamKetThuc";
	public static String KHOA_HOC_MA_CTDT = "FK_CTDT";
	
	//tbl_KiHoc
	public static String KI_HOC_TABLE = "tbl_KiHoc";
	public static String KI_HOC_MA_KI_HOC = "PK_KiHoc";
	public static String KI_HOC_TEN_KI_HOC = "TenKiHoc";
	public static String KI_HOC_THOI_GIAN_BAT_DAU = "ThoiGianBatDau";
	public static String KI_HOC_THOI_GIAN_KET_THUC = "ThoiGianKetThuc";
	
	//tblLichHoc
	public static String LICHhoc_TABLE = "tbl_LichHoc";
	public static String LICHhoc_MA_LICH_HOC = "PK_LichHoc";
	public static String LICHhoc_NGAY_LICH_HOC = "NgayLH";
	public static String LICHhoc_MA_CA_HOC = "CaHoc";
	public static String LICHhoc_BUOI_HOC = "Buoi";
	public static String LICHhoc_MA_TRANG_THAI = "TenTrangThai";
	public static String LICHhoc_MA_PHONG_HOC = "TenPhongHoc";
	//public static String LICHhoc_MA_LOP_TIN_CHI = "FK_LopTinChi";
	public static String LICHhoc_TENMON = "TenMonHoc";
	
	//tbl_LichThi
	public static String LICHthi_TABLE = "tbl_LichThi";
	public static String LICHthi_TEN_LICH_THI = "TenLichThi";
	public static String LICHthi_NGAY_LICH = "NgayLich";
	public static String LICHthi_MA_CA_HOC = "FK_CaHoc";
	public static String LICHthi_MA_MON_HOC = "FK_MonHoc";
	public static String LICHthi_MA_KI_HOC = "FK_KiHoc";
	public static String LICHthi_MA_PHONG_HOC = "FK_PhongHoc";
	
	//tbl_LopHanhChinh
	public static String LopHANHCHINH_TABLE = "tbl_LopHanhChinh";
	public static String LopHANHCHINH_MA_LOP_HANH_CHINH = "PK_LopHanhChinh";
	public static String LopHANHCHINH_TEN_LOP_HANH_CHINH = "TenLopHanhChinh";
	public static String LopHANHCHINH_MA_KHOA_HOC = "FK_KhoaHoc";
	public static String LopHANHCHINH_MA_GIANG_VIEN = "FK_GV";
	
	//tbl_LopTinChi
	public static String LopTINCHI_TABLE = "tbl_LopTinChi";
	public static String LopTINCHI_MA_LOP_TIN_CHI = "PK_LopTinChi";
	public static String LopTINCHI_TEN_LOP_TIN_CHI = "TenLopTinChi";
	public static String LopTINCHI_NGAY_BAT_DAU = "NgayBatDau";
	public static String LopTINCHI_NGAY_KET_THUC = "NGayKetThuc";
	public static String LopTINCHI_MA_GIANG_VIEN = "FK_GV";
	public static String LopTINCHI_MA_KI_HOC = "FK_KiHoc";
	public static String LopTINCHI_MA_MON_HOC = "FK_MonHoc";
	
	//tbl_MonHoc
	public static String MON_HOC_TABLE = "tbl_MonHoc";
	public static String MON_HOC_MA_MON_HOC = "PK_MonHoc";
	public static String MON_HOC_TEN_MON_HOC = "TenMonHoc";
	public static String MON_HOC_SO_TIN_CHI = "SoTinChi";
	
	//tbl_MonTienQuyet
	public static String MonTIENQUYET_TABLE = "tbl_MonTienQuyet";
	public static String MonTIENQUYET_MA_MON_CTDT = "PK_CTDT_Mon";
	public static String MonTIENQUYET_MA_MON_CTDT_TIEN_QUYET = "PK_MonTienQuyet";
	
	//tbl_NganhHoc
	public static String NGANH_HOC_TABLE = "tbl_NganhHoc";
	public static String NGANH_HOC_MA_NGANH_HOC = "PK_NganhHoc";
	public static String NGANH_HOC_TEN_NGANH_HOC = "TenNganhHoc";
	
	//tblPhongHoc
	public static String PHONG_HOC_TABLE = "tbl_PhongHoc";
	public static String PHONG_HOC_MA_PHONG_HOC = "PK_PhongHoc";
	public static String PHONG_HOC_TEN_PHONG_HOC = "TenPhongHoc";
	
	//tbl_SV
	public static String SV_TABLE = "tbl_SinhVien";
	public static String SV_MA_SV = "PK_SV";
	public static String SV_TEN_SV = "HoTenSV";
	public static String SV_NGAY_SINH_SV = "NgaySinhSV";
	public static String SV_GIOI_TINH_SV = "GioiTinhSV";
	public static String SV_DIA_CHI_SV = "DiaChiSV";
	public static String SV_SDT_SV = "SDTSV";
	public static String SV_EMAIL_SV = "EmailSV";
	public static String SV_PWD_SV = "PwdSV";
	public static String SV_SO_LAN_DANG_NHAP = "SoLanDangNhapSV";
	public static String SV_MA_LOP_HANH_CHINH = "FK_LopHanhChinh";
	
	//tblTrangThai
	public static String TRANGTHAI_TABLE = "tbl_TrangThai";
	public static String TRANGTHAI_MA_TRANG_THAI = "PK_TrangThai";
	public static String TRANGTHAI_TEN_TRANG_THAI = "TenTrangThai";
	
	//tbl_TrinhDo
	public static String TRINHDO_TABLE = "tbl_TrinhDo";
	public static String TRINHDO_MA_TRINH_DO = "PK_TrinhDo";
	public static String TRINHDO_TEN_TRINH_DO = "TenTrinhDo";
	
	//tbl_Chuong
	public static String CHUONGDIEULE_TABLE = "tbl_Chuong";
	public static String CHUONGDIEULE_MA_CHUONG = "PK_Chuong";
	public static String CHUONGDIEULE_TEN_CHUONG = "TenChuong";
	
	//tbl_ThongBao
	public static String THONG_BAO_TABLE = "tbl_ThongBao";
	public static String THONG_BAO_MA_THONG_BAO = "PK_ThongBao";
	public static String THONG_BAO_TIEU_DE = "TieuDeThongBao";
	public static String THONG_BAO_NOI_DUNG = "NoiDungThongBao";
	public static String THONG_BAO_NGAY_TAO_THONG_BAO = "NgayTaoThongBao";
	public static String THONG_BAO_MA_GIAO_VIEN = "MaGV";
	
	//tbl_CTThongBao
	public static String CHI_TIET_THONG_BAO_TABLE = "tbl_CTThongBao";
	public static String CHI_TIET_THONG_BAO_MA_CT_THONG_BAO = "PK_CTThongBao";
	public static String CHI_TIET_THONG_BAO_MA_NGUOI_GUI_REPLY = "MaNguoiDung";
	public static String CHI_TIET_THONG_BAO_TEN_NGUOI_GUI_REPLY = "TenNguoiDung";
	public static String CHI_TIET_THONG_BAO_NOI_DUNG_TRA_LOI = "NoiDungTraLoi";
	public static String CHI_TIET_THONG_BAO_THOI_GIAN_REPLY = "ThoiGianTraLoi";
	public static String CHI_TIET_THONG_BAO_MA_THONG_BAO = "FK_ThongBao";
	
	//tbl_BaiViet
	public static String BAI_VIET_TABLE = "tbl_BaiViet";
	public static String BAI_VIET_MA_BAI_VIET = "PK_BaiViet";
	public static String BAI_VIET_TIEU_DE_BAI_VIET = "TieuDeBaiViet";
	public static String BAI_VIET_NOI_DUNG_BAI_VIET = "NoiDungBaiViet";
	public static String BAI_VIET_NGAY_TAO = "NgayTaoBaiViet";
	public static String BAI_VIET_THOI_GIAN_CHINH_SUA = "ThoiGianChinhSua";
	public static String BAI_VIET_LOAI_BAI_VIET = "FK_LoaiBaiViet";
	public static String BAI_VIET_MA_GV = "FK_GV";
}
