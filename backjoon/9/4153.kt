// bronze 3
// 9-9

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st : StringTokenizer
    while(true){
        st = StringTokenizer(br.readLine())
        var x = st.nextToken().toInt()
        var y = st.nextToken().toInt()
        var z = st.nextToken().toInt()
        if(x==0 && y==0 && z==0)
            break
        
        val num1 = x*x
        val num2 = y*y
        val num3 = z*z
        if(num1+num2==num3 ||
            num2+num3==num1 ||
            num3+num1==num2
        )
            bw.write("right\n")
        else
            bw.write("wrong\n")
        
    }

    bw.flush()
    bw.close()
    br.close()

}