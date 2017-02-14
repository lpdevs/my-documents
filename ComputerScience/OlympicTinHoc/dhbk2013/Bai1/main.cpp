#include<conio.h>
#include<iostream.h>
#include<fstream>
#include<vector>

using namespace std;

int main(){
    ifstream input("VOL.INP");
    vector<int> a;
    char b;
    int k; // so luong test
    if(!input.is_open()) cout<<"Can not open the file!"<<endl;
    else {
         cout<<"The file is opened"<<endl;
         while(!input.eof()){
         input >> b;
         a.push_back(b-48);// chuyen ky tu sang int
         }
         a.pop_back();
         k = a[0];
         for(int i=0;i<k;i++){
             
         }    
    }    
    getch();
}
