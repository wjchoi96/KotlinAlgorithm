// bronze 3
// 9-10
import java.io.*
import java.math.BigDecimal
fun main(args : Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val pi : Double = 3.141592653589793
    val radius = br.readLine().toDouble()

    bw.write(String.format("%.6f", radius * radius * pi) + "\n")
    bw.write(String.format("%.6f", radius * radius * 2.0) + "\n")

    bw.flush()
    bw.close()
    br.close()
}