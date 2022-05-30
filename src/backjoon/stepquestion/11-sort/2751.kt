package src.backjoon.stepquestion.`11-sort`
//sliver5
//12-2


import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val arr : Array<Int> = Array(size){0}
    for(i in 0 until size){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()
    for(i in arr){
        bw.write("$i\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
} 

