

import kotlin.collections.sort/*
    바킹독님 알고리즘 강의 중 그리디 단원 연습문제
    sliver 4

    로프

    N(1<= n <= 100000)개의 로프가 있다
    로프를 이용하여 이런 저런 물체를 들어올릴 수 있다
    각 로프는 그 굵기나 길이가 다르기 때문에 들 수 있는 물체의 중량이 서로 다를 수 있다

    여러개의 로프를 병렬로 연결하면 각 로프에 걸리는 중량을 나눌 수 있다
    k개의 로프를 사용하여 중량이 w인 물체를 들어올릴떄, 각 로프는 w/k만큼의 중량이 걸리게 된다

    로프들에 대한 정보가 주어졌을때, 들어올릴 수 있는 물체의 최대 중량을 구해내는 프로그램을 작성
    모든 로프를 꼭 사용해야 할 필요는 없다
    
    1 <= w <= 10000
*/
/*
    1. 가장 작은 로프의 중량 * 로프의 개수 => 거짓

    명제
    가장 작은 로프의 중량에 로프의 개수를 곱하면 최대 중량을 알 수 있다
    : 거짓이라 정의
    => 1 9 10 
    => 1*3 인 3을 들어올리는 것 보다, 9와 10 로프만을 이용해서 18을 들어올리는게 최대 중량이다
    - 거짓

    2. 로프를 작은 순으로 정렬 후, 리스트를 순회하며 (현재 로프 중량 * 자신을 포함한 남은 로프의 개수) 를 구한 뒤 최대값을 찾는다
    
    명제
    로프를 작은 순으로 정렬 후, (현재 로프 중량 * 자신을 포함한 남은 로프의 개수) 의 최대값을 구하면 최대 중량을 구할 수 있다
    : 거짓이라 정의
    => 없는것같은데

*/

/*
    제출
    1. 성공
*/

/*
    바킹독님 구현

    1. k개의 로프를 사용해서 최대 중량을 구한다
    => 가장 무거운 k개의 로프를 사용한다

    2. 로프의 최대중량을 정렬한 후 로프를 k개 고른다면 w[n-k]*k 해준다
    3. 그 후 그중 최대값을 구한다

    이방법이건, 내방법이건 o(n)은 동일
*/
fun main(args: Array<String>){
    Solution2217().solve()
}
class Solution2217 {
    private lateinit var coins: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val roaps = Array(n){0}
        repeat(n){ roaps[it] = br.readLine().toInt() }
        roaps.sort()

        var max = 0
        for(i in 0 until n){
            max = Math.max(max, roaps[i]*(n-i))
        }
        bw.write("$max\n")
        
        bw.flush()
        bw.close()
        br.close()
    }
}
