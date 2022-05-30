// bronze 1
// 8-7

import java.io.*
fun main(arge : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val original : Int = br.readLine().toInt()
    var n : Int = original
    var five: Int = n / 5
    var three: Int
    var value = -1
    n %= 5
    while(true){
        if(five < 0)
            break
        else if(n % 3 == 0){
            three = n / 3
            // print("five $five, three : $three\n")
            value = five + three
            break
        }
        five--
        n += 5
        if(n > original)
            break
    }
    bw.write("${value}\n")

    bw.flush()
    bw.close()
    br.close()
}

/*
    n 을 % 5 작업 -> 3으로 나눠떨어지는지
    n % 5 + 5 -> 3으로 나눠떨어지는지?

*/