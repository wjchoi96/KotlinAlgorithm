// bronze 3
// 8-5

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    try{
        var st = StringTokenizer(br.readLine())
        for(i in 0 until st.nextToken().toInt()){
            st = StringTokenizer(br.readLine())
            val h = st.nextToken().toInt()
            val w = st.nextToken().toInt()
            val n = st.nextToken().toInt()
            bw.write("${notifyRoom(n, h, w)}\n")
        }
    }catch(e : Exception){
        e.printStackTrace()
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun notifyRoom(idx : Int, height : Int, width : Int) : Int{
    var h = idx%height
    if(h == 0)
        h = height
    var wTmp = idx.toDouble()/height.toDouble()
    val w = if(wTmp.toInt() < wTmp)
            wTmp.toInt() + 1
         else
            wTmp.toInt()
    return h*100 + w
}
/*
1 ≤ H, W ≤ 99, 1 ≤ N ≤ H × W
h, w

1 => 101
2 => 201
3 => 301
h => h01
h+1 => 102
h+h => h02

h : 3
n : 2

h : 4
n : 2

h : 4
n : 9

n/h + 1 -> 호수 
n%h -> 층수 

103
*/
