import java.util.*;
import java.lang.*;

public class luduSnake{
    static int a[] = new int[101];
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){
        genBoard(a);
        System.out.print("Enter The number of Player : ");
        int n = sc.nextInt();
        sc.nextLine();
        start(a,n);
    }
    public static void genBoard(int a[]){
        for(int i=0;i<101;i++)a[i]=0;
        a[4] = 14;      a[8] = 30;      a[21] = 42;     a[28] = 76;
        a[50] = 67;     a[71] = 92;     a[88] = 99;
        a[97] = 78;     a[95] =56;      a[88] = 24;     a[63] = 18;
        a[48] = 26;     a[32] = 10;     a[36] = 6;
    }
    public static void start(int[] a,int n){
        int[] player = new int[n];
        for(int i=0;i<n;i++)player[i]=0;
        int k=0;
        while(check(player)){
            if(k==n)k=0;
            System.out.print("Player no "+(k+1)+" Turn ");
            player[k]=turn(a,player[k]);
            System.out.println("Now Player "+(k+1)+" Position : "+player[k]);
            System.out.println();
            k++;
        }
    }
    public static boolean check(int[] player){
        for(int i=0;i<player.length;i++){
            if(player[i]==100){
                System.out.println("***Game is Over***");
                System.out.println("Congratulation !! Player "+(i+1)+" has won the game");
                return false;
            }
        }
        return true;
    }
    public static int turn(int[] a,int playerPos){
        int dice = roll();
        System.out.print("** Press Enter to roll **");
        sc.nextLine();
        System.out.println("You Rolled : "+dice);
        if(playerPos==0){
            if(dice==1){
                System.out.println("You Ready to Go");
                return 1;
            }
            else{
                System.out.println("You need to Roll first time(1) , So Try Again");
                return 0;
            }
        }
        else if(100-playerPos>=dice){
            playerPos+=dice;
            if(a[playerPos]!=0){
                if(a[playerPos]>playerPos){
                    System.out.println("HORREY , You got a Ladder");
                }
                else{
                    System.out.println("OPPS, You got bitten by a Snake");
                }
                return a[playerPos];
            }
            return playerPos;
        }
        else return playerPos;
    }
    public static int roll(){
        return (int)(Math.random()*6)+1;
    }
}