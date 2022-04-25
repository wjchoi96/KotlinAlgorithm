// gold4
/*
    네이버 카페 문제50선 중 백트래킹, 완전탐색 유형

    좋은 수열

    숫자 1,2,3 으로만 이루어지는 수열이 있다
    임의의 길이에 인접한 두 개의 부분 수열이 동일한것이 있다면 => 나쁜수열
    예)
    33
    32121323
    123123213

    그렇지 않다면 => 좋은수열
    예)
    2
    32
    32123
    1232123

    길이가 N인 좋은 수열들을 N자리의 정수로 보아, 그중 가장 작은 수를 구하라
    1<= N <= 80

    1초
*/
/*
    1,2,3 으로만 이루어져있다
    길이가 n

    1. 중복된 수 픽업 가능

    단순히 생각나는건
    1. 수를 뽑고, 뽑힌 숫자들을 탐색하며 나쁜수열인지 판단
    2. 지금 이 수를 뽑으면 나쁜수열이 되는지 판단

    나쁜수열 판단 방법
    - 인접한 두개의 부분수열이 동일하다면
    1. 같은 수가 연속으로 올때
    2. 현재idx 부터 2, 3, 4 단위로 수열을 쪼갰을때 동일한게 있다면
*/

/*
    idx = 1 => size = 2
    for(i in 1 until size/2){
        if(size/i != 0){ 현재 size/2가 i로 나뉜다면 => size/2 가 기준인 이유는 부분수열이 되려면 적어도 절반의 크기는 되어야 하기 때문이다 ( 80 => 40, 40 / 80 => 39,41 이건 동일한 부분수열이 아니다)
            // i 크기의 부분 수열을 체크해야한다
        }
    }
    
    근데 그럼 80번째 item을 놓을때는
    1, 2, 3, ... 40 까지 부분수열을 체크해줘야하나?
*/
private fun checkBadArr(idx : Int){ // 값이 입력된 부분까지의 idx
    val size = idx + 1
}

import java.io.*
private lateinit var arr : Array<Int>
private var n = 0
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    n = br.readLine().toInt()

    bw.flush()
    bw.close()
    br.close()
}