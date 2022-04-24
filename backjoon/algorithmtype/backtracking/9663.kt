//gold5
/*
    N-Queen 문제

    n*n 체스판 위에 퀸 n개를 서로 공격할 수 없게 배치

    배치가능한 수를 출력

    1 <= N < 15

    1. 같은 x축에 배치할 수 없다
    2. 같은 y축에 배치할 수 없다
    3. 같은 대각선에 배치할 수 없다
    - 대각선 계산 공식 (x1-x1)! == (y1-y2)!
    - x좌표끼리 차의 절대값 == y좌표끼리 차의 절대값
    - (1,1)(2,2)(1,3)
    - (3,1)     (3,3)

    n*n board 를 순회하면서 퀸을 하나씩 배치한다
    배치하면서 이전에 배치된 퀸들과 유효성 검사를 실시 후 가능한 위치에만 퀸을 배치

    for(x in 1 until n+1){
        for(y in 1 until n+1){
            x1에 배치했다면 다음 순회는 x=2부터 시작하면 되나? => no 
            x2이 첫번째로 배치되는 경우의 수도 존재할것
        }
    }

    ====================================
    이전에 어떻게 생각했나 확인해보다가 1차원배열 + for문 하나로 수행하는 방식을 검색해서 인지했다는 사실을 알아냄

    1. 같은 x축에는 하나밖에 퀸이 존재할수 있다
    2. queen[x] = y
    이런식으로 queen 배열 수행가능

    ====================================
    제출
    1. 성공
*/

import java.io.*
private lateinit var bw : BufferedWriter
private lateinit var queens : Array<Int>
private var n = 0
private var queenCount = 0
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    n = br.readLine().toInt()
    queens = Array(n){-1}

    dfs(0)
    bw.write("$queenCount\n")

    bw.flush()
    bw.close()
    br.close()
}

/*
    퀸을 배치한다
    depth 가 n과 같다면 모두 배치한것
*/
private fun dfs(depth : Int){
    if(depth == n){
        queenCount++
        return
    }
    for(y in 0 until n){
        if(avliableQueen(depth, y)){
            queens[depth] = y
            dfs(depth + 1)
        }
    }
}


// x축은 검사하면 안된다 -> 배열 초기화 과정에서 같은 x축이 무조건 있기 때문
// dfs에서 같은 x축이 나올 수 없기 때문에 제한을 해제해줘도 괜찮다
// queens 의 전체x축을 다 검사하는게 아닌, 현재 배치가 완료된 x축들끼리만 검사를 해야한다
// for(x in 0 until queens.size){ // queens.size -> nx 로 대체하면 정답이 되네
private fun avliableQueen(nx : Int, ny : Int) : Boolean{
    for(x in 0 until nx){ // queens.size -> nx 로 대체하면 정답이 되네
        if(queens[x] == ny){
            return false
        }
        if(Math.abs(x-nx) == Math.abs(queens[x]-ny)){ // 대각선
            return false
        }
    }
    return true
}

//work
private fun avliableQueen2(depth : Int) : Boolean{
    for(x in 0 until depth){
        if(queens[x] == queens[depth]){
            return false
        }
        if(Math.abs(queens[x] - queens[depth]) == Math.abs(x-depth)){
            return false
        }
    }
    return true
}