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

    /* Returns room number customer is placed in if available. */
    public int checkin(Person p)
    {
        for(int i = 0; i < rooms.length; i++){

            /* Available room. Place customer in room immediately. */
            if(rooms[i] == null){
                rooms[i] = p;
                p.setRoomNum(i);
                return i;
            }
        }

        /* No available rooms. Customer added to queue. */
        queue.add(p);
        p.setPos(queue.indexOf(p));
        return -1;
    }

    public Person checkout(int roomNum)
    {
        rooms[roomNum] = null;
        if(!queue.isEmpty())
            return queue.remove(0);
        return null;
    }

    /* To share 'database' across multiple files. */
    public static CustomerDB getInstance()
    {
        if(instance == null)
            instance = new CustomerDB();
        return instance;
    }
}
