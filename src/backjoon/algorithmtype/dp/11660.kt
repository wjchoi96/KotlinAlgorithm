/*
    네이버 카페 문제 50선 중 구간합 구하기 연습문제
    sliver 1

    구간 합 구하기 5

    N*N의 board
    (x1, y1) 부터 (x2, y2)까지의 합을 구하시오 x는행, y는 열

    1 2 3 4
    2 3 4 5 
    3 4 5 6
    4 5 6 7

    (2,2) 부터 (3,4)까지의 합을 구하면
    3+4+5+4+5+6 = 27
    (4,4) 부터 (4,4)까지는 7이다

    1초

    1<= N <= 1024

    dp[x][y] 는 (0,0) 부터 (x,y)까지의 구간합

*/
/*
    누적합까지 구해놨다만 0 indexed 방식이다
    => 그런데 이렇게 누적합을 구하면 안된다
    => 문제조건에 맞게 dp를 쌓아야 한다

    (2,2) ~ (3,4)
    dp[3][4] - dp[2][1] -board[3][1]

    (2,1) ~ (3,4)
    dp[3][4] - dp[1][4]

    (1,3) ~ (3,2)

    x1, x2 중 큰값
    y1, y2 중 큰값
    dp[maxX][maxY] - dp[minX][minY] + board[minX][minY]

    dp[3][3] - dp[1][2] + board[1][2]
*/
/*
    1. 테이블 정의하기
    dp[x][y]: board[1][1] ~ board[x][y]까지의 구간합

    2. 점화식 구하기
    dp[x][y] 

    0. x==1 && y==1
    기본값 설정되어있으니 continue

    1. x == 1 일때
    dp[1][y] = dp[1][y-1] + board[1][y]

    2. y == 1 일때
    dp[x][1] = dp[x-1][1] + board[x][1]

    3. x != 1 && y != 1 일떄
    dp[x][y] = dp[x-1][y] + dp[x][y-1] - dp[x-1][y-1]
    => 중복된 구간을 뺴주는것
    
*/
/*
    제출
    1. 출력초과(3%)
    - 디버깅 로그를 제외 안했다

    2. 성공
    - https://subbak2.tistory.com/65 를 참고하여 풀었다
    - dp를 1-indexed로 변경하면 코드 개선이 될것 같다

    3. 성공
    - dp를 1-indexed 로 변경 
    - 0일때를 고려하는 코드가 싹 사라져서 훨씬 개선되었다
    - bottom top 방식으로 data input 받으며 진행되도록 개선해보자

    4. 성공
    - data 를 input 받으며 bottom-top 방식으로 메모이제이션 진행
 
*/

import java.util.StringTokenizer // use at Solution11660C
fun main(args: Array<String>){
    Solution11660C().solve()
}

// data 를 input 받으며 bottom-top 방식으로 메모이제이션 진행 -> 4번 제출
class Solution11660C {
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        // input data
        val (n,m) = br.readLine().split(' ').map{it.toInt()}
        dp = Array(n+1){Array(n+1){-1}}
        for(i in 0..n){
            dp[i][0] = 0
            dp[0][i] = 0
        }

        var st: StringTokenizer
        for(x in 1..n){
            st = StringTokenizer(br.readLine())
            for(y in 1..n){
                dp[x][y] = dp[x-1][y] + dp[x][y-1] - dp[x-1][y-1] + st.nextToken().toInt()
            }
        }

        repeat(m){
            val (x1, y1, x2, y2) = br.readLine().split(' ').map{it.toInt()}
            bw.write("${getBoardSum(x1, y1, x2, y2)}\n")
        }
        
        bw.flush()
        br.close()
        bw.close()
    }
    private fun getBoardSum(xa: Int, ya: Int, xb: Int, yb: Int): Int{
        val x1 = Math.max(xa, xb)
        val x2 = Math.min(xa, xb)
        val y1 = Math.max(ya, yb)
        val y2 = Math.min(ya, yb)
        return dp[x1][y1] - dp[x1][y2-1] - dp[x2-1][y1] + dp[x2-1][y2-1]
    }
}

// dp를 1-indexed로 변경하여 개선 된 코드 -> 3번 제출
class Solution11660B {
    private lateinit var board: Array<Array<Int>>
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        // input data
        val (n,m) = br.readLine().split(' ').map{it.toInt()}
        board = Array(n){Array(n){0}}
        dp = Array(n+1){Array(n+1){-1}}
        for(i in 0..n){
            dp[i][0] = 0
            dp[0][i] = 0
        }
        repeat(n){
            board[it] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        }
        getBoardDp(n, n)
        repeat(m){
            val (x1, y1, x2, y2) = br.readLine().split(' ').map{it.toInt()}
            bw.write("${getBoardSum(x1, y1, x2, y2)}\n")
        }
        
        bw.flush()
        br.close()
        bw.close()
    }

    //top-bottom
    private fun getBoardDp(x: Int, y: Int): Int{
        if(dp[x][y] != -1) return dp[x][y]
        dp[x][y] = getBoardDp(x-1, y) + getBoardDp(x, y-1) - getBoardDp(x-1, y-1) + board[x-1][y-1]
        return dp[x][y]
    }
    private fun getBoardSum(xa: Int, ya: Int, xb: Int, yb: Int): Int{
        val x1 = Math.max(xa, xb)
        val x2 = Math.min(xa, xb)
        val y1 = Math.max(ya, yb)
        val y2 = Math.min(ya, yb)
        return dp[x1][y1] - dp[x1][y2-1] - dp[x2-1][y1] + dp[x2-1][y2-1]
    }
    private fun Array<Array<Int>>.print() {
        for(x in 0 until this.size){
            for(y in 0 until this.size){
                print("${this[x][y]} ")
            }
            print("\n")
        }
    }

}

// 0-indexed 방식으로 해결
// 개선 이전 코드 -> 2번 제출
class Solution11660 {
    private lateinit var board: Array<Array<Int>>
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        // input data
        val (n,m) = br.readLine().split(' ').map{it.toInt()}
        board = Array(n){Array(n){0}}
        dp = Array(n){Array(n){-1}}

        repeat(n){
            board[it] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        }
        dp[0][0] = board[0][0]
        getBoardDp(n-1, n-1)
        dp.print()

        repeat(m){
            val (x1, y1, x2, y2) = br.readLine().split(' ').map{it.toInt()}
            bw.write("${getBoardSum(x1-1, y1-1, x2-1, y2-1)}\n")
        }

        
        bw.flush()
        br.close()
        bw.close()
    }

    //top-bottom
    private fun getBoardDp(x: Int, y: Int): Int{
        if(dp[x][y] != -1) return dp[x][y]
        dp[x][y] = when {
            x==0 && y==0 -> dp[x][y] // 초기값
            x==0 -> getBoardDp(x, y-1) + board[x][y]
            y==0 -> getBoardDp(x-1, y) + board[x][y]
            else -> getBoardDp(x-1, y) + getBoardDp(x, y-1) - getBoardDp(x-1, y-1) + board[x][y]
        }
        return dp[x][y]
    }
    // -1 해서 전달해줘야한다
    private fun getBoardSum(xa: Int, ya: Int, xb: Int, yb: Int): Int{
        val x1 = Math.max(xa, xb)
        val x2 = Math.min(xa, xb)
        val y1 = Math.max(ya, yb)
        val y2 = Math.min(ya, yb)

        var res: Int
        res = when {
            x2==0 && y2==0 -> dp[x1][y1]
            x2!=0 && y2!=0 -> dp[x1][y1] - dp[x1][y2-1] - dp[x2-1][y1] + dp[x2-1][y2-1]
            else -> {
                if(x2==0){
                    dp[x1][y1] - dp[x1][y2-1]
                }else if(y2==0){
                    dp[x1][y1] - dp[x2-1][y1] 
                }else{
                    dp[x1][y1] - dp[x1][y2-1] - dp[x2-1][y1] + dp[x2-1][y2-1]
                }
            }
        }
        return res
    }

    private fun Array<Array<Int>>.print() {
        for(x in 0 until this.size){
            for(y in 0 until this.size){
                print("${this[x][y]} ")
            }
            print("\n")
        }
    }

}
/*
    (0,0) (0,1) (0,2) (0,3)
    (1,0) (1,1)
*/