import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserSolution {
	static final int MAX_N = 25;

	int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };
	int[] dx = { 0, 0, -1, 1, 1, -1, -1, 1 };
	String[][] arr = new String[MAX_N][MAX_N];
	Map<String, Integer> soldiers = new HashMap<>();
	Map<String, Set<String>> allies = new HashMap<>();
	Map<String, Set<String>> enemies = new HashMap<>();

	void init(int N, int mSoldier[][], char mMonarch[][][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int soldier = mSoldier[i][j];
				String s = extractName(mMonarch[i][j]);
				arr[i][j] = s;

				soldiers.put(s, soldier);

				// 동맹 리스트는 초기에 자신 추가
				Set<String> allySet = new HashSet<>();
				allySet.add(s);
				allies.put(s, allySet);
				enemies.put(s, new HashSet<>());
			}
		}
	}

	void destroy() {

	}

	int ally(char mMonarchA[], char mMonarchB[]) {
		String a = extractName(mMonarchA);
		String b = extractName(mMonarchB);
		if (a.equals(b)) {
			return -1;
		}
		Set<String> aAllies = allies.get(a);
		Set<String> bAllies = allies.get(b);
		Set<String> aEnemies = enemies.get(a);
		Set<String> bEnemies = enemies.get(b);

		// 어차피 양방향으로 추가되니 확인은 단방향만 해줘도 된다.
		if (aAllies.contains(b)) {
			return -1;
		}
		for (String s : aAllies) {
			if (bEnemies.contains(s)) {
				return -2;
			}
		}

		// 동맹 상대의 동맹들도 추가
		for (String s : bAllies) {
			aAllies.add(s);
		}
		for (String s : aAllies) {
			bAllies.add(s);
		}

		// 동맹 상대의 적군들도 추가
		for (String s : bEnemies) {
			aEnemies.add(s);
		}
		for (String s : aEnemies) {
			bEnemies.add(s);
		}

		allies.put(a, aAllies);
		allies.put(b, bAllies);
		enemies.put(a, aEnemies);
		enemies.put(b, bEnemies);

		return 1;
	}

	int attack(char mMonarchA[], char mMonarchB[], char mGeneral[]) {
		String a = extractName(mMonarchA);
		String b = extractName(mMonarchB);

		Set<String> aAllies = allies.get(a);
		Set<String> bAllies = allies.get(b);
		Set<String> aEnemies = enemies.get(a);
		Set<String> bEnemies = enemies.get(b);

		if (a.equals(b) || aAllies.contains(b)) {
			return -1;
		}

		int[] pos = isAvailableBattle(a, b, aAllies);
		if (pos == null) {
			return -2;
		}

		return battle(a, b, pos[0], pos[1]);
	}

	int recruit(char mMonarch[], int mNum, int mOption) {

		String monarch = extractName(mMonarch);

		int total = 0;

		if (mOption == 0) {
			soldiers.merge(monarch, mNum, Integer::sum);
			total = soldiers.get(monarch);
		} else if (mOption == 1) {
			for (String ally : allies.get(monarch)) {
				soldiers.merge(ally, mNum, Integer::sum);
				total += soldiers.get(ally);
			}
		}

		return total;
	}

	private String extractName(char[] mMonarch) {
		StringBuilder sb = new StringBuilder();
		for (char c : mMonarch) {
			sb.append(c);
		}
		return sb.toString();
	}

	private int[] isAvailableBattle(String a, String b, Set<String> aAllies) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {

				if (arr[i][j].equals(b)) {
					for (int k = 0; k < 8; k++) {

						int y = i + dy[k];
						int x = j + dx[k];
						if (y < 0 || x < 0 || y >= arr.length || x >= arr.length) {
							continue;
						}

						// 전투 시작 !
						if (aAllies.contains(arr[y][x])) {
							return new int[] { y, x };
						}
					}
				}

			}
		}

		return null;
	}

	// return 1 if offense win
	// return 0 if defense win
	private int battle(String a, String b, int y, int x) {
		Set<String> aAllies = allies.get(a);
		Set<String> bAllies = allies.get(b);
		Set<String> aEnemies = enemies.get(a);
		Set<String> bEnemies = enemies.get(b);

	}
}
