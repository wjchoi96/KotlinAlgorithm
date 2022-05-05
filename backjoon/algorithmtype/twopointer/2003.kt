/*
    https://mhwan.tistory.com/57 에서 제시된 투포인터 문제
    sliver 3

    수들의 합2

    N개의 수로 된 수열이 존재
    해당 수열의 i번째수 부터 j번째 수까지의 합 이 M이 되는 경우의 수를 구하시오

    1<= n <= 10,000
    1<= m <= 300,000,000

    A[x] 는 30,000 을 넘지않는다

    0.5초
    
*/
/*
    투포인터 개념이 처음이라 개념을 학습한다 생각하고 정답 코드와 해설을 보며 풀이

    start = 0
    end = 0 
    으로 시작하여 
    end 와 start 를 늘려가며 (start<=end)인 구간을 탐색

    종료조건
    start == n && end == n
    start 와 end가 모두 n일때까지 모든 경우를 탐색

    조건
    - 합이 m이 되는 구간의 개수를 묻는것
    - 구간 합을 sum으로 계싼, sum == m일때 result++

    start 증가 조건
    - 문제의 조건이 성립하는 경우를 포함(sum >= m)
    - end를 더이상 증가시킬 수 없을때
    -> start를 증가시킬것이니, 현재 start가 가리키는곳을 sum에서 빼고, start를 증가

    end 증가 조건
    - 문제의 조건이 성립하지 않을때 ( num < m )
    -> end를 증가시킬것이니, 현재 end가 있는 범위를 sum에 더해주고, end를 증가

*/

fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val (n, m) = br.readLine().split(' ').map{it.toInt()}
    val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

    var (start, end) = 0 to 0
    var sum = 0
    var result = 0

    while(true){
        print("[$start to $end] : $sum\n")
        if(start<n && (sum >=m || end == n)){ // start 증가조건
            if(sum == m){
                result++
            }
            sum -= arr[start++] // sum에서 start값을 빼주고, start++
        }
        else if(end < n){ // end 증가조건
            sum += arr[end++] 
        }
        if(start == n && end == n){ // 종료조건
            break
        }
    }
    bw.write("$result\n")

    bw.flush()
    bw.close()
    br.close()
}
