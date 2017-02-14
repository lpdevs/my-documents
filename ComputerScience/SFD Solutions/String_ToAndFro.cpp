#include <iostream>
#include <string.h>
using namespace std;

int main(void)
{
	//freopen("sample_input.txt", "r", stdin);
	int len;
	int i,j,k,t;
	int temp = 0;
	char A[100][100];

	cin >> t; // số cột của test case đầu tiên
	while(t != 0)
	{
		char S[1000];
		temp = 0;
		cin >> S;
		len = strlen(S); 	// chiều dài của string S
		k = len/t; 			// số hàng

		for(i = 1;i <= k; i++)
		{
			for(j = 1; j <= t; j++)
			{
				if(i%2==0)	// duyệt từ cột phải qua trái
				{
					A[i][t-j+1] = S[temp];
					temp++;
				}
				else  // duyệt từ cột trái qua phải
				{
					A[i][j] = S[temp];
					temp++;
				}
			}
		}
		// in theo từng cột cho đến hết
		for(j = 1; j <= t; j++)
			for(i = 1; i <= k; i++)
				cout << A[i][j];
		cout << endl;
		cin >> t;// số cột của test_case tiếp theo
	}
	return 0;
}
