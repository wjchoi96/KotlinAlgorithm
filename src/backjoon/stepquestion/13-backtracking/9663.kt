//gold5
//14-5

//https://st-lab.tistory.com/118 참고
//https://yeongjin13.tistory.com/62 참고
/*
    point 객체 + for문 2개로 풀어보려 했으나 중복제어를 못해서(오름차순 정렬로 해결되는 중복이였다)
    검색해서 방법 탐색

    1차원배열 + for문 1개로 해결하는 방법 알아내서 시도 후 성공(idx 를 열로 취급)(dfs2)
    
    정답알고 난 후에
    point 객체 + for문 2개로 시도하는 방법도 정답이 나오게끔은 처리 완료(oldDfs)
    -> 메모리초과, 시간초과
    point 객체 + for문 1개로 시도하는 방법으로 개량(dfs)
    -> 메모리초과, 시간은 비슷
    -> 연산 최대한 줄였는데도 메모리초과뜬다, 객체 사용 방식은 좀 위험한가?
    -> 2차원배열로 풀어봐야겠는데

    1차원배열 + for문 1개(idx를 x좌표로 취급, 위에랑 행,열 바꾼방식) 학습목적으로 시도 후 성공(dfs1)
*/
import java.io.*
fun main(args : Array<String>){
    Solution9663().solve()
}

class Solution9663 {
    private var count = 0
    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        val size = br.readLine().toInt()

        // val arr : Array<Int> = Array(size){-1}
        val arr1 : Array<Point> = Array(size) { Point(-1,- 1) }

        dfs(size, 0, arr1)
        bw.write("$count\n")

        bw.flush()
        bw.close()
        br.close()
    }

    // 2차원 배열로 어케하지?
// 0으로 초기화하고 말을 놓으면 1 놓지 않으면 0으로 구분 => 생각한 방법이 맞기는한데..
    private fun dfs5(n : Int, depth : Int, arr : Array<Array<Int>>){

    }

    // dfs1 만들어보자
/*
    depth 가 x
*/
    private fun dfs1(n : Int, depth : Int, arr : Array<Int>){
        if(n == depth){
            count++
            return
        }
        for(i in 0 until n){
            arr[depth] = i // (depth, i)
            //공격범위가 아니라면
            if(!checkQueenAtack(depth, arr)){
                dfs1(n, depth+1, arr) // 다음 x칸으로 넘어간다
            }
        }
    }
    //퀸의 공격범위라면 true
    private fun checkQueenAtack(depth : Int, arr : Array<Int>) : Boolean{
        for(i in 0 until depth){
            // 같은 y열
            if(arr[i] == arr[depth]){
                return true
            }
            //대각선
            else if(Math.abs(arr[i]-arr[depth]) == Math.abs(i-depth)){
                return true
            }
        }
        return false
    }

    // point 써서 만들어보자
// => point 쓰는건 메모리 초과네
// => 시간도 초과된다
// => for문 하나만 돌도록 개량했는데도 메모리초과
// => 시간은 답이랑 비슷한듯
/*
    이전에 중복된값이 나오는게 문제
    1234 1243 1324 1342 처럼

    해당 중복을 잡으려면 내림차순, 오름차순으로 정렬된 값만 뽑아내자
    -> 같은 x,y값이 안나오니까 x값이든 y값이든 하나를 기준으로 정렬하자
*/
    private fun dfs(n : Int, depth : Int, arr : Array<Point>){
        if(depth == n){
            count++
            print("dfs finish($count) : { ")
            for(value in arr){
                print("(${value.x}, ${value.y}) ")
            }
            print("} \n")
            return
        }

        // depth = x값
        // i = y값
        for(i in 0 until n){
            arr[depth] = Point(depth, i)
            if(!checkPointQueenAtack(depth, arr)){
                dfs(n, depth+1, arr)
            }
        }
    }
    //퀸의 공격범위라면 true
    private fun checkPointQueenAtack(depth : Int, arr : Array<Point>) : Boolean{
        for(i in 0 until depth){
            // 같은 y열
            if(arr[i].y == arr[depth].y){
                return true
            }
            //대각선
            else if(checkDiagonal(arr[i], arr[depth])){
                return true
            }
        }
        return false
    }

    // 대각선이면 true
    private fun checkDiagonal(item : Point, old : Point) : Boolean{
        return Math.abs(item.x-old.x) == Math.abs(item.y-old.y)
    }

    private class Point internal constructor (
        val x : Int,
        val y : Int
    ){}

    private fun oldDfs(n : Int, depth : Int, arr : Array<Point>){
        for(x in 0 until n){
            var xBlock = false
            for(y in 0 until n){
                var yBlock = false
                if(depth == 0){
                    arr[depth] = Point(x, y)
                    dfs(n, depth+1, arr)
                }else{
                    for(i in 0 until depth){
                        // 이전에 같은 x 값이 있다면 x를 다음으로 넘어간다
                        if(arr[i].x == x){
                            xBlock = true
                            break
                        }
                        // x 값 기준으로 오름차순 정렬
                        else if(arr[i].x > x){
                            xBlock = true
                            break
                        }
                        // 같은 y값이 있다면 y를 다음으로 넘어간다
                        else if(arr[i].y == y){
                            yBlock = true
                            continue
                        }
                        // 대각선인 값이 있다면 다음 loop 체크
                        else if(checkDiagonal(Point(x, y), arr[i])){
                            yBlock = true
                            continue
                        }
                    }
                    // 이전에 같은 x 값이 있다면 x를 다음으로 넘어간다
                    if(xBlock){
                        break
                    }
                    if(!yBlock){
                        arr[depth] = Point(x, y)
                        dfs(n, depth+1, arr)
                    }
                }
            }
        }
    }







    private fun dfs2(n : Int, depth : Int, arr : Array<Int>){
        if(depth == n){
            count++
            print("dfs2 finish($count) : { ")
            for(i in 0 until depth){
                print("(${arr[i]}, ${i}) ")
            }
            print("}\n")
            return
        }
        // depth 가 열
        // 행 loop
        for(i in 0 until n){
            arr[depth] = i // depth 열의 i행
            //해당 열에 퀸을 놓을수 있는지 판단
            if(checkQueenAttack2(arr, depth)){
                //공격받는다면
                continue // 다음 행으로 이동해서 체크
            }else{
                //안전하다면 다음 열로 이동
                // print("save node : (${arr[depth]}, $depth)\n")
                dfs2(n, depth+1, arr)
            }
        }
    }

    // 해당 depth 에 퀸을 놓았을때 공격받는지 아닌지 체크
// true -> 공격받는다
    private fun checkQueenAttack2(arr : Array<Int>, depth : Int) : Boolean{
        for(i in 0 until depth){ // 현재 depth 는 loop 에서 제외
            //arr[i] => (arr[i], i)

            // 같은 행이라면
            if(arr[i] == arr[depth]){
                // print("block node1 : (${arr[depth]}, $depth)\n")
                return true
            }
            // 대각선이라면
            else if(getAbsolueValue((arr[i]-arr[depth])) == getAbsolueValue((i-depth))){
                // print("block node2 : (${arr[depth]}, $depth)\n")
                return true
            }
        }
        return false
    }
    private fun getAbsolueValue(value : Int) : Int {
        return if(value >= 0){
            value
        }else{
            -value
        }
    }
/*
    대각선에 있는경우 => 열의 차와 행의 차가 같다면 대각선
    (0,0) (1,1) (2,2)
    (1,2) (2,3)
*/

/*
    도저히 못구하겠어서 방법이 맞나 검색
    2차원 배열로 하면 개어렵다고함
    참고 : https://st-lab.tistory.com/118

    1차원 배열로해서 idx값이 열, 배열값이 행 취급
    => 같은 열 또는 행에 값이 들어갈일이 없기때문 ( 퀸의 공격범위 )

    1. 같은 열의 값이 들어갈수없다
    => idx값을 열 취급함으로 해결
    2. 같은 행의 값이 들어갈수 없다
    => arr을 순회하여 현재 값과 같은 행의 값이 있는지 체크
    3. 대각선의 값이 들어갈수 없다
    => 대각선에 있는경우 => 열의 차와 행의 차가 같다면 대각선
*/

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
}
