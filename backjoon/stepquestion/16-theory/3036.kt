//sliver3
//16-6

/*
    첫번째 링을 한바퀴 돌리면, 나머지 링은 몇바퀴 돌아가는가
    3<= size <= 100
    1<= r <= 1000
    첫번째 링에 대해서 

    1. 첫밴째 링의 원의 둘레를 구한다 
    ( 2pr => 파이,r 은 공통되어있으니 단순 r이 둘레라고 판단해도 무방)
    2. 2번째 링 => 8/4 => 2/1

    3. 1r / nr => 최대공약수가 존재한다면 나눠서 표기
    
*/

import java.io.*
import java.util.StringTokenizer 

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    

    val size = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    
    var first = st.nextToken().toInt()
    for(i in 1 until size){
        val x = st.nextToken().toInt()
        val gcd = gcd(first, x)
        bw.write("${first/gcd}/${x/gcd}\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}

private fun gcd(x : Int, y : Int) : Int {
    var a = x
    var b = y
    // 250 100 -> 100 50 -> 50 50 -> 50 0
    while(b != 0){
        val t = a%b
        a = b
        b = t
    }
    return a
}