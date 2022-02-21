// bronze 2
// 10-2

import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    bw.write("${favonachi(br.readLine().toInt())}\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun favonachi(n : Int) : Int{
    return if(n == 0) 
        0
    else if(n==1)
        1
    else
        favonachi(n-2)+favonachi(n-1)
}