//gold5
//14-5

import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val arr : Array<Point> = Array(size){Point(0,0)}
    var visit : Array<Array<Boolean>> = Array(size){Array(size){false}}

    dfs(size, 0, arr, visit, bw)
    bw.write("$count\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private var count = 0
private fun dfs(n : Int, depth : Int, arr : Array<Point>, visit : Array<Array<Boolean>>, bw : BufferedWriter){
    if(depth == n){ // 배열 하나가 완성된것
        count++
        return
    }
    for(x in 0 until n){
        for(y in 0 until n){
            if(visit[x][y] == true){
                continue
            }
            if(depth != 0){
                // 이전에 배치된 퀸들이 있다면
                for(i in 0 until depth){
                    // 현재 좌표가 안전한 좌표인지 체크
                    if(checkSafe(Point(x,y), arr[i], n) == false){
                        continue
                    }
                    print("debug2\n")
                }
            }
            print("debug1 => ($x, $y)\n")
            visit[x][y] = true
            arr[depth] = Point(x,y)
            dfs(n, depth+1, arr, visit, bw)
            visit[x][y] = false
        }
    }
}

private fun checkSafe(newItem : Point, oldItem : Point, size : Int) : Boolean {
    return checkSafeHorizontal(newItem, oldItem) &&
        checkSafeVertical(newItem, oldItem) &&
        checkDiagonal(newItem, oldItem, size)
}

private fun checkSafeHorizontal(newItem : Point, oldItem : Point) : Boolean{
    return newItem.x != oldItem.x
}
private fun checkSafeVertical(newItem : Point, oldItem : Point) : Boolean{
    return newItem.y != oldItem.y
}
private fun checkDiagonal(newItem : Point, oldItem : Point, size : Int) : Boolean{
    // 우상향
    // 1. (x+1, y+1), (x+2, y+2) ... x,y 가 n 을 넘지 않을때까지 반복
    // 좌하향
    // 2. (x-1, y-1), (x-2, y-2) ... x,y 가 0 을 넘지 않을때까지 반복
    // 우하향
    // 3. (x+1, y-1), (x+2, y-2) ... x가 n을, y가 0을 넘지 않을때까지 반복
    // 좌상향
    // 4. (x-1, y+1), (x-2, y+2) ... x가 0을, y가 N을 넘지 않을때까지 반복

    for(i in 1 until size + 1){
        // 1. 우상향
        if(
            newItem.x+i == oldItem.x &&
            newItem.y+i == oldItem.y
        ){
            return false
        }
        // 2. 좌하향
        else if(
            newItem.x-i == oldItem.x &&
            newItem.y-i == oldItem.y
        ){
           return false 
        }
        // 3. 우하향
        else if(
            newItem.x+i == oldItem.x &&
            newItem.y-i == oldItem.y
        ){
            return false
        }
        // 4. 좌상향
        else if(
            newItem.x-i == oldItem.x &&
            newItem.y+i == oldItem.y 
        ){
            return false
        }

        // 범위를 벗어났다면 다음 루프를 돌 필요가 없다
        if(
            newItem.x + i >= size &&
            newItem.x - i < 0 && 
            newItem.y + i >= size &&
            newItem.y - i < 0
        ){
            return true
        }
    }
    
    return true
}

class Point internal constructor(
    val x : Int,
    val y : Int
){

}

/*
    조금 더 복잡한 백트래킹 문제
    
    크기가 N * N 인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는다
    퀸을 놓을수 있는 경우의 수를 구하라

    (0,0), (0,1), (0,2) ...
    (1,0), (1,1) ...
    ...
    ... (n,n) 까지 순회

    1. 퀸 n개를 놓을 사이즈가 n 인 point 배열
    2. 퀸의 공격범위? 가 조건
     - 가로 + 세로 + 대각선
     - 가로 : x좌표가 같다
     - 세로 : y좌표가 같다
     - 대각선 : 
     // 우상향
     1. (x+1, y+1), (x+2, y+2) ... x,y 가 n 을 넘지 않을때까지 반복
     // 좌하향
     2. (x-1, y-1), (x-2, y-2) ... x,y 가 0 을 넘지 않을때까지 반복
     // 우하향
     3. (x+1, y-1), (x+2, y-2) ... x가 n을, y가 0을 넘지 않을때까지 반복
     // 좌상향
     4. (x-1, y+1), (x-2, y+2) ... x가 0을, y가 N을 넘지 않을때까지 반복

    1. (0,0) 부터 (n,n) 까지 탐색
    2. 퀸이 저장된 배열을 확인하면서 현재 좌표가 안전한지 체크
    3. 안전하다면 저장
    4. 다음 노드 출발
    5. 재귀 종료 조건 -> 퀸이 저장된 배열이 꽉찬다면 종료

    1. 가로 안전 함수
    2. 세로 안전 함수
    3. 대각 안전 함수
    -> 1개로 퉁치는게 효율좋을지, 3개 각각 만들어도 될지 체크해보자
*/