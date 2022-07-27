package com.example.jpa.demojpa.test;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class testTest {
    public static boolean becomebad(Bread bread,int a)//判断面包是否过期，过期返回true，没过期返回false
    {
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置Date类型格式
        Date date=new Date();//当前时间
        Date date1=bread.getCreate();
        Date date2= null;
        try {
            date2 = df.parse(df.format(new Date(date1.getTime()+(bread.getExpiration()+a)*24*60*60*1000)));//面包过期当天时间
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int i=-100;
        i=date.compareTo(date2);//判断当前时间是否超过期时间，超过返回1，没超过返回-1，刚好返回0
        if(i==1)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public static double calculatesum(Bread [] s){//计算全部面包的总金额
        double sum=0;
        for(int i=0;i<s.length;i++){
            boolean judge=becomebad(s[i],0);
            if(judge)
            {
                if(s[i].getType().equals("全麦面包")) {
                    if(becomebad(s[i],1)){//判断时间是否超过过期当天时间
                        s[i]=null;
                    }else {
                        sum+=s[i].getPrice()/2;
                    }
                }
                else if(s[i].getType().equals("杂粮面包")) {
                    Date now=new Date();
                    if(!becomebad(s[i],1) && now.getHours()>=7 && now.getHours()<9) {
                        sum+=0;
                    }else{
                        s[i]=null;
                    }
                }
                else {
                    s[i]=null;
                }
            }
            else{
                sum+=s[i].getPrice();
            }

        }
        return sum;
    }
    @Test
    public void main1() {

        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = df.parse("2022-07-25 16:42:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Bread t1=new Bread("全麦面包",12.00,2,date);
        Bread t2=new Bread("杂粮面包",10.00,3,date);
        Bread t3=new Bread("金枪鱼三明治",12.00,1,date);
        Bread [] s=new  Bread[3];
        s[0]=t1;
        s[1]=t2;
        s[2]=t3;
        double sum=0;
        sum=calculatesum(s);
        System.out.println(sum);

    }
}