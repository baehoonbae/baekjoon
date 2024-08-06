#include<iostream>
using namespace std;

int arr[100001];
int sum[100001];
int main(void)
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int start, end, temp = 1, n, m;
	cin >> n >> m;

	for (int i = 1; i <= n; i++)
		cin >> arr[i];

	for (int i = 1; i <= m; i++)
	{
		cin >> start >> end;
		if (end < temp)
			cout << sum[end] - sum[start - 1] << '\n';
		else
		{
			for (int i = temp; i <= end; i++)
				sum[i] = sum[i - 1] + arr[i];
			cout << sum[end] - sum[start - 1] << '\n';
			temp = end;
		}
	}
	return 0;
}