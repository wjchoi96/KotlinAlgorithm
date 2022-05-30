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

/*
    개선 제출
    
    1. 실패(72%)
    - end값이 가리키고 있는 값도 선택중이게 변경해서 진행해봤다

    2. 성공
    - end값의 범위가 달라짐에따라 전체 인덱싱이 틀려진다
    - 인덱싱이 굉장히 까다롭다

*/
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val (n, s) = br.readLine().split(' ').map{it.toInt()}
    val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    print("${arr.toList()}\n")

    /*
        solve1은 end가 다음 idx를 미리 가리키고 있는 형식인데
        end가 현재 가리키고있는 값이 적용되어 있는 방식으로 진행해보자
    */
    var min = Int.MAX_VALUE
    var start = 0
    var end = 0
    var sum = arr[end]
    while(start < n){
        print("[$start to $end] : $sum\n")
        if(sum>=s || end == n-1){
            if(sum>=s){
                min = Math.min(min, end+1-start)  // 현재 가리키는 end도 선택된것이기 때문에 +1
                print("catch : ${end+1-start} to min : $min\n")
            }
            sum -= arr[start++]
        } else if(sum < s){
            sum += arr[++end] // end값을 증가시키고, 증가된 end가 가리키는 값을 sum에 적용
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


// ========= 성공 ============
    /*
        지금은 end가 가리키고 있는 값은 적용이 안되고, end가++될때 현재 가리키던값을 증가하고 다음값을 가리킨다
        즉 다음값을 미리 가리키고있는 상태

        초기값 start=0, end=0 인 상태는 아무것도 선택하지 않은 상태
        조건을 만족하지 않아서 sum+=arr[end++] 되면서 arr[0]값이 적용되고 end는 1로 올라간다

        이렇기 때문에 end가 n-1일때 종료되면 안되고 end가 n일때 종료가 되어야한다
    */
private fun solve1(){
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