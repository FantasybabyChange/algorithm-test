package com.fantasybaby.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created on 7/4/2023.
 *
 * @author Reid Liu
 */
public class BcryptDemo {
    public static void main(String[] args) {
        String hello1 = BCrypt.hashpw("hello111111111111111bbbbbbbbbbbbb", BCrypt.gensalt());
        String hello = BCrypt.hashpw("hello1", BCrypt.gensalt());
        System.out.println(hello1);
        System.out.println(hello);
        if(BCrypt.checkpw("hello1",hello)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
