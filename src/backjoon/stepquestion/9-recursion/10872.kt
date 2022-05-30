// bronze 3
// 10-1

import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    bw.write("${factorial(br.readLine().toInt())}\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun factorial(n : Int) : Int{
    if(n < 1)
        return 1
    return factorial(n-1)*n
}