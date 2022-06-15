/*
    https://www.crocus.co.kr/1000 에서 제시한 Parametric Search 연습 문제
    gold 5

    공유기 설치

    도현이의 집 N개가 수직선 위에 있다
    각 집의 좌표는 x1, x2...xn 이고, 집이 동일한 좌표를 가지는 일은 없다

    집에 공유기 c개를 설치하려고 한다
    최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를
    하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 설치하려고 한다
    
    C개의 공유기를 N개의 집에 적당히 설치하여
    가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램 작성

    2초
    2<= N <= 200000
    2<= C <= N
    0<= xi <= 1,000,000,000 => start+end 해도 int범위 내
*/
/*
    x좌표의 범위를 이분탐색하며 간격을 선택 => O(lg 1000000000)
    해당 간격에 맞게 공유기 c개를 모두 설치 가능한지 판별 => O(200000)
    => O(2000000 lg 1000000000) 될거같은데
*/
/*
    제출
    1. 틀렸습니다(16%)
    - 더 많이 설치할 수 있는 경우도 정답인 경우인데, 처리가 누락되었다

    2. 성공
*/

fun main(args: Array<String>){
    Solution2110().solve()
}
class Solution2110 {
    private var n = 0
    private var c = 0
    private var max = 1000000000 // or max.x1 
    private lateinit var arr: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        br.readLine().split(' ').map{it.toInt()}.apply{
            n=this[0]; c=this[1]
        }
        arr = Array(n){0}
        repeat(n){
            arr[it] = br.readLine().toInt()
        }
        arr.sort()

        val res = getWifiInterval()
        bw.write("$res\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun getWifiInterval(): Int{
        var start = 0
        var end = max
        var res = start
        while(start<=end){
            val mid = (start+end)/2
            print("start[$start], mid[$mid], end[$end] ")
            val build = canBuildWifi(mid)
            when {
                build>0 -> {
                    start=mid+1
                    res = mid // 많이 설치할 수 있는경우는 c개만큼 만 설치하면 되니까 이 경우도 알맞은 경우
                 } // 더 많이 설치된 경우, 범위를 늘려서 더 적게 설치하게 유도
                build==0 -> {
                    start=mid+1
                    res = mid
                } // 알맞게 설치한 경우, 범위를 늘려서 최대값을 갱신하도록 유도
                build<0 -> end=mid-1 // 부족하게 설치된 경우, 범위를 좁혀서 더 많이 설치할 수 있도록 유도
            }
        }
        return res
    }
    
    private fun canBuildWifi(interval: Int): Int{
        /* 
            가장 인접한 두 공유기 사이의 거리를 interval로 하여
            => 공유기 간의 최소 거리를 interval로 하여
            공유기를 설치할때 몇개의 공유기를 설치할 수 있나?

            1. 집 간의 간격이 interval보다 클때 -> 설치
            2. 집 간의 간격이 interval보다 작을때 
            -> pendingInterval에 +해주고, 다음 집으로 넘어간다

            # 첫번째 집 or 마지막 집 둘중 하나는 무조건 설치하고 시작?
            => interval 이 x0 과 xn 사이의 간격보다 좁다면
            => 이게 안되면 설치 개수는 0개
        */
        // 첫번째 집 설치
        var count = 1
        var pendingInterval = 0
        if(interval>arr[n-1]-arr[0]){
            count = -888
        }else{
            print("[ b[0] ")
            // 두번째 집 부터 이전집과의 간격을 비교하며(정확히는 이전에 설치된 집) interval보다 떨어졌다면 설치
            for(i in 1 until n){
                val homeInterval = arr[i]-arr[i-1]
                if(homeInterval+pendingInterval>=interval){
                    pendingInterval = 0
                    count++
                    print("b[$i] ")
                }else{
                    pendingInterval+=homeInterval
                    print("n[$i] ")
                }
            }
            print("] ")
        }
        println("interval[$interval] can build wifi $count")
        return count-c
    }
}
/*
    1 2 4 8 9 에 3개

    1 2 3 4 5 6 7 8 9
    h h   h       h h

4 3
1
4
7
10
=> 3
*/