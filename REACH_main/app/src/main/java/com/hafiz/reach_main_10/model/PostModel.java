package com.hafiz.reach_main_10.model;

public class PostModel
{
    private  String postTitle,fair,area,NID , PrmAdress,CntNo;
     String productImage;

    public PostModel() {

    }

    public PostModel(String postTitle, String fair, String area, String NID, String prmAdress, String cntNo, String productImage) {
        this.postTitle = postTitle;
        this.fair = fair;
        this.area = area;
        this.NID = NID;
        this.PrmAdress = prmAdress;
        this.CntNo = cntNo;
        this.productImage = productImage;
    }

    public String getTitle() {
        return postTitle;
    }

    public void setTitle(String title) {
        this.postTitle = title;
    }

    public String getFair() {
        return fair;
    }

    public void setFair(String fair) {
        this.fair = fair;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getPrmAdress() {
        return PrmAdress;
    }

    public void setPrmAdress(String prmAdress) {
        PrmAdress = prmAdress;
    }

    public String getCntNo() {
        return CntNo;
    }

    public void setCntNo(String cntNo) {
        CntNo = cntNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
