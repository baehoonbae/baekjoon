import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 19237. 어른 상어 / 골드2 / 8:37~10:39
class Main {
	static class Shark{
		int[][] priority;
		int dir;
		int y,x;
		Shark(int y,int x){
			this.y=y;
			this.x=x;
			priority=new int[4][4];
		}
	}
	
	static class Info{
		int idx;
		int smell;
		boolean isShark;		
		Info(int idx,int smell,boolean isShark) {
			this.idx=idx;
			this.smell=smell;
			this.isShark=isShark;
		}
	}
	
	static final int[] dy= {-1,1,0,0};
	static final int[] dx= {0,0,-1,1};
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,m,k;
	static Shark[] ss;
	static Info[][] arr;
	
	public static void main(String[] args) throws IOException {
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		ss=new Shark[m+1];
		arr=new Info[n][n];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int num=Integer.parseInt(st.nextToken());
				if(num>0) {
					ss[num]=new Shark(i,j);
					arr[i][j]=new Info(num,k,false);
				}else {
					arr[i][j]=new Info(-1,0,false);
				}
			}
		}
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=m;i++) {
			int dir=Integer.parseInt(st.nextToken())-1;
			ss[i].dir=dir;
		}
		
		for(int i=1;i<=m;i++) {
			for(int j=0;j<4;j++) {
				st=new StringTokenizer(br.readLine());
				for(int k=0;k<4;k++) {
					int dir=Integer.parseInt(st.nextToken())-1;
					ss[i].priority[j][k]=dir;
				}
			}
		}
		
		int time=0;
		while(true) {
//			print();
			time++;
			if(time>1000) {
				System.out.println(-1);
				return;
			}
			move();		// 상어 이동
			update();	// 냄새 상태 업데이트
			if(check()) break;
		}
		System.out.println(time);
	}

	private static void move() {
		Info[][] temp=new Info[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				Info info=arr[i][j];
				temp[i][j]=new Info(info.idx,info.smell,info.isShark);
			}
		}
		for(int i=1;i<=m;i++) {
			Shark s=ss[i];
			if(s==null) continue;
			boolean canMove=false;
			for(int d:s.priority[s.dir]) {
				int ny=s.y+dy[d];
				int nx=s.x+dx[d];
				if(ny<0||nx<0||ny>=n||nx>=n||arr[ny][nx].idx!=-1)continue;
				
				canMove=true;
				
				temp[s.y][s.x].isShark=false;
				s.y=ny;
				s.x=nx;
				s.dir=d;
				if(i>1&&temp[ny][nx].idx!=-1&&temp[ny][nx].idx<i&&temp[ny][nx].isShark) {
					ss[i]=null;
					break;
				}
				temp[ny][nx]=new Info(i,k,true);
				break;
			}
			if(!canMove) {
				for(int d:s.priority[s.dir]) {
					int ny=s.y+dy[d];
					int nx=s.x+dx[d];
					if(ny<0||nx<0||ny>=n||nx>=n)continue;
					if(arr[ny][nx].idx==i) {
						temp[s.y][s.x].isShark=false;
						s.y=ny;
						s.x=nx;
						s.dir=d;
						if(i>1&&temp[ny][nx].idx!=-1&&temp[ny][nx].idx<i&&temp[ny][nx].isShark) {
							ss[i]=null;
							break;
						}
						temp[ny][nx]=new Info(i,k,true);
						break;						
					}
				}
			}
		}
		for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) {
				arr[j][k]=temp[j][k];
			}
		}
	}

	private static void update() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j].isShark) continue;
				if(arr[i][j].idx==-1) continue;
				if(arr[i][j].idx!=-1) arr[i][j].smell--;
				if(arr[i][j].smell==0) arr[i][j].idx=-1;
			}
		}
		
	}

	private static boolean check() {
		for(int i=2;i<=m;i++) if(ss[i]!=null)return false;
		return true;
	}
	
	private static void print() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j].idx==-1) {
					System.out.print(0+"(0) ");
				}else {
					System.out.print(arr[i][j].idx+"("+arr[i][j].smell+") ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}
}