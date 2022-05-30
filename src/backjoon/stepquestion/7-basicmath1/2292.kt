// bronze 2
// 8-2

import java.io.*

/*
 수열 문제 같은데
 1 : 0
 2~7 : 1
 8~19 : 2
 20~37 : 3
 38~61 : 4

 1,2,8,20,38,72 
  1,6,12,18,24
 첫항인 1을 제외한 공비가 6인 등비수열
 2,8,20,38,72 
  6,12,18,24

 2번째 행 : 6(n-1) + 첫번째행
 3번째 행 : 6(n-1) + 두번째행
 4번째 행 : 6(n-1) + 세번째행

 일반항 : an = ar (n-1)
 n번째 항 = 초항 * 공비의 n-1승
 */
fun main(args: Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    bw.write("${getSequenceIndex(br.readLine().toInt()) + 1}")
    // bw.write("${getSequenceIndex2(1)}\n")
    // bw.write("${getSequenceIndex2(2)}\n")
    // bw.write("${getSequenceIndex2(7)}\n")
    // bw.write("${getSequenceIndex2(8)}\n")
    // bw.write("${getSequenceIndex2(20)}\n")
    bw.flush()
    br.close()
    bw.close()
}

// size를 포함하지 않는, 
// 공비가 6인 등비수열
/*
 1 : 0
 2~7 : 1
 8~19 : 2
 20~37 : 3
 38~61 : 4
 */
private fun getSequenceIndex(size : Int) : Int {
    if(size == 1)
        return 0
    var res = 1
    var count = 1
    while(true){
        if(res >= size) //2
            break
        count++
        for(i in 0 until count-1){
            // print("res : $res, count : $count\n")
            res += 6
        }
    }
    // print("$res, ${count-1}\n")
    return count-1
}

// private fun getSequenceIndex2(size : Int) : Int {
//     var res = 1
//     var count = 0
//     while(true){
//         count++
//         for(i in 0 until count){
//             print("size : $size, res : $res, count : $count\n")
//             res += 6
//         }
//         if(res >= size) 
//             break
//     }
//     // print("$res, ${count-1}\n")
//     return count
// }