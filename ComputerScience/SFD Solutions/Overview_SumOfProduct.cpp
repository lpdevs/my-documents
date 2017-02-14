#include<iostream>        //SUMPRO 
#define mod 1000000007
using namespace std;

int main(){
	// freopen("input.txt", "r", stdin);	// su dung ham freopen cho viec debug
	setbuf(stdout, NULL);
    int testcase;
	scanf("%d",&testcase);
	for(int T=0; T < testcase; T++) {
        int n , i, last;
		scanf("%d", &n);	// long long nen phai dung %lld hoac su dung cin >> n
        long long ans = 0;	// gia tri co the bang 10^9 * 10^9 nen phai dung long long
        for(i = 1; i*i <= n; i++)                  // gia su cac tich dc liet ke dang k * p thi vong for nay de tinh cac tich co k < can bac 2 cua n 
            ans = (ans + (i * (n/i))%mod)%mod;     //tu k+1 tro di p se quay lai cac gia tri cua k
		i--;	// do i++ lan cuoi cung
        last = n/i;
        for(i = 1; i < last; i++){       // vong for nay de tinh tong lan luot cac tich voi p chay tu 1 den k
            long long en = n/i;
            long long st = n/(i+1);
            long long diff = (en*(en+1)/2)-  (st*(st+1)/2);
            ans =  (ans + (i*diff)%mod)%mod;
        }
		printf("%lld \n", ans);
    }
    return 0;
}