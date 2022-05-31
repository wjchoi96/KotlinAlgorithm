/*
    바킹독님 알고리즘 강의 중 그리디 단원 잘못된 그리디 예제
    dp문제
    gold5

    준서가 여행에 필요하다고 생각하는 N개의 물건이 있다
    각 물건은 무게 W와 가치V를 가지는데, 해당 물건을 베낭에 넣어서 가면 준서가 V만큼 즐길 수 있다
    준서는 최대 K만큼의 무게만을 넣을 수 있는 베낭만 들고 갈 수 있다
    준서가 베낭에 넣을 수 있는 물건들의 가치의 최댓값을 구하라

*/
/*
    그리디 반례
    명제
    1. 가벼운순으로 정렬 후 최대한 많이 고르면 최대 가치를 구할 수 있다
    => 절대 안된다

    2. 가치가 높은순으로 정렬 후 최대한 많이 고르면 최대 가치를 구할 수 있다
    => 이것도 안된다
    => 최대무게에 최대가치이지만, 그 다음 2개를 합치면 더 큰 가치가 될 수 있다

*/
/*
    dp

    1. 테이블 정의하기
    - 헤메다가 이전에 푼 정답코드 일부를 확인해보니 2차원 dp였음을 확인
    dp[현재무게][현재물건Idx]
    : 현재 담은 무게 일때 현재물건 idx까지 고려했을때(담을 수 있다면 담고, 아니라면 안담는) 가치의 최대값

    dp[w][0]: 최대w까지 담을 수 있는 가방에 하나도 담지 않았을때
    dp[w][1]: 최대w까지 담을 수 있는 가방에 1번 물건만을 고려했을때 최대값
    dp[w][2]: 최대w까지 담을 수 있는 가방에 1,2번 물건들 만을 고려했을때 최대값
    dp[w][3]: 최대w까지 담을 수 있는 가방에 1,2,3번 물건들 만을 고려했을때 최대값
    dp[w][4]: 최대w까지 담을 수 있는 가방에 1,2,3번 물건들 만을 고려했을때 최대값

    2. 점화식
    wArr[i]가 w보다 작거나 같아서 담을 수 있다면
    1. 담는다 -> 이전 idx dp값에 + vArr[wArr[i]]
    2. w를 쪼갠다 -> dp[w-wArr[i]] + vArr[wArr[i]]

    wArr[i]가 w보다 커서 담을 수 없다면 -> 이전 idx값의 dp를 가져온다
    => 이전에 담을 수 있었다면 그 값을 따르기 위함

    3. 초기값
    dp[0..k][0]= 0
    모든 최대무게에 대응해서, 하나도 담지 않았다면 0이다
    => 0은 하나도 담지 않은것이니 1 indexed 로 카운팅해야한다
*/
/*
    제출
    1. 틀렸습니다(1%)
    - 베낭에 아무것도 담지 못하는 경우 추가

    2. 틀렸습니다(2%)
    - 다 담아도 최대 무게를 채우지 못하는 경우
    - 일정 이하의 무게는 모두 같다? 뭐라 말해야하나 여튼 그걸 고려 못했는데
    - 잘못 접근중인거같아서 이전 풀이좀 확인했다가, 이해가 안가서 풀이 검색해서 공부중

    3. 틀렸습니다(8%)
    - 답을 보고 푸는식이였는데 틀림
    - v배열의 size를 k+1 로 해놓고 무게를 idx로 접근했던것을 그냥 idx로 변경

    4. 성공
    - 다시풀어도 못풀것같다 뭔가
    - bottom-top이라도 구현해보자

    bottom-top
    1. 성공
    - 근데 아리송하다
*/

import java.util.HashMap
fun main(args: Array<String>){
    Solution12865().solve()
}
class Solution12865 {
    private lateinit var vArr: Array<Int>
    private lateinit var wArr: Array<Int>
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, k) = br.readLine().split(' ').map{it.toInt()}
        wArr = Array(n+1){0}
        vArr = Array(n+1){0}
        dp = Array(k+1){Array(n+1){-1}}
        for(i in 0..k){
            dp[i][0] = 0
        }

        repeat(n){
            val (w, v) = br.readLine().split(' ').map{it.toInt()}
            if(w <= k){
                wArr[it+1] = w
                vArr[it+1] = v
            }
        }
        getBagValueBottomTop(k, n)
        var max = 0
        for(i in 0..n){
            max = Math.max(max, dp[k][i])
        }
        bw.write("${max}\n")
                
        bw.flush()
        bw.close()
        br.close()
    }

    /*
        최대무게를 늘려가면서 각 무게마다 idx를 탐색해야한다
    */
    private fun getBagValueBottomTop(k: Int, n: Int){
        for(w in 0..k){
            for(i in 1..n){
                if(wArr[i] > w){
                    dp[w][i] = dp[w][i-1]
                }else{
                    dp[w][i] = Math.max(
                        dp[w-wArr[i]][i-1] + vArr[i],
                        dp[w][i-1]
                    )
                }
            }
        }
    }

    /*
        dp[w][0]: 최대w까지 담을 수 있는 가방에 하나도 담지 않았을때
        dp[w][1]: 최대w까지 담을 수 있는 가방에 1번 물건을 고려했을때 최대값
        dp[w][2]: 최대w까지 담을 수 있는 가방에 1,2번 물건을 고려했을때 최대값
        dp[w][3]: 최대w까지 담을 수 있는 가방에 1,2,3번 물건을 고려했을때 최대값
        dp[w][4]: 최대w까지 담을 수 있는 가방에 1,2,3번 물건을 고려했을때 최대값

        wArr[i]가 w보다 작거나 같아서 담을 수 있다면
        1. 담는다 -> 이전 idx dp값에 + vArr[wArr[i]]
        2. w를 쪼갠다 -> dp[w-wArr[i]] + vArr[wArr[i]]

        wArr[i]가 w보다 커서 담을 수 없다면 -> 이전 idx값의 dp를 가져온다
        => 이전에 담을 수 있었다면 그 값을 따르기 위함
    */
    private fun getBagValue(w: Int, i: Int): Int{
        if(dp[w][i] != -1) return dp[w][i]

        //담을 수 없다면
        if(w < wArr[i]){
            dp[w][i] = getBagValue(w, i-1)
        }else{
            dp[w][i] = Math.max(
                getBagValue(w, i-1), // 이전 idx를 고려했을때의 값과
                getBagValue(w-wArr[i], i-1) + vArr[i]  // 무게를 쪼개 담는것
            )
        }
        return dp[w][i]
    }
}