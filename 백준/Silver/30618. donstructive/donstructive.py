n = int(input())
for i in range(1, n + 1, 2):
    print(i, end=" ")
if n % 2 == 1:
    n -= 1
for i in range(n, 1, -2):
    print(i, end=" ")
