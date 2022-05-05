/*
    단계별로 풀어보기 투포인터
    sliver 3

    두 수의 합

    n개의 서로 다른 양의 정수로 이루어진 수열

    1<= A[n] <= 1000000

    x가 주어졌을때 a[i] + a[j]를 만족하는 쌍의 수를 구하라
    1<= i<j <= n

    1초
*/
/*
    start : 0
    end : start + 1 
    로 시작

    조건
    start + end == x
    start < end

    end증가 조건
    start + end != x and
    end < n-1

    start 증가 조건
    start + end == x or
    end == n-1
    => 이때 end = start + 1을 해줘야한다

    종료조건
    start==end or
    start == n-1 and end == n-1
    두개가 동시에 만족될거다 아마
    둘중에 하나만 해도 될거같기도 하고
*/
/*
    진행해보니 느끼는점
    1. 종료조건 변경
    - start >= n || end >= n
    - 단순하므로 while문의 조건으로 변경

    2. 종료조건 체크 시점도 생각하고 정해야한다
*/
/*
    제출
    1. 시간초과
    - 알고리즘을 잘못 이해했나?

    2. 성공
    - 알고리즘을 잘 이해못한거같아서 풀이를 보고 진행
*/
/*
    검색을 통해 풀이 진행

    1. 배열 정렬
    2. start = 0, end = n-1 에서 시작
    - 합이 x보다 큰경우 - 더 작은 값을 더해야 하니 end-1
    - 합이 x보다 작은경우 - 더 큰값을 더해야 하니 start + 1
    - 합이 x인 경우 - result++, start + 1
*/

fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val n = br.readLine().toInt()
    val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    arr.sort()
    val x = br.readLine().toInt()

    var start = 0
    var end = n-1
    var result = 0
    while(true){
        if(start == end){
            break
        }
        val sum = arr[start] + arr[end]
        print("a[$start] + a[$end] = $sum\n")
        when {
            sum<x -> start++
            sum>x -> end--
            sum==x -> {
                result++
                start++
            }
        }
    }
   
    bw.write("$result\n")
    bw.flush()
    bw.close()
    br.close()
}

private fun fail1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val n = br.readLine().toInt()
    val arr = br.readLine().split(' ').map{it.toInt()}
    val x = br.readLine().toInt()

    var start = 0
    var end = start + 1
    var result = 0
    while(start < n && end < n){
        print("a[$start] + a[$end] = ${arr[start] + arr[end]}\n")
        if(start < n && (arr[start] + arr[end] == x || end == n-1)){
            if(arr[start] + arr[end] == x){
                result++
            }
            start++
            end = start + 1
        }else if(end < n){
            end++
        }
    }
    bw.write("$result\n")
    bw.flush()
    bw.close()
    br.close()
}