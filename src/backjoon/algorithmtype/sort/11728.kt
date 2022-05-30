/*
    바킹독님 알고리즘 강의 중 정렬단원 
    merge sort 설명 중 제시된 문제
    sliver5

    정렬되어있는 배열 A,B를 합쳐서 정렬 후 출력
*/
/*
    원시정렬 형태로 정렬한다면 O((n+m)^2) 의 시간복잡도가 나올것

    A,B 모두 정렬되어있으니 
    0번쨰 idx부터 비교해서 더 작은 값을 mergeArr 에 추가, 추가된 배열의 idx++
    한쪽 배열이 끝날때까지 진행
    한쪽 배열이 끝나면, 남은 배열을 순차적으로 merge
    => O(n+m)
*/
/*
    제출
    1. 메모리초과
    - 괜히 출력하는거 toList().toString().replace ~~~ 하다가 발생한듯

    2. 성공
    - 시간복잡도 굉장히 빡빡하게 해놓은 문제인거같다. 채점이 엄청 오래걸림
*/

fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n,m) = br.readLine().split(' ').map{it.toInt()}
    val firstArr = br.readLine().split(' ').map{it.toInt()}
    val secondArr = br.readLine().split(' ').map{it.toInt()}
    val merge = Array(n+m){0}

    var first = 0
    var second = 0
    var mIdx = 0
    while(first != n && second != m){
        if(firstArr[first] <= secondArr[second]){
            merge[mIdx++] = firstArr[first++]
        }else{
            merge[mIdx++] = secondArr[second++]
        }
    }
    while(first != n) merge[mIdx++] = firstArr[first++]
    while(second != m) merge[mIdx++] = secondArr[second++]

    for(i in 0 until n+m){
        bw.write("${merge[i]} ")
    }
    bw.write("\n")

    bw.flush()
    bw.close()
    br.close()
}