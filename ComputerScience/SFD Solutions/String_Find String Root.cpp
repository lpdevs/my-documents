#include <iostream>
#include <string.h>

using namespace std;

char S[100001]; //khai bao mang de luu xau ky tu

int main(){
	//freopen("input.txt", "r", stdin); //doc du lieu tu file
	cin >> S;
	while(S[0] != '*'){ 					//kiem tra dieu kien dung
		int slen = strlen(S); 				//tinh do dai xau dau vao
		bool result = false;  				//trang thai cho biet da tim thay ket qua hay chua? -> true tuc da tim duoc ket qua
		int root; 							//gia tri do dai xau con be nhat can tim
		for(root = 1; !result && root <= slen; root++){ 				//duyet lan luot tu do dai 1-> slen cho den khi tim duoc ket qua (result == true)
			if(slen%root == 0){ 										//ket qua can tim phai la uoc cua slen
				result = true; 											//ngam dinh truong hop dang xet la ket qua dung
				for(int i = 0; result && i < root; i++){ 				//duyet lan luot cac vi tri cua xau dau tien
					for(int j = root; result && j < slen; j+=root){ 	//duyet cac vi tri tuong ung theo do nhay root (do dai cua xau ket qua dang xet)
						if(S[i] != S[i+j]) { 							//so khop gia tri cac vi tri tung ung
							result = false; 							//truong hop dang xet khong phai la ket qua, tra ve gia tri false
						}
					}
				}
			}
		}
		// ket thuc vong FOR thi root se lon hon gia tri dung 1 gia tri do 'root++'
		cout << slen/(root-1) << endl;
		cin >> S; //doc dong tiep theo
	}
	return 0;
}