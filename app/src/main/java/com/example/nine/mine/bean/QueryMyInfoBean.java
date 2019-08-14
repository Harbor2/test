package com.example.nine.mine.bean;

/**
 * 查询个人信息
 */

/**
 * {
 *   "code": 0,
 *   "data": {
 *     "appUserMsg": {
 *       "area": "3",
 *       "attentionNum": 23,
 *       "country": "中国",
 *       "address": "测试地址",
 *       "fansNum": 34,
 *       "city": "2",
 *       "level": 1,
 *       "nickName": "阿七",
 *       "sex": "0",
 *       "icon": "/icon",
 *       "goldCoin": 43,
 *       "levelName": "一级",
 *       "pkTotal": 14,
 *       "identityNum": "133208166996581365",
 *       "province": "1",
 *       "phone": "15535327416",
 *       "levelCoin": "/level",
 *       "pkSurplus": 5
 *     }
 *   },
 *   "msg": ""
 * }
 */
public class QueryMyInfoBean {
    //请求码
    public int code;
    //消息
    public String msg;
    public Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        public UserMsg appUserMsg;

        public UserMsg getAppUserMsg() {
            return appUserMsg;
        }

        public void setAppUserMsg(UserMsg appUserMsg) {
            this.appUserMsg = appUserMsg;
        }

        public class UserMsg{
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

            @Override
            public String toString() {
                return "UserMsg{" +
                        "area='" + area + '\'' +
                        ", attentionNum=" + attentionNum +
                        ", country='" + country + '\'' +
                        ", address='" + address + '\'' +
                        ", fansNum=" + fansNum +
                        ", city='" + city + '\'' +
                        ", level=" + level +
                        ", nickName='" + nickName + '\'' +
                        ", sex='" + sex + '\'' +
                        ", icon='" + icon + '\'' +
                        ", goldCoin=" + goldCoin +
                        ", levelName='" + levelName + '\'' +
                        ", pkTotal=" + pkTotal +
                        ", identityNum='" + identityNum + '\'' +
                        ", province='" + province + '\'' +
                        ", phone='" + phone + '\'' +
                        ", levelCoin='" + levelCoin + '\'' +
                        ", pkSurplus=" + pkSurplus +
                        '}';
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = (area != null) ? area : "";
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
                this.country = (country != null) ? country : "";
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = (address != null) ? address : "";
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
                this.city = (city != null) ? city : "";
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
                this.nickName = (nickName != null) ? nickName : "";
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = (sex != null) ? sex : "";
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = (icon != null) ? icon : "";
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
                this.levelName = (levelName != null) ? levelName : "";
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
                this.identityNum = (identityNum != null) ? identityNum : "";
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = (province != null) ? province : "";
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = (phone != null) ? phone : "";
            }

            public String getLevelCoin() {
                return levelCoin;
            }

            public void setLevelCoin(String levelCoin) {
                this.levelCoin = (levelCoin != null) ? levelCoin : "";
            }

            public int getPkSurplus() {
                return pkSurplus;
            }

            public void setPkSurplus(int pkSurplus) {
                this.pkSurplus = pkSurplus;
            }
        }
    }

}
