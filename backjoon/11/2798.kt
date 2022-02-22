// bronze 2
// 11-1

import java.io.*
import java.util.StringTokenizer

fun main(args : Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())

    val size = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    var card = Array<Int>(size){0}
    for(i in 0 until size){
        card[i] = st.nextToken().toInt()
    }
    
    var max = -1
    for(i in 0 until size){
        for(j in i+1 until size){
            for(k in j+1 until size){
                val res = card[i]+card[j]+card[k]
                // bw.write("$i $j $k\n")
                if(max <= res && res <= m){
                    max = res
                }
            }
        }
    }
    bw.write("$max\n")

    bw.flush()
    bw.close()
    br.close()
}

/*
    n : 총 카드의 개수
    m : 기준점 ( m을 넘지 않으면서 m 과 최대한 가까워야한다 )

    0 ~ 6
    012 013 014 015 016
    023 024 025 026
    034 035 036
    045 046
    056

    123 ~

    234 ~

    맨앞자리  i => 0 ~ n 순환
    두번째자리 j => i+1 ~ n 순환
    세번째자리 k => j+1 ~ n 순환
*/