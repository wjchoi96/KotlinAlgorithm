//sliver3
/*
    바킹독님 백트래킹 단원에서 제시된 연습문제

    1부터 n까지 중복없이 m개를 고른 수열
    고른 수열은 오름차순이여야 한다
*/

/*
    제출
    1. 성공

    개선
    - for문을 사용하지않는 방법으로 구현해 보았다
    - 개선이란 의미에 어울리는지는 모르겠지만, 약간 다른 방식을 학습
*/

import java.io.*
private lateinit var bw : BufferedWriter
private lateinit var visit : Array<Boolean>
private lateinit var res : Array<Int>
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(' ').map{ it.toInt() }
    visit = Array(n+1){false}
    res = Array(m){-1}   

    // dfs(1, 0)
    dfs2(size= n+1)
 
    bw.flush()
    bw.close()
    br.close()
}

/*
    개선
    - 이전 item과 비교하는게 아닌, dfs에 시작 숫자를 같이 넘겨주게 해서 자동으로 오름차순이 되도록 한다
    - push 된 값을 넘겨주는게 아닌, +1을 해서 넘겨주므로 visit 배열도 필요가 없어진다
*/
private fun dfs(num : Int, depth : Int){
    if(depth == res.size){
        for(i in 0 until res.size){
            bw.write("${res[i]} ")
        }
        bw.write("\n")
        return
    }
    for(i in num until visit.size){
        res[depth] = i
        dfs(i+1, depth+1)
    }
}

private fun dfs2(num : Int = 1, depth : Int = 0, size : Int){
    if(depth == res.size){
        for(i in 0 until res.size){
            bw.write("${res[i]} ")
        }
        bw.write("\n")
        return
    }
    if(num >= size){
        return
    }
    res[depth] = num
    dfs2(num + 1, depth + 1, size) // 현재 item을 선택한 가지
    dfs2(num + 1, depth, size) // 현재 item을 선택하지 않은 가지
}