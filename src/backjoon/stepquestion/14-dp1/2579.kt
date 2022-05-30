//sliver3
// 15-7

/*
    1. 한번에 한계단 or 두계단 오를수 있다
    2. 연속된 세개의 계단을 모두 밟아선 안된다( 시작점은 포함 x )
    3. 마지막 도착 계단은 반드시 밟아야한다

    최대 점수 구하기
    stairArr[size] : 각 계단의 점수를 저장할 배열
    stairDpArr[size] : idx 계단까지의 최대값을 저장할 배열

    연속된 세개의 계단을 밟는건 어떻게 체크하지?

    맨 마지막 계단에서 시작
    n번 계단 : Math.max(self + n-1, self + n-2)
    n-1번계단: Mtah.max(self + n-1, self + n-2)
    ..
    3번계단 : n-2 + self
    => 3번계단은 2번계단을 밟아서는 안된다, 2번계단은 최대값 계산을 위해 무조건 0,1,2 계단을 합친 결과값이기 때문
    2번계단 : n-1 + self
    1번계단 : 0 + self
    0번계단 : self

    한번에 몇계단 뛰었는지 전달받아야하나?
    해당 변수를 jump 로 명
    jump == 1 : 이전계단에서 한칸 내려와 현재 계단 : 이럴경우 나는 n-1칸을 절대 밟을 수 없다
    jump == 2 : 이전계단에서 두칸 내려와 현재 계단 : n-1, n-2 선택이 자유롭다

    0번째 칸은 연달아 3칸 조건에 포함되지않으므로
    0,1,2 까지의 칸은 고정
*/
// import java.io.*
// lateinit var stairArr : Array<Int>
// lateinit var stairDpArr : Array<Int>
// lateinit var bw : BufferedWriter
// fun main(args : Array<String>){
//     val br = BufferedReader(InputStreamReader(System.`in`))
//     bw = BufferedWriter(OutputStreamWriter(System.out))    

//     val size = br.readLine().toInt()
//     stairArr = Array(size) {-1}
//     stairDpArr = Array(size) {-1}

//     for(i in 0 until size){
//         stairArr[i] = br.readLine().toInt()
//     }
//     initStairDp(size)

//     // val res = getStairJump(size-1, 0)
//     val res = getStairJump(size-1)
//     bw.write("$res\n")

//     bw.write("\n")
//     for(i in stairDpArr){
//         bw.write("$i\n")
//     }

//     br.close()
//     bw.flush()
//     bw.close()
// }


// // 0번째 칸은 연달아 3칸 조건에 포함되지않으므로
// // 0,1,2 까지의 칸은 고정
// private fun initStairDp(size : Int){
//     stairDpArr[0] = stairArr[0]
//     stairDpArr[1] = stairDpArr[0] + stairArr[1]
//     // size가 1이 입력될수도 있기 때문
//     if(size >= 2){
//         stairDpArr[2] = stairDpArr[1] + stairArr[2]
//     }
// }
// private fun getStairJump(idx : Int) : Int{
//     if(stairDpArr[idx] != -1){
//         bw.write("## dp stairDpArr[$idx] : ${stairDpArr[idx]}\n")
//         return stairDpArr[idx]
//     }
//     stairDpArr[idx] = Math.max(getStairJump(idx-2), getStairJump(idx-3) + stairArr[idx-1]) + stairArr[idx]
//     bw.write("   fu stairDpArr[$idx] : ${stairDpArr[idx]}\n")
//     return stairDpArr[idx]
// }
/*
    참고 : https://st-lab.tistory.com/132
    dp[n-1]을 구할때, 해당 값이 그 이전값인 n-2칸을 밟은 상태인지 아닌지 알 수 없다
    ad[n-1]값을 구하는건 dp[n-3] + arr[n-1] 을 통해 구해줘야만한다
    => 연달아 3개의 계단을 밟아선 안되기때문이다
*/
// private fun getStairJump(idx : Int, jump : Int) : Int{
//     if(stairDpArr[idx] != -1){
//         bw.write("## dp stairDpArr[$idx] : ${stairDpArr[idx]}\n")
//         return stairDpArr[idx]
//     }
//     // 이전계단에서 한칸 내려와 현재 계단 : 이럴경우 나는 n-1칸을 절대 밟을 수 없다
//     if(jump == 1){
//         stairDpArr[idx] = getStairJump(idx-2, 2) + stairArr[idx]
//     }
//     // jump 가 2 or 0 ( 0은 첫실행 )
//     // n-1칸, n-2칸 선택이 자유롭다
//     else {
//         stairDpArr[idx] = Math.max(getStairJump(idx-1, 1), getStairJump(idx-2, 2)) + stairArr[idx]
//     }
//     bw.write("   fu stairDpArr[$idx] : ${stairDpArr[idx]}\n")
//     return stairDpArr[idx]
// }

/*
    10, 20, 15, 25, 10, 20

    stairDpArr[1] : 30
    stairDpArr[3] : 70
    stairDpArr[5] : 90
    90

    5번칸 -> 3번칸 -> 1번칸 -> 0번칸 이렇게 간거같은데..

    1번칸 30
    3번칸 45 가 되어야하는데, 70이네
    2번칸도 합쳐졌다

    10 + 20 + 25 + 20 : 75 답인데
    왜 90이 나온거지?
    
    15가 어디서 튀어나온거지..

    10, 20, 15, 25 로 생각해보자
    0 : 10
    1 : 10 + 20 => 30

    0,1,2,3

    0 : 0
    1 : 0 + 1
    2 : 0 + 2 / 0 + 1 + 2
*/

// 제출용
import java.io.*
// size 설정부터 약간 틀어졌었네..
lateinit var stairArr : Array<Int>
lateinit var stairDpArr : Array<Int>
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))    

    val size = br.readLine().toInt()
    stairArr = Array(size+1) {0}
    stairDpArr = Array(size+1) {-1}

    for(i in 1 until size+1){
        stairArr[i] = br.readLine().toInt()
    }
    initStairDp(size)

    val res = getStairJump(size)
    bw.write("$res\n")

    br.close()
    bw.flush()
    bw.close()
}


// 0번째 칸은 연달아 3칸 조건에 포함되지않으므로
// 0,1,2 까지의 칸은 고정
private fun initStairDp(size : Int){
    stairDpArr[0] = 0
    stairDpArr[1] = stairArr[1]
    //size 가 1이 입력될수도 있기때문
    if(size >= 2){
        stairDpArr[2] = stairDpArr[1] + stairArr[2]
    }
}
private fun getStairJump(idx : Int) : Int{
    if(stairDpArr[idx] != -1){
        return stairDpArr[idx]
    }
    stairDpArr[idx] = Math.max(getStairJump(idx-2), getStairJump(idx-3) + stairArr[idx-1]) + stairArr[idx]
    return stairDpArr[idx]
}
/*
    size 를 n인 배열을 생성하여
    각 줄을 idx-1로 접근하려했는데
    idx-3 을 오류없이 띄워야하기때문에

    size 를 n+1로 설정하고
    각 줄을 idx로 접근해야했다
*/