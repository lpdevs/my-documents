#include <iostream>
//Trong đề cho max của N là 10000, tuy nhiên ta cứ định nghĩa rộng ra thêm vài giá trị để đỡ phải quản lý biên
#define MAXSIZE 10010
using namespace std;
int arr[MAXSIZE];//mảng arr để chứa dãy block đề cho

int main(){
	int T, N; //T là số testcase, N là số block;
	int sum;
	//freopen("input.txt", "r", stdin);
	cin >> T;
	for(int tc=1;tc<=T;tc++){
		sum = 0;
		cin >> N;
		for(int i=1;i<=N;i++)
			cin >> arr[i];
		//Kết thúc nhập dữ liệu. Bắt đầu thực hiện chương trình.
		int maxBlock = 0; 
		int indexMaxBlock = 0;//Biến indexMaxBlock để lưu vị trí của block có độ cao lớn nhất
		for(int i=1;i<=N;i++){
			if(arr[i]>maxBlock){
				maxBlock=arr[i];
				indexMaxBlock=i;
			}
		}
		//Đến đây ta đã có được vị trí block có độ cao lớn nhất.
		//Thuật toán sau chia toàn bộ block thành 2 nửa:
		//-Nửa đầu tiên tính từ block đầu tiên đến block có chiều cao lớn nhất.
		//-Nửa thứ 2 tính tình block max đến hết dãy block
		//Thực hiện tính phần nửa đầu tiên
		int leftBlock=arr[1];
		for(int i=2;i<=indexMaxBlock;i++){
			if(arr[i]>leftBlock){
				leftBlock=arr[i];
				continue;
			}
			sum=sum+(leftBlock-arr[i]);
		}
		//Thực hiện tính nửa thứ 2
		int rightBlock=arr[N];
		for(int i=N-1;i>=indexMaxBlock;i--){
			if(arr[i]>rightBlock){
				rightBlock=arr[i];
				continue;
			}
			sum=sum+(rightBlock-arr[i]);
		}
		//in kết quả
		cout << sum << endl;
	}
	return 0;
}