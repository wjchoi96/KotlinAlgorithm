
import java.io.*
var arr : Array<Array<Int> = Array(41){Array(2){-1}}
var fabonacciArr : Array<Int> = Array(41){-1}
fun main(arg : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val size = br.readLine().toInt()
    for(i in 0 until size){
        val res = fabonacci(br.readLine().toInt())
        bw.write("$res => ")
        for(idx in 0 until 2){
            bw.write("${arr[idx]} ")
            arr[idx] = 0
        }
        bw.write("\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

// 계산은 맞는데..
/*
    fabonacci 1 일때 0,1 이 몇번 호출되는지 기억
    fabonacci 2 일때 기억
    ...
    fabonacci 10 일때 0,1 이 몇번 호출되었는지 기억

    알락말락 하네
*/
private fun fabonacci(n : Int) : Int{
    if(n==0){
        arr[n][0]++
        return 0
    }else if(n==1){
        arr[n][1]++
        return 1
    }
    if(fabonacciArr[n] < 0){
        fabonacciArr[n] = fabonacci(n-1) + fabonacci(n-2)
    }
    return fabonacciArr[n]
}