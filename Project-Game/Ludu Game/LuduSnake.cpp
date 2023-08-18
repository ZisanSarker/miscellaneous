#include<bits/stdc++.h>
using namespace std;
vector<int>a(101,0);
void genBoard(vector<int> a);
bool check(vector<int>pos);
int turn(int pos);
void start(int players);
int main(){
    genBoard(a);
    cout<<"Enter the number of Players : ";
    int players;cin>>players;
    start(players);
}
void genBoard(vector<int> a){
    a[4] = 14;  a[8] = 30;  a[21] = 42; a[28] = 76;
    a[50] = 67; a[71] = 92; a[88] = 99; a[97] = 78;
    a[95] =56;  a[88] = 24; a[63] = 18; a[48] = 26;
    a[32] = 10; a[36] = 6;
}
bool check(vector<int>pos){
    for(int i=0;i<pos.size();i++){
        if(pos[i] == 100){
            cout<<"Congratulation! Player "<<i+1<<" won the game"<<endl;
            cout<<"**Game is Over**"<<endl;
            return false;
        }
        return true;
    }
}
int turn(int pos){
    int d = rand()%7;
    //int nothing;cin>>nothing;
    cout<<"You rolled : "<<d<<endl;
    if(pos==0){
        if(d==1)return true;
        else return false;
    }
    else if(100-pos>=d){
        pos = pos+d;
        if(a[pos]!=0){
            if(a[pos]>pos)cout<<"congrats, you found a ladder"<<endl;
            else cout<<"Opps, you got bitten by a snake"<<endl;
            return a[pos];
        }
        return pos;
    }
    else return pos;
}
void start(int players){
    vector<int>pos(players,0);
    int p=0;
    while(check(pos)){
        if(p==players)p = 0;
        cout<<"Player "<<p+1<<"'s move : ";
        pos[p] = turn(pos[p]);
        cout<<"Player "<<p+1<<"'s Position is : "<<pos[p]<<endl;
        cout<<endl;
        p++;
    }
}
