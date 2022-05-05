/*
    바킹독님 알고리즘 강의 중 투포인터 단원
    gold 5

    수 고르기

    n개의 정수로 이루어진 수열
    두 수를 골랐을때(같은 수일수도 있다), 그 차이가 m이상이면서 제일 작은 경우를 구하라
    항상 차이가 m이상인 수를 고를 수 있다

    1<= n <= 100,000
    0<= m <= 2,000,000,000
    0<= |A[i]| <= 1,000,000,000

    2초
*/
/*
    1. 정렬
    2. start = 0, end = n-1
    3. 차(minus)를 구한다
    - minus <= m 일때 바로 이전값을 출력하면 될듯?
*/
/*
    제출
    1. 틀렸습니다
*/
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val (n, m) = br.readLine().split(' ').map{it.toInt()}
    val arr = Array(n){0}
    for(i in 0 until n){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()
    print("${arr.toList()}\n")

    var start = 0
    var end = n-1
    var min = Int.MAX_VALUE
    while(start <= end){
        val minus = arr[end]-arr[start]
        if(minus >= m){
            min = Math.min(min, minus)
        }
        when {
            minus == m -> break
            minus > m -> start++
            minus < m -> end--
        }
    }
    bw.write("$min\n")


    bw.flush()
    bw.close()
    br.close()
}
