//sliver5
//16-9
/*
    다리 놓기
    서쪽에 n개의 사이트
    동쪽에 m개의 사이트
    n<=m, 0 < N ≤ M < 30

    최대한 많은 다리를 지으려고 한다 => 서쪽의 사이트에 모두 다리를 놓는다

    다리를 최대한 많이 지을 수 있는 경우의 수
    -> 겹치지 않도록 다리를 최대한 많이 배치

    dp를 사용해서 최대 다리개수가 나오는 경우의 수?

    왼쪽은 최대개수 고정
    오른쪽은 왼쪽에 맞춰서 순서가 꼬이지않고 정렬된 상태로 최대 경우의 수
    2 2 -> 1
    1 5 -> 5
    13 29 -> 67863915 

    3 7 
    7개중에 3개를 선택, 순서가 오름차순이 되도록
    1,2,3
    1,2,4
    1,2,5
    1,2,6
    1,2,7
    1,3,4
    1,3,5 
    ...
    7,8,9
    백트래킹 아닌가? 근데 0.5초네..
    => 백트래킹으로 가능은하나, 역시나 시간제한에 걸린다
    => 저정도 경우의 수를 체크하는데 0.5초면 dp..?

    n개의 수열중에서 중복없이 오름차순으로 m개를 선택

    n개의 수열중에 중복없이 오름차순으로 m개의 수를 선택하는 dp
    
    => 레전드.. 수학을 다시 공부할까 ㅅㅂㅋㅋㅋㅋㅋ
    조합공식
    nCr => n개중의 r개를 선택하는 경우의수(조합)
    n! / (n-r)!r!
    => 
*/

import java.io.*
import java.util.StringTokenizer
// 최대 30*30 까지 가능한 입력범위
private val bridgeDp : Array<Array<Int>> = Array(30){Array(30){-1}}
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))   
    
    val size = br.readLine().toInt()

    for(i in 0 until 30){
        bridgeDp[i][0] = 1
        for(j in 0 until 30){
            if(i==j){
                bridgeDp[i][j] = 1
            }
        }
    }

    for(i in 0 until size){
        val st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        bw.write("${getBridgeDp(Math.max(n,m), Math.min(n,m))}\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}

private fun getBridgeDp(n : Int, k : Int) : Int {
    if(bridgeDp[n][k]>= 0){
        return bridgeDp[n][k]
    }
    bridgeDp[n][k] = getBridgeDp(n-1, k-1) + getBridgeDp(n-1, k)
    return bridgeDp[n][k]
}



private lateinit var bridgeArr : Array<Int>
private lateinit var visit : Array<Boolean>
private var buildCount = 0
// 구현은 되었으나 예상대로 0.5초안엔 절대로 못한다
private fun bridgeBacktracking(){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))   
    
    val size = br.readLine().toInt()
    for(i in 0 until size){
        buildCount = 0
        val st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        bridgeArr = Array(n+1){-1}
        visit = Array(m+1){false}
        makeBridge(1, m)
        bw.write("$buildCount\n")
    }

    bw.flush()
    bw.close()
    br.close()

}

private fun makeBridge(depth : Int, size : Int){
    if(depth == bridgeArr.size){
        // print("finish => [")
        // for(i in bridgeArr){
        //     print("$i ")
        // }
        // print("]\n")
        buildCount++
        return
    }
    for(i in 1 until size+1){
        if(visit[i] == false){
            if(bridgeArr[depth-1] < i){
                // print("save $i\n")
                visit[depth] = true
                bridgeArr[depth] = i
                makeBridge(depth+1, size)
                visit[depth] = false
            }
        }
    }
}

