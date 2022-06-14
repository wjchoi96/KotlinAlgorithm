/*
    바킹독님 알고리즘 강의 중 이분탐색 단원 parametric search 알고리즘 연습문제
    sliver 2

    랜선 자르기

    캠프 때 쓸 N개의 랜선을 만들어야 한다
    K개의 랜선 보유, 길이가 제각각이다

    N개의 같은 길이의 랜선으로 만들기 위해 K개의 랜선을 잘라야 한다
    자른 랜선은 붙일 수 없다
    N개보다 많이 만드는 것도 N개를 만드는 것에 포험된다

    만들 수 있는 최대 랜선의 길이를 구하시오

    랜선을 자르거나 만들때 손실되는 길이는 없다
    K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다
    자를때는 항상 센티미터 단위로 정수 길이만큼 자른다

    2초
    1<= k <= 10000
    1<= N <= 1,000,000 => O(nlgn) 까지 허용일 것
    1<= 랜선 길이 <= 2^31 -1 
*/
/*
    Parametric Search 란?
    매개 변수 탐색
    조건을 만족하는 최소/최대값을 구하는 문제(최적화 문제)를 결정문제로 변환해 이분탐색을 수행하는 것

    == 적용 ==
    최적화 문제 
    : N개를 만들 수 있는 랜선의 최대 길이
    결정 문제
    : 랜선의 길이가 X일 때 랜선이 N개 이상인가 아닌가?

    이분탐색으로 랜선의 길이의 범위를 줄여본다

*/
/*
    제출
    1. 시간초과(50%)
    - 무한루프 걸렸던거같다
    - mid구하는 코드에 +1 추가
    - start+end 순간에 오버플로우가 발생할 수 있으니 long 타입으로 변경
    - 런타임 에러(/by zero)
    - start<=end 반복 조건을 start<end 로 변경
    => 틀렸습니다(5%)
    - 코드 대폭 수정
    - 반복 완료 조건(start, end) 조건 만족시 start 갱신 코드 디테일에 따라 결과가 크게 달라진다
    - 까다롭네..

    2. 성공
    - 바킹독님 코드 참고해서 max값을 제거하고, start 값에 적용
    - mid 구하는 코드에 +1 추가하여 무한루프 제거
*/

fun main(args: Array<String>){
    Solution1654().solve()
}
class Solution1654 {
    private var k = 0
    private var n = 0
    private lateinit var arr: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (size,count) = br.readLine().split(' ').map{it.toInt()}
        n=size; k=count
        arr = Array(n){0}
        var maxLine = 0
        repeat(n){
            arr[it] = br.readLine().toInt()
            maxLine = Math.max(maxLine, arr[it])
        }
        val res = getLanLineWidth2(1, maxLine)
        bw.write("$res\n")

        bw.flush()
        bw.close()
        br.close()
    }
    // O(lg2^31)
    private fun getLanLineWidth(startWidth: Int, endWidth: Int): Int {
        var start: Long = startWidth.toLong()
        var end: Long = endWidth.toLong()
        while(start<end){
            var mid = ((start+end+1)/2).toInt() //start, end 모두 int범위 내지만, start+end 가 순간 int 범위를 넘을 수 있다
            when(checkPossibleWidth(mid)){
                true -> start = mid.toLong() // 현재 일치하는 mid값 보다 큰 값 중에 가능한게 있는지 탐색
                false -> end = (mid-1).toLong() // mid 보다 작은 값이 있는지 탐색
            }
        }
        return start.toInt()
    }

    private fun getLanLineWidth2(startWidth: Int, endWidth: Int): Int {
        var start: Long = startWidth.toLong()
        var end: Long = endWidth.toLong()
        var max = 1
        while(start<end){
            var mid = ((start+end+1)/2).toInt()
            when(checkPossibleWidth(mid)){
                true -> {
                    start = (mid+1).toLong()
                    max = Math.max(max, mid)
                }
                false -> end = (mid-1).toLong()
            }
        }
        return max
    }
    
    //O(n)
    private fun checkPossibleWidth(width: Int): Boolean {
        var count = 0
        for(i in 0 until n){
            count += arr[i]/width
        }
        println("$width has $count")
        return count>=k
    }
}