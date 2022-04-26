//gold4
/*
    사다리 조작

    N개의 세로선
    M개의 가로선
    H개의 가로선을 놓을 수 있는 위치의 개수
    H * N 의 board

    인접한 세로선 사이에는 가로선을 놓을 수 있다
    각 세로선마다 가로선을 놓을 수 있는 위치의 개수는 H
    모든 세로선이 같은 위치를 가진다

    가로선은 인접한 두 세로선을 연결해야한다
    가로선은 연속하거나, 서로 접하면 안된다

    i번 세로선의 결과가 i번이 나오도록 조작해야한다
    추가해야하는 가로선 개수의 최소값을 구하라
    => 현재 가로선이 이미 놓아져 있음

    2초
    2<= N <= 10
    1<= H <= 30
    0<= M <= (N-1)*H

    가로선의 정보는 두 정수 a,b로 나타낸다 
    1<= a <= H, 1<= b <= N-1

    b번 세로선과 b+1 세로선을 a번 점선 위치에 연결했다는 의미
    좌표는 [1,1] 로 시작한다
*/

/*
    사다리[x][y] = 0 
    => 가로선 안놓아져있음
    사다리[x][y] = 2(세로기둥)
    => 세로기둥2로 가는 가로선이 놓아져 있음

    조건
    1. 가로선은 인접한 두 세로선을 연결해야한다
    => (0<x || x>n) -> continue 대충 이느낌~

    2. 가로선은 연속하거나, 서로 접하면 안된다
    => 사다리[1][1] = 2 
    => 사다리[1][2] 에는 가로선을 놓을 수 없다
    => 사다리[x][y] 에 가로선을 놓을지 고민중이라면 y의 값을 가진 사다리 배열이 있나 확인해봐야한다
    => y에 놓으려면 사다리[x][y-1], 사다리[x][y+1] 둘중 하나뿐이니 이 둘을 체크해봐야한다

    백트래킹
    1. [1,1]부터 시작
    2. y-1, y+1에 사다리를 놓을 수 있나 확인
    => 놓을 수 있다면 놓고 다음 dfs 진행
    => 없다면 다음 y+1, if(y+1>N) x+1
    3. M개의 가로선을 다 배치했거나, size 를 벗어난다면 return 
    => M개의 가로선을 다 배치했다면 사다리를 타본다
    

    1. 하나의 세로선 먼저 해결해 나갈까?
    해결된 세로선에는 가로선을 연결 못하게끔 진행

    - [1,1] 시작
    - y-1, y+1 에 사다리를 놓을 수 있나 확인
    => size check, 해당위치에 사다리를 놓았을때 인접해지는 가로선 존재 유무, 이미 완료된 세로선인지 체크

    가로선을 하나 배치할때마다 사다리를 타본다
    => 아직 완료되지않은 가장 작은 사다리부터
    => i출발 i도착이 완료되었다면 해당 사다리 잠금, dfs 진행, 해당 사다리 해금

    사다리[x][y] = 0 => 사다리가 배치되어있지 않은 상태
    사다리[x][y] = i => (x,y) -> (x,i) 로 사다리 배치, i == y-1 || i == y+1
    

    dfs(x, y, depth)
    사다리 배치 코드
    1. 사다리 배치 가능한지 체크
    2. 사다리 배치
    3. 사다리를 진행해보고 현재 y기둥이 y기둥으로 끝나는지 체크
    3.1 : y기둥으로 도착
    => dfs(1, y+1, depth + 1)
    3.2 : y기둥으로 도착x
    => dfs(x+1, y, depth + 1)

    4. 사다리 배치 취소
    => 사다리[x][y] = 0

    근데 이러면 y-1 로는 가로선을 배치 못하지않나?
    마지막 기둥에서는 어쩌지?
    쭉 진행하고 depth == m 이 되었을때 마지막 기둥 체크

    [1,1] 부터 쭉 진행하는게 아닌 가로선의 진행대로 나아가야하나?

    1. 현재 좌표 체크
    1-1. 사다리가 놓아져 있다면 해당 좌표로 이동, 1번으로 돌아감
    1-2. 사다리가 놓여지있지 않다면 사다리 배치 작업 실시

    2. 사다리 배치작업
    2-1. 현재 위치에 사다리를 놓을 수 있는지 체크
    2-2. 놓을 수 있다면 배치
    2-3. 놓을 수 없다면 x+1
    2-4. x+1 했을때 x사이즈를 넘은경우 -> 도착한 기둥이 출발기둥과 같다면 y+1

*/

import java.io.*
import java.util.StringTokenizer
private lateinit var board : Array<Array<Int>>
private var m = 0 // 배치되어있는 가로선의 개수
private var n = 0 // y
private var h = 0 // x
private val dy = arrayOf(-1, 1)
private var min = Int.MAX_VALUE
private fun dfs(fromY : Int = 1, x : Int = 1, y : Int = 1, addRadder : Int = 0){
    if(fromY == n && x == h && y == n){ // 5번기둥출발이 5번으로 왔을때
        print("finish : [$x][$y] : $addRadder\n")
        min = Math.min(min, addRadder)
        return
    }
    // x의 끝좌표에 도착했을때
    if(x == h){
        if(y == fromY){ // 출발기둥 == 도착기둥
            dfs(fromY + 1, 1, y+1, addRadder) // 다음기둥의 1좌표로 이동, 출발 기둥 update
        }
        // 다른기둥에 도착했다면 버려야하는 가지
        return
    }
    // 배치를 안한 가지가 나아갈 곳이 없다
    // for문으로 한번 더 감아주던지, 배치를 안하는 가지가 나아갈 길을 제시해줘야할듯
    for(i in 0 until 2){
        val ny = y + dy[i]
        if(ny<0 || ny>n){ // size check
            continue
        }
        if(!canAddRadder(x, ny)){ // 사다리를 놓을 수 없다면
            dfs(fromY, x+1, y, addRadder) // 한칸 아래로 이동
        }else{
            board[x][y] = ny // [x,y] -> [x,ny]로 사다리 배치
            dfs(fromY, x, ny, addRadder + 1) // 사다리 배치한 좌표로 이동
            board[x][y] = 0 // 백트래킹을 위해 사다리 배치 취소
        }
    }
}

private fun canAddRadder(x : Int, y : Int) : Boolean {
    return board[x][y] == 0
}

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt() // 세로선 y
    m = st.nextToken().toInt() // 배치되어있는 사다리 개수
    h = st.nextToken().toInt() // 가로선 x

    board = Array(h+1){Array(n+1){0}}

    for(i in 0 until m){ // 사다리 배치
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val fromY = st.nextToken().toInt()
        board[x][fromY] = fromY + 1
    }

    for(x in 0 until h+1){
        for(y in 0 until n+1){
            print("${board[x][y]} ")
        }
        print("\n")
    }

    dfs()
    bw.write("$min\n")

    bw.flush()
    bw.close()
    br.close()
}
