/*
    바킹독님 알고리즘 강의 중 이진검색트리 단원
    https://blog.encrypted.gg/1013

    ===================================================================
    #시작 전 특이사항
    특이하게도, 이번 단원은 구현을 직접 하지 않는다
    이진검색트리는 자가균형트리라는것을 적용하지 않으면 시간복잡도가 좋지 않아서 사용하기 
    걸맞지 않다.
    문제는 자가균형트리는 구현 난이도가 무척(헬) 높아서 실제 코딩테스트에서 구현하는것은 불가능에 가깝다

    STL(C++의 경우)을 사용하여 풀이하고, STL을 쓸 수 없는 환경에서 이진검색트리를 사용하고 싶다면
    이진검색트리를 사용하지 않는 풀이를 찾아내는 것을 추천한다
    어찌 이진검색트리를 구현한다 해도, 자가균형트리 없이는 높은 확률로 시간초과가 발생할 것
    어떻게든 해시나 이분탐색 같은 다른 방법을 사용하는 풀이를 고민해야 한다

    ===================================================================
    #트리
    해당 강의 기준으로 트리는 아직 학습을 하지 않았기에, 계층 구조를 가지고,
    제일 꼭대기를 제외하고 각 동그라미들은 위로 딱 1개와 연결이 되어있는 녀석이라고 기억하고 넘어가자

    -정점, 노드
    트리에서의 각 원소

    -루트
    제일 꼭대기에 위치한 정점

    -리프
    제일 말단에 위치해 아래로 뻗은게 없는 정점

    -간선
    정점을 연결하는 선
    간선으로 이루어진 위, 아래의 관계를 부모 자식 관계라 칭한다

    -서브트리
    어느 한 정점에 대해 그 밑에있는 정점과 간선만을 모은 트리를 서브트리 라고 한다

    -높이
    트리의 높이는 위 아래로 뻗어있는 정도를 나타낸다
    노드가 1개만 있을때 높이를 1로 두느냐 0으로 두느냐 나뉠 수 있다

    ===================================================================
    #이진트리
    각 노드의 자식이 2개 이하인 트리
    자식이 2개 이하이기 때문에 왼쪽 자식, 오른쪽 자식으로 구분 가능


    ===================================================================
    #이진검색트리
    특별한 성질을 만족하는 트리
    
*/