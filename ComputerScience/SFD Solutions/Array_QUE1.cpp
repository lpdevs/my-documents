/*
	Lưu chiều cao vào mảng A, chỉ số vào mảng B
	Sắp xếp theo thứ tự giảm dần của chiều cao ( lưu ý đổi chỗ cả chỉ số)
	Nếu chỉ số B không khớp vs index trong mảng thì đổi chỗ sao cho index và chỉ số khớp nhau
	
*/
#include <stdio.h>
#define maxN 1001

int N;
int A[maxN], B[maxN];

// Sắp xếp theo thứ tự giảm dần của chiều cao - đổi chỗ cả chỉ số
void sort() {
	int i, j, temp;
	for (i=0; i<N-1; i++)
	for (j=i+1; j<N; j++)
		if (A[j]>A[i]) {
			temp = A[i]; A[i] = A[j]; A[j] = temp;
			temp = B[i]; B[i] = B[j]; B[j] = temp;
		}
}

// Nếu chỉ số B không khớp vs index trong mảng thì đổi chỗ sao cho index và chỉ số khớp nhau
void select() {
	int i, j, temp;
	for (i=0; i<N; i++)
		if (B[i]!=i) {
			for (j=i; j>B[i]; j--) {
				temp = A[j]; A[j] = A[j-1]; A[j-1] = temp;
			}
		}
	for (i=0; i<N; i++) printf("%d ", A[i]);
	printf("\n");
}

int main() {
	int Tc, i;
	//freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &Tc);
	while (Tc>0) {
		scanf("%d", &N);
		for (i=0; i<N; i++)
			scanf("%d", &A[i]);
		for (i=0; i<N; i++)
			scanf("%d", &B[i]);
		sort();
		select();
		Tc--;
	}
	return 0;
}