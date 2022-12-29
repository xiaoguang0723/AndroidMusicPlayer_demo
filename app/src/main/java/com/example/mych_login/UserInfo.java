package com.example.mych_login;

public class UserInfo {
    int id;
    String username;
    String paswd, sex;
    String hobby;
    public UserInfo(int id,String username, String paswd, String sex, String hobby){
        this.id=id;
        this.username = username;
        this.paswd = paswd;
        this.sex = sex;
        this.hobby = hobby;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setAge(String hobby) {
        this.hobby = hobby;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPaswd() {
        return paswd;
    }
    public String getSex() {
        return sex;
    }
    public String getHobby() {
        return hobby;
    }
    @Override
    public String toString() {
        return "userInfo{" + "id=" + id + "， username='" + username + '\'' +"， paswd='" + paswd + '\'' + "， sex='" + sex + '\'' +"， age=" + hobby + '}';
    }
}
