#include <iostream>
using namespace std;

int Cache[10001][4];

int main()
{
	int T;
	cin >> T;

	Cache[1][1] = 1;
	Cache[2][1] = 1;
	Cache[2][2] = 1;
	Cache[3][1] = 1;
	Cache[3][2] = 1;
	Cache[3][3] = 1;

	for (int i = 0; i < T; i++)
	{
		int Answer;
		int N;
		cin >> N;
		if (N >= 4)
		{
			for (int i = 4; i <= N; ++i)
			{
				Cache[i][1] = Cache[i - 1][1];
				Cache[i][2] = Cache[i - 2][1] + Cache[i - 2][2];
				Cache[i][3] = Cache[i - 3][1] + Cache[i - 3][2] + Cache[i - 3][3];
			}
		}
		Answer = Cache[N][1] + Cache[N][2] + Cache[N][3];
		cout << Answer << '\n';
	}

	return 0;
}