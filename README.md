# JpaShop

### 새롭게 배운 내용

#### 1. 연관관계 설정
   - 연관관계의 주인 
   - 지연로딩



#### 2. 변경 감지와 병합 (merge)
   - 준영속엔티티
      - JPA가 관리하지 않음

   - 수정 방법
      1. 변경 감지 기능
        - 레포지토리에 저장하지 않아도 영속성 컨텍스트에 의해 변경이 감지되고 저장됨
      ```java
      @Transactional
     void update(Item itemParam){ // itemParam: 준영속 상태
     Item findItem = em.find(Item.class, itemParam.getId()); // 같은 엔티티 조회
     findItem.setPrice(itemParam.getPrice()); // 데이터 수정
     // itemRepository.save(findItem); 
     }
      ```         

      2. merge 사용
          - 준영속 상태를 영속 상태로 바꿀 때 사용
           - 아래 코드에서 item은 준영속 상태
           - 머지 적용한 merge는 영속성으로 관리됨
           - 따라서 다시 변경/사용 시 merge를 사용해야 관리됨
          ```java
         Item merge = em.merge(item);
         ```

     주의할 점: 변경감지를 사용하면 원하는 속성만 변경할 수 있지만, 
     병합을 사용하면 모든 속성이 변경된다. 
     병합시 값이 없으면 null로 업데이트 될 위험이 있다. 

#### 3. Controller에서 Entity를 넘기지 말 것

     Entity 변경시에 API의 스펙이 변경될 수 있다. 
     Entity를 깨끗하게 유지하지 못할 수 있다. 
     노출되면 안되는 컬럼이 노출될 수 있다. 
     DTO를 만들어 전달하자.
     Controller에서 어설프게 Entity를 생성하지 말자.

