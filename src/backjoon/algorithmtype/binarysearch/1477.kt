/*
    바킹독님 알고리즘 강의 중 그리디 단원 잘못된 그리디 유형 -> parametric search 유형의 문제라고 소개 된 문제
    바킹독님 알고리즘 강의 중 이분탐색 단원 parametric search 부분 학습 중 풀이 
    gold 4
    https://www.acmicpc.net/problem/1477

    휴게소 세우기

    유로 고속도로에 휴게소를 n개 가지고 있다
    휴게소의 위치는 고속도로의 시작으로부터 얼만큼 떨어져 있는지로 주어진다
    휴게소를 m개 더 세우려고 한다

    휴게소는 
    이미 휴게소가 존재하는 곳에 세울 수 없고
    고속도로 끝에 세울 수 없다
    정수 위치에만 세울 수 있다

    고속도로를 이용할때 모든 휴게소를 방문하는 다솜이는 휴게소를 M개 더 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 하려고 한다
    (반드시 M개를 모두 다 지어야 한다)

    예)
    고속도로 길이 1000
    휴게소: {200, 701, 800} 일때 1개 더 세우려고 한다
    현재 휴게소가 없는 구간의 최댓값은 200-701 구간인 500
    하지만 새로운 휴게소를 451 구간에 짓게 되면, 최대가 251이 되어서 최소가 된다
 
    2초
    0<= N <=50 (현재 휴게소 개수)
    1<= M <= 100 (더 지으려는 휴게소)
    100<= L <= 1000 (고속도로 길이)
*/
/*
    최적화 문제: 휴게소를 M개 더 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 한다
    결정 문제: 
    현재 위치에 휴게소를 지을때 휴게소가 없는 구간의 길이의 최소값이 되는가? * M번
    => 그리디로 접근이 되는거 같은데 이러면
    -> 현재 가장 긴 구간에 휴게소를 짓는다 랑 같게 되는것
    => 틀림

    === 내생각 ====
    휴게소가 없는 구간의 길이의 배열을 만든다
    {200, 501, 99} 가 될것
    를 M을 쪼개서 나눌 수 있다

    예) M이 2라면 쪼개서 1,1로 나누거나 온전히 2로 사용 가능
    200/2(1+1) + 501/2(1+1) + 99
    200 + 501/3(2+1) + 99 
    중 최소값이 되는 게 답이 될것


    === 풀이 검색 ===
    휴게소의 위치배열에 0, L(고속도로 길이)를 추가해 sort 후 이분탐색을 진행
    탐색하는 값은 휴게소의 간격
    탐색한 간격으로 휴게소를 맞춰 설치할때, 몇개를 설치 가능한지 체크
    M개보다 적게 설치가 가능하다면 간격을 좁히고,
    더 많이 설치 가능하다면 간격을 넓힌다

    이분탐색 => O(lgL)
    간격 가능 여부 확인 => O(N)

    O(NlgL) 
*/
/*
    제출
    1. 틀렸습니다(1%) 
    - 예제는 모두 맞은 상태

    2. 틀렸습니다(6%)
    https://moonsbeen.tistory.com/273
    https://coder-in-war.tistory.com/m/entry/BOJ-JAVA1477-%ED%9C%B4%EA%B2%8C%EC%86%8C-%EC%84%B8%EC%9A%B0%EA%B8%B0

    풀이 비교해서 이해해 보자
    1. start, end 종료조건 < 와 <=의 차이에 대한 설정
    2. interval 을 구할때 왜 -1을 해주는지
    3. start=mid+1 -> 왜 꼭 +1을 해줘야 하는지


    parametric search 란?
    https://www.crocus.co.kr/1000

    이분탐색
    https://blog.encrypted.gg/985

    mid 구할때 +1 해줘야 하는 경우에 대해 한번 더 읽어봐라



*/
import java.util.StringTokenizer
fun main(args: Array<String>){
    Solution1477().solve()
}
class Solution1477 {
    private var n = 0
    private var m = 0
    private var k = 0
    private lateinit var arr: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (size, add, road) = br.readLine().split(' ').map{it.toInt()}
        n = size; m = add; k = road
        arr = Array(n+2){0}
        arr[arr.size-1] = k
        val st = StringTokenizer(br.readLine())
        for(i in 1 until arr.size-1){
            arr[i] = st.nextToken().toInt()
        }
        arr.sort()
        val res = findRoadInterval()
        bw.write("$res\n")
        bw.flush()
        bw.close()
        br.close()
    }
    //O(lg k)
    private fun findRoadInterval(): Int{
        var start = 0
        var end = k
        while(start<=end){
            val mid = (start+end)/2
            print("start[$start], mid[$mid], end[$end] ")
            val canBuild = canBuildRestArea(mid)
            when{ 
                canBuild>0 -> start=mid+1 // 더 많이 지은 경우 -> 간격을 넓혀서 더 적게 짓도록 한다 -> *+1을 해주는것과 안해주는것의 차이는?
                canBuild<=0 -> end=mid-1 // 더 적게 지은 경우, 알맞게 지은 경우 -> 간격을 좁혀서 더 지을 수 있게 해준다, 간격을 좁혀서 최소값을 유도해본다
            }
        }
        return start
    }
    //O(n)
    private fun canBuildRestArea(interval: Int): Int{
        var count = 0
        for(i in 0 until arr.size-1){
            val restInterval = arr[i+1]-arr[i]-1 // 휴게소 간 간격 -> -1을 하는 이유?
            count += restInterval/interval // 휴게소 간 간격에 설정한 간격이 최대가 되도록 휴게소를 몇개 설치할 수 있나
        }
        println("interval[$interval] can build rest area $count")
        return count-m 
    }

}