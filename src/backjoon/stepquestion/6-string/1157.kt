import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    // a== 97 z == 122
    var alpaArr = Array<Int>(26) { 0 } // -97 해서 담아주면된다
    val value = br.readLine().toString()
    for( i in value ){
        alpaArr[i.toLowerCase().toInt() - 97]++
    } // 각 char 의 나온 횟수를 저장해놨음 

    // maxIndex 를 구해야한다
    var maxValue = -1
    var maxIndex = -1
    var prinltable = true
    for( index in alpaArr.indices ){
        if( alpaArr[index] > maxValue && alpaArr[index] != 0 ){
            maxValue = alpaArr[index]
            maxIndex = index
        }else if( alpaArr[index] == maxValue ) {
            bw.write("?")
            prinltable = false
            break
        }
    }
    if(prinltable)
        bw.write("${(maxIndex + 97).toChar().toUpperCase()}")
   
    bw.flush()
    br.close()
    bw.close()
}


