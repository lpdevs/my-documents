#include<iostream>
using namespace std;

int test_case;

// reversedNumber(int n): đầu vào là 1 số và trả về số đảo ngược của số đó
int reversedNumber(int n){
    int sum = 0;
    while (n > 0)
	{
        int a = n % 10; // tách từng chữ số từ số ban đầu
        n = n/10;
        sum = sum * 10 + a; // cộng từng chữ số bằng cách * 10 và cộng dồn vào từng chữ số
    }
    return sum;
}

int main(){
    int a, b, sum;
    cin >> test_case;
    for(int i = 0; i < test_case; i++){
        cin >> a >> b;
		// in ra đào ngược của tổng 2 số đảo ngược
        cout << reversedNumber(reversedNumber(a) + reversedNumber(b)) << endl;
    }
    return 0;
}
