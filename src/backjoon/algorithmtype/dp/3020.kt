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


fun main(args: Array<String>){
    Solution3020().solve()
}

class Solution3020 {
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
    
        bw.flush()
        br.close()
        bw.close()
    }

}
