package lottery;

import java.util.Arrays;
import java.util.Random;

public abstract class Lottery {
    private int[] user_number;
    private int[] win_number;

    Lottery(int[] userNumber) {
        user_number = userNumber;
        win_number = new int[3];//开奖号码在初始化后随机生成
        Random random = new Random();
        for (int i = 0; i < 3; i++)
            win_number[i] = Math.abs(random.nextInt()) % 10;
        System.out.println("中奖号为：" + win_number[0] + win_number[1] + win_number[2]);
    }

    public int[] getWin_number(){
        return win_number;
    }
    public int[] getUser_number(){
        return user_number;
    }
    abstract public int getWins();

}
class single extends Lottery{
    single(int[] userNumber){
        super(userNumber);
    }
    @Override
    public int getWins(){
        int[]user_number = getUser_number();
        int[]win_number = getWin_number();
        for (int i =0; i < user_number.length; i++)
            if(user_number[i] != win_number[i])
                return 0;
        return 1040;
    }
}

class group extends Lottery{
    group(int[] userNumber){
        super(userNumber);
    }
    @Override
    public int getWins(){
        int[]user_number = getUser_number();
        int[]win_number = getWin_number();

        Arrays.sort(user_number);
        Arrays.sort(win_number);//将两数组排序
        for(int i=0; i<3; i++){
            if(user_number[i]!=win_number[i])
                return 0;
        }
        if(win_number[0] == win_number[1] ||  win_number[1] == win_number[2] || win_number[0] == win_number[2])//3选
            return 346;
        else//6选
            return 173;
    }
}

class oned extends Lottery{
    oned(int[] userNumber){
        super(userNumber);
    }
    @Override
    public int getWins(){
        int[]user_number = getUser_number();
        int[]win_number = getWin_number();
        for(int i=0; i<3; i++)
            if(user_number[i]==win_number[i])
                return 10;
        return 0;
    }
}

class guess1d extends Lottery{
    private int user_number;
    guess1d(int[] userNumber){
        super(userNumber);
        user_number = userNumber[0];
    }
    @Override
    public int getWins(){
        int[]win_number = getWin_number();
        if(win_number[0] == user_number && win_number[1] == user_number && win_number[2] == user_number)
            return 230;
        else if(win_number[0] != user_number && win_number[1] != user_number && win_number[2] != user_number)
            return 0;
        else if((win_number[0] == user_number && win_number[1] == user_number && win_number[2] != user_number)||
                (win_number[0] == user_number && win_number[1] != user_number && win_number[2] == user_number)||
                (win_number[0] != user_number && win_number[1] == user_number && win_number[2] == user_number))
            return 12;
        else
            return 2;
    }
}

class twod extends Lottery{
    twod(int[] userNumber){
        super(userNumber);
    }
    @Override
    public int getWins(){
        int times = 0;//同位相等次数
        int[]user = getUser_number();
        int[]wins = getWin_number();
        for (int i =0; i < user.length; i++)
            if(user[i] == wins[i])
                times++;
        if(times>1)
            return 104;
        else
            return 0;
    }
}

class sum extends Lottery{
    private int user_number;
    private int[]bonus = {1040,345,172,104,69,49,37,29,23,19,16,15,15,14};
    sum(int[] userNumber){
        super(userNumber);
        user_number = userNumber[0];
    }
    public int getSum(int[]nums){
        int sum = 0;
        for (int i : nums)
            sum += i;
        return sum;
    }
    @Override
    public int getWins(){
        if(user_number == getSum(getWin_number()))
            return user_number<=13?bonus[user_number]:bonus[27-user_number];
        return 0;
    }
}