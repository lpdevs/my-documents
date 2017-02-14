#include <iostream>

using namespace std;

int T, N, blocks[10001], number_rp, rp, temp;

int main() {
	cin>> T;
	for(int tc = 0; tc < T; tc ++) {
		cin >> N >> number_rp;// đọc độ dài đoạn đường và chi phí sửa k
		rp = 0;// ban đầu số điểm sửa chữa bằng 0
		for (int i = 0; i < N; i++) {
			cin >> temp;
			if (temp == 0) blocks[rp++] = i+1;// ghi giá trị đoạn bị hỏng
		}
		if (rp <= number_rp) {	// nếu chi phí k đủ để sửa tất cả đoạn bị hỏng
			cout << N << endl;
			continue;
		}

		blocks[rp] = N + 1; //thêm 1 giá trị điểm hỏng ảo ở vị trí N + 1

		temp = blocks[number_rp] - 1;//đoạn đường nếu sửa k đoạn đầu tiên
		for (int i = number_rp + 1; i <= rp; i++)
			if (blocks[i] - blocks[i - number_rp - 1] - 1 > temp) temp = blocks[i] - blocks[i - number_rp - 1] - 1; // nếu sửa k đoạn liên tiếp nhau mà có khoảng cách xa hơn thì ghi nhận
		
		cout<<temp<<endl;	// trả kết quả
	}
	return 0;
}