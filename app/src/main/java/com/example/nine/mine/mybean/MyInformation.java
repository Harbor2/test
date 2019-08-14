package com.example.nine.mine.mybean;

import android.graphics.Bitmap;

/**
 * 单例类来存储我的信息
 */
public class MyInformation {

    //登陆状态
//    private Boolean loginState = false;
    //用户头像
    private Bitmap userphoto;
    //区
    public String area;
    //关注数量
    public int attentionNum;
    //国家
    public String country;
    //详细地址
    public String address;
    //粉丝数量
    public int fansNum;
    //市
    public String city;
    //等级
    public int level;
    //昵称
    public String nickName;
    //性别
    public String sex;
    //头像
    public String icon;
    //金币数量
    public int goldCoin;
    //等级名称
    public String levelName;
    //pk总数
    public int pkTotal;
    //身份证号
    public String identityNum;
    //省
    public String province;
    //手机号
    public String phone;
    //等级图标
    public String levelCoin;
    //可用pk数
    public int pkSurplus;

    public static MyInformation myInformation = new MyInformation();
    private MyInformation(){

    }
    public static MyInformation getInstance(){
        return myInformation;
    }

//    public Boolean getLoginState() {
//        return loginState;
//    }
//
//    public void setLoginState(Boolean loginState) {
//        this.loginState = loginState;
//    }

    public Bitmap getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(Bitmap userphoto) {
        this.userphoto = userphoto;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(int attentionNum) {
        this.attentionNum = attentionNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(int goldCoin) {
        this.goldCoin = goldCoin;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getPkTotal() {
        return pkTotal;
    }

    public void setPkTotal(int pkTotal) {
        this.pkTotal = pkTotal;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevelCoin() {
        return levelCoin;
    }

    public void setLevelCoin(String levelCoin) {
        this.levelCoin = levelCoin;
    }

    public int getPkSurplus() {
        return pkSurplus;
    }

    public void setPkSurplus(int pkSurplus) {
        this.pkSurplus = pkSurplus;
    }
}
