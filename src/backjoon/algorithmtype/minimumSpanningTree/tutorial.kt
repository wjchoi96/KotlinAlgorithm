/*
    바킹독님 알고리즘 강의 중 최소신장 트리 단원
    https://blog.encrypted.gg/1024

    #신장트리(Spanning Tree)
    신장트리는 주어진 방향성이 없는, 그래프의 부분 그래프들 중에서 모든 정점을 포함하는 트리
    - 부분그래프란, 주어진 그래프에서 일부 정잠과 간선만을 택해서 구성한 새로운 그래프

    즉, 그래프의 정점과 간선 일부를 선택했을떄
    - 트리이면서(무방향, 사이클이 없는 그래프)
    - 그래프의 모든 정점을 포함하는 경우


    #최소신장트리(Minumum Spanning Tree, MST)
    - 신장트리중 간선의 합이 최소인 트리를 의미
    - 동일한 그래프에서 여러개의 최소신장트리가 존재 가능


    #최소신장트리를 구하는 대표적인 알고리즘
    - 크루스칼 알고리즘
    - 프림 알고리즘
    2종류가 존재


    #크루스칼 알고리즘
    - Union Find알고리즘을 알아야 구현할 수 있음
    - 최소신장트리의 구현법만이 궁금한 경우는, 프림알고리즘을 사용
    1. 간선의 크기를 오름차순으로 정렬하고, 제일 낮은 비용의 간선을 선택
    2. 현재 선택한 간선이 정점 u, v를 연결하는 간선이라고 할때 
    - 만약 u와 v가 같은 그룹이라면 아무것도 하지 않고 넘어감
    - u와 v가 다른 그룹이라면, 같은 그룹으로 만들고 현재 선택한 간선을 최소 신장 트리로 추가
    3. 최소신장트리에 v-1개의 간선을 추가했다면 과정을 종료
    그렇지 않다면, 그 다음으로 비용이 작은 간선을 선택해 2번을 반복

    #크루스칼 알고리즘의 그룹
    1. 초기에는 모두 다른 그룹
    2. 2번과정을 통해 같은 그룹으로 만들면서 크루스칼 알고리즘을 진행
    - 같은 그룹을 연결하는 간선을 포함하지 않는 이유는, 최소신장트리에 사이클의 발생을 차단하기 위함
    - 특정 정점이 같은 그룹임을 확인하는방법은 Flood Fill이 존재
        - 최소신장트리에 편입된 간선들만을 가지고, A에서 B로 갈 수 있는지 Flood Fill을 돌렸을때
        B에 방문하는지 확인
        - Flood Fill은 O(V)의 시간복잡도가 걸림
        - 간선의 정렬에서 O(E log E), 이후 최대 E번에 걸쳐 같은 그룹인지 판단해야하니 O(VE)
        => O(E log E + EV)가 되어 비효율적
    - 다른 방법으로는 Union Find가 존재
        - 상수시간에 확인 가능
        - 확히는 아커만 역함수라는 생전 처음 들어봤을 함수의 값이 계수로 붙긴 하지만 
        이 함수의 값은 현실적인 범위 안에서 항상 4 이하이기 때문에 상수 시간이라고 생각해도 무방
        - 크루스칼 알고리즘을 O(E log E)로 해결 가능 


    #크루스칼 알고리즘과 그리디
    - 크루스칼 알고리즘은 사이클을 만들어내지 않는 선에서, 
    비용이 작은 간선부터 최소 신장 트리에 편입시키는 그리디 알고리즘


    #프림 알고리즘
    1. 임의의 정점을 선택해 최소 신장트리에 추가
    2. 최소신장트리에 포함된 정점과, 포함되지 않은 정점을 연결하는 간선 중
    비용이 가장 작은것을 최소신장트리에 추가
    3. 최소신장트리에 V-1개의 간선이 추가될때까지 2번을 반복
    
    - 우선순위 큐를 사용하여 편하게 구현 가능
    - 크루스칼에 비해 구현이 어려운편
    - 다익스트라 알고리즘과 매우 유사

    #프림 알고리즘과 그리디 
    - 매 순간마다 가장 비용이 낮은 간선을 택하는 그리디 알고리즘

    #프림 알고리즘의 구체적인 구현_우선순위큐
    - 최소신장트리에 포함된 정점과, 포함되지 않은 정점을 연결하는 간선중 최소 비용 간선을 선택할때
    모든 간선을 조사한다면 O(VE)의 시간복잡도

    1. 임의의 정점을 선택해 최소신장 트리에 추가
    2. 우선순위 큐에서 비용이 가장 작은 간선을 선택
    3. 해당 간선이 최소 신장 트리에 포함된 두 정점을 연결한다면 넘어감
    4. 아니라면, 해당 간선과 정점을 최소신장트리에 추가하고
    1번을 진행
    5. 최소신장트리에 V-1개의 간선이 추가될때까지 반복



    #연습문제
    1. 백준 1197(solve)
    2. 백준 1368(solve)
    - 최소신장트리로 대응 가능한 문제인지 헷갈릴 수 있는 문제
    - 적절한 그래프 모델링을 구하기 까다로운 문제
    - 백준 1600
    - 백준 14442 
    등의 문제도 BFS문제이지만, 그래프를 변형시켜서 진행


*/