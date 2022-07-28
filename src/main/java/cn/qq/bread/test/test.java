package cn.qq.bread.test;

import cn.qq.bread.entity.Bread;
import cn.qq.bread.service.BreadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class test {
    @Resource
    private  BreadService breadService;

    public  boolean becomebad(Bread bread){//检验面包是否过期
        Date date=new Date();
        Date date1=bread.getEndtime();


        int i=-100;
        i=date.compareTo(date1);//比较两个时间大小，若date1比date大则返回1，反之则返回-1，若相等则返回0
        if(i==1)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public  double calculatesum(Bread [] s){//计算总金额
        Date now=new Date();
        double sum=0;
        for(int i=0;i<s.length;i++){
            boolean judge=becomebad(s[i]);//判断是否过期
            if(judge)
            {
                if(s[i].getType().equals("全麦面包")) {
                    if(s[i].getEndtime().getDay()==now.getDay() && s[i].getEndtime().getHours()<24){//如果面包在当天过期则将价格下降至原来的一半
                        sum+=s[i].getPrice()/2;
                    }else {
                        s[i]=null;
                        breadService.deleteBreadbyId(i);//面包过期，删除面包
                    }
                }
                else if(s[i].getType().equals("杂粮面包")) {
                    if(s[i].getEndtime().getDay()==now.getDay() && now.getHours()>=7 && now.getHours()<9) {//如果面包当天过期且时间在七点至九点间则免费赠送
                        sum+=0;
                    }else{
                        s[i]=null;
                        breadService.deleteBreadbyId(i);
                    }
                }
                else {//肉类面包，过期直接删除
                    s[i]=null;
                    breadService.deleteBreadbyId(i);
                }
            }
            else{//若面包没过期则原价出售
                sum+=s[i].getPrice();
            }

        }
        return sum;
    }



    @GetMapping("/start")
    @ResponseBody
    public void main() {

        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        Date date1=null;
        Date date2=null;
        Date date3=null;
        try {
            date = df.parse("2022-07-26 16:42:00");
            date1 = df.parse(df.format(new Date(date.getTime()+2*24*60*60*1000)));
            date2 = df.parse(df.format(new Date(date.getTime()+3*24*60*60*1000)));
            date3 = df.parse(df.format(new Date(date.getTime()+1*24*60*60*1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int n=3;
        Bread b1=new Bread(0,"全麦面包",date,2,date1,12.00);
        Bread b2=new Bread(1,"杂粮面包",date,3,date2,10.00);
        Bread b3=new Bread(2,"金枪鱼三明治",date,1,date3,12.00);

        breadService.addBread(b1);//添加面包信息
        breadService.addBread(b2);
        breadService.addBread(b3);
        Bread [] s=new  Bread[n];
       for(int i=0;i<n;i++)
       {
           s[i]=breadService.getBreadbyId(i);//将面包信息赋值到数组s中
       }
        double sum=0;
        sum=calculatesum(s);//计算总金额
        System.out.println("总金额为："+sum);

    }
}
