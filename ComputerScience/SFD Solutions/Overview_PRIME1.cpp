#include <stdio.h>

int main(void) {
	// freopen("input.txt", "r", stdin);
	int T, m, n;
	scanf("%d", &T);
	
	for (int t = 0; t < T; t++) {
		scanf("%d %d", &m, &n);
		
		for (int i = m; i <=n; i++) { // Duyệt tất cả các số nguyên nằm giữa m và n
			if (i > 1) { // Bỏ qua 1 không phải số nguyên tố
				char isPrime = 1;
			
				for (int j = 2; j * j <= i; j++) { // Duyệt tất cả các số nguyên từ 2 đến [sqrt(số đang xét)], bỏ qua các số 2, 3 đã là nguyên tố
					if (i % j == 0) { // Kiểm tra xem số đang xét có chia hết cho giá trị nào không
						isPrime = 0; // Nếu có thì số đang xét không phải nguyên tố
						break; // Thoát vòng lặp tránh bị time limit exceeded
					}
				}
				
				if (isPrime) { // Nếu đúng là số nguyên tố thì in ra, xuống dòng giữa các giá trị
					printf("%d\n", i);
				}
			}
		}
		printf("\n"); // In 1 dòng cách giữa các test case
	}
	return 0;
}