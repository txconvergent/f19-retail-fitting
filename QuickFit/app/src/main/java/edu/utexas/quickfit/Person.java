package edu.utexas.quickfit;

public class Person {

    String name;
    String phoneNum;
    String email;
    int numClothes;
    int roomNum;
    int pos;

    public Person(){
        this.name = null;
        this.phoneNum = null;
        this.email = null;
        this.numClothes = -1;
        this.roomNum = -1;
        this.pos = -1;
    }

    public Person(String name, String phoneNum, String email, int numClothes){
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.numClothes = numClothes;
        this.roomNum = -1;
        this.pos = -1;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getNumClothes(){
        return numClothes;
    }

    public void setNumClothes(int numClothes){
        this.numClothes = numClothes;
    }

    public int getRoomNum(){
        return roomNum;
    }

    public void setRoomNum(int roomNum){
        this.roomNum = roomNum;
    }

    public int getPos(){
        return pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }
}
