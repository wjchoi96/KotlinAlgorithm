//sliver5
//16-3

/*
    두개의 자연수를 입력받아 최대공약수와 최소공배수를 출력
*/

import java.io.*
import java.util.StringTokenizer 

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    
    val st = StringTokenizer(br.readLine())
    val a = st.nextToken().toInt()
    val b = st.nextToken().toInt()

    var maxDivide : Int = 0
    val minMultiple : Int

    for(i in a downTo 0){
        if(a%i == 0 && b%i == 0){
            maxDivide = i
            break
        }
    }

    
    var count = 1
    while(true){
        if(a*count % b == 0){
            minMultiple = a*count
            break
        }
        count++
    }

    // maxDivide = gcd(a, b)
    // minMultiple = a * b / maxDivide

    bw.write("$maxDivide\n$minMultiple\n")
  
    bw.flush()
    bw.close()
    br.close()
}

private fun gcd(x : Int, y : Int) : Int{
    var a = x
    var b = y
    while(b != 0){
        val r = a%b
        a = b
        b = r
    }
    return a
}
private fun lcm(x : Int, y : Int) : Int{
    return x * y / gcd(x,y)
}