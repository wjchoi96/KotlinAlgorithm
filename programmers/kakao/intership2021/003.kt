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
    수정 이력

    1. try1 방식으로는 문제를 못찾을것같아서 카카오 공식 문제 해설을 확인
    현재 방식으로 변경
    - tc 반타작

    2. remove 이후 삭제되지 않은 행을 선 택해야한다
    - tc 2개정도 런타임에러

    3. 내일 linked list 로 풀어보자
*/

import java.util.Stack
fun main(args : Array<String>){
    val cmd = arrayOf(
        // "D 2","C","U 3","C","D 4","C","U 2","Z","Z"
        "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
    )
    val n = 8
    val k = 2

    val res = Kakao003().solution(n, k, cmd)
    print("$res\n")
}
private class Kakao003 {
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
        var res = ""
        for(i in 0 until n){
            if(list[i] == 0){
                res+="X"
            }else{
                res+="O"
            }
        }
        return res
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
        var res = ""
        for(i in 0 until n){
            if(removeStack.contains(i)){
                res += "X"
            }else{
                res += "O"
            }
        }
        
        return res
    }
    private fun select(to : Int){
        p+=to // 범위를 벗어나게 하는 값도 나올까?
        if(p<0){
            p = 0
        }else if(p>=list.size){
            p = list.size-1
        }
        print("select to[$to] => $p\n")
    }
    private fun remove(){
        print("remove : $p\n")
        // 가장 아래인경우 한칸 위로 옮긴다
        // 그 외에는 remove를 수행하면 자연스레 아래칸이 위로 올라오므로 그대로 두면 된다
        removeStack.push(p)
        list.removeAt(p)
        if(p == list.size){
            p-=1
        }
        printSheet()
    }
    // remove 되었던게 마지막 행인경우, addAt 을 호출해봐야 마지막 행으로 들어가게 될것
    private fun restore(){
        val restore = removeStack.pop()
        print("restore : $restore\n")
        list.add(restore, restore) // 삭제되었던 idx에 다시 집어넣어준다
        printSheet()
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