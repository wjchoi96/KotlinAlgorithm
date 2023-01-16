/*
    백준 6236
    백준 이분탐색 유형
    https://www.acmicpc.net/problem/6236

    sliver 2

    N일 동안 자신이 사용할 금액을 미리 계산
    정확히 M번만 통장에서 돈을 뺴서 쓰기로 결정

    k원을 인출
    - 하루를 보낼 수 있다면 그대로 사용
    - 모자라게 되면 남은 금액은 통장에 집어넣고, 다시 k원을 인출
    - 돈이 남아도 횟수(M)을 채우기 위해 넣고 뺴기 가능
        - M번 이하로 인출

    ex) 현재 100원, 지출 400원, k는 500원
    1. 100원을 통장에 넣음
    2. k(=500)을 인출
    3. 500원으로 400원을 결제
    4. 남은 100원을 가지고 다음 지출로 이동

    문제 번역이 헷갈리게 되어있어 문제 이해부분만 블로그를 참고
    https://maivve.tistory.com/150
*/
/*
    1 <= N <= 100,000
    1<= M <= N
*/
/*
    - 모든 경우의 수를 계산할 수 있는 금액이 k원이 되어야함
        - k의 범위의 시작점은 지출금액 중 최대값
    - k의 범위의 최대값은?
        - 모든 지출금의 합
    - M번 이하 중 k의 최소값
        k일때 M번 이하인가를 체크
*/
/*
    제출
    1. 틀렸습니다(7%)
    - getWithdrawalCount의 money<0을 해야하는데, money<-1로 한 오타를 수정

    2. 성공
*/

fun main() {
    Solution6236().solve()
}

class Solution6236 {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        val spendings = Array(n){0}
        var max = 1
        var sum = 0
        repeat(n) {
            spendings[it] = br.readLine().toInt()
            max = Math.max(max, spendings[it])
            sum += spendings[it]
        }

        var start = max
        var end = sum
        while(start < end) {
            val mid = (start + end) / 2
            val withdrawlCount = getWithdrawalCount(mid, spendings)
            println("start[$start], mid[$mid], end[$end] => withdrawlCount[$withdrawlCount]")
            when {
                withdrawlCount <= m -> end = mid // 더 인출할 수 있으니, mid를 줄여봄
                else -> start = mid + 1 // 초과해서 인출하므로, mid를 늘림
            }
        }
        println("finish => start[$start], end[$end]")
        bw.write("$end\n")


        bw.flush()
        bw.close()
        br.close()
    }

    private fun getWithdrawalCount(withdrawlMoney: Int, spendings: Array<Int>): Int {
        var count = 1
        var money = withdrawlMoney
        for(spending in spendings) {
            money -= spending
            if(money < 0){
                money = withdrawlMoney - spending
                count++
            }
        }
        return count
    }
}