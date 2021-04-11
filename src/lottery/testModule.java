package lottery;

import java.util.Locale;
import java.util.Scanner;

enum gameMode {SINGLE, GROUP, ONED, GUESS1D, TWOD, SUM, EXIT;}

public class testModule {
    public static boolean isAvailableMode(String mode){
        for (gameMode i:gameMode.values()) {
            if(i.name().compareTo(mode)==0)
                return true;
        }
        return false;
    }
    public static boolean isLegal3nums(int[]arr){
        if(arr.length!=3)
            return false;
        for(int i:arr)
            if(i<0||i>9)
                return false;
        return true;
    }
    public static boolean isLegal1nums(int[]arr){
        if(arr.length!=3)
            return false;
        int times = 0;
        for(int i:arr){
            if(i == -1)
                times++;
            else{
                if(i<0||i>9)
                    return false;
            }
        }
        if(times != 2)
            return false;
        return true;
    }
    public static boolean isLegal1num(int[]arr){
        if(arr.length!=1)
            return false;
        if (arr[0]<0||arr[0]>9)
            return false;
        return true;
    }
    public static boolean isLegal2nums(int[]arr){
        if(arr.length!=3)
            return false;
        int times = 0;
        for(int i:arr){
            if(i == -1)
                times++;
            else{
                if(i<0||i>9)
                    return false;
            }
        }
        if(times != 1)
            return false;
        return true;
    }
    public static boolean isLegalnum(int[]arr){
        int len = arr.length;
        if(len!=1 && len!=2)
            return false;
        if (len == 1){
            if(arr[0]<0)
                return false;
        }
        else{
            if(arr[0]<0||arr[1]<0)
                return false;
            int num = Integer.valueOf(arr[0]+""+arr[1]);
            if(num<0||num>27)
                return false;
        }
        return true;
    }
    public static int[] stringToIntArray(String input){
        int [] array = new int [input.length()];
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '*')
                array[i] = -1;
            else if(input.charAt(i)-'0'>=0 && input.charAt(i)-'0'<=9)
                array[i] = Integer.valueOf(String.valueOf(input.charAt(i)));
            else
                array[i] = -2;
        }
        return array;
    }
    public static void main(String[] args) {
        Lottery lottery;

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入投注方式(或退出)：");
            System.out.println("1.single    2.group     3.oned      4.guess1d   5.twod      6.sum       7.exit");
            String mode = scanner.next();
            if(mode.equalsIgnoreCase("exit"))
                break;
            while(!isAvailableMode(mode.toUpperCase())){
                System.out.println("您输入的投注方式不存在，请重新输入：");
                mode = scanner.next();
            }
            int user_num[];
            switch (mode.toLowerCase()){
                case "single":
                    while (true){
                        System.out.println("请输入三位数字:");
                        user_num = stringToIntArray(scanner.next());
                        if(isLegal3nums(user_num))break;
                        System.out.println("请输入正确的投注内容");
                    }
                    lottery = new single(user_num);
                    System.out.println("您获得的奖金为"+lottery.getWins());
                    break;
                case"group":
                    while (true){
                        System.out.println("请输入三位数字:");
                        user_num = stringToIntArray(scanner.next());
                        if(isLegal3nums(user_num))break;
                        System.out.println("请输入正确的投注内容");
                    }
                    lottery = new group(user_num);
                    System.out.println("您获得的奖金为"+lottery.getWins());
                    break;
                case"oned":
                    while (true){
                        System.out.println("请输入确定位置的一个数字，其他位输入*，例如，如果确定个位数为2，请输入**2:");
                        user_num = stringToIntArray(scanner.next());
                        if(isLegal1nums(user_num))break;
                        System.out.println("请输入正确的投注内容");
                    }
                    lottery = new oned(user_num);
                    System.out.println("您获得的奖金为"+lottery.getWins());
                    break;
                case"guess1d":
                    while (true){
                        System.out.println("请输入一位0-9之间的整数:");
                        user_num = stringToIntArray(scanner.next());
                        if(isLegal1num(user_num))break;
                        System.out.println("请输入正确的投注内容");
                    }
                    lottery = new guess1d(user_num);
                    System.out.println("您获得的奖金为"+lottery.getWins());
                    break;
                case"twod":
                    while (true){
                        System.out.println("请输入确定位置的两个数字，其他位输入*，例如，如果确定个位数为2、十位数为1，请输入*12:");
                        user_num = stringToIntArray(scanner.next());
                        if(isLegal2nums(user_num))break;
                        System.out.println("请输入正确的投注内容");
                    }
                    lottery = new twod(user_num);
                    System.out.println("您获得的奖金为"+lottery.getWins());
                    break;

                case"sum":
                    while (true){
                        System.out.println("请输入0-27之间的整数:");
                        user_num = stringToIntArray(scanner.next());
                        if(isLegalnum(user_num))break;
                        System.out.println("请输入正确的投注内容");
                    }
                    lottery = new sum(user_num);
                    System.out.println("您获得的奖金为"+lottery.getWins());
                    break;

                case"exit":System.exit(0);
            }
        }
    }
}
