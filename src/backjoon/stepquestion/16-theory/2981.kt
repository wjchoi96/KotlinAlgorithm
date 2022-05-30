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

    6%m == 32%m == 38%m
    
    6/m + r
    32/m + r
    38/m + r

    r(나머지), m(나눈수) 는 모두 같다
    
    A1 = a1*m+r
    A2 = a2*m+r
    A1-A2 = (a1*m+r) - (a2*m+r)
    => a1*m - a2*m - r - r  ===>  r - r = 0
    => m(a1-a2) = A1-A2

    (A1-A2)/m = a1-a2

    (6-32)m
    (32-38)m 은 같은 값 => (6-32) 와 (32-38)의 공약수라는 의미

*/

import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))  
    val size = br.readLine().toInt()
    
    val arr = Array(size) { 0 }
    for(i in 0 until size){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()

    var gcdValue = arr[1] - arr[0]
    for(i in 2 until size){
        gcdValue = gcd(gcdValue, arr[i]-arr[i-1])
    }
    for(i in 2 until gcdValue+1){
        if(gcdValue%i == 0){
            bw.write("$i ")
        }
    }
    bw.write("\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun gcd(a : Int, b : Int) : Int {
    var x = a
    var y = b
    while(y!=0){
        val t = x%y
        x = y
        y = t
    }
    return x
}