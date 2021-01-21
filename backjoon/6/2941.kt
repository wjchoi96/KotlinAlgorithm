import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(args : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
 
    
    
    bw.flush()
    br.close()
    bw.close()
}

private fun isCroatiaWord(word : String) : Boolean {
    return when(){
        word.equals("c=") || word.equals("c-") || word.equals("dz=") || word.equals("d-") ||
        word.equals("lj") || word.equals("nj") || word.equals("s=") || word.equals("z=") -> true
        else -> false
    }
}




