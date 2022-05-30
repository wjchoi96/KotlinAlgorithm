//sliver 5
// 11-5

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val num = getMinTerminateNum(br.readLine().toInt())
    bw.write("$num\n")


    bw.flush()
    bw.close()
    br.close()
}
private fun getMinTerminateNum(size : Int) : Int {
    var count = 1
    var value = 666
    if(size == 1){
        return value
    }
    while(count <= size){
        if(isTerminateNum(value)){
             count++
        }
        value++
    }
    return value-1
}
/*
    1. 10으로 나누며 각 자리가 6인지 체크
    2. 연달아서 3개자리가 6이라면 true 리턴
    3. 연속해서 6이 나오지않거나 모든 자리를 체크했는데 연속한 6이 3개이상 나오지않는다면 false
 */
private fun isTerminateNum(num : Int) : Boolean {
    var count = 0
    var value = num

    while(value!=0){
        if(value%10 == 6){
            count++
        }else if(count !=0 && count < 3){ // 이번 자리의 수가 6이 아닌데, 6이 3개이상 나온적이 없다
            count=0 // 연속된 수 체크를 0으로 변경 => 반례 찾은것 : 66626
        }
        if(count>=3){
            return true
        }
        value/=10
    }
    return count>=3
}
private fun isTerminateNum2(num : Int) : Boolean {
    return (num-1).toString().contains("666")
}

/*
    종말의수 : 6이 연속으로 3개 들어가는 수
    예) 666 1666 2666 3666
    n번째로 작은 종말의 수를 구하라

    666
    1666, 2666, 3666 ... 9666
    10666 11666 12666 ... 16660 16666 ...19666
    ...
    66600 61666... 66666
 */