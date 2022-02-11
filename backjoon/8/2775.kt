// bronze 2
// 8-6

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    
    val floor = Array(15){ IntArray(15){0} }
    for(i in 1 until 15){
        floor[0][i] = i // 0층
    }
    for(i in 1 until 15){
        for(j in 1 until 15){
            if(j == 1)
                floor[i][j] = 1
            else
                floor[i][j] = floor[i-1][j] + floor[i][j-1]
        }
    }
    for(i in 0 until br.readLine().toInt()){
        val k = br.readLine().toInt()
        val n = br.readLine().toInt()
        bw.write("${floor[k][n]}\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}

/*
    202 : 101 + 102

    층 n, 호 w

    1 2 3 4 5
    1 3 6 10 15 
    1 4 10 20 35
    1 5 15 35 70

    (n-1)층의 w호수 까지의 사람들을 모두 더한다
    (n-1) 이 0보다 작다면 1로 취급

    => 바로 아래층 + 옆집

    1층 : 등차가 1인 등차수열
    2층 : 
*/
