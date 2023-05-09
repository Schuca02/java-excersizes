package warmups.recursion;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class sumguy {
    public static void main(String[] args) {


        int number = 4;
        float mean = 0;
        int[] arr = {2, 3, 4};
        int x = arr.length;
        int sum = addNumbers(number);
        int average = (int) averageNumbers(arr, x);
        System.out.println("Sum = " + sum);
    }

    public static String reverseWords(final String original) {
        String reverseWord = "";

        for (int i = original.length(); i >= 0; i--) {
           reverseWord = Character.toString(original.charAt(i));
        }
        return reverseWord;
    }
}

        return false;
                }


public static int addNumbers(int num){
        if(num!=0)
        return num+addNumbers(num-1);
        else
        return num;
        }

public static float averageNumbers(int[]arr,int x){

        if(x==1)
        return(float)arr[x-1];
        else
        return((averageNumbers(arr,x-1)*(x-1)+arr[x-1])/x);

        }

        }
        }
