//sliver2
/*
    바킹독님 백트래킹 강의 도중 제시된 연습문제

    부분수열의 합

    N개의 정수로 이루어진 수열이 존재
    크기가 양수인 부분수열 중에서, 해당 수열의 원소의 합이 S가 되는 경우의 수를 구하라

    정수의 개수N, 정수 S
    1 <= N <= 20
    |S| <= 1,000,000

    N개의 정수 부여 (절대값이 100,000을 넘지 않는다)

    1. 크기가N인 수열에서 1~N개의 수를 뽑아서 그 합을 구한다
    2. 합이S가 되면 count++
    3. 중복된 수를 뽑지않는다
    4. 동일한 경우의 수가 나오지않게 오름차순으로 수를 뽑는다 => 원본 수열을 정렬해놓은 상태로 수행해야할듯
    => 같은수를 여러번 뽑을 수 없다

    5. 1~N개의 수를 뽑는건데, 종료 조건을 어떻게 처리할지 생각해보자
    - N개의 수열을 뽑는다 생각하고, 뽑는 진행 과정에서 S가 완성이 되는지 체크?
    - 수를 뽑는데, 뽑은 수들의 합이 S가 되거나, S가 완성되지않고 N개의 수열을 다 뽑는다면 종료
*/
/*
    제출
    1. 시간초과(8%)
    - 오름차순 정렬 + 한번 추가된 idx는 다시 탐색하지 않도록 수정

    2. 시간초과(8%)
    - 불필요한 가지를 빠르게 제거
    - 같은 숫자의 조합이 안되도록 오름차순으로만 수열 조합이 가능하게 설정

    3. 틀렸습니다(70~80?)
    - sum이 s와 동일하면 return을 시켰었는데, return 제거
    - 현재 수열에 수가 더 추가가 되었을때도 조건을 만족시킬 수 있기 때문
    반례
    5 0
    0 0 0 0 0
    => 31

    4. 성공
*/

/*
    ========== 최적화 ========== 
    1. 같은수의 조합은 나와선 안된다 => 오름차순 정렬을 통해 해결
    => for 문을 돌 필요가 없다
    => visit 배열이 필요가 없다
    => res 배열이 필요가 없다 : sum 만 있으면 된다

    ========= 바킹독님 해설 ===========
    2. 현재수를 선택하겠다, 선택하지 않겠다로 트리를 그려나가는 방법(dfs2)
    
*/

import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, s) = br.readLine().split(' ').map{it.toInt()}
    val arr = br.readLine().split(' ').map{it.toInt()}.sorted()

    var count = 0
    fun dfs(idx : Int = 0, sum : Int = 0){
        // dfs2와 흡사한 방식인데 다른 코드로 동작할 수 있었던 이유 (idx 체크에서 걸러질수도있었다)
        // 현재 item을 선택한걸 가정해서 sum check를 하기 때문
        if(idx >= n){
            return
        }
        if(sum + arr[idx] == s){
            count++
        }
        dfs(idx + 1, sum + arr[idx]) // 현재 item 을 선택한 가지
        dfs(idx + 1, sum) // 현재 item을 선택하지 않은 가지
    }
    dfs()

    fun dfs2(idx : Int = 0, sum : Int = 0){
        // 무조건 idx가 size까지 와야 완료
        // 현재 item을 선택하겠다, 선택하지 않겠다로 그려지는 트리이기 때문
        if(idx == n){
            if(sum == s){
                count++
            }
            return
        }
        dfs2(idx+1, sum) // 현재 item을 선택하지 않겠다
        dfs2(idx+1, sum + arr[idx]) // 현재 item을 선택하겠다
    }
    // dfs2()
    // if(s == 0){
    //     count--
    // }

    
    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}


// val res : Array<Int> = Array(n){0}
// val visit : Array<Boolean> = Array(n) { false }
// fun dfs(idx : Int = 0, depth : Int = 0, sum : Int = 0){
//     if(depth != 0 && sum == s){
//         count++
//     }
//     if(depth == n || idx >= n){
//         return
//     }
//     for(i in idx until n){
//         if(visit[i] == false){
//             res[depth] = arr[i]
//             visit[i] = true
//             dfs(i + 1, depth + 1, sum + res[depth])
//             visit[i] = false
//         }
//     }
// }