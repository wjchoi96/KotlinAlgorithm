//gold 5
//16-5
//2981

/*
    숫자 N개를 적는다
    적은 수를 M으로 나누었을때, 나머지가 모두 같게되는 M을 모두 찾으려고 한다
    1<= M

    2<= size <= 100
    1 <= item <= 1000000000

    나머지가 모두 같은 M?

    6 32 38
    m 이 하나 이상 무조건 존재
    
    6/m + r
    32/m + r
    38/m + r

    r은 모두 같다
    

    r 을 0으로 만들 수 있다면 m을 쉽게 구할 수 있다
*/

import java.io.*
import java.util.StringTokenizer 

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    
    var st : StringTokenizer

    val size = br.readLine().toInt()
    bw.write("$size\n")
    
    
    bw.flush()
    bw.close()
    br.close()
}