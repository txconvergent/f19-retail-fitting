package edu.utexas.quickfit;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB {

    private static CustomerDB instance = null;
    List<Person> queue;
    Person[] rooms;

    public CustomerDB()
    {
        queue = new ArrayList<>();
        rooms = new Person[5]; // num of rooms NOT dynamic
    }

    public int checkin(Person p)
    {
        for(int i = 0; i < rooms.length; i++){

            /* Available room. Place customer in room immediately. */
            if(rooms[i] == null){
                rooms[i] = p;
                return i;
            }
        }

        /* No available rooms. Customer added to queue. */
        queue.add(p);
        p.setPos(queue.indexOf(p));
        return -1;
    }

    public void checkout(int roomNum)
    {
        if(!queue.isEmpty()){
            Person nextP = queue.remove(0);
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
