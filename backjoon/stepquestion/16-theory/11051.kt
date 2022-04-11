// sliver1
// 16-8
/*
    자연수N, 정수K가 주어졌을때 이항계수%10007 를 구하라
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

    1 <= n <= 1000
    0 <= k <= n
    dp로 푸는 문제라는데
    
    
*/
import java.io.*
import java.util.StringTokenizer
private lateinit var binDp : Array<Array<Int>> // binDp[n][k]
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    binDp = Array(n+1){Array(k+1){-1}}
    
    // 5 2
    for(ni in 0 until n+1){
        binDp[ni][0] = 1
        for(ki in 1 until k+1){
            if(ni == ki){
                binDp[ni][ki] = 1
            }
        }
    }

    bw.write("${bin(n,k)}\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun bin(n : Int, k : Int) : Int {
    if(binDp[n][k] >= 0){
        return binDp[n][k]%10007
    }
    binDp[n][k] = bin(n-1, k-1) + bin(n-1, k)
    return binDp[n][k]%10007
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