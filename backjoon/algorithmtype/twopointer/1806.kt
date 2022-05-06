/*
    바킹독님 알고리즘 강의 중 투포인터 영역
    gold 4

    부분합

    10000 이하의 자연수로 이루어진 길이 n의 수열

    연속된 수들의 부분합 중에 그 합이 s이상이 되는것중 가장 짧은 것의 길이를 구하라

    10 <= n <= 100,000
    0 < s <= 100,000,000
*/
/*
    정렬을 하면 안된다
    
    => 연속된 수들의 부분합

    start = 0
    end = 0
    res = arr[start] + arr[end]

    start 증가 조건
    - 문제의 조건을 만족할때 
    => start++ 해주며 res에서 기존 start가 가리키고있던 값을 제거
    => 길이의 최소값도 갱신한다
    - end가 더이상 증가할 수 없을때
    => end == n-1

    end 증가 조건
    - 문제의 조건을 만족하지 않을때
    => end++ 해주며 res에 end가 가리키던 값을 더해준다
    
    종료조건
    start, end가 더이상 증가할 수 없을때
    => start == n-1, end == n-1

    만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다. ㅅㅂ
    
*/
/*
    제출
    조건을 만족하는게 없다면 0 을 출력하라는 문제조건을 누락했다

    1. 틀렸습니다
    - startm end가 ++이 되는것이니 start와 end의 종료조건을 변경
    - 놓친조건 추가해서 적용 => 틀렸습니다(76%)

    2. 틀렸습니다
    - 놓친 조건 추가해서 적용 => 맞았습니다

    3. 틀렸습니다(24%)
    - 놓친 조건 추가해서 적용 => 시간초과(72%)
    - start 증가 조건에서 start<n 제거, end증가조건을 sum<s 에서 end<s 로 변경
    - 뭐때문에 시간초과가 뜨는걸까  

    4. 성공
    => end증가조건을 sum<s 에서 end<s 로 변경 : 이거 때문에 시간초과가 떴다
    => 아니 end를 s랑 왜비교해 ㅋㅋㅋㅋ
    => 이걸 end<n 으로 변경해도 성공한다

*/
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val (n, s) = br.readLine().split(' ').map{it.toInt()}
    val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    print("${arr.toList()}\n")

    var sum = 0
    var min = Int.MAX_VALUE
    var start = 0
    var end = 0
    while(true){
        if(end == n && start==n){
            break
        }
        print("[$start to $end] : $sum\n")
        if(sum>=s || end==n){
            if(sum>=s){
                min = Math.min(min, end-start)
                print("catch : ${end-start} to min : $min\n")
            }
            sum -= arr[start++] //start가 가리키던 값을 sum에서 제거하고 ++
        } else if(sum < s){
            sum += arr[end++] // end를 증가시키고 해당 값을 sum에 적용
        }
    }
   
    if(min == Int.MAX_VALUE){
        bw.write("0\n")
    }else{
        bw.write("$min\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}