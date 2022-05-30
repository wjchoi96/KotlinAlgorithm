// sliver 3
// 16-1
// greedy 

/*
    동전은 n종류  1<= n <=10
    가치의 합을 k 로 만단다 1<= k <= 100000000

    동전 개수의 최소값

    동전배열 coinArr(n)

    오름차순 으로 주어진다

    1. k 보다 큰 동전은 제외
    2. k 보다 작은 동전중에 제일 큰 동전을 구한다
    -> sum += k/coin, k %= coin

    반복 -> k = 0 이될때까지

    => 알고리즘 최적화
    0번째가 1 이니까 어차피 무조건 나눠 떨어진다
    list 를 역순으로 조회하며 쪼갤수 있을때 쪼갠다
 */

import java.io.*
import java.util.StringTokenizer
lateinit var coinArr : Array<Int>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    var k = st.nextToken().toInt()
    var sum = 0
    coinArr = Array(size){-1}

    for(i in 0 until size){
        coinArr[i] = br.readLine().toInt()
    }
    
    for(i in size-1 downTo 0){ // 큰값부터 비교해야하니 역순으로 loop
        if(coinArr[i] <= k){
            sum += k/coinArr[i]
            k = k%coinArr[i]    
        }
    }
    bw.write("$sum\n")

    br.close()
    bw.flush()
    bw.close()
}

// private fun greedyCoin1(){
//     while(k != 0){
//         var idx = -1
//         for(i in size-1 downTo 0){ // 큰값부터 비교해야하니 역순으로 loop
//             if(coinArr[i] <= k){
//                 idx = i
//                 break
//             }
//         }
//         if(idx != -1){
//             sum += k/coinArr[idx]
//             k = k%coinArr[idx]
//         }
//     }
// }