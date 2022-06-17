/*
    https://www.crocus.co.kr/1000 에서 제시한 Parametric Search 연습 문제
    gold2
    난이도가 좀 높아서 문제 유형을 확인했는데 
    dp, 그리디, 이분탐색 유형

    난이도가 높고 어려운 유형이니 부담 내려놓고 편하게 풀다가 정 모르겠으면 답을 보고 이해하는 쪽으로 해보자

    숫자구슬
    https://www.acmicpc.net/problem/2613

    N개의 숫자 구슬이 막대에 꿰어져 일자로 놓여 있다
    구슬을 막대에서 빼낼 수 없고, 바꿀 수 없다

    숫자 구슬을 M개의 그룹으로 나누었을때 각 그룹의 합 중 최댓값이 최소가 되도록 하려 한다
    예)
    {5, 4, 2, 6, 9, 3, 8, 7}
    세개의 그룹으로 나눈다
    1번)
    {5, 4, 2}, {6, 9}, {3, 8, 7}
    => 11, 15, 18 이 되어 최댓값이 18이 된다

    2번)
    {5, 4, 2, 6}, {9, 3}, {8, 7}
    => 17, 12, 15 가 되어 최댓값이 17이 된다
    => 1번의 최댓값 보다 작은 17을 찾을 수 있다

    그룹에 포함된 숫자 구슬의 개수는 0개 보다 커야 한다

    그룹의 합 중 최댓값이 최소가 되도록 M개의 그룹으로 나누었을 때,
    그 최댓값과 각 그룹을 구성하는 개수를 출력하시오

    1초
    1<= N <= 300
    1<= M <= N
    구슬의 숫자는 100 이하 자연수

*/
/*
    Parametric Search 로써의 결정문제
    그룹의 합이 될 수 있는 최소~최대 의 범위를 이분탐색하며, 해당 합이 나오도록 3개의 그룹 구성이 가능한지 체크
    구슬의 값 중 최소값 ~ 모든 구슬의 합(사실 3개의 그룹으로 써 나올 수 있는 최대값이여야 하지만, 까다로우니 합으로 대체)

    그룹의 합이 주어졌을때, 해당 합이 나오도록 그룹을 구성하는 코드는 dp로?
    투포인터는 안되나?
    투포인터로 해당 합을 도출해 내고, 도출해 냈을때 그룹이 몇개가 나오는지, 나온 그룹들 중 합이 현재 값이 최대인지?
    
*/
/*
    dp
    https://forstudy.tistory.com/entry/%EB%B0%B1%EC%A4%80-2613%EB%B2%88-%EC%88%AB%EC%9E%90%EA%B5%AC%EC%8A%AC-java
    => 얘는 나랑 생각이 다른 dp이긴 한데 일단 링크

    dp[startIdx][endIdx] => startIdx-endIdx 까지의 합
    => startIdx = 0~n-2
    => endIdx = 1~n-1 
    즉 n-1 * n-1 사이즈
*/
/*
    제출
    1. 틀렸습니다(6%)
    - 시간초과 될거 같았는데 일단 틀렸다
*/


fun main(args: Array<String>){
    Solution2613().solve()
}
class Solution2613 {
    private var n = 0
    private var m = 0
    private lateinit var arr: Array<Int>
    private var max = 0 // 최대 100
    private var sum = 0 // 최대 300*100
    private lateinit var dp: Array<Array<Int>>
    private var result: Triple<Int, Int, Int> = Triple(0, 0, 0)
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        br.readLine().split(' ').map{it.toInt()}.apply{
            n = this[0]
            m = this[1]
        }
        arr = Array(n){0}
        br.readLine().split(' ').map{it.toInt()}.forEachIndexed { i,v ->
            arr[i] = v
            max = Math.max(max, v)
            sum += v
        }
        dp = Array(n){Array(n){-1}}
        println("max[$max] sum[$sum]")

        initDp()
        getMaxGroupSum()
    
        bw.flush()
        bw.close()
        br.close()
    }
    //최대 O(lg 500*100) 
    private fun getMaxGroupSum(){
        var start = max
        var end = sum
        while(start<=end){
            val mid = (start+end)/2
            print("start[$start] mid[$mid] end[$end] ")
            val canGroup = canMakeGroupSum(mid)
            println("\ncanGroup => $canGroup")
            when(canGroup){
                true -> end=mid-1 // 가능하다면, mid를 낮춰 최소값을 구하도록 한다
                false -> start=mid+1 // 불가능하다면, mid를 높여 가능한 값을 찾는다
            }
        }
        println("$start\n${result.first} ${result.second} ${result.third}")
    }
    //m개의 그룹으로 나누면서, 그룹의 합 중 최댓값이 value가 나오도록 할 수 있는가
    //근데 이거 dp로 저장해 놓으면 편하겠는데?
    //dp[startIdx][endIdx] 로 저장해서?
    private fun canMakeGroupSum(value: Int): Boolean{
        var start = 0
        var end = start+1
        while(start<end && end<n){
            print("\nstartIdx[$start] endIdx[$end] => ${dp[start][end]}")
            when{
                dp[start][end]<value -> {
                    if(end==n-1) break
                    end++
                } // 합이 value보다 작다면 end 를 늘려 갚을 키운다
                dp[start][end]==value -> { 
                    //3개의 그룹을 만드려면 2개는 남기고 현재 그룹이 만들어 져야 한다
                    //유효성이 일치하지 않는다면, start를 올린다
                    if(end+1-start > n-2){ 
                        start++
                    }else { // 나머지 그룹의 합을 체크
                        if(start == 0){
                            // 나머지 그룹이 더 큰 경우를 탐색
                            for(i in end+1 until n){ 
                                //end+1~i, i+1~n-1
                                if(dp[start][end]>=dp[end+1][i] && dp[start][end]>=dp[i+1][n-1]){    
                                    setResult(start to end, end+1 to i, i+1 to n-1)
                                    return true
                                }
                            }
                            start++
                        }else if(end == n-1){
                            for(i in 0 until start){ 
                                //0~i, i+1~start-1
                                if(dp[start][end]>=dp[0][i] && dp[start][end]>=dp[i+1][start-1]){   
                                    setResult(0 to i, i+1 to start-1, start to end)
                                    return true
                                }
                            }
                            start++
                        }else{
                            if(dp[start][end]>=dp[0][start-1] && dp[start][end]>=dp[end+1][n-1]){    
                                setResult(0 to start-1, start to end, end+1 to n-1)
                                return true
                            }
                            start++
                        }
                    }
                } // 합이 value와 같다면, 유효성을 체크한다 -> 3개의 그룹이 가능한지, 나머지 구슬을 합쳐서 현재 value를 넘는지
                dp[start][end]>value -> {
                    start++
                } // 합이 value보다 작다면 start를 늘려 값을 줄인다
            }
        }
        return false
    }
    private fun setResult(first: Pair<Int, Int>, mid: Pair<Int, Int>, end: Pair<Int, Int>){
        print("\nres:: [${first.first}, ${first.second}](${dp[first.first][first.second]}),"+
        " [${mid.first}, ${mid.second}](${dp[mid.first][mid.second]})," + 
        " [${end.first}, ${end.second}](${dp[end.first][end.second]})")
        result = Triple(
            first.second+1-first.first,
            mid.second+1-mid.first,
            end.second+1-end.first,
        )
    }

    // O(2N) -> 이거 다 채워놓으려면 n^2 이네; 
    private fun initDp(){
        for(start in 0 until n){
            var group = arr[start]
            for(end in start until n){
                if(start != end){
                    group+=arr[end]
                }
                dp[start][end] = group
                println("startIdx[$start] endIdx[$end] => ${dp[start][end]}")
            }
        }
        // var start = 0
        // var end = start
        // var group = arr[start]
        // while(start<=end){
        //     dp[start][end] = group
        //     println("startIdx[$start] endIdx[$end] => ${dp[start][end]}")
        //     if(end!=n-1){
        //         group+=arr[++end]
        //     }else{
        //         group-=arr[start++]
        //     }
        // }
    }
}

/*
5 3
1 2 3 4 5

16
0 0 0 
이 나오네?

이 경우는 최대값을 구성을 못하는 경우라 mid를 낮춰줘야하는데..
*/