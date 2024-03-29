// silver 5
// 9-3
import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var value = br.readLine().toInt()
    doMainFunc3(value, bw)

    bw.flush()
    bw.close()
    br.close()
}

private fun doMainFunc(num : Int, bw : BufferedWriter){
    var value = num
    val sosuList = getSosuList(value)
    var sosuIdx = 0
    while(true){
        if(isSosu(value)){
            bw.write("$value\n")
            break
        }
        if( value % sosuList[sosuIdx] == 0 ){
            bw.write("${sosuList[sosuIdx]}\n")
            value /= sosuList[sosuIdx]
        }else{
            sosuIdx++
        }
    }
}

private fun doMainFunc2(num : Int, bw : BufferedWriter){
    var value = num
    var sosu = 2
    while(true){
        if(isSosu(value)){
            bw.write("$value\n")
            break
        }
        if( value % sosu == 0 ){
            bw.write("$sosu\n")
            value /= sosu
        }else{
            sosu++
        }
    }
}

private fun doMainFunc3(num : Int, bw : BufferedWriter){
    var value = num
    var sosu = 2
    while(value>1){
        if( value % sosu == 0 ){
            bw.write("$sosu\n")
            value /= sosu
        }else{
            sosu++
        }
    }
}

private fun getSosuList(max : Int) : ArrayList<Int> {
    val list = ArrayList<Int>()
    for(i in 2 until max + 1){
        if(isSosu(i)) list.add(i)
    }
    return list
}

private fun isSosu(value : Int) : Boolean {
    if(value == 1 || value == 0)
        return false
    for(i in 2 until value){
        if(value % i == 0)
            return false
    }
    return true
}