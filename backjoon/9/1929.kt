// silver2
// 9-4

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val min = st.nextToken().toInt()
    val max = st.nextToken().toInt()

    for(i in min until max + 1){
        if(i<2)
            continue
        if(isSosu(i))
            bw.write("$i\n")
    }

    bw.flush()
    bw.close()
    br.close()
}
private fun isSosu(value : Int) : Boolean{
    if(value == 1 || value == 0)
        return false
    
    var i = 2
    while(i*i <= value){
        if(value % i == 0)
            return false
        i++
    }
    return true
}