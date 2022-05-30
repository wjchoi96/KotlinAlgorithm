// bronze1
//8-3

/*
 1 : 1/1

 2 : 1/2
 3 : 2/1

 4 : 3/1
 5 : 2/2
 6 : 1/3
 
 7 : 1/4
 8 : 2/3
 9 : 3/2
 10: 4/1

 11: 5/1
 12: 4/2
 13: 3/3
 14: 2/4
 15: 1/5

 1 : 1
 2 : 2~3
 3 : 4~6
 4 : 7~10
 5 : 11~15

 1,2,4,7,11,16
  1,2,3,4,5

 n이 몇번째 덩이의 몇번쨰 item 인지 구한다

 1. 자기 덩이의 item 개수 + 1 = 분모 + 분자
 2. 자기 item 개수가 짝수이면 큰수가 분모로 시작
 */
import java.io.*
fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    bw.write("${getSequenceValue(br.readLine().toInt())}")

    bw.flush()
    bw.close()
    br.close()
}

private fun getSequenceValue(n : Int) : String{
    var count = 0
    var res = 0

    while(true){
        count++
        res += count
        if(res >= n)
            break
    }
    // print("n : $n, res : $res, count : $count\n")
    val idx = count - (res-n)
    return if(count%2 == 0){ // item 의 개수가 짝수 -> 작은수가 분자로 시작 -> idx 가 분자
        // print("n : $n => $idx/${count + 1 - idx}\n")
        "$idx/${count + 1 - idx}"
    }else{ // item 의 개수가 홀수 -> 작은수가 분모로 시작 -> idx 가 분모
        // print("n : $n => ${count + 1 - idx}/$idx\n")
        "${count + 1 - idx}/$idx"
    }
}
// res -> 각 덩이의 마지막
// count -> 각 덩이의 item 개수
// count - (res - n) => 각 덩이에서의 순서

// 14
// res -> 15
// count = 5
// count - (15 - 14) => 4 각 덩이에서의 idx ( 1번부터 시작 ) -> 분모 or 분자 중 하나
// 분자 분모 합 -> 6
// item 의 개수가 홀수이니 작은수가 분모로 시작 -> idx 가 분모 / 반대는 idx 가 분자
// 2/4