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

    N+M < L
    => 반드시 M개의 휴게소를 지을 수 있다
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
    
    3. 틀렸습니다(6% 보단 더 올라갔다)
    - 런타임 에러(/by zero)
    - 0 90 100 같은 경우 확인
    - mid 값 구할때 +1 을 추가로 진행

    4. 틀렸습니다(16%)
    - 런타임 에러(/by zero)
    - 다른 블로그 정답코드와 차이가 없어보이는데.. 일단 start 초기값을 1로 주어서 진행해봄
    
    5. 정답
    - start 를 0으로 놓고 어떻게 /by zero 를 통과하는거지?



*/
/*
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
        arr[arr.size-1] = k // 시작은 0, 끝은 k
        // 간격을 계산하기 위한 배열이기 때문에 시작을 휴게소를 설치할 수 있는 시작점인 1이 아닌 0으로 둔다
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
        var start = 1 // 0으로 둘 경우 /by zero 런타임 에러가 발생. 
        var end = k
        //실제 탐색 범위와 start, end 값이 일치하기 때문에 mid값을 구할때 +1을 안해줘도 된다
        while(start<=end){
            val mid = (start+end)/2
            print("start[$start], mid[$mid], end[$end] ")
            val canBuild = canBuildRestArea(mid)
            when{
                canBuild>0 -> start=mid+1 // 더 많이 지었으니, 간격을 늘려 build개수를 줄이도록 한다
                canBuild==0 -> end=mid-1 // 알맞게 지었으나, 간격을 줄여 최소값을 구하도록 유도한다
                canBuild<0 -> end=mid-1 // 더 적게 지었으니, 간격을 줄여 build개수를 늘이도록 한다
            }
        }
        /*
            0 1 500 의 경우 start값이 연속으로 조정되다가 반복문이 종료된다
            이때 반복문이 끝나는 이유는 start값이 계속 증가하다가 end값을 넘어갔(같아졌)기 때문인데, 이때의
            start값은 canBuildRestArea 유효성 체크를 하지 않고 리턴을 하게 된다. 
            하지만 이는 유효한 값인데 이유는, end는 유효한 mid-1을 통해 변경된 값(이거나 초기값)이기 때문에
            유효한 값에 대한 정보를 알고있다.
            때문에 while문의 조건을 통해 start값의 유효성을 보장할 수 있다.

            이 경우 end=mid-1을 했기때문에 start값이 증가되며 반복문이 종료될때 유효했던 mid값을 가지려면
            start<=end 로 반복 조건을 채워야한다. (start값이 end+1이 되었을때 종료되어야 유효한 값이기 때문)
            => 그에 따라 end의 초기값도 k-1로 변경?
            => 문제 조건이 반드시 M개의 휴게소를 지을 수 있다이니, 무조건 유효한 end값은 한번이라도 나오게 될것
            때문에 end는 실제 탐색 범위인 k로 둔다

        */
        return start
    }
    //O(n)
    private fun canBuildRestArea(interval: Int): Int{
        var count = 0
        for(i in 0 until arr.size-1){
            val restInterval = arr[i+1]-arr[i]-1 // 휴게소 간 간격-1
            //-1을 하는 이유? -> 실제 거리가 10일떄, interval이 10이 나오면 휴게소를 건설하지 못해야 한다. 그런 경우에 대응하기 위함?
            count += restInterval/interval // 휴게소 간 간격에 설정한 간격이 최대가 되도록 휴게소를 몇개 설치할 수 있나
        }
        println("interval[$interval] can build rest area $count")
        return count-m 
    }

}
/*
3 1 1000
200 710 800

6 7 1000
622 411 201 555 755 82
*/