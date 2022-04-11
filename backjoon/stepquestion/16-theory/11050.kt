// bronze1
// 16-7
/*
    자연수N, 정수K가 주어졌을때 이항계수를 구하시오
    n = 5
    k = 2

    점화식을 함수로 표현한것
    //https://rh-tn.tistory.com/32
    fun bin(n,k) : Int {
        if(k == 0 || k == 1){
            return 1
        }else{
            return bin(n-1,k-1) + bin(n-1, k)
        }
    }

    
    
*/
import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    bw.write("${bin(n,k)}\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun bin(n : Int, k : Int) : Int {
    print("bin $n, $k\n")
    if(k == 0 || k == n){
        return 1
    }else{
        return bin(n-1,k-1) + bin(n-1, k)
    }
}
/*
    5, 2

    bin(5,2)
    => bin(4,1) + bin(4,2)
    => 1 + bin(4,2)

    bin(4,2)
    => bin(3,1) + bind(3,2)
     => 1 + bin(3,2)
*/