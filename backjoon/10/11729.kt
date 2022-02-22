//sliver 2
//10-4

/*
    원반 n개를 a->c 이동하는것은
    1 : 원반 n-1개를 a->b로 이동하는것과
    2 : 큰 원반 1개를 a->c,
    3 : 원반 n-1개를 b->c 로 이동하는것

    하노이 일반항
    https://st-lab.tistory.com/96
    2^n - 1 => 2의 n제곱 - 1

*/

import java.io.*
import java.math.BigInteger
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    bw.write("${getHanoiCount(n)}\n")
    if(n<=20)
        hanoi(n, 1, 2, 3, bw)
    bw.flush()
    bw.close()
    br.close()
}

fun getHanoiCount(n : Int) : String {
    var res = BigInteger("1")
    for(i in 0 until n){
        res = res.multiply(BigInteger("2"))
    }
    res = res.subtract(BigInteger("1"))
    return res.toString()
    // return "${(Math.pow(2.0, n.toDouble())-1).toInt()}"
}

fun hanoi(n : Int, from : Int, by : Int, to : Int, bw : BufferedWriter){
    if(n==1){
        bw.write("$from $to\n")
    }else{
        //원반 n-1개를 a->b로 이동하는것
        hanoi(n-1, from, to, by, bw)
        //큰 원반 1개를 a->c
        bw.write("$from $to\n")
        //원반 n-1개를 b->c로 이동
        hanoi(n-1, by, from, to, bw)
    }
}

// from 의 원반n개를 by를 거쳐 to로 이동
// https://brenden.tistory.com/31
// fun hanoi(n : Int, from : Int, by : Int, to : Int, count : Int, bw : BufferedWriter? = null) : Int{
//     var c = 0
//     if(n==1){
//         bw?.write("$from $to :: 원반 ${n}을 ${from}에서 ${to}로 이동\n")
//         c++
//     }else{
//         //원반 n-1개를 a->b로 이동하는것
//         c += hanoi(n-1, from, to, by, count+1, bw)
//         //큰 원반 1개를 a->c
//         bw?.write("$from $to :: 원반 ${n}을 ${from}에서 ${to}로 이동\n")
//         c++
//         //원반 n-1개를 b->c로 이동
//         c += hanoi(n-1, by, from, to, count+1, bw)
//     }
//     return c
// }