//sliver 1
// 15-5
import java.io.*
import java.util.StringTokenizer
private val R = 0
private val G = 1
private val B = 2
lateinit var rgbCostArr : Array<Array<Int>>
lateinit var rgbHouseArr : Array<Array<Int>>
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val n = br.readLine().toInt()
    rgbCostArr = Array(n){ Array(3){-1} }
    rgbHouseArr = Array(n){ Array(3){-1} }

    var st : StringTokenizer
    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        rgbCostArr[i][R] = st.nextToken().toInt()
        rgbCostArr[i][G] = st.nextToken().toInt()
        rgbCostArr[i][B] = st.nextToken().toInt()
    }

    var min = Int.MAX_VALUE
    for(i in 0 until 3){
        val res = getRgbCost(n-1, i)
        if(min > res){
            min = res
        }
    }
    bw.write("$min\n")

    
    br.close()
    bw.flush()
    bw.close()
}

/*
    rgb 거리

    1번집 != 2번집
    n번집 != n-1번집
    i(2 <= i <= n-1)번집은 i-1, i+1과 달라야한다

    n번집까지 색을 칠하는데 드는 비용의 최소값

    // 참조 : https://st-lab.tistory.com/128
    // 참조 강도 : 중

    arr[집번호-1][0~2 : 색종류] = cost
                    r        g         b 순
    1번집 : arr[0][0] arr[0][1] arr[0][2]
    2번집 : arr[1][0] arr[1][1] arr[1][2]
    
    1번집 : arr[0][R,G,B] 중 최소값
    2번집 : arr[0] 의 값과 arr[1]의 값 경우의 수 중 최소값
    3번집 : arr[2][R,G,B] 중 최소값

    2번집 r : arr[0][G] + arr[1][B] / arr[0][G] + arr[1][B] 중 최소값
    3번집 r : arr[1][B] + arr[0][G] / arr[1][G] + arr[0][B] 중 최소값
    ...
    n번집 r : arr[n-1][B] + arr[n-2][G] / arr[n-1][B] + arr[n-2][G]
    n번집 b : arr[n-1][R] + arr[n-2][G] / arr[n-1][G] + arr[n-2][B]
*/

fun getRgbCost(n : Int, color : Int) : Int{
    val idx = n
    if(idx==0){
        return rgbCostArr[idx][color]
    }
    if(rgbHouseArr[idx][color] == -1){
        when(color){
            R -> {
                rgbHouseArr[idx][R] = Math.min(getRgbCost(idx-1, G), getRgbCost(idx-1, B)) + rgbCostArr[idx][R]
            }
            G -> {
                rgbHouseArr[idx][G] = Math.min(getRgbCost(idx-1, R), getRgbCost(idx-1, B)) + rgbCostArr[idx][G]
            }
            //B
            else -> {
                rgbHouseArr[idx][B] = Math.min(getRgbCost(idx-1, R), getRgbCost(idx-1, G)) + rgbCostArr[idx][B]
            }
        }
    }
    return rgbHouseArr[idx][color]
}
