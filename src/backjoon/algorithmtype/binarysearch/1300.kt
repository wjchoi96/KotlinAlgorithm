/*
    백준 1300
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/1300

    gold2

    크기가 N*N인 배열 A
    A[i][j] = i*j

    이 수를 일차원 배열 B에 넣으면 B의 크기는 N*N이 됨
    B를 오름차순 정렬했을때 B[k]를 구하라

    배열 A,B의 인덱스는 1부터 시작

    1 <= N <= 10^5
    1 <= k <= min(10^9, N^2)
*/
/*
    1. 배열 B를 구함
        - A를 실제로 구현하는건 O(n^2)이 됨
    2. 배열 B를 이분탐색?
*/
/*
    접근방식을 못찾겠어서 블로그 참고
    https://st-lab.tistory.com/281

    B[k] = x
    - x보다 작거나 같은 원소의 수가 최소 k개
    - B가 정렬되어 있기 때문

    B[k] = 6 일때 k는?
    6보다 작거나 같은 원소의 개수는 10개이니 B[10] = 6

    B[9]을 구하라한다면
    - 5보다 작거나 같은 수의 합을 구함 => 8
    - 6보다 작거나 같은 수의 합을 구함 => 10
    5와 6의 사이이니 답은 6

    n보다 작거나 같은 수의 합을 구하는 방법
    1, 2, 3, 4
    2, 4, 6, 8
    3, 6, 9, 12
    4, 8, 12, 16
    처럼 구구단의 형식

    거의 블로그를 보고 이해하는 수준으로 진행
*/
/*
    제출
    1. 틀렸습니다(0%)
    - long 타입 변수로 적용

    2. 틀렸습니다(8%)
    - upper bound로 되어있던걸 lower bound로 적용

    3. 성공
    - 풀이에 대한 이해는 가나, 접근 방식을 떠올리기 쉽지 않을것 같음
*/
fun main() {
    Solution1300().solve()
}

class Solution1300 {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toLong()
        val k = br.readLine().toInt()

        var start: Long = 1
        var end: Long = n*n
        while(start < end) {
            val mid = (start + end) / 2

            var count: Long = 0
            for(i in 1..n) {
                count += Math.min(mid/i, n) // 각 단별 mid보다 작은 수의 합을 구함. n을 초과하는 숫자는 나오지 않게 설정
            }

            //찾고자 하는 값과 같거나 큰 수가 있는 첫번째 인덱스를 찾는 lower bound사용
            when {
                count < k -> start = mid + 1 // k보다 적은 개수라면, 개수를 늘리기 위해 mid를 올림.
                else -> end = mid // k보다 크거나 같다면, 개수를 줄이기 위해 mid를 낮춤
            }
        }
        bw.write("$start\n")

        bw.flush()
        bw.close()
        br.close()
    }
}