

import java.io.*
import java.util.StringTokenizer
// silver 4
// 9-1

/*
    소수 찾기 알고리즘

    소수 : 1과 자기 자신을 제외하고 나누어떨어지는 숫자가 없는 수


*/
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var count = 0
    val size = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    for(i in 0 until size){
        val num = st.nextToken().toInt()
        val isSosu = isSosu(num)
        bw.write("$num is suso?? => $isSosu\n")
        if(isSosu) count++
    }
    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun isSosu(value : Int) : Boolean{
    if(value == 1 || value == 0)
        return false
    for(i in 2 until value){
        if(value % i == 0)
            return false
    }
    return true
}