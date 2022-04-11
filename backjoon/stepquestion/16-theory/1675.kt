// sliver 4
// 16-11

/*
    N! 에서 뒤에서부터 처음 0이 아닌 숫자가 나올때까지 0의 개수를 구하라
*/


import java.io.*
lateinit var facDp : Array<Long>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    

    val n = br.readLine().toInt()
    facDp = Array(n+1){0}
    facDp[1] = 1
    facDp[2] = 2

    var res : Long = fac(n)
    print("$res\n")
    var count = 0
    do{
        // print("$res\n")
        if(res%10.toLong()==0.toLong()){
            count++
        }else{
            break
        }
        res /= 10
    }while(true)
    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}
private fun fac(n : Int) : Long {
    if(facDp[n] != 0.toLong()){
        return facDp[n]
    }
    facDp[n] = fac(n-1)*n
    return facDp[n]
}