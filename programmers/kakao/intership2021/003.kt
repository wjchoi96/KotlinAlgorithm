/*
    카카오 2021 인턴 기출문제 3번
    level 3

    표 편집
    
    명령어 기반으로 표의 행을 선택, 삭제, 복구 프로그램

    선택 
    - 한번에 한 행만 선택 가능
    - U X : 현재 선택된 행에서 x칸 위에 있는 행을 선택
    - D X : 현재 선택된 행에서 X칸 아래에 있는 행을 선택
    - C   : 현재 선택된 행을 삭제한 후, 바로 아래 행 선택, 삭제된행이 맨 아래행이였다면 바로 윗행 선택
    - Z   : 가장 최근에 삭제된 행을 복구. 선택된 행은 바뀌지 않는다
*/
/*
    arrayList 에 표 정보를 저장

    remove 명령을 수행한다면 해당 수행정보를 스택에 저장

    현재 선택 idx => p (pointer)

    원본 list를 보관하고있다가
    원본을 순회하며 결과물과 일치하지 않는 행은 삭제된것
    삭제된 행은 삭제된행 처리를 하고 건너뛰고, 다시 일치하는 행을 찾게된다면 다시 진행
    => x 
    removeStack 에 남은 값들이 삭제된 행 

    5<= n <= 1,000,000 : 처음 표의 행 개수
    0<= K < n : 처음 선택된 위치
    1<= cmd <= 200,000 : 명령어 배열

    "이름" 열은 문제풀이에 딱히 쓰이지 않는다
*/

/*

*/

/*
    수정 이력

    0. arrayList 를 사용하는 방식으로 시도
    - tc 2~3개 제외하고 다틀림
    - 5번과정으로 넘어갔음

    1. try1 방식으로는 문제를 못찾을것같아서 카카오 공식 문제 해설을 확인
    현재 방식으로 변경
    - tc 반타작

    2. remove 이후 삭제되지 않은 행을 선택해야한다
    - tc 2개 런타임에러
    - 효율성 개박살

    3. 2번에서 OX 문자열 설정하는것을 String+ 연산이 아닌 StringBuilder append로 변경
    - tc 2개 런타임에러
    - 효율성 절반 통과
    - up, down때 배열을 순회하면서 삭제되지않은 행을 찾아서 이동하기떄문에 최악의 경우O(n)의 시간복잡도
    - 이거때문에 2개 런타임에러가 뜨는거같다

    4. 원래방식(1번)에서 배열을 제거하고 size, pointer 로만 접근하는 방식 수행
    - StringBuilder의 insert 연산의 시간복잡도를 알아내보자
    - insert(at)은 O(1), insert는O(n)이라고 기억
    - insert(at)이 아닌 setCharAt메소드가 O(1)이였다 => 해당 위치의 값을 변경하는 메소드
    //https://loosie.tistory.com/339
    =>>> 정확성 효율성 모두 통과!

    5. arrayList 사용하는 방식도 가능성은 있다. restore 할때 p를 조정 안해주는게 문제였던거 같다
    => 정확성 통과, 효율성은 2개 제외하고 모두 시간초과
    - 이는 arrayList의 add, add(at)연산이 O(n)이여서 그렇다
    - arrayList를 사용해서는 효율성을 통과할 수 없다고 판단된다

    5. linkedList로 구현해보자
*/

import java.util.Stack
fun main(args : Array<String>){
    val cmd = arrayOf(
        // "D 2","C","U 3","C","D 4","C","U 2","Z","Z"
        "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
    )
    val n = 8
    val k = 2

    val res = Kakao003Solve().solution(n, k, cmd)
    print("$res\n")
}
// 처음부터 다시 해보자
// LinkedList를 사용안하더라도 정확성 테스트는 통과할수있다는데
// StringBuilder 를 사용하니까 효율성도 어느정도 통과하는게 생기네
// 실패 (런타임 에러) 대체 뭔데
private class Kakao003Solve {
    private var removeStack = Stack<Int>()
    private var size = 0
    private var p = 0
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        p = k
        size = n
        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> up(op[1].toInt())
                "D" -> down(op[1].toInt())
                "C" -> remove()
                "Z" -> restore()
            }
        }
        val res = StringBuilder()
        for(i in 0 until size){
            res.append("O")
        }
        //https://loosie.tistory.com/339
        while(!removeStack.isEmpty()){
            res.insert(removeStack.pop().toInt(), "X")
        }
        return res.toString()
    }
    private fun up(count : Int){
        p-=count
    }
    private fun down(count : Int){
        p+=count
    }
    private fun remove(){
        removeStack.push(p)
        if(p==size-1){
            p-- // 한칸 위
        }
        size-- // size가 줄기때문에 알아서 한칸 아래(++)한 효과가 된다
    }
    private fun restore(){
        val restore = removeStack.pop()
        if(restore <= p){
            p++
        }
        size++
    }
}
// try2 : 전날 풀었을땐 tc 2개뺴곤 통화
// 효율성 개 박살 => StringBuilder 사용으로 바꾸니 효율성도 어느정도 통과한게 생긴다
// 21, 27 런타임 에러 대체 뭔데
private class Kakao003Try2 {
    private var removeStack = Stack<Int>()
    private lateinit var list : Array<Int>
    private var p = 0
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        p = k
        list = Array(n){1}

        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> up(op[1].toInt())
                "D" -> down(op[1].toInt())
                "C" -> remove()
                "Z" -> restore()
            }
        }
        var res = StringBuilder()
        for(i in 0 until n){
            if(list[i] == 0){
                res.append("X")
            }else{
                res.append("O")
            }
        }
        return res.toString()
    }
    private fun up(count : Int){
        var upCount = count
        while(upCount > 0){
            if(list[--p] != 0){ 
                upCount--
            }
        }
    }
    private fun down(count : Int){
        var downCount = count
        while(downCount > 0){
            if(list[++p] != 0){ 
                downCount--
            }
        }
    }
    private fun remove(){
        list[p] = 0
        removeStack.push(p)
        // 삭제되지 않은 행을 선택해야한다
        if(p == list.size-1){ // 가장 마지막 행이였다면 윗칸 선택
            while(list[--p] == 0){}
        }else{
            while(list[++p] == 0){} // 아니면 아래칸 선택
        }
    }
    private fun restore(){
        val restore = removeStack.pop()
        list[restore] = 1
    }

}

// try 1 => 3개 빼고 다 fail
// restore시 p조정을 안해준 문제 발견
/*
    tc 통과
    효율성은 2개 빼고 시간초과

    ArrayList는 get은 예상대로 O(1)이지만
    add(addAt 포함)은 O(N)이였다
    add과정은 처음 기본size 10 이후부터는 새 배열을 만들고 복사 후 옮기게 되는 과정
    add(at)은 특정위치 다음에 존재하는 데이터를 복사 후 한칸 뒤에 붙이는 과정
    때문에 arrayList로는 효율성을 통과 할 수가 없다
*/
private class Kakao003Try1 {
    private var removeStack = Stack<Int>()
    private val list : ArrayList<Int> = ArrayList<Int>()
    private var p = 0
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        p = k
        for(i in 0 until n){
            list.add(i)
        }
        for(i in 0 until cmd.size){
            val op = cmd[i].split(' ')
            when(op[0]){
                "U" -> select(-op[1].toInt())
                "D" -> select(op[1].toInt())
                "C" -> remove()
                "Z" -> restore()
            }
        }
        var res = StringBuilder()
        for(i in 0 until n){
            res.append("O")
        }
        /*
            insert는 성공하고 charAt은 실패하는 경우가 있는 이유
            removeStack에 저장된 idx들은 삭제될때 당시의 list의 size에 대응되는 idx이다
            때문에 처음부터 원래 size를 가져다놓고, setCharAt을 하면 틀린다
            stack pop횟수에 따른(몇번째 pop인지) 추가 idx 계산을 해주면 될듯?
            => 아니다 이건 여기서는 못쓴다
            => linkedList나 배열을 사용해서 idx가 remove, restore을 해도 원래 자리대로 유지되는 상태에서만 가능
        */
        var count = 0
        while(!removeStack.isEmpty()){
            res.setCharAt(removeStack.pop()+count++, 'X')
        }
        return res.toString()
    }
    private fun select(to : Int){
        p+=to // 범위를 벗어나게 하는 값도 나올까?
        // print("select to[$to] => $p\n")
    }
    private fun remove(){
        // print("remove : $p\n")
        // 가장 아래인경우 한칸 위로 옮긴다
        // 그 외에는 remove를 수행하면 자연스레 아래칸이 위로 올라오므로 그대로 두면 된다
        removeStack.push(p)
        list.removeAt(p)
        if(p == list.size){
            p-=1
        }
    }
    // remove 되었던게 마지막 행인경우, addAt 을 호출해봐야 마지막 행으로 들어가게 될것
    private fun restore(){
        val restore = removeStack.pop()
        // print("restore : $restore\n")
        list.add(restore, restore) // 삭제되었던 idx에 다시 집어넣어준다
        if(restore <= p){ // 현재 선택항목보다 위에 삽입되었다면 p 조정
            p++
        }
    }
    private fun printSheet(){
        print("print sheet\n")
        for(i in 0 until list.size){
            print("$i : ${list[i]} ")
            if(p == i){
                print("p\n")
            }else{
                print("\n")
            }
        }
    }
}