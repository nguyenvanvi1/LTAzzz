package com.phantanthinh.Model;

public class Sach {
    String SCode;
    String SName;
    double SPrice;

    public Sach(String SCode, String SName, double SPrice) {
        this.SCode = SCode;
        this.SName = SName;
        this.SPrice = SPrice;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public double getSPrice() {
        return SPrice;
    }

    public void setSPrice(double SPrice) {
        this.SPrice = SPrice;
    }

}
