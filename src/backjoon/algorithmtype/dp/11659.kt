/*
    바킹독님 알고리즘 강의 중 dp단원 연습문제
    sliver 3

    구간 합 구하기 4

    Prefix sum 이라는 테크닉을 배울 수 있다고 하신다.
    일단 그냥 풀어보자

    수 n개가 주어졌을때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성



    1<= n <= 100000
    1<= m <= 100000
    1<= i <= j <= n
    
    1초
*/

/*
    그냥 단순히 푸는건 의미가 없는것 같고, 누적합 방식이나, 바킹독님이 제시한 Prefix sum 방식으로 풀어야 할것같아, 강의를 따라가보기로 결정
    => 그냥 메모이제이션 해서 푸는거였네?
    => 풀다가 느낀건데, Prefix sum이 누적합방식인거같아 찾아보니 맞다
    => 엄밀히 말하면, 메모이제이션이 아닌 본 배열에 값을 입력받으며 누적합을 저장하는 방식인것같다(공간 절약?)

    단순히 for(x in i..j) sum+=arr[x] 한다면 시간복잡도가 O(nm)이 된다 => 100억
    누적합(메모이제이션) 방식 => O(n) 으로 메모이제이션, O(1)로 값 접근
*/
/*
    dp[n] = arr[1] + arr[2] + ... arr[n]
    dp[n] = dp[n-1] + arr[n]
    bottom-top 방식으로 dp를 채운다
    2~5 누적합은 dp[5] - dp[1]
*/
/*
    제출
    1. 성공
*/

import java.util.StringTokenizer
private lateinit var dp: Array<Int>
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(' ').map{it.toInt()}
    val arr = Array(n+1){0}
    val st = StringTokenizer(br.readLine())
    repeat(n){arr[it+1] = st.nextToken().toInt()}
    dp = Array(n+1){-1}
    dp[0] = arr[0]
    dp[1] = arr[1]
    for(i in 2..n){
        dp[i] = dp[i-1]+arr[i]
    }

    repeat(m){
        val (i,j) = br.readLine().split(' ').map{it.toInt()}
        bw.write("${dp[j] - dp[i-1]}\n")
    }
    
    bw.flush()
    br.close()
    bw.close()
}