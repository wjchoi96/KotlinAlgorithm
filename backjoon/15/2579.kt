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
import java.io.*
lateinit var stairArr : Array<Int>
lateinit var stairDpArr : Array<Int>
lateinit var bw : BufferedWriter
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    bw = BufferedWriter(OutputStreamWriter(System.out))    

    val size = br.readLine().toInt()
    stairArr = Array(size) {-1}
    stairDpArr = Array(size) {-1}

    for(i in 0 until size){
        stairArr[i] = br.readLine().toInt()
    }
    initStairDp()

    val res = getStairJump(size-1, 0)
    bw.write("$res\n")

    br.close()
    bw.flush()
    bw.close()
}


// 0번째 칸은 연달아 3칸 조건에 포함되지않으므로
// 0,1,2 까지의 칸은 고정
private fun initStairDp(){
    stairDpArr[0] = stairArr[0]
    stairDpArr[1] = stairDpArr[0] + stairArr[1]
    stairDpArr[2] = stairDpArr[1] + stairArr[2]
}
private fun getStairJump(idx : Int, jump : Int) : Int{
    if(stairDpArr[idx] != -1){
        bw.write("stairDpArr[$idx] : ${stairDpArr[idx]}\n")
        return stairDpArr[idx]
    }
    // 이전계단에서 한칸 내려와 현재 계단 : 이럴경우 나는 n-1칸을 절대 밟을 수 없다
    if(jump == 1){
        stairDpArr[idx] = getStairJump(idx-2, 2) + stairArr[idx]
    }
    // jump 가 2 or 0 ( 0은 첫실행 )
    // n-1칸, n-2칸 선택이 자유롭다
    else {
        stairDpArr[idx] = Math.max(
            getStairJump(idx-1, 1) + stairArr[idx],
            getStairJump(idx-2, 2) + stairArr[idx]
            )
    }
    bw.write("stairDpArr[$idx] : ${stairDpArr[idx]}\n")
    return stairDpArr[idx]
}

/*
    10, 20, 15, 25, 10, 20

stairDpArr[2] : 45
stairDpArr[4] : 55
stairDpArr[2] : 45
stairDpArr[1] : 30
stairDpArr[3] : 70
stairDpArr[5] : 90
90

    5번칸 -> 3번칸 -> 1번칸 -> 0번칸 이렇게 간거같은데..

    10 + 20 + 25 + 20 : 75 답인데
    왜 90이 나온거지?
    
*/