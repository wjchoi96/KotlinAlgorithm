// bronze 3
// 9-7

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val x = st.nextToken().toInt()
    val y = st.nextToken().toInt()
    val w = st.nextToken().toInt()
    val h = st.nextToken().toInt()

    val xValue = if(w-x < x) w-x else x
    val yValue = if(h-y < y) h-y else y
    bw.write("${if(xValue > yValue) yValue else xValue}\n")

    bw.flush()
    bw.close()
    br.close()

}