/*
    백준 16401
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/16401

    sliver 2

    조카들에게 과자를 나눠줌
    - 최대한 긴 과자를 나눠주려함
    - 모든 조카에게 같은 길이의 막대 과자를 나눠주며함

    M명의 조카
    N개의 과자
    막대과자는 길이와 상관없이 여러 조각으로 나눌 수 있지만, 합칠수는 없음

    조카 1명에게 줄 수 있는 막대 과자의 최대 길이를 구하라
    모든 조카에게 같은 길이의 막대과자를 나눠줄 수 없다면 0을 출력

    1 ≤ M ≤ 1,000,000
    1 ≤ N ≤ 1,000,000
    과자의 길이 (1 ≤ L1, L2, ..., LN ≤ 1,000,000,000)
*/
/*
    이전에 그 지출 문제랑 비슷한거 같은데

    - k길이의 막대과자들을 나눠주려할때 몇개를 줄 수 있는지
    - 이분탐색 범위의 최소값은 1, 최대값은 나눠준 막대과자들의 최대값
    - 동일한 경우도 존재하기에 Lower Bound
*/
/*
    제출
    1. 틀렸습니다(32%쯤?)
    - Lower이 맞는거 같은데 혹시나 해서 Upper bound로 변경해봄. 

    2. 틀렸습니다(1%)
    - Lower로 다시 바꿈

    3 3
    1 1 1
    답: 1
    - 위 테케를 적용시킴(while 조건식을 < 로 한다면 해당 TC의 경우 첫번째 Loop도 돌지 못하여 0을 출력하게 됨)
    - 때문에 while의 조건식을 <=로 변경하고, 그에 따라 start, end의 값 변경식도 수정

    3. 성공 


*/

fun main(){
    Solution16401().solve()
}

class Solution16401 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (m, _) = br.readLine().split(' ').map{ it.toInt() }
        val cookies = br.readLine().split(' ').map{ it.toInt() }.sorted().toTypedArray()
        
        var max = Int.MIN_VALUE
        cookies.forEach {
            max = Math.max(it, max)
        }

        var start = 1
        var end = max
        while(start <= end) {
            val mid = (start + end) / 2
            val count = splitCookie(mid, cookies)
            println("start[$start], mid[$mid], end[$end] => count[$count]")
            when {
                m <= count -> start = mid + 1 // 조카보다 쿠키가 많음. 쿠키의 길이를 늘림
                else -> end = mid - 1
            }
        }
        println("finish start[$start], end[$end]")

        bw.write("${start-1}\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun splitCookie(length: Int, cookies: Array<Int>): Int {
        var count = 0
        cookies.forEach {
            count += it/length
        }
        return count 
    }
}

/*
TC 

4 3
10 10 15
7

4 3
1 1 1
0



5 3
10 10 15
5

7 10
1 2 3 4 5 6 7 8 9 10


3 3
1 0 1



3 3
1 1 1
1
*/