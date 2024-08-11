class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int idx = 0;

        for (int i = 0; i < balls.length; i++) {
            int minVal = Integer.MAX_VALUE;
            if (balls[i][0] == startX) {
                minVal = Math.min(minVal, calculateDist(balls[i][0] + 2 * (m - balls[i][0]), balls[i][1], startX, startY));
                minVal = Math.min(minVal, calculateDist(balls[i][0] - (2 * balls[i][0]), balls[i][1], startX, startY));
                if (balls[i][1] < startY) {
                    minVal = Math.min(minVal, calculateDist(balls[i][0], balls[i][1] + 2 * (n - balls[i][1]), startX, startY));
                } else {
                    minVal = Math.min(minVal, calculateDist(balls[i][0], balls[i][1] - (2 * balls[i][1]), startX, startY));
                }
            } else if (balls[i][1] == startY) {
                minVal = Math.min(minVal, calculateDist(balls[i][0], balls[i][1] + 2 * (n - balls[i][1]), startX, startY));
                minVal = Math.min(minVal, calculateDist(balls[i][0], balls[i][1] - (2 * balls[i][1]), startX, startY));
                if (balls[i][0] < startX) {
                    minVal = Math.min(minVal, calculateDist(balls[i][0] + 2 * (m - balls[i][0]), balls[i][1], startX, startY));
                } else {
                    minVal = Math.min(minVal, calculateDist(balls[i][0] - (2 * balls[i][0]), balls[i][1], startX, startY));
                }
            } else {
                minVal = Math.min(minVal, calculateDist(balls[i][0] + 2 * (m - balls[i][0]), balls[i][1], startX, startY));
                minVal = Math.min(minVal, calculateDist(balls[i][0] - (2 * balls[i][0]), balls[i][1], startX, startY));
                minVal = Math.min(minVal, calculateDist(balls[i][0], balls[i][1] + 2 * (n - balls[i][1]), startX, startY));
                minVal = Math.min(minVal, calculateDist(balls[i][0], balls[i][1] - (2 * balls[i][1]), startX, startY));
            }
            answer[idx++] = minVal;
        }
        return answer;
    }

    public int calculateDist(int x, int y, int startX, int startY) {
        return (int) (Math.pow(startX - x, 2) + Math.pow(startY - y, 2));
    }
}