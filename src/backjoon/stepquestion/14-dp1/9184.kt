package src.backjoon.stepquestion.`14-dp1`
//sliver 2
// 15-2
import java.io.*
import java.util.StringTokenizer
private var wArr : Array<Array<Array<Int>>> = Array(101){Array(101){Array(101){-1}}}
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    var st : StringTokenizer
    while(true){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        if(a==-1 && b==-1 && c==-1){
            break
        }
        bw.write("w($a, $b, $c) = ${w1(a,b,c)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
// 3차원 배열을 범위를 21로 해도 가능한듯

// 동적 프로그램식의 구현
// -50 ≤ a, b, c ≤ 50
// size 가 100인 3차원배열?
private fun w1(a : Int, b : Int, c : Int) : Int {
    if( a<=0 || b<=0 || c<=0 ){
        wArr[a+50][b+50][c+50] = 1
        return wArr[a+50][b+50][c+50] // 이게 누락되어있어서
        // 21, 0, 0 을 넣었을때 여기에 걸려서 리턴되어야했는데, 아래 >20 블록도 실행되어버려서 다른값이 나왔다
    }
    if ( a>20 || b>20 || c>20 ){
        wArr[a+50][b+50][c+50] = w1(20, 20, 20)
    }
    if(wArr[a+50][b+50][c+50] == -1){
        if( a<b && b<c ){
            wArr[a+50][b+50][c+50] = w1(a, b, c-1) + w1(a, b-1, c-1) - w1(a, b-1, c)
        }else{
            wArr[a+50][b+50][c+50] = w1(a-1, b, c) + w1(a-1, b-1, c) + w1(a-1, b, c-1) - w1(a-1, b-1, c-1)
        }
    }
    return wArr[a+50][b+50][c+50]
}

// 기본 w 재귀함수
// private fun w1(a : Int, b : Int, c : Int) : Int {
//     if( a<=0 || b<=0 || c<=0 ){
//         return 1
//     }
//     if ( a>20 || b>20 || c>20 ){
//         return w1(20, 20, 20)
//     }
//     if( a<b && b<c ){
//         return w1(a,b,c-1) + w1(a,b-1,c-1) - w1(a-1,b-1,c-1)
//     }else{
//         return w1(a-1,b,c) + w1(a-1,b-1,c) + w1(a-1, b, c-1) - w1(a-1,b-1,c-1)
//     }
// }

/*
    1,1,1 일때
    w1(a-1,b,c) + w1(a-1,b-1,c) - w1(a-1,b-1,c-1)
    
    
    ==> w1(a-1,b,c)
    0,1,1 => 1
    ==> w1(a-1,b-1,c)
    0,0,1 => 1
    ==>  w1(a-1,b-1,c-1)
    0,0,0 => 1
    1+1-1 = 1
*/