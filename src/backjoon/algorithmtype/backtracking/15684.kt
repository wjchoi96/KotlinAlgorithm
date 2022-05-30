package src.backjoon.algorithmtype.backtracking
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

    3 초과는 -1, 불가능해도 -1 출력
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

/*
    참고 
    https://leveloper.tistory.com/96 => 주 
    https://pangtrue.tistory.com/282 => 보조

    제출
    1. 틀렸습니다(6%)
    - x 마지막 줄 체크를 안하는것 확인

    2. 시간초과(64%)
    - 자주 하는 실수 또함
    - 현재 idx 를 다음 dfs로 넘겨주는데 거기에 함수인자로 전달받은 x를 넘겨버림

    3. 성공
 
    4. 실패(70%)
    - 테스트겸 다음 dfs호출시 nx 대신 nx+1을 넘겨봄
    - https://www.acmicpc.net/board/view/85699
*/

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    Solution15684().solve()
}

class Solution15684 {
    private lateinit var board : Array<Array<Int>>
    private var m = 0 // 배치되어있는 가로선의 개수
    private var n = 0 // y
    private var h = 0 // x
    private val dy = arrayOf(-1, 1)
    private var min = Int.MAX_VALUE

    private fun dfs(x : Int = 1, addRadder : Int = 0, maxRadder : Int){
        if(addRadder == maxRadder){
            if(checkRadder()) {
                min = Math.min(min, maxRadder)
            }
            return
        }

        for(nx in x until h + 1){
            for(ny in 1 until n){ //마지막 세로줄은 체크 안한다 -> 오른쪽으로만 배치
                // 배치된 사다리가 없고, 사다리를 배치할 수 있다면
                if(board[nx][ny] == 0 && board[nx][ny+1] == 0){
                    board[nx][ny] = ny+1 // 사다리 배치
                    board[nx][ny+1] = ny // 사다리 배치

                    dfs(nx, addRadder+1, maxRadder) // 여기를 nx+1 로할지 nx로할지에 따라서도 답이 갈린다 // https://www.acmicpc.net/board/view/85699

                    board[nx][ny] = 0// 사다리 철거
                    board[nx][ny+1] = 0 // 사다리 철거
                }
            }
        }

    }

    private fun checkRadder() : Boolean {
        for(i in 1 until n+1){
            var x = 1
            var y = i
            while(x <= h){
                if(board[x][y] != 0){ // 배치된 사다리가 있는것
                    if(board[x][y] < y){
                        y--
                    }else{
                        y++
                    }
                }
                x++
            }
            if(y != i){
                return false
            }
        }
        return true
    }

    fun solve(){
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
            // 상호 연결
            board[x][fromY] = fromY + 1
            board[x][fromY + 1] = fromY
        }

        for(i in 0 until 4){
            dfs(maxRadder=i)
        }

        bw.write("${if(min == Int.MAX_VALUE)-1 else min}\n")

        bw.flush()
        bw.close()
        br.close()
    }

    // ##################### try 1 개선 ######################
    private var minRadder = 3 // maxRadder 와 같게 하면 된다
    private fun dfs2(x : Int = 1, addRadder : Int = 0){
        if(minRadder < addRadder){
            return
        }
        if(checkRadder()){
            minRadder = Math.min(minRadder, addRadder)
            return
        }
        for(nx in x until h+1){
            for(ny in 1 until n){
                if(board[nx][ny] == 0 && board[nx][ny+1] == 0){
                    board[nx][ny] = ny+1
                    board[nx][ny+1] = ny

                    dfs2(nx, addRadder + 1)

                    board[nx][ny] = 0
                    board[nx][ny+1] = 0
                }
            }
        }

    }
    private fun canAddRadder(x : Int, y : Int) : Boolean {
        return board[x][y] == 0
    }

// ##################### try 1 ######################
// private fun dfsTry1(fromY : Int = 1, x : Int = 1, y : Int = 1, addRadder : Int = 0){
//     if(fromY == n && x == h && y == n){ // 5번기둥출발이 5번으로 왔을때
//         print("finish : [$x][$y] : $addRadder\n")
//         min = Math.min(min, addRadder)
//         return
//     }
//     // x의 끝좌표에 도착했을때
//     if(x == h){
//         if(y == fromY){ // 출발기둥 == 도착기둥
//             dfsTry1(fromY + 1, 1, y+1, addRadder) // 다음기둥의 1좌표로 이동, 출발 기둥 update
//         }
//         // 다른기둥에 도착했다면 버려야하는 가지
//         return
//     }

//     // 배치를 위해 체크하는 코드
//     for(i in 0 until 2){
//         val ny = y + dy[i]
//         if(ny<0 || ny>n){ // size check
//             continue
//         }
//         // 배치할수있다면 배치하는 가지
//         if(canAddRadder(x, ny)){
//             board[x][y] = ny // [x,y] -> [x,ny]로 사다리 배치
//             dfsTry1(fromY, x, ny, addRadder + 1) // 사다리 배치한 좌표로 이동
//             board[x][y] = 0 // 백트래킹을 위해 사다리 배치 취소
//         }
//     }
//     dfsTry1(fromY, x+1, y, addRadder) // 배치를 안하는 가지
// }
// private fun canAddRadder(x : Int, y : Int) : Boolean {
//     return board[x][y] == 0
// }
}
