// 15-14
// gold 5

/*
    commit test
    LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는
    두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제

    ACAYKP 
    CAPCAK
    의 lcs 는 ACAK 가 된다

    lcs[X][n]
    lcs[Y][n]
    두가지에 각각 저장

    lcs[1][A] 
    B 를 훑으면서 같은 문자가 있나 체크
    있다면 저장 후 다음 idx

    P로 끝나는 최장수열의 길이
      A C A Y K P
    C 0 1 1 1 1 1
    A 1 1 2 2 2 2
    P 1 1 2 2 2 3
    C 1 2 2 2 2 3
    A 1 2 3 3 3 3
    K 1 2 3 3 4 4

    x번째 원소와 y번째 원소가 같다면 (x-1, y-1) 의 LCS길이의 +1이 된다.
    if(lcs[X][i] == lcs[Y][j])
        dp[x][y] = dp[x-1][y-1] +1 해준다
    else
        Math.max(lcs(x-1,y), lcs(x, y-1) 중에 큰값

    // 침조 : https://st-lab.tistory.com/139
    거의 베껴서 풀었다
    접근방향도, 구현 방향도
    이해가 잘 안가네.. 완전 답 베끼는 수준

*/

import java.io.*
lateinit var lcsArr : Array<String>
lateinit var lcsDp : Array<Array<Int>>
private val A = 0
private val B = 1
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    lcsArr = Array(2) { "" }
    for(i in 0 until 2){
        lcsArr[i] = br.readLine()
    }
   
    // 두 문자열의 크기가 같을것이라고 생각했는데, 다를 수 있다
    val size1 = lcsArr[A].length
    val size2 = lcsArr[B].length
    lcsDp = Array(size1){ Array(size2){-1} }

    bw.write("${getLcs(size1-1, size2-1)}\n")

    br.close()
    bw.flush()
    bw.close()
}

private fun getLcs(x : Int, y : Int) : Int{
    if(x < 0 || y < 0){ // idx 값을 넘어가면 0 리턴
        return 0
    }
    if(lcsDp[x][y] >= 0){
        return lcsDp[x][y]
    }
    lcsDp[x][y] = 0
    //같다면 +1
    if(lcsArr[A].get(x) == lcsArr[B].get(y)){
        lcsDp[x][y] = getLcs(x-1, y-1) + 1
    }else{
        lcsDp[x][y] = Math.max(getLcs(x-1, y), getLcs(x, y-1))
    }
    return lcsDp[x][y]
}