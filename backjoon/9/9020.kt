// sliver 1
// 9-6

import java.io.*
fun main(args : Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    for(i in 0 until size){
        bw.write("${goldBahu(br.readLine().toInt())}")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun goldBahu(value : Int) : String {
    var sosu = 0
    for(i in value/2 downTo 2){
        if(isSosu(i) && isSosu(value - i)){
            sosu = i
            break
        }
    }
    // return "$sosu + ${value - sosu} = value, ${isSosu(value - sosu)}\n"
    return "$sosu ${value - sosu}\n"
}

private fun isSosu(value : Int) : Boolean {
    if(value == 1 || value == 0)
        return false
    var idx = 2
    while(true){
        if(idx * idx > value)
            break
        if(value % idx == 0)
            return false
        idx++
    }
    return true
}

/*

    골드바흐
    2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다( 4를 제외하곤 홀수 2개 가 될것 )
    -> 이걸 골드바흐 파티션 이라 명명
    가능한 골드바흐 파티션이 여러개라면 두 소수의 차이가 가장 적은 것을 출력

    4 -> 2 + 2
    6 -> 3 + 3
    12 -> 3 + 9

    52 ->
     1 + 51
     3 + 49
     5 + 47
     

     26
     23 + 29

    72 -> 
    36
    31 + 41

    88 -> 
    44
    41 43

    108 -> 
    54
    47 -> 47

    204 -> 

 */