//sliver1
//15-6

/*
    7
    3   8
    8   1   0
    2   7   4   4
    4   5   2   6   5

    맨 위7 부터 시작해서 아래에 있는 수 중 하나를 선택
    여태 수 합의 최대값이 되도록 경로 구하기

    2차원 배열에 저장
    triArr : [n][n]
    dpArr : 동일

    n = 0
    [0][0] = 7

    n = 1
    [1][0] = 7 + self 
    [1][1] = 7 + self

    n = 2
    [2][0] = [1][0] + self
    [2][1] = Math.max([1][0] + self, [1][1] + self)
    [2][2] = [1][1] + self

    n = 3
    [3][0] = [2][0] + self
    [3][1] = Math.max([2][0] + self, [2][1] + self)
    [3][2] = Math.max([2][1] + self, [2][2] + self)
    [3][3] = [2][2] + self


    [n][n] :
    if (n == 0){
        [n][n] = [n-1][n] + self
    }else if(n == n){
        [n][n] = [n-1][n-1] + self
    }else{
        [n][n] = Math.max([n-1][n-1], [n-1][n])
    }
    
*/


// import java.io.*
// import java.util.StringTokenizer
// lateinit var triArr : Array<Array<Int>>
// lateinit var triDpArr : Array<Array<Int>>
// lateinit var bw : BufferedWriter
// fun main(args : Array<String>){
//     val br = BufferedReader(InputStreamReader(System.`in`))
//     bw = BufferedWriter(OutputStreamWriter(System.out))    
//     var st : StringTokenizer

//     val n = br.readLine().toInt()
//     triArr = Array(n+1){Array(n){-1}}
//     triDpArr = Array(n+1){Array(n){-1}}

//     for(i in 0 until n){
//         st = StringTokenizer(br.readLine())
//         for(j in 0 until i+1){
//             triArr[i][j] = st.nextToken().toInt()
//         }
//     }

//     var max = Int.MIN_VALUE
//     for(i in 0 until n){
//         val res = getTriValue(n-1, i, n)
//         max = Math.max(res, max)
//         bw.write("$res\n")
//     }
//     bw.write("===> $max\n")

//     br.close()
//     bw.flush()
//     bw.close()
// }

// private fun getTriValue(col : Int, n : Int, size : Int) : Int{
//     if(col == 0 && n == 0){
//         bw.write("[$col, $n] => ${triArr[0][0]}\n")
//         return triArr[0][0]
//     }else if(col == 1){
//         bw.write("[$col, $n] => ${triArr[col][n]}\n")
//         return triArr[0][0] + triArr[col][n]
//     }
    
//     if(triDpArr[col][n] >= 0){
//         bw.write("[$col, $n] => ${triArr[col][n]}\n")
//         return triDpArr[col][n]
//     }
//     // dp 랑 arr 을 혼동해서 잘못짠거같은데
//     if(n == 0){
//         triDpArr[col][n] = getTriValue(col-1, n, size) + triArr[col][n]
//     }else if(n == size){
//         triDpArr[col][n] = getTriValue(col-1, n-1, size) + triArr[col][n]
//     }else{
//         triDpArr[col][n] = Math.max(getTriValue(col-1, n-1, size) + triArr[col][n], getTriValue(col-1, n, size) + triArr[col][n])
//     }
//     bw.write("[$col, $n] => ${triArr[col][n]}\n")
//     return triDpArr[col][n]
// }

// 제출용

import java.io.*
import java.util.StringTokenizer
lateinit var triArr : Array<Array<Int>>
lateinit var triDpArr : Array<Array<Int>>
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))    
    var st : StringTokenizer

    val n = br.readLine().toInt()
    triArr = Array(n+1){Array(n){-1}}
    triDpArr = Array(n+1){Array(n){-1}}

    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        for(j in 0 until i+1){
            triArr[i][j] = st.nextToken().toInt()
        }
    }

    var max = Int.MIN_VALUE
    for(i in 0 until n){
        max = Math.max(getTriValue(n-1, i, n), max)
    }
    bw.write("$max\n")

    br.close()
    bw.flush()
    bw.close()
}

private fun getTriValue(col : Int, n : Int, size : Int) : Int{
    if(col == 0 && n == 0){
        return triArr[0][0]
    }else if(col == 1){
        return triArr[0][0] + triArr[col][n]
    }
    
    if(triDpArr[col][n] != -1){
        return triDpArr[col][n]
    }
    if(n == 0){
        triDpArr[col][n] = getTriValue(col-1, n, size) + triArr[col][n]
    }else if(n == size-1){
        triDpArr[col][n] = getTriValue(col-1, n-1, size) + triArr[col][n]
    }else{
        triDpArr[col][n] = Math.max(getTriValue(col-1, n-1, size) + triArr[col][n], getTriValue(col-1, n, size) + triArr[col][n])
    }
    return triDpArr[col][n]
}