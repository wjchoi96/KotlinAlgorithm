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
*/

fun main(args: Array<String>){
    Solution11660().solve()
}
class Solution11660 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        // input data
        val (n,m) = br.readLine().split(' ').map{it.toInt()}
        val board = Array(n){Array(n){0}}
        val dp = Array(n){Array(n){-1}}

        repeat(n){ x->
            // board[it] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
            br.readLine().split(' ').map{it.toInt()}.forEachIndexed { y, v ->
                board[x][y] = v
                print("[${x}][${y}] = $v\n")
                if(x!=0 || y!=0){
                    val prevDp: Int
                    prevDp = when {
                        y==0 -> dp[x-1][n-1]
                        else -> dp[x][y-1]
                    }
                    print("[${x}][${y}] at prev[$prevDp]\n")
                    dp[x][y] = prevDp + v
                }else{
                    dp[x][y] = board[x][y]
                }
                bw.write("${dp[x][y]} ")
            }
            bw.write("\n")
        }

        
        bw.flush()
        br.close()
        bw.close()
    }

}
/*
    (0,0) (0,1) (0,2) (0,3)
    (1,0) (1,1)
*/