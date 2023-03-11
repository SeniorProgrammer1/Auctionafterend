package com.accp.Auctionafterend.boot.commons.utlis;

import com.accp.Auctionafterend.boot.commons.commons.Contants;
import java.util.Calendar;

public class util {

    public static boolean isString(String result){
        if(result != null && result != "") return true; else return false;
    }

    /**
     * 判断是否成功或失败
     * @param bl
     * @param i
     * @return
     */
    public static String whetherOrNot(Boolean bl,Integer i){
        switch (i){
            case 1:
                if(bl) return "添加成功"; else return "添加失败";
            case 2:
                if(bl) return "删除成功"; else return "删除失败";
            case 3:
                if(bl) return "修改成功"; else return "修改失败";
            default:
                return "失败";
        }
    }

    /**
     * obj类型转Integer
     * @param o
     * @return
     */
    public static Integer Turn(Object o){
        return Integer.parseInt((String) o);
    }

    /***
     * 获取系统时间工具类
     * @param i
     * @return
     */
    public static String GetsCheCurrentSystemTime(int i){
        Calendar now = Calendar.getInstance();
        if(i==0){
            //获取当前系统时间年月日
            String tpsj=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH);
            return tpsj;
        }else if(i==1){
            //获取当前系统时间年月日时分秒
            String tpsj=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+
                    now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+
                    now.get(Calendar.SECOND);
            return tpsj;
        }else if(i==2){
            //获取当前时间时分秒
            String tpsj=now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+
                    now.get(Calendar.SECOND);
            return tpsj;
        }
        return "";
    }
}
