//sliver3
//16_3

/*
    돈을 뽑는 속도가 각각 다른 사람들이 atm 한대에 줄을 서있다

    줄을 서는 속도에 따라 각 사람들이 돈을 뽑을 수 있는 시간이 달라진다

    P1 = 3, P2 = 1, P3 = 4, P4 = 3, P5 = 2
    [1, 2, 3, 4, 5] 로 선경우
    1 : 3
    2 : 4
    = 3 + 1
    3 : 8
    = 3 + 1 + 4
    4 : 11
    5 : 13
    -> 3 + 4 + 8 + 11 + 13 = 39분

    [2, 5, 1, 4, 3] 로 선다면
    2 : 1
    5 : 3
    1 : 6
    4 : 9
    3 : 13
    -> 32분

    돈을 뽑는 시간이 짧은 사람부터 뽑으면 된다
    userArr[idx] = time
*/

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){ 
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val userArr = Array<Int>(size){-1}
    for(i in 0 until size){
        userArr[i] = st.nextToken().toInt()
    }

    userArr.sort()
    var sum = 0
    var pendingTime = 0
    for(i in 0 until size){
        sum += pendingTime + userArr[i] // 대기시간 + 내가 돈뽑는데 걸리는 시간
        pendingTime += userArr[i] // 내가 끝났으니, 다음차례사람에겐 내가 돈뽑는 시간이 대기시간
    }
    bw.write("$sum\n")

    br.close()
    bw.flush()
    bw.close()
}