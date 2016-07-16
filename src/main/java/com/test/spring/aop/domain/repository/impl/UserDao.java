package com.test.spring.aop.domain.repository.impl;

import com.test.spring.aop.domain.entiry.User;
import com.test.spring.aop.domain.repository.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaorf on 2016/7/16.
 */
@Repository
public class UserDao implements IUserDao {


    public List<User> getAll() {
        //mock data
        List<User> list = new ArrayList<User>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(new User(i,"name_"+i,getRondomString(6),i,i%2,getRondomString(100)));
        }
        return list;
    }

    private String getRondomString(int length){
        StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
        buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
        buf.append(",1,2,3,4,5,6,7,8,9,0");
        String[] arr = buf.toString().split(",");

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * 10000 + 1);
            result.append(arr[random % arr.length]);
        }

        return result.toString();
    }



}
