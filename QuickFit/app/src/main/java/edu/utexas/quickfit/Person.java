package edu.utexas.quickfit;

public class Person {

    String name;
    String phoneNum;
    String email;
    int numClothes;
    int roomNum;

    public Person(String name, String phoneNum, String email, int numClothes){
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.numClothes = numClothes;
        this.roomNum = -1;
    }

    public String getName(){
        return name;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public String getEmail(){
        return email;
    }
    public int getNumClothes(){
        return numClothes;
    }
    public void setRoomNum(int roomNum){
        this.roomNum = roomNum;
    }
    public int getRoomNum(){
        return roomNum;
    }
}
