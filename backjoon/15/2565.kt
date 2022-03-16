//15-13
//gold 5

// 참조 : https://st-lab.tistory.com/138

import java.io.*
import java.util.StringTokenizer
lateinit var lineArr : Array<Array<Int>>
lateinit var lineDp : Array<Int>
private val A = 0
private val B = 1
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))    
    var st : StringTokenizer
    
    val size = br.readLine().toInt()
    lineArr = Array(size+1){Array(2){-1}}
    lineDp = Array(size+1){-1}

    for(i in 1 until size +1){
        st = StringTokenizer(br.readLine())
        lineArr[i][A] = st.nextToken().toInt()
        lineArr[i][B] = st.nextToken().toInt()
    }
    // 전봇대 하나를 기준으로 정렬
    lineArr.sortBy { it[A] }
    // for(i in 1 until size + 1){
    //     bw.write("[$i] : A(${lineArr[i][A]}) - B(${lineArr[i][B]})\n")
    // }
    bw.write("${size - getLineDp(size)}\n")

    // bw.write("${getLineDp(size)}\n")
    // for(i in 1 until size+1){
    //     bw.write("dp[$i] : ${lineDp[i]}\n")
    // }
    
    br.close()
    bw.flush()
    bw.close()
}

/*
    전깃줄을 없앤다

    철거해야할 전선의 개수 = 전체 전선 개수 - 설치 가능 개수

    2차원 배열에 전선을 저장

    n번째 전선부터 접근해서
    더 큰 idx의 전선이 더 작은 idx전선의 값보다 작으면 안된다
*/

private fun getLineDp(n : Int) : Int{
    if(lineDp[n] >= 0){
        return lineDp[n]
    }
    lineDp[n] = 1
    for(i in n-1 downTo 1){
        //lineArr[n][A] >= lineArr[i][A] 는 제거해도된다 -> 정렬했기때문에
        if(lineArr[n][B] >= lineArr[i][B]){
            lineDp[n] = Math.max(getLineDp(i) + 1, lineDp[n])
        }
    }
    return lineDp[n]
}

/*
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6

정렬후
1 8
2 2
3 9
4 1
6 4
7 6
9 7
10 10


*/

