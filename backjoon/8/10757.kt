// bronze5 
// 8-8

import java.io.*
import java.util.StringTokenizer
fun main(args: Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val a = st.nextToken()
    val b = st.nextToken()

    val big : String
    val small : String
    if(a.length>=b.length){
        big = a.toString().reversed()
        small = b.toString().reversed()
    }else {
        big = b.toString().reversed()
        small = a.toString().reversed()
    }
    var tmp = 0
    var res : String = ""
    for(i in 0 until big.length){ // big.length-1 downTo 0
        var value : Int
        if(i < small.length){
            value = Character.getNumericValue(big[i]) + 
                Character.getNumericValue(small[i]) + tmp
            if(value >= 10){
                tmp = value / 10
                value %= 10
            }else{
                tmp = 0
            }
        }else{
            value = Character.getNumericValue(big[i]) + tmp
            if(value >= 10){
                tmp = value / 10
                value %= 10
            }else{
                tmp = 0
            }
        }
        res = "${value}${res}"
        // bw.write("value : $value , res : $res\n")
    }
    if(tmp > 0)
        res = "${tmp}${res}"
    bw.write("$res\n")
    bw.flush()
    bw.close()
    br.close()
}