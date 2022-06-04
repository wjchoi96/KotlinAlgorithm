/*
    네이버 카페 문제 50선 중 구간합 유형
    gold 5

    개똥벌레
    https://www.acmicpc.net/problem/3020

    개똥벌레 한 마리가 장애물(석순과, 종유석)로 가득찬 동굴에 들어갔다
    동굴의 길이는 N미터이고, 높이는 H미터이다 (N은 짝수)
    첫번째 장애물은 항상 석순이고 그 다음에는 종유석과 석순이 번갈아가면서 등장한다

    개똥벌레는 장애물을 피하지 않는다
    자신이 지나갈 구간을 정한 다음, 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다

    동굴의 크기와 높이, 모든 장애물의 크기가 주어진다
    이때 개똥벌레가 파괴해야하는 장애물의 최솟값과, 최솟값의 구간이 총 몇 개 있는지 구하라

    1초
    N은 항상 짝수
    동굴의 크기와 높이
    2<= N <= 200,000
    2<= H <= 500,000
    N개 줄에는 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양수이다.
*/
/*
    board: 2차원 배열 => 동굴
    높이H => x
    길이N = > y

    dp[x][y] : 해당 칸에 오기까지 몇개의 장애물을 파괴했는가

    dp와 board를 통합해도 될것같다

    

    첫번쨰 입력 석순 => 1
    dp[n-1][0] = 1

    두번째 입력 종유석 => 5
    dp[0][1]..dp[4][1] = (dp[0][0]..dp[4][0]) + 1


    dp[x][y] = dp[x][y-1] + 1
    -1을 수행해야하니 1-indexed로 작업하면 편할 것 같다

    현재 장애물 순서: i
    장애물의 길이: k
    for(v in 1..k){
        if(석순이라면)
            dp[n+1-v][i] = dp[n+1-v][i-1] + 1
        else
            dp[v][i] = dp[v][i-1] + 1
    }
    for(x in 1..n){
        if(dp[x][i] == -1){
            dp[x][i] = dp[x][i-1]
        }
    }

    1-indexed를 진행할 거니 ArrayIndexOutOfBounds 방지 계산
    N: 6
    H: 7

    dp[7][6] 까지 가능

    석순이 1이라면 dp[n][i] 가 막혀야한다. 그에 일치하게 식 수정
    종유석이 1이라면 dp[1][i] 가 막혀야 한다.

*/

/*
    제출
    1. 메모리 초과(8%) => Solution3020A
    - 설계를 잘못한것일텐데
    - 2차원 배열이 아닌, 1차원 배열로 진행해보라 한다

    2. 메모리 초과(8%) = > Solution3020B
    - 1차원 배열로 변경하였음에도 메모리 초과 발생
    - 문제가 된것은 data를 입력받으며 메모이제이션을 진행하려고 했던 부분
    - 최대 (n(k-1))의 공간복잡도와 시간복잡도
    => 200000 * 500000 => 100000000000 => 절대 불가능
    => 무조건 1차원 배열 + O
    !! 누적합은 O(n)에 누적합을 구하려고 하는것이다

    3. 성공 => Solution3020C
    - https://machine-geon.tistory.com/155 참고하여 진행
    - 근데 솔직히.. 잘 이해 안간다

    4. 맞았습니다
    - https://lotuslee.tistory.com/108 참고하여 다른 방식 진행 
    - 내가 구역을 거꾸로 생각한것같다
    - 당연히 종유석의 시작위치가 0인줄 알았는데, 석순의 시작 위치가 0이였네

*/

/*
    top, bot => 종유석과 석순의 정보
    조

    i를 i-indexed로 작성 

    move 배열 = Array(h)
*/


fun main(args: Array<String>){
    Solution3020().solve()
}

class Solution3020 {
    private lateinit var top: Array<Int>  // 종유석
    private lateinit var bot: Array<Int> // 석순
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val (n, h) = br.readLine().split(' ').map{it.toInt()}
        top = Array(h+1){0}
        bot = Array(h+1){0}
        for(i in 1..n/2){
            top[h+1-br.readLine().toInt()]++ // 종유석
            bot[br.readLine().toInt()]++ // 석순
        }
        println("jong : ${top.toList()}")
        println("suc : ${bot.toList()}")

        val topDp = Array<Int>(h+2){0}
        val botDp = Array<Int>(h+2){0}
        for(i in 1..h){
            /*
                i == 1
                jongDp[7] = jongDp[8]+jong[7]
                i == 2
                jongDp[2] = jongDp[1]+jong[2]
            */
            topDp[h+1-i] = topDp[h+2-i]+top[h+1-i]
            /*
                suc을 계산할때는 i가 작을수록 높은값이다
                i == 1
                sucDp[1] = sucDp[0]+suc[1]
                i == 2
                sucDp[2] = sucDp[1]+suc[2]
            */
            botDp[i] = botDp[i-1]+bot[i]
            println("jongDp[${h+1-i}] : ${topDp[h+1-i]}, sucDp[${i}] : ${botDp[i]}")
        }

        var min = Int.MAX_VALUE
        var count = 0
        for(i in 1..h){
            var crash = topDp[1]-topDp[i+1]
            crash += botDp[h]-botDp[i-1]
            println("crahs[$i] : $crash")
            if(min == crash)
                count++
            else if(min > crash){
                count = 1
                min = crash
            }
        }
        bw.write("$min $count\n")

    
        bw.flush()
        br.close()
        bw.close()
    }
}

class Solution3020C {
    private lateinit var bot: Array<Int>
    private lateinit var top: Array<Int>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val (n, h) = br.readLine().split(' ').map{it.toInt()}
        bot = Array(h+1){0}
        top = Array(h+1){0}
        for(i in 1..n/2){
            bot[br.readLine().toInt()]++
            top[br.readLine().toInt()]++
        }
        // 이렇게 된다면
        // bot: [0, 1, 0, 1, 0, 1, 0, 0]
        // top: [0, 1, 0, 1, 0, 1, 0, 0] 이렇게 된다
        // 각 idx는 높이를 의미한다

        // top, bot 누적합 배열 생성
        // botDp[n] = 1~n 번 높이까지 모든 석순의 개수
        // topDp[n] = 1~n 번 높이까지 모든 종유석 개수
        val botDp = Array<Int>(h+1){0}
        val topDp = Array<Int>(h+1){0}
        for(i in 1..h){
            botDp[i] = botDp[i-1] + bot[i]
            topDp[i] = topDp[i-1] + top[i]
        }

        var min = Int.MAX_VALUE
        var count = 0
        for(i in 1..h){
            var crash = botDp[h]-botDp[i-1] // 석순
            crash += topDp[h]-topDp[h-i] // 종유석
            println("crahs[$i] : $crash")
            if(min == crash)
                count++
            else if(min > crash){
                count = 1
                min = crash
            }
        }
        bw.write("$min $count\n")
    
        bw.flush()
        br.close()
        bw.close()
    }
}

class Solution3020B {
    /*
        사실 이정도면 dp가 아니라 단순 누적합이긴하지만, 메모이제이션 느낌은 나니 dp로 명하여 사용하자
        1. 테이블 정의하기 
        dp[x]: 해당 x열로 진행하면 박살내는 장애물의 개수

        data를 입력받으며 해당x열에 장애물이 입력되면 dp[x]++

        2. 기본값
        dp[x] = 0
    */
    private lateinit var dp: Array<Int>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val (n, h) = br.readLine().split(' ').map{it.toInt()}
        dp = Array(h){0}

        repeat(n){ 
            val k = br.readLine().toInt()
            // 장애물 구간 => 이전 dp + 1을 해준다
            for(v in 0 until k){
                when(it%2){
                    0 -> dp[h-1-v]++
                    1 -> dp[v]++
                }
            }
        }

        var min = Int.MAX_VALUE
        var count = 0
        for(x in 0 until h){
            if(min == dp[x]) 
                count++
            else if(min > dp[x]){
                count = 1
                min = dp[x]
            }
        }
        bw.write("$min $count\n")
    
        bw.flush()
        br.close()
        bw.close()
    }
}

class Solution3020A {
    /*
    1. 테이블 정의하기
    dp[x][y] 는 [x][1] 에서 시작하여 해당 칸에 도착하기까지 파괴하는 장애물의 개수

    2. 점화식 구하기
    dp[x][y] = dp[x][y-1] + 1
    => -1 작업을 수행하니 1-indexed 방식으로 작업하자
    현재 장애물 순서: i
    장애물의 길이: k
    for(v in 1..k){
        if(석순이라면)
            dp[n+1-v][i] = dp[n+1-v][i-1] + 1
        else
            dp[v][i] = dp[v][i-1] + 1
    }
    for(x in 1..n){
        if(dp[x][i] == -1){
            dp[x][i] = dp[x][i-1]
        }
    }

    3. 초기값 정하기
    dp[0][0] = 0
    dp[0..x][0] = 0
    dp[0][0..y] = 0

*/
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val (n, h) = br.readLine().split(' ').map{it.toInt()}
        dp = Array(h+1){Array(n+1){-1}}
        for(x in 0..h){
            dp[x][0] = 0
        }
        for(y in 0..n){
            dp[0][y] = 0
        }
        repeat(n){ i -> 
            val it = i+1
            val k = br.readLine().toInt()
            // 장애물 구간 => 이전 dp + 1을 해준다
            for(v in 1..k){
                when(it%2){
                    0 -> dp[h+1-v][it] = dp[h+1-v][it-1] + 1 // 석순
                    1 -> dp[v][it] = dp[v][it-1] + 1 // 종유석
                }
            }
            // 장애물이 아닌 구간 => 이전 dp의 값을 가져온다
            for(v in k+1..h){
                when(it%2){
                    0 -> dp[h+1-v][it] = dp[h+1-v][it-1] // 석순
                    1 -> dp[v][it] = dp[v][it-1]// 종유석
                }
            }
        }

        // dp.print()

        var min = Int.MAX_VALUE
        var count = 0
        for(x in 1..h){
            if(min == dp[x][n]) 
                count++
            else if(min > dp[x][n]){
                count = 1
                min = dp[x][n]
            }
        }
        bw.write("$min $count\n")
    
        bw.flush()
        br.close()
        bw.close()
    }
    private fun Array<Array<Int>>.print() {
        for(x in 0 until this.size){
            for(y in 0 until this[x].size){
                print("${this[x][y]} ")
            }
            print("\n")
        }
    }

}

/*

4번 진행 도중 틀린방식
jong : [0, 1, 0, 1, 0, 1, 0, 0]
suc : [0, 0, 0, 1, 0, 1, 0, 1]
jongDp[7] : 0, sucDp[1] : 0
jongDp[6] : 0, sucDp[2] : 0
jongDp[5] : 1, sucDp[3] : 1
jongDp[4] : 1, sucDp[4] : 1
jongDp[3] : 2, sucDp[5] : 2
jongDp[2] : 2, sucDp[6] : 2
jongDp[1] : 3, sucDp[7] : 3
crahs[1] : 4
crahs[2] : 4
crahs[3] : 5
crahs[4] : 4
crahs[5] : 5
crahs[6] : 4
crahs[7] : 4
4 5


맞는 방식
jong : [0, 0, 0, 1, 0, 1, 0, 1]
suc : [0, 1, 0, 1, 0, 1, 0, 0]
jongDp[7] : 1, sucDp[1] : 1
jongDp[6] : 1, sucDp[2] : 1
jongDp[5] : 2, sucDp[3] : 2
jongDp[4] : 2, sucDp[4] : 2
jongDp[3] : 3, sucDp[5] : 3
jongDp[2] : 3, sucDp[6] : 3
jongDp[1] : 3, sucDp[7] : 3
crahs[1] : 3
crahs[2] : 2
crahs[3] : 3
crahs[4] : 2
crahs[5] : 3
crahs[6] : 2
crahs[7] : 3
2 3
*/