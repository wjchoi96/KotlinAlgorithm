// sliver 2
// 9-5

import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    while(true){
        var n : Int = br.readLine().toInt()
        if(n<=0)
            break
        
        var count = 0
        for(i in n+1 until n*2 + 1){
            if(isSosu(i)) count++
        }
        bw.write("$count\n")

    }

    bw.flush()
    bw.close()
    br.close()
}

private fun isSosu(value : Int) : Boolean{
    if(value == 1 || value == 0)
        return false
    var i = 2 
    while(true){
        if(i*i > value)
            break
        if(value % i == 0)
            return false
        i++
    }
    return true
}