package algorithm;

import java.util.ArrayList;
import java.util.List;

public class DynamicKnapSack {
    private int[] v;
    private int[] w;
    private int[][] c;
    private int weight;



    public DynamicKnapSack(int length,int weight,int[] vin,int[] win){
        v = new int[length+1];
        w = new int[length+1];
        c = new int[length+1][weight+1];
        this.weight = weight;
        for(int i=0;i<length+1;i++){
            v[i] = vin[i];
            w[i] = win[i];
        }
    }

    public int solve(){
        for(int i=1;i<v.length;i++){
            for(int k=0;k<=weight;k++){
                if(k >= w[i]){
                    c[i][k] = Math.max(v[i]+c[i-1][k-w[i]],c[i-1][k]);
                }else{
                    c[i][k] = c[i-1][k];
                }
            }
        }
        return c[v.length-1][weight];
    }

    public static void main(String[] args){
        int[] v = {0, 60, 100, 120};
        int[] w = {0, 10, 20, 30};
        int weight = 50;
        DynamicKnapSack knapsack = new DynamicKnapSack(3, weight, v, w);
        System.out.println(knapsack.solve());

//       StringBuffer str= new StringBuffer(3);
//       str.append("hellp");
//       System.out.println(str.length()+ "."+ str.capacity());
//        char a = 'g';
//        System.out.println(Character.toString(a));
//        System.out.println(String.valueOf(a));

        List<String> list = new ArrayList<>();
        list.add("f1");
        list.remove("f1");
        for(String s:list){
            System.out.println(s);
        }

    }

}


