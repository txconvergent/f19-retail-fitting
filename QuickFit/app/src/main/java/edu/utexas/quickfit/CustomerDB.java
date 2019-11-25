package edu.utexas.quickfit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CustomerDB {

    private static CustomerDB instance = null;
    Queue<Person> line;
    Person[] rooms;

    public CustomerDB()
    {
        line = new LinkedList<>();
        rooms = new Person[5]; // num of rooms NOT dynamic
    }

    public void checkin(Person p)
    {
        for(int i = 0; i < rooms.length; i++){
            if(rooms[i] == null){
                rooms[i] = p;
                return;
            }
        }
        line.add(p);
    }

    public void checkout(int roomNum)
    {
        if(!line.isEmpty()){
            Person nextP = line.poll();
            rooms[roomNum] = nextP;
        }
        else
            rooms[roomNum] = null;
    }

    /* To share 'database' across multiple files. */
    public static CustomerDB getInstance()
    {
        if(instance == null)
            instance = new CustomerDB();
        return instance;
    }
}
