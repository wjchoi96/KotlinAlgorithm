//gold4
//17-6

/*
    A[i]의 오큰수(NGE(i)) = 오른쪽에 있으면서 A[i]보다 큰 수 중에서 가장 왼쪽에 있는 수
    없다면 -1이 오큰수

    => A[i]의 다음 인자들을 순회하는 도중 가장 먼저 만나는 A[i]보다 큰수
    => 수열의 크기가 1000000인데, 1초 

    A[3, 5, 2, 7]
    NGE(1) = 5
    NGE(2) = 7
    NGE(3) = 7
    NGE(4) = -1

     A[9, 5, 4, 8]
    NGE(1) = -1
    NGE(2) = 8
    NGE(3) = 8
    NGE(4) = -1

    수열을 탐색하면서 현재 원소가 이전의 원소보다 작을 때 까지 stack에 수열의 index를 stack에 추가(push) ?

    3,5,2,7
    0번 push
    3 과 5 비교 => 5가 더 크다
    0번 pop 해주며 arr[0]의 값을 현재 원소(5)로 초기화 해준다 => NGE[0] = 5
    스택에서 꺼낼것이 없으니 현재 idx를 push
    1번 push

    5와 2 비교 => 5가 더 크다
    2번 push

    2와 7 비교 => 7이 더 크다
    2번 pop 해주며 arr[2]의 값을 7로 초기화 => NGE[2] = 7

    원소가 남아있으니 계속 pop 진행

    1번 pop 해주며 arr[1]의 값을 7로 초기화 => NGE[1] = 7

    스택에서 꺼낼것이 없으니 현재 idx를 push

    종료
    스택에 남아있는 idx의 값들은 nge 가 -1인것
    NGE = [5, 7, 7, -1]


    [9, 5, 4, 8]
    0 push

    9 > 5
    1 push

    5 > 4
    2 push

    4 < 8
    2pop
    res[2] = 8
    1pop
    res[1] = 8
    
    3 push
 */

import java.io.*
import java.util.StringTokenizer
import java.util.Stack
private lateinit var resArr : Array<Int>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    
    val st = StringTokenizer(br.readLine())
    resArr = Array(size){ st.nextToken().toInt() }
    val stack = Stack<Int>()

    for(i in 0 until size){
        // stack 상단의 idx 의 값보다 현재 값이 클때
        while( !stack.isEmpty() && resArr[stack.peek()] < resArr[i] ){
            resArr[stack.pop()] = resArr[i] // 현재 원소를 넣어준다
        }
        stack.push(i)
    }  
    while(!stack.isEmpty()){
        resArr[stack.pop()] = -1
    }

    for(i in resArr){
        bw.write("$i ")
    }
    bw.write("\n")

    bw.flush()
    bw.close()
    br.close()
}