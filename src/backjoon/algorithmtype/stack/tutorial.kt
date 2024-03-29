/*
    바킹독님 알고리즘 강의 중 스택 단원

    한쪽 끝에서만 원소를 넣거나 뺄 수 있는 자료구조

    제일 먼저 들어간 원소가 제일 나중에 나온다
    FILO(First In Last Out)자료구조라고 부르기도 한다

    스택, 큐, 덱 처럼 특정 위치에서만 원소를 넣거나 뺄 수 있는 제한이 걸려있다
    - Restricted Structure 라고 부르기도 한다

    스택
    - 원소의 추가가 O(1)
    - 원소의 제거가 O(1)
    - 제일 상단의 원소 확인이 O(1)
    - 제일 상단이 아닌 나머지 원소들의 확인/변경이 원칙적으로 불가능

    응용
    스택의 대표적인 활용사례는 수식의 괄호 쌍, 전위/중위/후위 표기법, Dfs, Flood Fil 등이 있다
    전/중/후위 표기법은 코딩테스트 대비용이라긴 너무 지엽적이라고 판단하신다함(본질적이거나 중요하지않고 부차적인 것)
    수식의 괄호 쌍
    {()} 등과 같이 올바른 괄호 쌍
    {(}) 등과 같이 올바르지 않은 괄호 쌍
    과 같이 주어진 괄호 문자열이 올바른지 판단하는것

    => 이전에 풀어보았던 기억으로는 구현 설계
    1. 문자열을 괄호만 빼고 전부 replace 한다
    2. 문자열의 시작부터 push 해간다
    3. 모두 push 한 뒤 pop 시작
    4. pop 하고 peek 확인 -> stack empty 체크하면서
    5. pop 이 닫는 괄호 종류인데, peek 가 알맞은 여는 괄호가 아니라면 false
    => 틀렸다

    1. 문자열을 순회하며 여는 괄호를 push
    2. 닫는 괄호를 만나면, pop을 진행하여 비교
    3. 올바른 한 쌍이라면 continue
    4. 올바르지 않다면 false



    연습문제
    1. 백준 10828(solve before)
    - 단순 스택 구현문제
    - 이전에 풀어봤었다

    2. 백준 4949(solve before)(solve)
    - 올바르지 않은 괄호 쌍 문제
    - 한번 더 풀어보자
    - 성공

    3. 백준 10799(solve)
    
    4. 백준 2504(solve)


*/