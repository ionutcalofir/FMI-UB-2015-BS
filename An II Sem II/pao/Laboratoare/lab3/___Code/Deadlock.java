/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Cip
 */

class Person implements Runnable
{
    Person(String personName)
    {
        mName = personName;
    }
    
    void setOther(Person other)
    {
        mOtherPerson = other;        
    }
    
    void sayHello()
    {
        mLock.lock();
        try {
            mOtherPerson.mLock.lock();            
            
            System.out.println(mName + " says hello to " + mOtherPerson.mName);
            
            mOtherPerson.mLock.unlock();
        } finally {
            mLock.unlock();
        }
    }
    
    public void run()
    {
        sayHello();
    }
    
    public Lock mLock = new ReentrantLock();
    Person mOtherPerson;
    String mName;
}

public class Deadlock 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
        // TODO code application logic here
        Person a = new Person("A");
        Person b = new Person("B");        
        a.setOther(b);
        b.setOther(a);

        // Serial version
        //a.sayHello();
        //b.sayHello();
        
        // threaded version
        new Thread(a).start();
        new Thread(b).start();
    }    
}
