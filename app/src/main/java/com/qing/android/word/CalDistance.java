package com.qing.android.word;

/**
 * Created by qing on 2017/5/28.
 */

public class CalDistance {

    private String s1;
    private String s2;

    public CalDistance(String s1,String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public float getSimilarity()
    {
        int matrix[][]=new int[s1.length()+1][s2.length()+1];
        int i,j;
        int cost=0;
        //初始化矩阵
        for (i=0;i<=s1.length();i++)
            matrix[i][0]=i;

        for (j=0;j<=s2.length();j++)
            matrix[0][j]=j;

        for (i=1;i<=s1.length();i++)
        {
            for (j=1;j<=s2.length();j++)
            {
                //字符不等时，cost为1，否则为0
                if (s1.charAt(i-1)!=s2.charAt(j-1))  cost=1;
                int left=matrix[i][j-1];
                int top=matrix[i-1][j];
                int left_top=matrix[i-1][j-1];
                int min=(left<top)?left:top;
                matrix[i][j]=left_top<=min?(left_top+cost):(min+1);
                cost=0;
            }
        }

        int dis=matrix[i-1][j-1];
        System.out.println(dis);
        float sim=1-(float)dis/(s1.length()>s2.length()?s1.length():s2.length());
        return sim;
    }

    /*
    public static void main(String args[]) {
        String s1 = "welcome";
        String s2 = "wolcame";
        calDistance ed  = new calDistance(s1,s2);
        System.out.print("The similarity between s1 and s2 is:");
        System.out.println(ed.getSimilarity());
    }
*/


}
