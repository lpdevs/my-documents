#include <stdio.h>
void main(){
	int T, testCase;
	int x, y, N;
	int myNumber;
	scanf("%d", &T); // Nhap vao so luong test case
	for (testCase = 0; testCase<T; testCase++){
		// Nhap 3 so N, x, y
		scanf("%d", &N);
		scanf("%d", &x);
		scanf("%d", &y);
		myNumber = x;   // khoi dau gia tri tim kiem bang x
		while (myNumber<N)
		{
			if (myNumber%y != 0) // Kiem tra so dang xet co chia het cho y khong, neu khong thi in ra
				printf("%d ", myNumber);
			myNumber += x; // tiep tuc kiem tra cac so co dang 2*x, 3*x,    nho hon N
		}
		printf("\n");
	}
}