#include<iostream>
using namespace std;

int cal[4];
int a[12];
int n;
int minnum = 1111111111;
int maxnum = -1111111111;

void dfs(int result, int here)
{
	if (here == n - 1)
	{
		if (minnum > result)
			minnum = result;
		if (maxnum < result)
			maxnum = result;
		return;
	}
	for (int i = 0; i < 4; i++)
	{
		if (cal[i] == 0)
			continue;
		cal[i]--;
		if (i == 0)
			dfs(result + a[here + 1], here + 1);
		else if (i == 1)
			dfs(result - a[here + 1], here + 1);
		else if (i == 2)
			dfs(result * a[here + 1], here + 1);
		else
			dfs(result / a[here + 1], here + 1);
		cal[i]++;
	}
}
int main()
{
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> a[i];
	for (int i = 0; i < 4; i++)
		cin >> cal[i];
	dfs(a[0], 0);
	cout << maxnum << endl << minnum;

	return 0;
}