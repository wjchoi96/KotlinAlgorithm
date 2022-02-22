//bronze 2
//11-2

/*
    198 + 1 + 9 + 8 = 216

    216의 생성자 구하는 방법
    216 보다 작은 자연수를 훑어?

    216이 되려면 최소 999 27
    216 - 27 ~ 216 까지 훑으면 되는건가?

    각 자리수의 경우의 수 -> 9개
    n - 9*(n의 자리수) ~ n까지 훑으면 될듯

    가장 작은 생성자를 구하라
*/

import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

  
    val n = br.readLine().toInt()
    val digits = getDigits(n)
    var init = 0
    for(i in (n-9*digits) until n){
        var value = i
        var res = i
        for(i in 0 until digits){
            res += value%10
            value /=10
        }
        if(res == n){
            // bw.write("result : $i\n")
            init = i
            break
        }
    }
    bw.write("$init\n")

    bw.flush()
    bw.close()
    br.close()
    
}
private fun getDigits(n : Int) : Int {
    var value = n
    var count = 1
    while(value / 10 != 0){
        value /= 10
        count++
    }
    return count
}