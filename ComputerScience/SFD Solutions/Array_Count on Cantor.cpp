/*
Hinh anh mo ta quy luat:
https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Diagonal_argument.svg/429px-Diagonal_argument.svg.png
*/

#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

int main(int agrc, char** argv)
{
	// your code here
	//freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);

	int T = 0, N = 0, x = 1, y = 1, Temp = 0;
	bool DiCheoLen = true;
	cin >> T;

	for (int testCase = 0; testCase < T; testCase++)
	{
		cin >> N;
		x = 1; y = 1;		// So dau tien la 1/1
		DiCheoLen = true;	// Khoi tao di cheo len la dung de di xuong cho so thu 2
		Temp = N - 1;		// Bat dau tu so thu 2
		while (Temp > 0)
		{
			Temp--;

			if (x == 1 && DiCheoLen == true)	//Dang di cheo len va gap x = 1 thi di sang phai (giu nguyen x va tang y)
			{
				y++;
				DiCheoLen = false;
			}
			else if (y == 1 && DiCheoLen == false) //Dang di cheo xuong va gap y = 1 thi di xuong duoi (tang x va giu nguyen y)
			{
				x++;
				DiCheoLen = true;
			}
			else
			{
				if (DiCheoLen == true)	//Dang di cheo len thi giam x va tang y
				{
					x--;
					y++;
				}
				else //Dang di cheo xuong thi tang x va giam y
				{
					x++;
					y--;
				}
			}
		}

		cout << "TERM " << N << " IS " << x << "/" << y << endl;
	}

	return 0;
}