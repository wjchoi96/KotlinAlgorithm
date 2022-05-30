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

    output
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

    정렬방식 제출
    1. 성공
*/
/*
    투포인터 방식
    - 정렬로 은근 비슷하게 풀이한것같은데
    - 당장 정렬과 비슷한 방식밖에 떠오르지않아서, 접근 방식을 검색을 해봤다
    - 메모이제이션 변수를 두어서 맨 마지막에 합하는 방식을 많이 채용하는것 같기도 하다
    #참고
    https://baby-ohgu.tistory.com/13

    시간복잡도

    투포인터 방식 제출
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
        a.sort()
        b.sort()

        var aIdx = 0
        var bIdx = 0 
        var dp = Array(n){0}
        var count = 0 // aIdx가 가리키는 a가 Bidx가 가리키는 b를 먹을 수 있는경우의 수

        while(aIdx<n){
            // b가 끝에 도달했거나, b가 b를 먹지 못하는 경우
            if(bIdx == m || a[aIdx] <= b[bIdx]){
                if(aIdx == 0){
                    dp[0] = count
                }else{
                    // 이전 a가 먹을 수 있는 것들은 현재 a가 모두 먹을 수 있다
                    dp[aIdx] = dp[aIdx-1]+count 
                }
                count = 0
                aIdx++ // a 크기를 늘린다
            }
            // 현재 a가 현재 b를 잡아먹는 경우
            else{
                count++ 
                bIdx++ // b 크기를 늘린다
            }
        }
        // bw.write("${dp.toList()}\n")
        var sum = 0
        dp.forEach {
            sum+=it
        }
        bw.write("$sum\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun solve1UseSort(){
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