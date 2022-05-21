/*
    바킹독님 알고리즘 강의 중 정렬단원 정렬 응용문제
    sliver 3

    먹을 것인가 먹힐 것인가
    - 이분탐색, 투포인터로 구현을 한 정답이 많던데 정렬을 이용해서 한번 풀어보시길
    - 정렬, 투포인터 방식으로 구현해보자

    두 종류의 생명체 A,B가 심해에 존재한다
    A는 B를 먹는다

    A는 자기보다 크기가 작은 먹이만 먹을 수 있다
    A,B가 주어졌을때 큰 쌍이 몇개가 있는지 구하라

    예)
    A: [8, 1, 7, 3, 1]
    B: [3, 6, 1]

    output)
    <8,1>
    <8,6>
    <7-3>
    <7-6>
    <7-1>
    <3-1>
*/
/*
    A의 수N, B의 수M
    1<= N,M <= 20000
    1초
*/
/*
    정렬방식
    A는 오름차순 정렬(큰 수가 앞으로 오게), B는 내림차순 정렬
    A를 순회하며 현재값보다 작은 B값을 만날때까지 B배열을 순회한다
    현재 값보다 작은 B값을 만났다면 count를 전체 count 에 더한다
*/
/*
    정렬방식 제출
    1. 성공
*/

fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val tc = br.readLine().toInt()
    repeat(tc){
        val (n, m) = br.readLine().split(' ').map{it.toInt()}
        val a = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        val b = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

        a.sortDescending()
        b.sort()

        var totalCount = 0
        a.forEach{
            var count = 0
            for(i in 0 until b.size){
                if(it>b[i])count++
                else break
            }
            totalCount+=count
        }
        bw.write("$totalCount\n")
    }

    bw.flush()
    bw.close()
    br.close()
}