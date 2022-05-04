/*
    카카오 2020 인턴 기출문제 1번
    level1

    키패트 누르기

    1 2 3
    4 5 6
    7 8 9
    * 0 #

    123 3이하
    456 6이하
    789 9이하

    왼손은 *
    오른손은 # 
    위치에서 시작

    엄지손가락은 상하좌우 4가지 방향으로 이동 가능
    키패드 이동 한칸은 거리 1

    왼쪽열의 1,4,7 는 왼손
    오른열의 3,6,9 는 오른손

    가운데 2580은 현재 키패드에서 더 가까운 손을 사용
    - 두 손의 위치가 같다면 오른손잡이는 오른, 왼손잡이는 왼손을 사용

    숫자목록 numbers
    어느손잡이인지 알려주는 hand

    각 번호를 누른 손이 엄지인지 왼손인지 리턴

    3*4 배열 
    거리구하기는 |x1-x2| + |y1-y2|
    map에 number, pair(x,y) 를 저장?
    현재 오른손, 왼손 위치 좌표

    번호가 떨궈진다
    1,4,7 -> 왼손 이동
    3,6,9 -> 오른손 이동

    2,5,8,0
    
    현재손이있는 좌표, 눌러야 할 좌표간의 거리를 구한다
    오른손 왼손 다 구해서 비교 후 작업

    각 번호를 누르는 이력은 stringBuilder 를 통해 저장해놓는다

    번호가 주어졌을때 해당 좌표를 구하는 방법 -> map에 저장해놓자
*/

fun main(args : Array<String>){
    val input = intArrayOf(
        1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5
        // 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 0
    )
    val res = Kakao001().solution(input, "right")
    print("$res\n")
}
private class Kakao001 {
    private val star = -1
    private val shap = -2
    private lateinit var keypad : Array<Array<Int>> 
    private val left = "left"
    private var lPoint = Pair(3,0)
    private var rPoint = Pair(3,2)
    private val right = "right"
    private val keyMap = HashMap<Int, Pair<Int, Int>>()
    fun solution(numbers: IntArray, hand: String): String {
        val sb = StringBuilder()
        initKeypad()

        for(i in 0 until numbers.size){
            val num = numbers[i]
            val cor = keyMap.get(num)!!
            print("number : $num, cor : $cor ")
            if(num == 1 || num == 4 || num == 7){
                sb.append(pressUseLeft(cor))
                continue
            }else if(num == 3 || num == 6 || num == 9){
                sb.append(pressUseRight(cor))
                continue
            }
            
            val ld = getDistance(lPoint, cor)
            val rd = getDistance(rPoint, cor)
            if(ld<rd){
                sb.append(pressUseLeft(cor))
            }else if(ld>rd){
                sb.append(pressUseRight(cor))
            }else{
                if(hand == left){
                    sb.append(pressUseLeft(cor))
                }else{
                    sb.append(pressUseRight(cor))
                }
            }
        }
        
        return sb.toString()
    }
    private fun initKeypad(){
        keypad = Array(4) { Array(3){0} }
        var i = 0
        for(x in 0 until 3){
            for(y in 0 until 3){
                keypad[x][y] = ++i
                keyMap[i] = x to y
            }
        }
        keypad[3][0] = star
        keyMap[star] = 3 to 0
        keypad[3][1] = 0
        keyMap[0] = 3 to 1
        keypad[3][2] = shap
        keyMap[shap] = 3 to 2
    }
    private fun pressUseLeft(to : Pair<Int, Int>) : String {
        lPoint = to.first to to.second
        print("L\n")
        return "L"
    }
    private fun pressUseRight(to : Pair<Int, Int>) : String {
        rPoint = to.first to to.second
        print("R\n")
        return "R"
    }

    private fun getDistance(from: Pair<Int, Int>, to: Pair<Int, Int>) : Int {
        return Math.abs(from.first - to.first) + Math.abs(from.second - to. second)
    }
    
}