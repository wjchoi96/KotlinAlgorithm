// bronze 1
// 8-4


// 높이가 V 인 나무 막대기
// 낮에 A 미터 올라감
// 밤에 B 미터 떨어짐
// 정상에 올라가면 미끄러지지 않는다
// 1 ≤ B < A ≤ V ≤ 1,000,000,000
import java.io.*
import java.util.StringTokenizer
fun main(args: Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    try {
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        bw.write("${getClimbDay2(v,a,b)}\n")
    }catch(e : Exception){
        e.printStackTrace()
    }

    bw.flush()
    bw.close()
    br.close()
}
/*
    높이 / up - down
*/

private fun getClimbDay(height: Int, up: Int, down: Int) : Int {
    var climb = 0 
    var day = 0
    while(true){
        day++
        climb += up
        if(height <= climb)
            break
        climb -= down
    }
    return day
}
/*
6 5 1
실제론 2일째 낮에 올라갔다

1.5

6 4 2
실제론 2일째 낮에 올라갔다
3

6 3 2
실제론 일째 낮에 올라갔다

6 5 1
5 / 4
*/

private fun getClimbDay2(height: Int, up: Int, down: Int) : Int {
    // var res = height.toDouble() / (up - down).toDouble()
    var res = (height - down).toDouble() / (up - down).toDouble()
    // print("res : $res\n")
    // print("res + 0.9 : ${(res+0.9).toInt()}\n")

    // 소수점의 경우는 날이 지난것이니, 다음 날짜로 적용
    if(res.toInt() < res)
        return res.toInt() + 1
    else
        return res.toInt()
}