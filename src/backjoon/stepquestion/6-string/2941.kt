// silver 5
// 7-9

import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader

fun main(args : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
 
    val value = br.readLine().toString()

    /*
    크로아티아 알파벳을 대체하는 key 값들은 최소 2자리 ~ 최대 3자리 의 길이를 가진다
    입력된 value 를 순회한다
    현재값 +1, +2 까지 고려하여 현재값이 크로아티아 key 값의 일부인지 판단

    크로아티아 key 값의 일부라면 + 된 길이를 buffer 에 저장, count++
    해당 buffer 의 개수만큼 다음 loop 는 무시 ( 크로아티아 key값 이다 )
     */
    var idx = 0
    var buffer = 0
    var count = 0
    bw.write("input value : $value\n")
    while(true){
        if(buffer != 0){
            buffer--
            bw.write("buffer minus ($idx)\n")
            idx++
            continue
        }
        if(idx+1 < value.length && isCroatiaWord("${value[idx]}${value[idx+1]}")){
            bw.write("coriatia(1) : ${value[idx]}${value[idx+1]}\n")
            buffer++
            count++
        }else if(idx+2 < value.length && isCroatiaWord("${value[idx]}${value[idx+1]}${value[idx+2]}")){
            bw.write("coriatia(1) : ${value[idx]}${value[idx+1]}${value[idx+2]}\n")
            buffer+=2
            count++
        }else if(idx < value.length) {
            bw.write("coriatia else : $idx\n")
            count++
        }
        if(idx++ >= value.length)
            break
    }
    
    bw.write("$count\n")
    
    bw.flush()
    br.close()
    bw.close()
}

private fun isCroatiaWord(word : String) : Boolean {
    return when{
        word.equals("c=") || word.equals("c-") || word.equals("dz=") || word.equals("d-") ||
        word.equals("lj") || word.equals("nj") || word.equals("s=") || word.equals("z=") -> true
        else -> false
    }
}




