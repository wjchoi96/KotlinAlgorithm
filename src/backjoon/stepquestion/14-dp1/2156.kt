package src.backjoon.stepquestion.`14-dp1`
// 15-10
// sliver 1

/*
    포도주 잔이 일렬로 놓아져있다
    1. 잔을 선택하면 원샷해야한다. 원샷 후 원래 위치에 놓아야한다
    2. 연속으로 놓여있는 3잔을 마실 수 없다

    될 수 있는 많은 양의 포도주를 마시고싶다
    각 잔에 들어있는 포도주의 양은 다르다


    size 가 n+1 인 포도주 잔 배열
    size 가 n+1 인 dp 배열

    1<= n <= 10000
    포도주의 양은 1000 이하의 양의정수

    반례1 : 6 1000 1000 1 1 1000 1000 (두 잔 연속 안 마시는 경우) => 4000
6
1000
1000
1
1
1000
1000
    번례2 : 10 977 200 517 851 23 662 880 815 26 214 (통상적인 경우) =>4254
10
977
200
517
851
23
662
880
815
26
214

*/
import java.io.*
lateinit var wineArr : Array<Int> 
lateinit var wineDpArr : Array<Int>
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))    

    val size = br.readLine().toInt()
    wineArr = Array(size+1){0}
    wineDpArr = Array(size+1){-1}

    for(i in 1 until size+1){
        wineArr[i] = br.readLine().toInt()
    }
    initWindDpArr(size)
    var max : Int = Int.MIN_VALUE
    for(i in 1 until size+1){
        max = Math.max(max, getWineDp(i))
    }
    bw.write("$max\n")

    br.close()
    bw.flush()
    bw.close()
}

private fun initWindDpArr(n : Int){
    wineDpArr[0] = wineArr[0]
    wineDpArr[1] = wineArr[1]
    if(n > 1){
        wineDpArr[2] = wineDpArr[1] + wineArr[2]
    }
}

private fun getWineDp(n : Int) : Int{
    if(wineDpArr[n] >= 0){
        print("dp[$n] : ${wineDpArr[n]}\n")
        return wineDpArr[n]
    }
    val value1 = getWineDp(n-2) + wineArr[n]
    val value2 = getWineDp(n-3) + wineArr[n-1] + wineArr[n]
    val value3 = getWineDp(n-1)
    wineDpArr[n] = getMaxValue(value1, value2, value3)
    print("dp[$n] : ${wineDpArr[n]}\n")
    return wineDpArr[n]
}

private fun getMaxValue(a : Int, b : Int, c : Int) : Int {
    var max =  Math.max(a, b)
    max = Math.max(max, c)
    return max
}

/*

    1번째 잔이면 다음잔은 무조건 2
    

    6, 10, 13, 9, 8, 1


    1 : 6
    최대 6

    2 : 10
    최대 6 + 10

    3 : 13
    최대 30 : 13

    4. 9

    n : max(dp[n-2], dp[n-3]+arr[n-1]) + self

    n 번째 잔을 꼭 마실 필요가 있나?

    n번째 잔 
    1. dp[n-1] + self
    2. dp[n-2] + self
    3. dp[n-1]

    1000
    1000
    1
    dp[3] => 1001 이 나온다, 2000이 올바른 답인데
 */