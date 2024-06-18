package com.phantanthinh.Model;

public class SinhVien {
    String SVClass;
    String SVName;
    int SVCode;

    public SinhVien(int SVCode, String SVName, String SVClass) {
        this.SVCode = SVCode;
        this.SVName = SVName;
        this.SVClass = SVClass;
    }

    public int getSVCode() {
        return SVCode;
    }

    public void setSVCode(int SVCode) {
        this.SVCode = SVCode;
    }

    public String getSVName() {
        return SVName;
    }

    public void setSVName(String SVName) {
        this.SVName = SVName;
    }

    public String getSVClass() {
        return SVClass;
    }

    public void setSVClass(String SVClass) {
        this.SVClass = SVClass;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "SVCode='" + SVCode + '\'' +
                ", SVName='" + SVName + '\'' +
                ", SVClass=" + SVClass +
                '}';
    }
}
