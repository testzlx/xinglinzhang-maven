package com.sankuai;

/**
 * 限流分两种，一种漏桶算法，另一种令牌桶算法
 */
public class RateLimit {

    private long now  = System.currentTimeMillis();

    private int size = 100;
    private int rate = 100;

    private int curentSize = 0;

    //漏桶算法
    public boolean limit1(){
        long currentTime = System.currentTimeMillis();
        curentSize = (int)Math.max(0,curentSize - (currentTime - now) *rate);
        if(curentSize < size){
            now  = currentTime;
            curentSize++;
            return true;
        }else{
            return false;
        }
    }

    //令牌桶算法
    public boolean limit2(){
        long currentTime = System.currentTimeMillis();
        curentSize = (int)Math.min(size,curentSize + (currentTime - now) *rate);
        if(curentSize > 0){
            now  = currentTime;
            curentSize--;
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        RateLimit rateLimit = new RateLimit();
        /*
       for(int i= 0 ;i<10000;i++){
           if(rateLimit.limit1()){
               System.out.println("i="+i+ "    yes");
           }else{
               System.out.println("i="+i+ "    no");
           }
       }
       */

        for(int i= 0 ;i<10000;i++){
            if(rateLimit.limit2()){
                System.out.println("i="+i+ "    yes");
            }else{
                System.out.println("i="+i+ "    no");
            }
        }
    }


}
