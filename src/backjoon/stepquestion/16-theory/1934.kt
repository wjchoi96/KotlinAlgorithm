// silver 5
// 16-4
/*
    최소공배수를 구하시오

    유클리드 호제법 외워야겠는데
*/
    
import java.io.*
import java.util.StringTokenizer 

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    
    var st : StringTokenizer

    val size = br.readLine().toInt()
    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        bw.write("${lcm(a,b)}\n")
    }

    
    bw.flush()
    bw.close()
    br.close()
}

// GCD(a, b) = GCD(b, r)
// GCD(322, 259) = GCD(259, 63) =  GCD(63, 7) = GCD(7, 0) = 7
private fun gcd(x : Int, y : Int) : Int {
    var a = x
    var b = y
    while(b!=0){
        val r = a%b
        a = b
        b = r
    }
    return a
}
private fun lcm(x : Int, y : Int) : Int {
    return x * y / gcd(x, y)
}