#include <iostream>
using namespace std;
long n;

int main(int argc, char** argv)
{
	freopen("input.txt", "r", stdin);
	int T, test_case;

	cin >> n;
	while (n != 0){							// if n == 0 return
		cout << n*(n+1)*(2*n+1)/6 << endl;	// Print answer
		cin  >> n;							// input n for the next testcase
	}
	return 0;
}

// Analysis:
//With N=1 -> Anser = 1 = 1^2;
//With N=2 -> Anser = 5 = 1^2 + 2^2;
//With N=3 -> Anser = 14= 1^2 + 2^2 + 3^2;
//.
//.
//General: Anser = 1^2+2^2+3^2+ .... + N^2 = N(N+1)(2N+1)/6