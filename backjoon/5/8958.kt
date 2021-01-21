import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer

    val size = br.readLine().toInt()
    val array : Array<Int> = Array(size) { 0 }

    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        var prevValue : Int = 0
        var sum = 0
        for(index in 0 until st.countTokens()){
            val word = st.nextToken().toString()
            var value : Int = 0 
            if( word == "O" ){
                if( index == 0 ){
                    value = 1  
                }else{
                    if( prevValue > 0 ){
                        value = prevValue + 1
                    }else{
                        value = 1
                    }
                }
            }else if( word == "X" ){
                value = 0
            }
            sum += value
            prevValue = value
        }
        array[i] = sum
    }

   
    bw.flush()
    br.close()
    bw.close()
}